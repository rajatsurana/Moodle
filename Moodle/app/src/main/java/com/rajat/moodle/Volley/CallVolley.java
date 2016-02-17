package com.rajat.moodle.Volley;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.rajat.moodle.MainActivity;
import com.rajat.moodle.Objects.CourseObject;
import com.rajat.moodle.Objects.GradeObject;
import com.rajat.moodle.Objects.NotificationObject;
import com.rajat.moodle.Objects.SubmissionObject;
import com.rajat.moodle.Tools.Tools;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CallVolley {
        //dialog box appears showing progress
        private static ProgressDialog pDialog;
       // static Map<String, String> myHeaders;
        //static String sessionID="";

        //TimeOut and maximum number of tries
        private static void setCustomRetryPolicy(StringRequest jsonObjReq) {
                Log.i("rajat", "setCustomRetryPolicy");
                jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS*2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        }
        // method that will send request  to server and get a response back
        public static void makeLoginCall(String url, final Context context)
        {
               // CookieHandler.setDefault(manager);

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
                        }){
                                protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
                                        String sessionId = networkResponse.headers.get("Set-Cookie");
                                        //sessionID=sessionId;
                                        MainActivity.editor = MainActivity.sharedpreferences.edit();
                                        MainActivity.editor.putString("Set-Cookie",sessionId);
                                        MainActivity.editor.apply();
                                        return super.parseNetworkResponse(networkResponse);
                                }


                        };

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
                                int id =0;
                                int type_=0;
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
                                Log.i("rajat","Exception: Login: "+ e.getLocalizedMessage());
                        }
                }

        public static void makeLogoutCall(String url, final Context context)
        {
                // CookieHandler.setDefault(manager);

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
                                        MainActivity.editor = MainActivity.sharedpreferences.edit();
                                        MainActivity.editor.putString("Set-Cookie","");
                                        MainActivity.editor.apply();
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

        //notify
        public static void getNotificationCall(String url, final Context context)
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
                                        NotificationApiJsonParser(response, context);
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
                                mHeaders.put("Cookie", MainActivity.sharedpreferences.getString("Set-Cookie", ""));//MainActivity.sharedpreferences.getString("Set-Cookie",""));
                                return mHeaders;
                        }

                };

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }
        // method will give message to user depending on respons from server
        public static void NotificationApiJsonParser(String JsonStringResult,Context con)
        {
                try {
                        JSONArray notifications=new JSONArray();


                        JSONObject notifyObj=new JSONObject();
                        String description="",created_at="";
                        int user_id =0,is_seen=0,id=0;

                        //create json object from response string
                        JSONObject resultJson = new JSONObject(JsonStringResult);
                        if (resultJson.has("notifications"))
                        {

                                notifications = resultJson.getJSONArray("notifications");
                                NotificationObject[] notificationObjects= new NotificationObject[notifications.length()];
                                for(int i=0;i<notifications.length();i++){
                                        notifyObj=notifications.getJSONObject(i);
                                        if(notifyObj.has("user_id")){user_id=notifyObj.getInt("user_id");}
                                        if(notifyObj.has("description")){description=notifyObj.getString("description");}
                                        if(notifyObj.has("is_seen")){is_seen=notifyObj.getInt("is_seen");}
                                        if(notifyObj.has("created_at")){created_at=notifyObj.getString("created_at");}
                                        if(notifyObj.has("id")){id=notifyObj.getInt("id");}
                                        //after each value is initialized
                                        notificationObjects[i]=new NotificationObject(user_id,description,is_seen,created_at,id);
                                }
                                // do something with NotificationObjectArray
                                        Tools.showAlertDialog(notificationObjects.length+" : length",con);

                        }
                }
                catch (Exception e)
                {
                        Log.i("rajat", e.getLocalizedMessage());
                }

        }
        public static void getCourseAssignmentCall(String url, final Context context)
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
                                        CourseAssignmentApiJsonParser(response, context);
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
                                mHeaders.put("Cookie", MainActivity.sharedpreferences.getString("Set-Cookie", ""));//MainActivity.sharedpreferences.getString("Set-Cookie",""));
                                return mHeaders;
                        }

                };

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }
        // method will give message to user depending on respons from server
        public static void CourseAssignmentApiJsonParser(String JsonStringResult,Context con)
        {
                try {
                        JSONObject assignment=new JSONObject();
                                String name="",created_at="",deadline="",description="";
                                Object file_=null;
                                int registered_course_id=0,late_days_allowed=0,type_=0,id=0;
                        JSONObject registered=new JSONObject();
                                String starting_date="",ending_date="";
                                int id_reg=0,professor=0,semester=0,year_=0,course_id=0;
                        JSONObject course=new JSONObject();
                                String code="",name_course="",description_course="",l_t_p="";
                                int credits=0,id_course=0;
                        JSONArray submissions=new JSONArray();
                                String name_submission="",created_at_submission="",file_submission="";
                                int event_id=0,user_id=0,id_submission=0;
                                JSONObject submitObj=new JSONObject();



                        //create json object from response string
                        JSONObject resultJson = new JSONObject(JsonStringResult);
                        if (resultJson.has("assignment")){
                                assignment=resultJson.getJSONObject("assignment");
                                if(assignment.has("registered_course_id")){registered_course_id=assignment.getInt("registered_course_id");}
                                if(assignment.has("late_days_allowed")){late_days_allowed=assignment.getInt("late_days_allowed");}
                                if(assignment.has("type_")){type_=assignment.getInt("type_");}
                                if(assignment.has("id")){id=assignment.getInt("id");}
                                if(assignment.has("name")){name=assignment.getString("name");}
                                if(assignment.has("created_at")){created_at=assignment.getString("created_at");}
                                if(assignment.has("deadline")){deadline=assignment.getString("deadline");}
                                if(assignment.has("description")){description=assignment.getString("description");}
                                if(assignment.has("file_")){file_=assignment.get("file_");}
                                Tools.showAlertDialog(registered_course_id+ late_days_allowed+type_+id+ name+created_at+deadline+file_+" : length",con);
                        }
                        if (resultJson.has("registered")){
                                registered=resultJson.getJSONObject("registered");
                                if(registered.has("id")){id_reg=registered.getInt("id");}
                                if(registered.has("professor")){professor=registered.getInt("professor");}
                                if(registered.has("semester")){semester=registered.getInt("semester");}
                                if(registered.has("year_")){year_=registered.getInt("year_");}
                                if(registered.has("course_id")){course_id=registered.getInt("course_id");}
                                if(registered.has("starting_date")){starting_date=registered.getString("starting_date");}
                                if(registered.has("ending_date")){ending_date=registered.getString("ending_date");}
                        }
                        if (resultJson.has("course")){
                                course=resultJson.getJSONObject("course");
                                if(course.has("id")){id_course=course.getInt("id");}
                                if(course.has("credits")){credits=course.getInt("credits");}
                                if(course.has("code")){code=course.getString("code");}
                                if(course.has("name")){name_course=course.getString("name");}
                                if(course.has("description")){description_course=course.getString("description");}
                                if(course.has("l_t_p")){l_t_p=course.getString("l_t_p");}

                        }
                        if (resultJson.has("submissions"))
                        {
                                submissions = resultJson.getJSONArray("submissions");
                                SubmissionObject[] submissionObjects= new SubmissionObject[submissions.length()];
                                for(int i=0;i<submissions.length();i++){
                                        submitObj=submissions.getJSONObject(i);
                                        if(submitObj.has("user_id")){user_id=submitObj.getInt("user_id");}
                                        if(submitObj.has("name")){name_submission=submitObj.getString("name");}
                                        if(submitObj.has("event_id")){event_id=submitObj.getInt("event_id");}
                                        if(submitObj.has("created_at")){created_at_submission=submitObj.getString("created_at");}
                                        if(submitObj.has("id")){id_submission=submitObj.getInt("id");}
                                        if(submitObj.has("file_")){file_submission=submitObj.getString("file_");}
                                        //after each value is initialized
                                        submissionObjects[i]=new SubmissionObject(name_submission,created_at_submission,file_submission,event_id,user_id,id_submission);
                                }
                                // do something with NotificationObjectArray
                                //Tools.showAlertDialog(submissionObjects.length+" : length",con);
                        }
                }
                catch (Exception e)
                {
                        Log.i("rajat","Exception:"+ e.getLocalizedMessage());
                }

        }

        public static void getCourseListCall(String url, final Context context)
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
                                        CourseListApiJsonParser(response, context);
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
                                mHeaders.put("Cookie", MainActivity.sharedpreferences.getString("Set-Cookie", ""));//MainActivity.sharedpreferences.getString("Set-Cookie",""));
                                return mHeaders;
                        }

                };

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }
        // method will give message to user depending on respons from server
        public static void CourseListApiJsonParser(String JsonStringResult,Context con)
        {
                try {
                        int current_sem=0,current_year=0;
                        JSONArray courses=new JSONArray();
                                JSONObject courseObj= new JSONObject();
                                String code="",name_course="",description_course="",l_t_p="";
                                int credits=0,id_course=0;

                        JSONObject user=new JSONObject();
                                String username="",first_name="",last_name="",entry_no="",registration_id="",
                                        reset_password_key="",registration_key="";
                                int type_=0,id=0;
                        //create json object from response string
                        JSONObject resultJson = new JSONObject(JsonStringResult);
                        if (resultJson.has("current_sem")){current_sem=resultJson.getInt("current_sem");}
                        if (resultJson.has("current_year")){current_year=resultJson.getInt("current_year");}
                        if (resultJson.has("user")){
                                user=resultJson.getJSONObject("user");
                                if(user.has("username")) {username=user.getString("username");}
                                if(user.has("type_")){type_=user.getInt("type_");}
                                if(user.has("id")){id=user.getInt("id");}
                                if(user.has("first_name")){first_name=user.getString("first_name");}
                                if(user.has("last_name")){last_name=user.getString("last_name");}
                                if(user.has("entry_no")){entry_no=user.getString("entry_no");}
                                if(user.has("registration_id")){registration_id=user.getString("registration_id");}
                                //registration_id
                                Tools.showAlertDialog(type_+" "+id+" "+current_sem+" "+current_year,con);
                        }

                        if (resultJson.has("courses"))
                        {
                                courses = resultJson.getJSONArray("courses");
                                CourseObject[] courseObjects= new CourseObject[courses.length()];
                                for(int i=0;i<courses.length();i++){
                                        courseObj=courses.getJSONObject(i);
                                        if(courseObj.has("id")){id_course=courseObj.getInt("id");}
                                        if(courseObj.has("credits")){credits=courseObj.getInt("credits");}
                                        if(courseObj.has("code")){code=courseObj.getString("code");}
                                        if(courseObj.has("name")){name_course=courseObj.getString("name");}
                                        if(courseObj.has("description")){description_course=courseObj.getString("description");}
                                        if(courseObj.has("l_t_p")){l_t_p=courseObj.getString("l_t_p");}
                                        //after each value is initialized
                                        courseObjects[i]=new CourseObject(code,name_course,description_course,l_t_p,credits,id_course);
                                }
                                // do something with NotificationObjectArray
                                //Tools.showAlertDialog(submissionObjects.length+" : length",con);
                                Tools.showAlertDialog(" len: "+ courseObjects.length,con);
                        }

                }
                catch (Exception e)
                {
                        Log.i("rajat","Exception:"+ e.getLocalizedMessage());
                }
        }



        public static void viewGradesCall(String url, final Context context)
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
                                        ViewGradesApiJsonParser(response, context);
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
                                mHeaders.put("Cookie", MainActivity.sharedpreferences.getString("Set-Cookie", ""));//MainActivity.sharedpreferences.getString("Set-Cookie",""));
                                return mHeaders;
                        }

                };

                //how many times to try and for how much duration
                setCustomRetryPolicy(request);
                //get instance of volleysingleton and add reuest to the queue
                VolleySingleton.getInstance(context).addToRequestQueue(request);
        }
        // method will give message to user depending on respons from server
        public static void ViewGradesApiJsonParser(String JsonStringResult,Context con)
        {
                try {

                        JSONArray courses=new JSONArray();
                                JSONObject courseObj= new JSONObject();
                                String code="",name_course="",description_course="",l_t_p="";
                                int credits=0,id_course=0;
                        JSONArray grades=new JSONArray();
                                JSONObject gradeObj= new JSONObject();
                                int weightage=0,user_id=0,out_of=0,registered_course_id=0,score=0,id=0;
                                String name="";

                        //create json object from response string
                        JSONObject resultJson = new JSONObject(JsonStringResult);

                        if (resultJson.has("grades"))
                        {
                                grades = resultJson.getJSONArray("grades");
                                GradeObject[] gradeObjects= new GradeObject[grades.length()];
                                for(int i=0;i<grades.length();i++){
                                        gradeObj=grades.getJSONObject(i);
                                        if(gradeObj.has("id")){id=gradeObj.getInt("id");}
                                        if(gradeObj.has("weightage")){weightage=gradeObj.getInt("weightage");}
                                        if(gradeObj.has("user_id")){user_id=gradeObj.getInt("user_id");}
                                        if(gradeObj.has("name")){name_course=gradeObj.getString("name");}
                                        if(gradeObj.has("out_of")){out_of=gradeObj.getInt("out_of");}
                                        if(gradeObj.has("registered_course_id")){registered_course_id=gradeObj.getInt("registered_course_id");}
                                        if(gradeObj.has("score")){score=gradeObj.getInt("score");}
                                        //after each value is initialized
                                        gradeObjects[i]=new GradeObject(weightage,user_id,out_of,registered_course_id,score,id,name);
                                }
                                // do something with NotificationObjectArray
                                //Tools.showAlertDialog(submissionObjects.length+" : length",con);
                                Tools.showAlertDialog(" Grade len: "+ gradeObjects.length,con);
                        }
                        if (resultJson.has("courses"))
                        {
                                courses = resultJson.getJSONArray("courses");
                                CourseObject[] courseObjects= new CourseObject[courses.length()];
                                for(int i=0;i<courses.length();i++){
                                        courseObj=courses.getJSONObject(i);
                                        if(courseObj.has("id")){id_course=courseObj.getInt("id");}
                                        if(courseObj.has("credits")){credits=courseObj.getInt("credits");}
                                        if(courseObj.has("code")){code=courseObj.getString("code");}
                                        if(courseObj.has("name")){name_course=courseObj.getString("name");}
                                        if(courseObj.has("description")){description_course=courseObj.getString("description");}
                                        if(courseObj.has("l_t_p")){l_t_p=courseObj.getString("l_t_p");}
                                        //after each value is initialized
                                        courseObjects[i]=new CourseObject(code,name_course,description_course,l_t_p,credits,id_course);
                                }
                                // do something with NotificationObjectArray
                                //Tools.showAlertDialog(submissionObjects.length+" : length",con);
                                Tools.showAlertDialog(" Course len: "+ courseObjects.length,con);
                        }
                }
                catch (Exception e)
                {
                        Log.i("rajat","Exception:"+ e.getLocalizedMessage());
                }
        }
}