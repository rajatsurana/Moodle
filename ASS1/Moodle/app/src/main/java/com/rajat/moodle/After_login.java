package com.rajat.moodle;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.rajat.moodle.Objects.UsersObject;
import com.rajat.moodle.Volley.VolleyClick;

public class After_login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static boolean Part=true;
    public static int List_id=0;
    //public  static int
    public static int level_details=0;
    Intent i;
    public static boolean courselist;
    public static int depth=0;
    public static Context context;
    Bundle bundle;
    @Override
    protected void onStart() {
        super.onStart();
        i=getIntent();
        if(i!=null){
            Log.i("rajat",i.getStringExtra("entry_no")+" "+i.getStringExtra("email"));
            String username =i.getStringExtra("userName");
            String email = i.getStringExtra("email");
            String entry_no =i.getStringExtra("entry_no");

            SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE).edit();
            editor.putString("userName", username);
            editor.putString("email", email);
            editor.putString("entry_no", entry_no);
            editor.apply();
        }
        else{
            Toast.makeText(getApplicationContext(), "yahan prob hai", Toast.LENGTH_SHORT).show();
        }

        if(!isMyServiceRunning(com.rajat.moodle.service.backgroundservice.class))
        {
            Log.i("rajat", "isMyServiceRunning is false ");
            Intent intent = new Intent(After_login.this, com.rajat.moodle.service.backgroundservice.class);
            startService(intent);

        }
    }


    private boolean isMyServiceRunning(Class<?> serviceClass) {
        try {
            ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    return true;
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }
    boolean logout_press = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        String username=getIntent().getStringExtra("userName");
        String email_id=getIntent().getStringExtra("email");

        setContentView(R.layout.activity_after_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Moodle");
        context =After_login.this;
        if(i!=null){

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header_lay=navigationView.getHeaderView(0);

        TextView name_user = (TextView)header_lay.findViewById(R.id.name_user);
        TextView email_user = (TextView)header_lay.findViewById(R.id.email_user);
        //SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        //Log.i("rajat","sp: " +sp.getAll().size());
        //if(sp.contains("userName")&&sp.contains("email")){
//if(name_user!=null)
            name_user.setText(username);
            //if(email_user!=null)
            email_user.setText(email_id);
        }


   // }


    boolean doubleBackToExitPressedOnce=false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
            if(After_login.Part) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }else{
                if(After_login.List_id==1){

                }else if(After_login.List_id==2){
                    courselist=true;
                    if(level_details==1){
                        VolleyClick.listAllCourses(After_login.this);//thread_fragment.goTodepth2();
                    }else if (level_details==3){
                        if(depth==2){
                            VolleyClick.listAllCourses(After_login.this);
                        }else if(depth==3){
                            thread_fragment.goTodepth2();
                        }else if(depth==4){
                            VolleyClick.viewCourseThreads(VolleyClick.cou_code, After_login.this);
                        }
                    }else{
                        VolleyClick.listAllCourses(After_login.this);//thread_fragment.goTodepth2();
                    }

                }else if(After_login.List_id==3){
                    //courselist=false;
                    //if(!courselist){
                        VolleyClick.listAllCourses(After_login.this);
                    //}

                }else{
                    super.onBackPressed();
                }
            }
        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.after_login, menu);
        courselist = true;
        VolleyClick.listAllCourses(After_login.this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notification) {
            //getSupportActionBar().setTitle("Notifications");
            VolleyClick.onNotifyClick(After_login.this);

        } else if (id == R.id.nav_courses) {

            //getSupportActionBar().setTitle("Courses");
            courselist = true;
            VolleyClick.listAllCourses(After_login.this);

        } else if(id==R.id.nav_grades){
            //getSupportActionBar().setTitle("Grades");
            courselist=false;
            VolleyClick.listAllCourses(After_login.this);
        }else if (id == R.id.nav_logout) {
            logout_press = true;
            VolleyClick.onLogoutClick(After_login.this);

        }else if (id == R.id.nav_profile){
            //getSupportActionBar().setTitle("Profile");
            profile_fragment fragment = new profile_fragment();

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_password){
            //getSupportActionBar().setTitle("Change password");
            password_fragment fragment = new password_fragment();

            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container,fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(logout_press == true){
            finish();
        }

    }
}
