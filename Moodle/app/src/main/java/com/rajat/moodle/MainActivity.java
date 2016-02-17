package com.rajat.moodle;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rajat.moodle.Tools.CheckNetwork;
import com.rajat.moodle.Tools.Tools;
import com.rajat.moodle.Volley.CallVolley;
import com.rajat.moodle.Volley.VolleySingleton;


public class MainActivity extends AppCompatActivity {
Button login,logout,notifyButton,courseAssignmentButton,listCoursesButton,viewGradesButton;
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
    }
    public void viewAllGrades(){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/default/grades.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.viewGradesCall(URL, MainActivity.this);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }
    public void listAllCourses(){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/courses/list.json";
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.getCourseListCall(URL, MainActivity.this);
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

            CallVolley.getNotificationCall(URL, MainActivity.this);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }

    public void getCourseAssignmentDetails(int number){
        CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
        String URL = "http://192.168.43.200/courses/assignment.json/"+number;
        if (!chkNet.checkNetwork()) {
            VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();
            CallVolley.getCourseAssignmentCall(URL, MainActivity.this);
        } else {
            Tools.showAlertDialog("Internet Available", MainActivity.this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
