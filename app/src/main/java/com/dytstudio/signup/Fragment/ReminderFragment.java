package com.dytstudio.signup.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dytstudio.signup.Dashboard;
import com.dytstudio.signup.DataModel.IncomeModel;
import com.dytstudio.signup.DataModel.NotificationModel;
import com.dytstudio.signup.R;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ReminderFragment extends Fragment {

    TextView addincome;
    EditText notify_msg;
    EditText percentage_notify;
    public static String noti, per,addin;
    Button reminderbtn;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.reminder_layout, container, false);
        getActivity().setTitle("Reminder");
        notify_msg = (EditText) view.findViewById(R.id.notifi_msg);
        percentage_notify = (EditText) view.findViewById(R.id.percentage);
        addincome = (TextView) view.findViewById(R.id.Showtotalincome);
        reminderbtn = (Button) view.findViewById(R.id.reminderBtn);
        FirebaseDatabase.getInstance().getReference().child("Add-Income").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {


                    IncomeModel incomeModel = dataSnapshot.getValue(IncomeModel.class);
                    addincome.setText(incomeModel.getIncome());
                    addin=addincome.getText().toString();
                } else {

                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reminderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (notify_msg.getText().length() == 0) {
                    notify_msg.setError( "Enter Notify Msg" );
                } else if (percentage_notify.getText().length() == 0 && percentage_notify.getText().length() < addincome.getText().length() ){
                    percentage_notify.setError( "Enter Percentage for Reminder" );
                }
                else{

                   noti = capitalize(notify_msg.getText().toString());
                    per = percentage_notify.getText().toString();

                    NotificationModel notificationModel = new NotificationModel(SharedPreferencesManager.getSomeStringValue(getContext()),noti,per);
                    FirebaseDatabase.getInstance().getReference().child("Notification").child(SharedPreferencesManager.getSomeStringValue(getContext())).setValue(notificationModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Intent intent = new Intent(getContext(), Dashboard.class);
                            startActivity(intent);
                        }
                    });

                }
            }
        });
        return view;
    }
    private  String capitalize(String str) {
        String[] strArray = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
        return builder.toString();
    }


}


