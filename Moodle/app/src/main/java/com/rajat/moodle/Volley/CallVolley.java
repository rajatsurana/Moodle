package com.rajat.moodle.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rajat.moodle.R;
import com.rajat.moodle.Tools.Tools;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
public class CallVolley {
        //dialog box appears showing progress
        private static ProgressDialog pDialog;
        //media player object
        //private static MediaPlayer dsound=null;
        //TimeOut and maximum number of tries
        private static void setCustomRetryPolicy(StringRequest jsonObjReq) {
                Log.i("rajat", "setCustomRetryPolicy");
                jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
        // method that will send request  to server and get a response back
        public static void makeLoginCall(String url, final Context context)
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
                                                LoginApiJsonParser(response, context);
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
                        });

                //how many times to try and for how much duration
                        setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                        VolleySingleton.getInstance(context).addToRequestQueue(request);
                }
                // method will give message to user depending on respons from server
                public static void LoginApiJsonParser(String JsonStringResult,Context con)
                {
                        try {
                                boolean status;

                                JSONObject user;
                                String last_name="",reset_password_key="",registration_key="",
                                        first_name="",entry_no="",email="",username="",registration_id="",password="";
                                int id =1000;
                                int type_=1000;
                                //create json object from response string
                                JSONObject resultJson = new JSONObject(JsonStringResult);
                                if (resultJson.has("success"))
                                {

                                        status = resultJson.getBoolean("success");

                                        user= resultJson.getJSONObject("user");
                                        if(status)
                                        {
                                                if(user.has("last_name")){last_name=user.getString("last_name");}
                                                if(user.has("reset_password_key")){reset_password_key=user.getString("reset_password_key");}
                                                if(user.has("registration_key")){registration_key=user.getString("registration_key");}
                                                if(user.has("first_name")){first_name=user.getString("first_name");}
                                                if(user.has("entry_no")){entry_no=user.getString("entry_no");}
                                                if(user.has("email")){email=user.getString("email");}
                                                if(user.has("username")){username=user.getString("username");}
                                                if(user.has("registration_id")){registration_id=user.getString("registration_id");}
                                                if(user.has("password")){password=user.getString("password");}
                                                if(user.has("id")){id=user.getInt("id");}
                                                if(user.has("type_")){type_=user.getInt("type_");}
                                                Tools.showAlertDialog(last_name + " "+id+" "+reset_password_key+" "
                                                        +registration_key+" "+first_name+" "+entry_no+" "+username+" "
                                                        +registration_id+" "+password+" "+type_+" "+email,con);
                                        }
                                        else
                                        {

                                        }
                                }
                        }
                        catch (Exception e)
                        {
                                Log.i("rajat", e.getLocalizedMessage());
                        }
                }
        }