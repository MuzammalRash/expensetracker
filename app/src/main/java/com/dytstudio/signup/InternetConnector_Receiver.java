package com.dytstudio.signup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class InternetConnector_Receiver extends BroadcastReceiver {

	HomeActivity mainActivity;

	public InternetConnector_Receiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		mainActivity= new HomeActivity();
		try {


			boolean isVisible = Tracker.isActivityVisible();
			Log.i("Activity is Visible ", "Is activity visible : " + isVisible);

			// If it is visible then trigger the task else do nothing
			if (isVisible == true) {
				ConnectivityManager connectivityManager = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connectivityManager
						.getActiveNetworkInfo();

				// Check internet connection and accrding to state change the
				// text of activity by calling method
				if (networkInfo != null && networkInfo.isConnected()) {


				} else {
					Toast.makeText(context, "No Internet", Toast.LENGTH_SHORT).show();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
