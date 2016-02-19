package com.rajat.moodle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.rajat.moodle.Tools.CheckNetwork;
import com.rajat.moodle.Tools.Tools;
import com.rajat.moodle.Volley.CallVolley;
import com.rajat.moodle.Volley.VolleySingleton;

public class MainActivity extends AppCompatActivity {
    Button  login,logout,notifyButton,courseAssignmentButton,
            listCoursesButton,viewGradesButton,listAllCourseAssignmentButton,
            viewCourseGradesButton,viewCourseThreadsButton,viewParticularThreadButton,
            createNewThreadButton,postNewCommentButton ;
        int Notification=1,
            CourseAssignment=2,
            CourseList=3,
            ViewGrades=4,
            listAllCourseAssignment=5,
            viewCourseGrades=6,
            viewCourseThreads=7,
            viewParticularThread=8,
            createNewThread=9,
            postNewComment=10;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences =getPreferences(Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLoginClick();
            }
        });
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogoutClick();
            }
        });
        notifyButton = (Button) findViewById(R.id.notify);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNotifyClick();
            }
        });
        listCoursesButton = (Button) findViewById(R.id.listCourses);
        listCoursesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAllCourses();
            }
        });
        viewGradesButton = (Button) findViewById(R.id.viewGrades);
        viewGradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewAllGrades();
            }
        });
        courseAssignmentButton = (Button) findViewById(R.id.courseAssignment);
        courseAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCourseAssignmentDetails(1);
            }
        });
        listAllCourseAssignmentButton= (Button) findViewById(R.id.listAllCourseAssignment);
        listAllCourseAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAllCourseAssignment("cop290");
            }
        });
        viewCourseGradesButton= (Button) findViewById(R.id.viewCourseGrades);
        viewCourseGradesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCourseGrades("cop290");
            }
        });
        viewCourseThreadsButton= (Button) findViewById(R.id.viewCourseThreads);
        viewCourseThreadsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewCourseThreads("cop290");
            }
        });
        viewParticularThreadButton= (Button) findViewById(R.id.viewParticularThread);
        viewParticularThreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewParticularThread(1);
            }
        });
        //createNewThread
        createNewThreadButton= (Button) findViewById(R.id.createNewThread);
        createNewThreadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewThread("Raja", "Maharaja", "cop290");
            }
        });
        postNewCommentButton= (Button) findViewById(R.id.postNewComment);
        postNewCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNewComment(7,"Maharaja");
            }
        });
        //postNewComment
    }
    //postNewCommentCall
    public void postNewComment(int thread_id,String description){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/threads/post_comment.json?thread_id="+thread_id+"&description="+description;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.afterLoginCall(URL, MainActivity.this, postNewComment);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
    public void createNewThread(String title,String description,String course_code){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/threads/new.json?title="+title+"&description="+description+"&course_code="+course_code;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.afterLoginCall(URL, MainActivity.this, createNewThread);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
    public void viewParticularThread(int thread_id){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/threads/thread.json/"+thread_id;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.afterLoginCall(URL, MainActivity.this, viewParticularThread);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
    public void viewAllGrades(){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/default/grades.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.afterLoginCall(URL, MainActivity.this, ViewGrades);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
    public void listAllCourses(){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/courses/list.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.afterLoginCall(URL, MainActivity.this, CourseList);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
    public void onLogoutClick(){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/default/logout.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.makeLogoutCall(URL, MainActivity.this);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
public void onLoginClick(){
    String entryNumber1 = "cs5110281", studentName1="jasmeet";
    CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
    String URL = "http://192.168.43.200/default/login.json?userid="+entryNumber1+"&password="+studentName1+"";
    if (!chkNet.checkNetwork()) {
        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
        CallVolley.makeLoginCall(URL, MainActivity.this);
    } else {
        Tools.showAlertDialog("Internet Available", MainActivity.this);
    }
}
    public void onNotifyClick(){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/default/notifications.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();

            CallVolley.afterLoginCall(URL, MainActivity.this, Notification);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
//
    //viewCourseThreadsCall
public void viewCourseThreads(String courseCode){
    CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
    String URL = "http://192.168.43.200/courses/course.json/"+courseCode+"/threads";
    if (!chkNet.checkNetwork()) {
        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
        CallVolley.afterLoginCall(URL, MainActivity.this, viewCourseThreads);
    } else {
        Tools.showAlertDialog("Internet Available", MainActivity.this);
    }
}
public void viewCourseGrades(String courseCode){
    CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
    String URL = "http://192.168.43.200/courses/course.json/"+courseCode+"/grades";
    if (!chkNet.checkNetwork()) {
        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
        CallVolley.afterLoginCall(URL, MainActivity.this, viewCourseGrades);
    } else {
        Tools.showAlertDialog("Internet Available", MainActivity.this);
    }
}
public void listAllCourseAssignment(String courseCode){
    CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
    String URL = "http://192.168.43.200/courses/course.json/"+courseCode+"/assignments";
    if (!chkNet.checkNetwork()) {
        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
        CallVolley.afterLoginCall(URL, MainActivity.this, listAllCourseAssignment);
    } else {
        Tools.showAlertDialog("Internet Available", MainActivity.this);
    }
}
    public void getCourseAssignmentDetails(int number){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/courses/assignment.json/"+number;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.afterLoginCall(URL, MainActivity.this, CourseAssignment);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
}