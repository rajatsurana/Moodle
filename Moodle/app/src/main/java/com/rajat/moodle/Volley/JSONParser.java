package com.rajat.moodle.Volley;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rajat.moodle.After_login;
import com.rajat.moodle.Login;
import com.rajat.moodle.Objects.AssignmentObject;
import com.rajat.moodle.Objects.CommentsObject;
import com.rajat.moodle.Objects.CourseObject;
import com.rajat.moodle.Objects.CourseThreadObject;
import com.rajat.moodle.Objects.GradeObject;
import com.rajat.moodle.Objects.NotificationObject;
import com.rajat.moodle.Objects.SubmissionObject;
import com.rajat.moodle.Objects.UsersObject;
import com.rajat.moodle.R;
import com.rajat.moodle.Tools.Tools;
import com.rajat.moodle.assignment_fragment;
import com.rajat.moodle.grade_fragment;
import com.rajat.moodle.mycourses_fragment;
import com.rajat.moodle.notification_fragment;
import com.rajat.moodle.thread_description_fragment;
import com.rajat.moodle.thread_fragment;
import com.rajat.moodle.universal_fragment;

import android.support.v4.view.GravityCompat;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Rajat on 19-02-2016.
 */
public class JSONParser {
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
                UsersObject userobj;
                if(resultJson.has("user")) {
                    user = resultJson.getJSONObject("user");
                    if (status) {

                        if (user.has("last_name")) {
                            last_name = user.getString("last_name");
                        }
                        if (user.has("reset_password_key")) {
                            reset_password_key = user.getString("reset_password_key");
                        }
                        if (user.has("registration_key")) {
                            registration_key = user.getString("registration_key");
                        }
                        if (user.has("first_name")) {
                            first_name = user.getString("first_name");
                        }
                        if (user.has("entry_no")) {
                            entry_no = user.getString("entry_no");
                        }
                        if (user.has("email")) {
                            email = user.getString("email");
                        }
                        if (user.has("username")) {
                            username = user.getString("username");
                        }
                        if (user.has("registration_id")) {
                            registration_id = user.getString("registration_id");
                        }
                        if (user.has("password")) {
                            password = user.getString("password");
                        }
                        if (user.has("id")) {
                            id = user.getInt("id");
                        }
                        if (user.has("type_")) {
                            type_ = user.getInt("type_");
                        }
                       /* //Tools.showAlertDialog(last_name + " " + id + " " + reset_password_key + " "
                                + registration_key + " " + first_name + " " + entry_no + " " + username + " "
                                + registration_id + " " + password + " " + type_ + " " + email, con);*/
                        userobj = new UsersObject(last_name, reset_password_key, registration_key, first_name, entry_no, email, username, registration_id, password, id, type_);
                        Log.i("rajat",status+" "+userobj.getEntry_no());
                        Intent openH = new Intent(con, After_login.class);
                        //openH.putExtra("userObj",  userobj);
                       // Bundle b=new Bundle();
                       // b.putParcelable("userobj",userobj);
                        //openH.putExtra("b",userobj);
                        openH.putExtra("userName", username);
                        openH.putExtra("email", email);
                        openH.putExtra("entry_no", entry_no);

                        con.startActivity(openH);
                    } else {
                        Toast.makeText(con, "username or password incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.i("rajat","Exception: Login: "+ e.getLocalizedMessage());
        }
    }
    public static void NotificationApiJsonParser(String JsonStringResult,Context con)
    {
        //boolean service=is_service;
        try {
            JSONArray notifications=new JSONArray();


            JSONObject notifyObj=new JSONObject();
            String description="",created_at="",user_id="";
            int is_seen=0,id=0;
            ArrayList<NotificationObject> notificationObjList= new ArrayList<NotificationObject>();
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("notifications"))
            {

                notifications = resultJson.getJSONArray("notifications");
                NotificationObject[] notificationObjects= new NotificationObject[notifications.length()];
                for(int i=0;i<notifications.length();i++){
                    notifyObj=notifications.getJSONObject(i);
                    if(notifyObj.has("user_id")){user_id=Integer.toString(notifyObj.getInt("user_id"));}
                    if(notifyObj.has("description")){description=notifyObj.getString("description");
                        description=android.text.Html.fromHtml(description).toString();}
                    if(notifyObj.has("is_seen")){is_seen=notifyObj.getInt("is_seen");}
                    if(notifyObj.has("created_at")){created_at=notifyObj.getString("created_at");}
                    if(notifyObj.has("id")){id=notifyObj.getInt("id");}
                    //after each value is initialized
                    notificationObjects[i]=new NotificationObject(user_id,description,is_seen,created_at,id);

                }
                notificationObjList=new ArrayList<NotificationObject>(Arrays.asList(notificationObjects));
                ////Tools.showAlertDialog(""+description,con);
                // do something with NotificationObjectArray
                ////Tools.showAlertDialog(notificationObjects.length+" : length Notify",con);

                Bundle b=new Bundle();
                b.putParcelableArrayList("notifications", notificationObjList);
                notification_fragment fragment=new notification_fragment();
                fragment.setArguments(b);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,fragment);
                fragmentTransaction.commit();

            }
        }
        catch (Exception e)
        {
            Log.i("rajat", e.getLocalizedMessage());
        }

    }
    static int noti_id=1;
    public static void NotificationApiJsonParserService(String JsonStringResult,Context con)
    {
        //boolean service=is_service;
        try {
            JSONArray notifications=new JSONArray();


            JSONObject notifyObj=new JSONObject();
            String description="",created_at="",user_id="";
            int is_seen=0,id=0;
            ArrayList<NotificationObject> notificationObjList= new ArrayList<NotificationObject>();
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("notifications"))
            {

                notifications = resultJson.getJSONArray("notifications");
                NotificationObject[] notificationObjects= new NotificationObject[notifications.length()];
                for(int i=0;i<notifications.length();i++){
                    notifyObj=notifications.getJSONObject(i);
                    if(notifyObj.has("description")){description=notifyObj.getString("description");
                        description=android.text.Html.fromHtml(description).toString();}
                    if(notifyObj.has("user_id")){String[] words = description.split(" ");user_id=words[0]+" "+words[1];}
                    if(notifyObj.has("is_seen")){is_seen=notifyObj.getInt("is_seen");}
                    if(notifyObj.has("created_at")){created_at=notifyObj.getString("created_at");}
                    if(notifyObj.has("id")){id=notifyObj.getInt("id");}
                    //after each value is initialized
                    notificationObjects[i]=new NotificationObject(user_id,description,is_seen,created_at,id);
                    if (is_seen==0){
                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(con)
                                        .setSmallIcon(R.drawable.pass)
                                        .setContentTitle(notificationObjects[0].user_id+"")
                                        .setContentText(notificationObjects[0].description);
                        NotificationManager mNotifyMgr =
                                (NotificationManager) con.getSystemService(Context.NOTIFICATION_SERVICE);

                        if(noti_id<10){
                            noti_id++;
                            mNotifyMgr.notify(noti_id,mBuilder.build());
                        }else{
                            noti_id=1;
                            mNotifyMgr.notify(noti_id,mBuilder.build());
                        }
                    }
                }
                notificationObjList=new ArrayList<NotificationObject>(Arrays.asList(notificationObjects));
                ////Tools.showAlertDialog(""+description,con);
                // do something with NotificationObjectArray
                ////Tools.showAlertDialog(notificationObjects.length+" : length Notify",con);

            }
        }
        catch (Exception e)
        {
            Log.i("rajat", e.getLocalizedMessage());
        }

    }
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
                //Tools.showAlertDialog(registered_course_id+ late_days_allowed+type_+id+ name+created_at+deadline+file_+" : length",con);
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
                ////Tools.showAlertDialog(submissionObjects.length+" : length",con);
            }
        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }

    }
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
                //Tools.showAlertDialog(type_+" "+id+" "+current_sem+" "+current_year,con);
            }

            if (resultJson.has("courses"))
            {
                courses = resultJson.getJSONArray("courses");
                ArrayList<CourseObject> courseObjects= new ArrayList<CourseObject>();


                for(int i=0;i<courses.length();i++){
                    courseObj=courses.getJSONObject(i);
                    if(courseObj.has("id")){id_course=courseObj.getInt("id");}
                    if(courseObj.has("credits")){credits=courseObj.getInt("credits");}
                    if(courseObj.has("code")){code=courseObj.getString("code");}
                    if(courseObj.has("name")){name_course=courseObj.getString("name");}
                    if(courseObj.has("description")){description_course=courseObj.getString("description");}
                    if(courseObj.has("l_t_p")){l_t_p=courseObj.getString("l_t_p");}
                    //after each value is initialized
                    courseObjects.add(new CourseObject(code,name_course,description_course,l_t_p,credits,id_course));
                }

                if(After_login.courselist){
                    Bundle bundle=new Bundle();
                    bundle.putParcelableArrayList("courses",courseObjects);
                    mycourses_fragment fragment = new mycourses_fragment();
                    After_login.courselist=false;
                    fragment.setArguments(bundle);
                    android.support.v4.app.FragmentTransaction fragmentTransaction =
                            ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.frame_container,fragment);
                    fragmentTransaction.commit();
                  //  After_login.courselist=false;

                }
                else{
                    Bundle bundle=new Bundle();
                bundle.putBoolean("grade",true);
                bundle.putParcelableArrayList("courses",courseObjects);
                universal_fragment fragment = new universal_fragment();
                fragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,fragment);
                fragmentTransaction.commit();}
                // do something with NotificationObjectArray
                ////Tools.showAlertDialog(submissionObjects.length+" : length",con);
                //Tools.showAlertDialog(" len: "+ courseObjects.size(),con);
            }

        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }
    }
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
            ArrayList<GradeObject> gradeObjList = new ArrayList<GradeObject>();
            ArrayList<CourseObject> courseObjList = new ArrayList<CourseObject>();
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
                gradeObjList=new ArrayList<GradeObject>(Arrays.asList(gradeObjects));
                // do something with NotificationObjectArray
                ////Tools.showAlertDialog(submissionObjects.length+" : length",con);
                //Tools.showAlertDialog(" Grade len: "+ gradeObjects.length,con);
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
                courseObjList=new ArrayList<CourseObject>(Arrays.asList(courseObjects));
                // do something with NotificationObjectArray
                ////Tools.showAlertDialog(submissionObjects.length+" : length",con);
                //Tools.showAlertDialog(" Course len: "+ courseObjects.length,con);
            }
            //use courseObjList and gradeObjList || courseObjectsFinal and gradeObjectsFinal
            CourseObject[] courseObjectsFinal= new CourseObject[courseObjList.size()];
            courseObjectsFinal = courseObjList.toArray(courseObjectsFinal);
            GradeObject[] gradeObjectsFinal= new GradeObject[gradeObjList.size()];
            gradeObjectsFinal = gradeObjList.toArray(gradeObjectsFinal);

        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }
    }
    public static void listAllCourseAssignmentApiJsonParser(String JsonStringResult,Context con)
    {
        try {
            JSONArray assignments=new JSONArray();
            JSONObject assignmentObj=new JSONObject();
            String name="",created_at="",deadline="",description="";
            Object file_=null;
            int registered_course_id=0,late_days_allowed=0,type_=0,id=0;
            JSONObject registered=new JSONObject();
            String starting_date="",ending_date="";
            int id_reg=0,professor=0,semester=0,year_=0,course_id=0;
            JSONObject course=new JSONObject();
            String code="",name_course="",description_course="",l_t_p="";
            int credits=0,id_course=0;
            JSONArray course_threads= new JSONArray();
            JSONArray grades= new JSONArray();
            JSONArray resources= new JSONArray();
            JSONArray previous= new JSONArray();
            int year=0;
            int sem =0;
            String tab="";
            ArrayList<AssignmentObject> assignmentObjList = new ArrayList<AssignmentObject>();
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);
            if (resultJson.has("course_threads")){course_threads = resultJson.getJSONArray("course_threads");}
            if (resultJson.has("grades")){grades = resultJson.getJSONArray("grades");}
            if (resultJson.has("resources")){resources = resultJson.getJSONArray("resources");}
            if (resultJson.has("previous")){previous = resultJson.getJSONArray("previous");}
            if (resultJson.has("tab")){tab = resultJson.getString("tab");}
            if (resultJson.has("year")){year = resultJson.getInt("year");}
            if (resultJson.has("sem")){sem = resultJson.getInt("sem");}
            if (resultJson.has("assignments")){
                assignments = resultJson.getJSONArray("assignments");
                AssignmentObject[] assignmentObjects= new AssignmentObject[assignments.length()];
                for(int i=0;i<assignments.length();i++){
                    assignmentObj=assignments.getJSONObject(i);
                    if(assignmentObj.has("registered_course_id")){registered_course_id=assignmentObj.getInt("registered_course_id");}
                    if(assignmentObj.has("late_days_allowed")){late_days_allowed=assignmentObj.getInt("late_days_allowed");}
                    if(assignmentObj.has("type_")){type_=assignmentObj.getInt("type_");}
                    if(assignmentObj.has("id")){id=assignmentObj.getInt("id");}
                    if(assignmentObj.has("name")){name=assignmentObj.getString("name");}
                    if(assignmentObj.has("created_at")){created_at=assignmentObj.getString("created_at");}
                    if(assignmentObj.has("deadline")){deadline=assignmentObj.getString("deadline");}
                    if(assignmentObj.has("description")){description=assignmentObj.getString("description");
                        description=android.text.Html.fromHtml(description).toString();}
                    if(assignmentObj.has("file_")){file_=assignmentObj.get("file_");}
                    assignmentObjects[i]=new AssignmentObject(name,created_at,deadline,description,file_,registered_course_id,late_days_allowed,type_,id);
                    //Tools.showAlertDialog(registered_course_id+ late_days_allowed+type_+id+ name+created_at+deadline+file_+" : length",con);
                }
                assignmentObjList=new ArrayList<AssignmentObject>(Arrays.asList(assignmentObjects));
            }
            if (resultJson.has("registered")){
                registered=resultJson.getJSONObject("registered");
                if(registered.has("id")){id_reg=registered.getInt("id");}
                if(registered.has("professor")){professor=registered.getInt("professor");}
                if(registered.has("semester")){semester = registered.getInt("semester");}
                if(registered.has("year_")){year_ = registered.getInt("year_");}
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
            AssignmentObject[] assignmentObjectsFinal= new AssignmentObject[assignmentObjList.size()];
            assignmentObjectsFinal = assignmentObjList.toArray(assignmentObjectsFinal);
            Bundle b=new Bundle();
            b.putParcelableArrayList("assignments",assignmentObjList);
            assignment_fragment fragment=new assignment_fragment();
            fragment.setArguments(b);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();
        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }

    }
    public static void viewCourseGradesApiJsonParser(String JsonStringResult,Context con)
    {
        try {
            JSONArray assignments=new JSONArray();
            JSONArray grades=new JSONArray();
            JSONObject gradeObj= new JSONObject();
            int weightage=0,user_id=0,out_of=0,registered_course_id=0,id=0;
            double score=0;
            String name="";

            JSONObject registered=new JSONObject();
            String starting_date="",ending_date="";
            int id_reg=0,professor=0,semester=0,year_=0,course_id=0;
            JSONObject course=new JSONObject();
            String code="",name_course="",description_course="",l_t_p="";
            int credits=0,id_course=0;
            JSONArray course_threads= new JSONArray();

            JSONArray resources= new JSONArray();
            JSONArray previous= new JSONArray();
            int year=0;
            int sem =0;
            String tab="";
            //ArrayList<AssignmentObject> assignmentObjList = new ArrayList<AssignmentObject>();
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);

            ArrayList<GradeObject> gradeObjList = new ArrayList<GradeObject>();
            //ArrayList<CourseObject> courseObjList = new ArrayList<CourseObject>();
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
                    if(gradeObj.has("score")){score=gradeObj.getDouble("score");}
                    //after each value is initialized
                    gradeObjects[i]=new GradeObject(weightage,user_id,out_of,registered_course_id,score,id,name_course);
                }

                ////Tools.showAlertDialog(" Grade len: "+ gradeObjects.length,con);
                gradeObjList=new ArrayList<GradeObject>(Arrays.asList(gradeObjects));
                Bundle bundle=new Bundle();
                bundle.putParcelableArrayList("grade",gradeObjList);
                grade_fragment fragment = new grade_fragment();
                fragment.setArguments(bundle);

                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,fragment);
                fragmentTransaction.commit();

                // do something with NotificationObjectArray
                ////Tools.showAlertDialog(submissionObjects.length+" : length",con);
                Log.i("rajat", " Grade len: " + gradeObjects.length);
                //Tools.showAlertDialog(" Grade len: "+ gradeObjects.length,con);
            }

            if (resultJson.has("registered")){
                registered=resultJson.getJSONObject("registered");
                if(registered.has("id")){id_reg=registered.getInt("id");}
                if(registered.has("professor")){professor=registered.getInt("professor");}
                if(registered.has("semester")){semester = registered.getInt("semester");}
                if(registered.has("year_")){year_ = registered.getInt("year_");}
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
            if (resultJson.has("course_threads")){course_threads = resultJson.getJSONArray("course_threads");}
            if (resultJson.has("assignments")){assignments = resultJson.getJSONArray("assignments");}
            if (resultJson.has("resources")){resources = resultJson.getJSONArray("resources");}
            if (resultJson.has("previous")){previous = resultJson.getJSONArray("previous");}
            if (resultJson.has("tab")){tab = resultJson.getString("tab");}
            if (resultJson.has("year")){year = resultJson.getInt("year");}
            if (resultJson.has("sem")){sem = resultJson.getInt("sem");}
            GradeObject[] gradeObjectsFinal= new GradeObject[gradeObjList.size()];
            gradeObjectsFinal = gradeObjList.toArray(gradeObjectsFinal);
            //Log.i("rajat"," Grade len: "+ gradeObjectsFinal.length);
        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }

    }
    public static void viewCourseThreadsApiJsonParser(String JsonStringResult,Context con)
    {
        try {
            JSONArray assignments=new JSONArray();
            JSONArray grades=new JSONArray();


            JSONObject registered=new JSONObject();
            String starting_date="",ending_date="";
            int id_reg=0,professor=0,semester=0,year_=0,course_id=0;
            JSONObject course=new JSONObject();
            String code="",name_course="",description_course="",l_t_p="";
            int credits=0,id_course=0;
            JSONArray course_threads= new JSONArray();
            JSONObject courseThreadObj= new JSONObject();
            String description="",title="",created_at="",updated_at="";
            int user_id_thread=0,registered_course_id_thread=0,id_thread=0;
            JSONArray resources= new JSONArray();
            JSONArray previous= new JSONArray();
            int year=0;
            int sem =0;
            String tab="";
            //ArrayList<AssignmentObject> assignmentObjList = new ArrayList<AssignmentObject>();
            //create json object from response string
            JSONObject resultJson = new JSONObject(JsonStringResult);

            ArrayList<CourseThreadObject> courseThreadObjList = new ArrayList<CourseThreadObject>();
            //ArrayList<CourseObject> courseObjList = new ArrayList<CourseObject>();
            if (resultJson.has("course_threads"))
            {
                course_threads = resultJson.getJSONArray("course_threads");
                CourseThreadObject[] courseThreadObjects= new CourseThreadObject[course_threads.length()];
                for(int i=0;i<course_threads.length();i++){
                    courseThreadObj=course_threads.getJSONObject(i);
                    if(courseThreadObj.has("id")){id_thread=courseThreadObj.getInt("id");}
                    if(courseThreadObj.has("user_id")){user_id_thread=courseThreadObj.getInt("user_id");}
                    if(courseThreadObj.has("title")){title=courseThreadObj.getString("title");}
                    if(courseThreadObj.has("description")){description=courseThreadObj.getString("description");
                    }
                    if(courseThreadObj.has("created_at")){created_at=courseThreadObj.getString("created_at");}
                    if(courseThreadObj.has("updated_at")){updated_at=courseThreadObj.getString("updated_at");}
                    if(courseThreadObj.has("registered_course_id")){registered_course_id_thread=courseThreadObj.getInt("registered_course_id");}
                    //after each value is initialized
                    courseThreadObjects[i]=new CourseThreadObject(description,title,created_at,updated_at,user_id_thread,registered_course_id_thread,id_thread);
                }
                courseThreadObjList=new ArrayList<CourseThreadObject>(Arrays.asList(courseThreadObjects));
                Bundle b=new Bundle();
                b.putParcelableArrayList("thread",courseThreadObjList);
                thread_fragment fragment=new thread_fragment();
                fragment.setArguments(b);
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,fragment);
                fragmentTransaction.commit();
                //Tools.showAlertDialog(" Grade len: "+ gradeObjects.length,con);

                // do something with NotificationObjectArray
                //Tools.showAlertDialog(submissionObjects.length+" : length",con);
                Log.i("rajat"," courseThread len: "+ courseThreadObjects.length);
                Tools.showAlertDialog(" courseThread len: " + courseThreadObjects.length, con);
            }



            if (resultJson.has("registered")){
                registered=resultJson.getJSONObject("registered");
                if(registered.has("id")){id_reg=registered.getInt("id");}
                if(registered.has("professor")){professor=registered.getInt("professor");}
                if(registered.has("semester")){semester = registered.getInt("semester");}
                if(registered.has("year_")){year_ = registered.getInt("year_");}
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
            //if (resultJson.has("course_threads")){course_threads = resultJson.getJSONArray("course_threads");}
            if (resultJson.has("assignments")){assignments = resultJson.getJSONArray("assignments");}
            if (resultJson.has("resources")){resources = resultJson.getJSONArray("resources");}
            if (resultJson.has("previous")){previous = resultJson.getJSONArray("previous");}
            if (resultJson.has("tab")){tab = resultJson.getString("tab");}
            if (resultJson.has("year")){year = resultJson.getInt("year");}
            if (resultJson.has("sem")){sem = resultJson.getInt("sem");}
            CourseThreadObject[] courseThreadObjectsFinal= new CourseThreadObject[courseThreadObjList.size()];
            courseThreadObjectsFinal =courseThreadObjList.toArray(courseThreadObjectsFinal);
            //Log.i("rajat"," Grade len: "+ gradeObjectsFinal.length);
        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }

    }

    public static void viewParticularThreadApiJsonParser(String JsonStringResult,Context con)
    {
        try {
            //ArrayList<String> times_readable =new ArrayList<String>();
            JSONArray timeReadable = new JSONArray();
            JSONArray users = new JSONArray();
            JSONObject userObj=new JSONObject();
            String last_name="",reset_password_key="",registration_key="",
                    first_name="",entry_no="",email="",username="",registration_id="",password="";
            int id =0,type_=0;
            JSONArray comments = new JSONArray();
            JSONObject commentObj=new JSONObject();
            int user_id=0,thread_id=0,id_comment=0;
            String description_comment="",created_at_comment="";
            JSONObject course=new JSONObject();
            String code="",name_course="",description_course="",l_t_p="";
            int credits=0,id_course=0;

            JSONObject courseThreadObj= new JSONObject();
            String description="",title="",created_at="",updated_at="";
            int user_id_thread=0,registered_course_id_thread=0,id_thread=0;
            JSONObject resultJson = new JSONObject(JsonStringResult);

            ArrayList<CommentsObject> commentObjList = new ArrayList<CommentsObject>();
            ArrayList<UsersObject> userObjList = new ArrayList<UsersObject>();
            if (resultJson.has("times_readable"))
            {
                timeReadable = resultJson.getJSONArray("times_readable");}
            String[] timeReadFinal =new String[timeReadable.length()];
            for(int i=0;i<timeReadable.length();i++){
                timeReadFinal[i]=timeReadable.get(i).toString();
                Log.i("rajat", i+ " "+timeReadable.get(i).toString());
            }
            if (resultJson.has("comment_users"))
            {
                users = resultJson.getJSONArray("comment_users");
                UsersObject[] userObjects= new UsersObject[users.length()];
                for(int i=0;i<users.length();i++){
                    userObj=users.getJSONObject(i);
                    if(userObj.has("username")) {username=userObj.getString("username");}
                    if(userObj.has("type_")){type_=userObj.getInt("type_");}
                    if(userObj.has("id")){id=userObj.getInt("id");}
                    if(userObj.has("first_name")){first_name=userObj.getString("first_name");}
                    if(userObj.has("last_name")){last_name=userObj.getString("last_name");}
                    if(userObj.has("entry_no")){entry_no=userObj.getString("entry_no");}
                    if(userObj.has("registration_id")){registration_id=userObj.getString("registration_id");}
                    //after each value is initialized
                    //reset_password_key="",registration_key="",email,password;
                    if(userObj.has("reset_password_key")){reset_password_key=userObj.getString("reset_password_key");}
                    if(userObj.has("registration_key")){registration_key=userObj.getString("registration_key");}
                    if(userObj.has("email")){email=userObj.getString("email");}
                    if(userObj.has("password")){entry_no=userObj.getString("password");}
                    userObjects[i]=new UsersObject(last_name,reset_password_key,registration_key,first_name,entry_no,email,username,registration_id,password,id,type_);
                }
                userObjList=new ArrayList<UsersObject>(Arrays.asList(userObjects));


                // do something with NotificationObjectArray

                Tools.showAlertDialog(" User len: "+ userObjects.length,con);
            }
            if (resultJson.has("comments"))
            {
                comments = resultJson.getJSONArray("comments");
                CommentsObject[] commentObjects= new CommentsObject[comments.length()];
                for(int i=0;i<comments.length();i++){
                    commentObj=comments.getJSONObject(i);
                    if(commentObj.has("id")){id_comment=commentObj.getInt("id");}
                    if(commentObj.has("user_id")){user_id=commentObj.getInt("user_id");}
                    if(commentObj.has("description")){description_comment=commentObj.getString("description");}
                    if(commentObj.has("thread_id")){thread_id=commentObj.getInt("thread_id");}
                    if(commentObj.has("created_at")){created_at_comment=commentObj.getString("created_at");
                    }
                    //after each value is initialized
                    commentObjects[i]=new CommentsObject(user_id,thread_id,id_comment,description_comment,created_at_comment);
                }
                commentObjList=new ArrayList<CommentsObject>(Arrays.asList(commentObjects));


                // do something with NotificationObjectArray

                Tools.showAlertDialog(" Comments len: "+ commentObjects.length,con);
            }
            Bundle b=new Bundle();
            b.putParcelableArrayList("user_ids",userObjList);
            b.putParcelableArrayList("comments",commentObjList);
            b.putParcelable("thread_description",VolleyClick.object);
            thread_description_fragment fragment=new thread_description_fragment();
            fragment.setArguments(b);
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    ((FragmentActivity)con).getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();
            if (resultJson.has("thread"))
            {
                courseThreadObj=resultJson.getJSONObject("thread");
                if(courseThreadObj.has("id")){id_thread=courseThreadObj.getInt("id");}
                if(courseThreadObj.has("user_id")){user_id_thread=courseThreadObj.getInt("user_id");}
                if(courseThreadObj.has("title")){title=courseThreadObj.getString("title");}
                if(courseThreadObj.has("description")){description=courseThreadObj.getString("description");}
                if(courseThreadObj.has("created_at")){created_at=courseThreadObj.getString("created_at");}
                if(courseThreadObj.has("updated_at")){updated_at=courseThreadObj.getString("updated_at");}
                if(courseThreadObj.has("registered_course_id")){registered_course_id_thread=courseThreadObj.getInt("registered_course_id");}

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
            UsersObject[] userObjectsFinal= new UsersObject[userObjList.size()];
            userObjectsFinal = userObjList.toArray(userObjectsFinal);
            CommentsObject[] commentObjectsFinal= new CommentsObject[commentObjList.size()];
            commentObjectsFinal = commentObjList.toArray(commentObjectsFinal);
            //Log.i("rajat"," Grade len: "+ gradeObjectsFinal.length);
        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }
    }
    public static void createNewThreadApiJsonParser(String JsonStringResult,Context con)
    {
        try {
            //ArrayList<String> times_readable =new ArrayList<String>();

            int thread_id=0;
            boolean success=false;
            JSONObject resultJson = new JSONObject(JsonStringResult);


            if (resultJson.has("success"))
            {
                success=resultJson.getBoolean("success");
                if(success){
                    if (resultJson.has("thread_id"))
                    {
                        thread_id=resultJson.getInt("thread_id");
                        //Tools.showAlertDialog(" Thread ID: "+ thread_id,con);
                    }
                }

            }

        }
        catch (Exception e)
        {
            Log.i("rajat","Exception:"+ e.getLocalizedMessage());
        }
    }
    public static void postNewCommentApiJsonParser(String JsonStringResult,Context con)
    {
        try {
            //ArrayList<String> times_readable =new ArrayList<String>();
            String user_name="",time_readable="";
            boolean success=false;
            JSONObject userObj= new JSONObject();
            UsersObject user;
            String last_name="",reset_password_key="",registration_key="",
                    first_name="",entry_no="",email="",username="",registration_id="",password="";
            int id =0,type_=0;
            JSONObject commentObj = new JSONObject();
            CommentsObject comment;
            int user_id=0,thread_id=0,id_comment=0;
            String description_comment="",created_at_comment="";
            JSONObject resultJson = new JSONObject(JsonStringResult);


            if (resultJson.has("success"))
            {
                success=resultJson.getBoolean("success");
                if(success){
                    if(resultJson.has("user_name")){user_name=resultJson.getString("user_name");}
                    if(resultJson.has("time_readable")){time_readable=resultJson.getString("time_readable");}
                    if(resultJson.has("user")){
                        userObj=resultJson.getJSONObject("user");
                        if(userObj.has("username")) {username=userObj.getString("username");}
                        if(userObj.has("type_")){type_=userObj.getInt("type_");}
                        if(userObj.has("id")){id=userObj.getInt("id");}
                        if(userObj.has("first_name")){first_name=userObj.getString("first_name");}
                        if(userObj.has("last_name")){last_name=userObj.getString("last_name");}
                        if(userObj.has("entry_no")){entry_no=userObj.getString("entry_no");}
                        if(userObj.has("registration_id")){registration_id=userObj.getString("registration_id");}
                        //after each value is initialized
                        //reset_password_key="",registration_key="",email,password;
                        if(userObj.has("reset_password_key")){reset_password_key=userObj.getString("reset_password_key");}
                        if(userObj.has("registration_key")){registration_key=userObj.getString("registration_key");}
                        if(userObj.has("email")){email=userObj.getString("email");}
                        if(userObj.has("password")){entry_no=userObj.getString("password");}
                        user=new UsersObject(last_name,reset_password_key,registration_key,first_name,entry_no,email,username,registration_id,password,id,type_);

                        //user = new UsersObject();
                    }
                    if(resultJson.has("comment")){
                        commentObj=resultJson.getJSONObject("comment");
                        //commentObj=comments.getJSONObject(i);
                        if(commentObj.has("id")){id_comment=commentObj.getInt("id");}
                        if(commentObj.has("user_id")){user_id=commentObj.getInt("user_id");}
                        if(commentObj.has("description")){description_comment=commentObj.getString("description");}
                        if(commentObj.has("thread_id")){thread_id=commentObj.getInt("thread_id");}
                        if(commentObj.has("created_at")){created_at_comment=commentObj.getString("created_at");
                        }
                        //after each value is initialized
                        comment=new CommentsObject(user_id,thread_id,id_comment,description_comment,created_at_comment);

                    }
                   // VolleyClick.viewParticularThread();
                    //Tools.showAlertDialog(id_comment + description_comment + entry_no, con);
                }

            }

        }
        catch (Exception e)
        {
            Log.i("rajat", "Exception:" + e.getLocalizedMessage());
        }
    }
}