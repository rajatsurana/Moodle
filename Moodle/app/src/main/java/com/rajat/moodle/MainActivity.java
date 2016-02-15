package com.rajat.moodle;

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
Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onLoginClick();
            }
        });
    }
public void onLoginClick(){
    String entryNumber1 = "vinay", studentName1="vinay";
    CheckNetwork chkNet = new CheckNetwork(MainActivity.this);
    String URL = "http://192.168.43.200/default/login.json?userid="+entryNumber1+"&password="+studentName1+"";
    if (!chkNet.checkNetwork()) {
        VolleySingleton.getInstance(MainActivity.this).getRequestQueue().getCache().clear();

        CallVolley.makeLoginCall(URL,MainActivity.this);
    } else {
        Tools.showAlertDialog("Internet Unavailable", MainActivity.this);
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
