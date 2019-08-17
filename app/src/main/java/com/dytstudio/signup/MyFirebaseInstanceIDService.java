package com.dytstudio.signup;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIDService extends Service {

    private static final String TAG = "mFirebaseIIDService";
    private static final String SUBSCRIBE_TO = "Public";



    public void onTokenRefresh() {

        String token = FirebaseInstanceId.getInstance().getToken();


        FirebaseMessaging.getInstance().subscribeToTopic(SUBSCRIBE_TO);
        Toast.makeText( this, "onTokenRefresh completed with token: " + token, Toast.LENGTH_SHORT ).show();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}