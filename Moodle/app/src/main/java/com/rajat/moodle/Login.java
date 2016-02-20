package com.rajat.moodle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Saurabh on 2/20/2016.
 */
public class Login extends Activity {
    Button login;
    boolean buttonpress = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ac);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Moodle" + "</font>")));
        initializeViews();*/
        login = (Button)findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                buttonpress = true;
                Intent openH = new Intent(v.getContext(),After_login.class);
                startActivityForResult(openH,0);
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        if(buttonpress == true){
            finish();
        }

    }


}
