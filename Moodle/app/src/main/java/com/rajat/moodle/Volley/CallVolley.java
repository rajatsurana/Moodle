package com.rajat.moodle.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rajat.moodle.Login;
import com.rajat.moodle.Tools.Tools;

import java.util.HashMap;
import java.util.Map;

public class CallVolley {
        //dialog box appears showing progress
        private static ProgressDialog pDialog;
        //TimeOut and maximum number of tries
        private static void setCustomRetryPolicy(StringRequest jsonObjReq) {
                Log.i("rajat", "setCustomRetryPolicy");
                jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
        // method that will send request  to server and get a response back
        public static void makeLoginCall(String url, final Context context)
        {
              //  pDialog=  Tools.showProgressBar(context);

                        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
                        {
                        // if a reponse is recieved after sending request
                                @Override
                                public void onResponse(String response)
                                {
                                        try
                                        {
                                                Log.i("rajat", " onResponseActive " + response);
                                                JSONParser.LoginApiJsonParser(response, context);
                                                pDialog.dismiss();
                                        }
                                        catch (Exception localException)
                                        {
                                                Log.i("rajat"," onResponseException "+localException.getMessage());
                                                localException.printStackTrace();
                                        }
                                }
                        }
                                , new Response.ErrorListener()
                        {
                                //if error occurs
                                @Override
                                public void onErrorResponse(VolleyError error)
                                {
                                        Log.i("rajat", "onErrorResponse" + error.toString());
                                           pDialog.dismiss();


                                }
                        }){
                                protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
                                        String sessionId = networkResponse.headers.get("Set-Cookie");
                                        //sessionID=sessionId;
                                        Login.editor = Login.sharedpreferences.edit();
                                        Login.editor.putString("Set-Cookie",sessionId);
                                        Login.editor.apply();
                                        return super.parseNetworkResponse(networkResponse);
                                }
                        };

                //how many times to try and for how much duration
                        setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                        VolleySingleton.getInstance(context).addToRequestQueue(request);
                }
                // method will give message to user depending on respons from server


        public static void makeLogoutCall(String url, final Context context)
        {
                pDialog=  Tools.showProgressBar(context);

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
                {
                        // if a reponse is recieved after sending request
                        @Override
                        public void onResponse(String response)
                        {
                                try
                                {
                                        Log.i("rajat", " onResponseActive " + response);
                                        //LoginApiJsonParser(response, context);
                                        Login.editor = Login.sharedpreferences.edit();
                                        Login.editor.putString("Set-Cookie","");
                                        Login.editor.apply();

                                        pDialog.dismiss();
                                        Intent openH = new Intent (context, Login.class);
                                        context.startActivity(openH);
                                }
                                catch (Exception localException)
                                {
                                        Log.i("rajat"," onResponseException "+localException.getMessage());
                                        localException.printStackTrace();
                                }
                        }
                }
                        , new Response.ErrorListener()
                {
                        //if error occurs
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                                Log.i("rajat", "onErrorResponse" + error.toString());
                                pDialog.dismiss();


                        }
                });

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }

        public static void afterLoginCall(String url, final Context context, final int API_NUMBER){
                pDialog=  Tools.showProgressBar(context);

                StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>()
                {
                        // if a reponse is recieved after sending request
                        @Override
                        public void onResponse(String response)
                        {
                                try
                                {
                                        switch (API_NUMBER){
                                                case 1:
                                                        JSONParser.NotificationApiJsonParser(response, context);
                                                        break;
                                                case 2:
                                                        JSONParser.CourseAssignmentApiJsonParser(response, context);
                                                        break;
                                                case 3:
                                                        JSONParser.CourseListApiJsonParser(response, context);
                                                        break;
                                                case 4:
                                                        JSONParser.ViewGradesApiJsonParser(response, context);
                                                        break;
                                                case 5:
                                                        JSONParser.listAllCourseAssignmentApiJsonParser(response, context);
                                                        break;
                                                case 6:
                                                        JSONParser.viewCourseGradesApiJsonParser(response, context);
                                                        break;
                                                case 7:
                                                        JSONParser.viewCourseThreadsApiJsonParser(response, context);
                                                        break;
                                                case 8:
                                                        JSONParser.viewParticularThreadApiJsonParser(response, context);
                                                        break;
                                                case 9:
                                                        JSONParser.createNewThreadApiJsonParser(response, context);
                                                        break;
                                                case 10:
                                                        JSONParser.postNewCommentApiJsonParser(response, context);
                                                        break;
                                                case 11: JSONParser.NotificationApiJsonParserService(response,context);
                                        }
                                        Log.i("rajat", " onResponseActive " + response);

                                        pDialog.dismiss();
                                }
                                catch (Exception localException)
                                {
                                        Log.i("rajat"," onResponseException "+localException.getMessage());
                                        localException.printStackTrace();
                                }
                        }
                }
                        , new Response.ErrorListener()
                {
                        //if error occurs
                        @Override
                        public void onErrorResponse(VolleyError error)
                        {
                                error.printStackTrace();
                                Log.i("rajat", "onErrorResponse" + error.toString());
                                pDialog.dismiss();
                                Tools.showAlertDialog(error.toString(), context);

                        }

                }
                ){
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                                //Log.i("size in getHeader: ",myHeaders.size()+"");
                                Map<String, String> mHeaders=new HashMap<String,String>();//myHeaders;
                                mHeaders.put("Cookie", Login.sharedpreferences.getString("Set-Cookie", ""));//MainActivity.sharedpreferences.getString("Set-Cookie",""));
                                return mHeaders;
                        }

                };

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }
        //notify
}