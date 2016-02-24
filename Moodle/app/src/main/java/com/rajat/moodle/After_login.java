package com.rajat.moodle;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
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
    Intent i;
    public static boolean courselist;
    Bundle b;
    public static Context context;
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


        setContentView(R.layout.activity_after_login);
        String username=getIntent().getStringExtra("userName");
        String emailid=getIntent().getStringExtra("email");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        View headerLayout = navigationView.getHeaderView(0);
        TextView name_user = (TextView)headerLayout.findViewById(R.id.name_user);

        if(name_user!=null)name_user.setText(username);
        TextView email_user = (TextView)headerLayout.findViewById(R.id.email_user);
        if(email_user!=null)email_user.setText(emailid);

        SharedPreferences sp = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Log.i("rajat","sp: " +sp.getAll().size());





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
            VolleyClick.onNotifyClick(After_login.this);

        } else if (id == R.id.nav_courses) {
            courselist = true;
            VolleyClick.listAllCourses(After_login.this);

        } else if(id==R.id.nav_grades){
            VolleyClick.listAllCourses(After_login.this);
        }else if (id == R.id.nav_logout) {
            logout_press = true;
            VolleyClick.onLogoutClick(After_login.this);

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
