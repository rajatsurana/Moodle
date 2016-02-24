package com.rajat.moodle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rajat.moodle.Volley.VolleyClick;

/**
 * Created by Saurabh on 2/20/2016.
 */
public class Login extends Activity {
    Button login;
    boolean buttonpress = false;
    EditText username,password;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static SharedPreferences sharedpreferences;
    public static SharedPreferences.Editor editor ;

    @Override
    protected void onStart() {
        super.onStart();
        if(getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)!=null) {
            if(!getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE).getString("Set-Cookie","").equals("")) {
                Intent openH = new Intent(Login.this, After_login.class);
                startActivity(openH);
            }
        }

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ac);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        titleId = getResources().getIdentifier("action_bar_title", "id", "android");
        setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Moodle" + "</font>")));
        initializeViews();*/
        login = (Button)findViewById(R.id.button_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                buttonpress = true;
                String user=username.getText().toString();
                String pass=password.getText().toString();
                VolleyClick.onLoginClick(user,pass,Login.this);
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
