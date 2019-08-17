package com.dytstudio.signup;

import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.DataModel.IncomeModel;
import com.dytstudio.signup.DataModel.RemaingModel;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

public class Tracker extends Application {


    public static boolean activityVisible; // Variable that will check the
        // current activity state

        public static boolean isActivityVisible()
    {
            return activityVisible; // return true or false
        }

        public static void activityResumed() {
            activityVisible = true;// this will set true when activity resumed

        }

        public static void activityPaused() {
            activityVisible = false;// this will set false when activity paused

        }


}
