package com.rajat.moodle.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import com.rajat.moodle.After_login;
import com.rajat.moodle.Login;
import com.rajat.moodle.Volley.VolleyClick;

/**
 * Created by Rajat on 23-02-2016.
 */
public class notification_service extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("rajat","notify_service start");
        try {
            if (Login.sharedpreferences != null) {
                if (Login.sharedpreferences.contains("Set-Cookie")) {
                    if (!Login.sharedpreferences.getString("Set-Cookie", "").equals("")) {
                        Log.i("rajat","Set Cookie key found");
                        VolleyClick.onNotifyService(After_login.context);
                    }
                }
            }
        }catch (Exception n){
            n.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
class Call_Notification extends AsyncTask<Void, Integer, String>{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}