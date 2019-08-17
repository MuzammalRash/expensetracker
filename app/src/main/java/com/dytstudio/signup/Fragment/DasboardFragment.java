package com.dytstudio.signup.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.dytstudio.signup.Adapter.CategoryRecyclerModel;
import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.DataModel.IncomeModel;
import com.dytstudio.signup.DataModel.NotificationModel;
import com.dytstudio.signup.DataModel.RemaingModel;
import com.dytstudio.signup.MySingleton;
import com.dytstudio.signup.OpenCalender;
import com.dytstudio.signup.R;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.dytstudio.signup.Tracker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class DasboardFragment extends Fragment {


    TextView income, remaining_balance;
    ArrayList<CreateListModel> arrayList = new ArrayList<>();
    CategoryRecyclerModel recyclerAdapter;
    RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    CreateListModel createListModel;
    private ProgressDialog progressDialog;


    TextView edtTitle;
    TextView edtMessage;
    final private String FCM_API = "https://fcm.googleapis.com/fcm/send";
    final private String serverKey = "AAAAQycAgE8:APA91bHF0hSr9-NR2uwDU-5HkZIMR5jDinBnPXWsvQrofZYgZT3oFhsQLrEMt7GkvG1oOaGh7kPA_rt2yj9ioNh0KwE0ZHXH2Frwq2mfbJB7IGO7sAGsWLj6e9hnzsnT_HYFZ_OvYxbN";
    final private String contentType = "application/json";
    final String TAG = "NOTIFICATION TAG";
    String NOTIFICATION_TITLE;
    String NOTIFICATION_MESSAGE;
    String Topic = "Public";
    int int_Remaining, int_SetIncome;
    String Title, setincome = "0";
    public static String Remaing_Inco = "0";
    public static String Total_inco;
    public String incomeplus, ResultIncome;
    public static int incomeint, ResultInt;
    int fragment_budget;
    public static String Value, Value2;
    String name;


    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard, container, false);
        getActivity().setTitle("Dashboard");

        income = (TextView) view.findViewById(R.id.ammount);
        income.setText("0");
        ResultIncome = income.getText().toString();


        edtTitle = view.findViewById(R.id.Title);
        edtMessage = view.findViewById(R.id.Msg);


        FirebaseDatabase.getInstance().getReference().child("Notification").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {

                    NotificationModel notificationModel = dataSnapshot.getValue(NotificationModel.class);
                    Title = notificationModel.getIdkey();
                    setincome = notificationModel.getSetIncome();


                } else {
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        remaining_balance = (TextView) view.findViewById(R.id.remaingbalance);
        progressDialog = new ProgressDialog(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.CategoryRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        progressDialog.setMessage("Loading...");
        progressDialog.show();
        FirebaseDatabase.getInstance().getReference().child("Add-Income").child(SharedPreferencesManager.getSomeStringValue(getActivity())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.getValue() != null) {
                    IncomeModel incomeModel = dataSnapshot.getValue(IncomeModel.class);
                    progressDialog.dismiss();
                    if (incomeModel == null) {
                        income.setText("0.00");
                    } else {


                        income.setText(incomeModel.getIncome());
                        ResultIncome = income.getText().toString();

                    }
                } else {


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FirebaseDatabase.getInstance().getReference().child("Remaining-Income").child(SharedPreferencesManager.getSomeStringValue(getActivity())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                RemaingModel remaingModel = dataSnapshot.getValue(RemaingModel.class);
                progressDialog.dismiss();
                if (dataSnapshot.getValue() != null) {
                    if (remaingModel == null) {
                        remaining_balance.setText("0.00");
                    } else {
                        remaining_balance.setText(remaingModel.getIncome());
                        if (remaingModel.getIncome() == null) {
                            Remaing_Inco = "0";
                        } else {

                            Remaing_Inco = remaingModel.getIncome();
                        }
                    }
                } else {

                    Remaing_Inco = "0";

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        FirebaseDatabase.getInstance().getReference().child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    if (dataSnapshot.getValue() != null) {
                        createListModel = snapshot.getValue(CreateListModel.class);

                        arrayList.add(createListModel);
                    }
                }

                recyclerAdapter = new CategoryRecyclerModel(getActivity(), arrayList, new CategoryRecyclerModel.Back() {

                    @Override
                    public void backPress(CreateListModel createListModel) {


                        Intent intent = new Intent( getContext(), OpenCalender.class );
                        intent.putExtra( "categoryList", createListModel.getList());
                        intent.putExtra( "uid", createListModel.getUid());
                        intent.putExtra( "bugdet", createListModel.getListBudget());
                        intent.putExtra( "remainingBudget", createListModel.getListRemaining());
                        startActivityForResult(intent,100);

//                        createListModel.getList();
//                        createListModel.getUid();
//                        createListModel.getListBudget();
//                        createListModel.getListRemaining();
//                        arrayList.clear();
//                        arrayList.add(createListModel);
//                        recyclerAdapter.notifyDataSetChanged();



                    }
                });
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(layoutManager = new GridLayoutManager(getContext(), 2));
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        notification();


        return view;
    }

    private void sendNotification(JSONObject notification) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, FCM_API, notification,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

//                        edtMessage.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", "key=" + serverKey);
                params.put("Content-Type", contentType);
                return params;
            }
        };
        MySingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    protected void onPostExecute(Void result) {
        if (isAdded()) {
            getResources().getString(R.string.app_name);
        }
    }

    private String capitalize(String str) {
        String[] strArray = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }
        return builder.toString();
    }


    public  void notification(){

        FirebaseMessaging.getInstance().subscribeToTopic("Public")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }

                        int_SetIncome = Integer.parseInt(setincome);
                        int_Remaining = Integer.parseInt(Remaing_Inco);

                        boolean sendNotificaiton = false;
                        if(int_Remaining == 0 && int_SetIncome == 0 && !SharedPreferencesManager.getnotificationState(getContext())) {
                            SharedPreferencesManager.setnotificationState(getContext(),true);
                            sendNotificaiton = true;
                            Title = "Your Account is 0.00";
                            NOTIFICATION_TITLE = Title;
                        }else if(int_Remaining < int_SetIncome && SharedPreferencesManager.getnotificationState(getContext())) {
                            SharedPreferencesManager.setnotificationState(getContext(),false);
                            sendNotificaiton = true;
                            NOTIFICATION_TITLE = Title;
                        } else{

                            if(!SharedPreferencesManager.getnotificationState(getContext())){
                                SharedPreferencesManager.setnotificationState(getContext(),true);
                                sendNotificaiton = false;
                                Title = "Please Recharge Your Account";
                                NOTIFICATION_TITLE = Title;
                            }
                        }

                        if (sendNotificaiton) {
                            JSONObject notification = new JSONObject();
                            JSONObject notifcationBody = new JSONObject();
                            try {
                                notifcationBody.put("title", NOTIFICATION_TITLE);

                                String token = FirebaseInstanceId.getInstance().getToken();
                                notification.put("to", token);
                                notification.put("data", notifcationBody);
                                sendNotification(notification);
                            } catch (JSONException e) {
                                Toast.makeText(getContext(), "catch", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 100) {
//            if (resultCode == RESULT_OK) {
//
//
//                String uidRetrun = data.getStringExtra("uid");
//                String  listRetrun = data.getStringExtra("categoryList");
//                String budgetRetrun = data.getStringExtra("bugdet");
//                String remaingRetrun = data.getStringExtra("remainingBudget");
////                String returnedResult = data.getData().toString();
//                // OR
//                // String returnedResult = data.getDataString();
//
//
//            }
//        }
//
//
//
//    }





}