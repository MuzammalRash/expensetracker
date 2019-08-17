package com.dytstudio.signup;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Path;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.DataModel.ExpensesModel;
import com.dytstudio.signup.DataModel.RemaingModel;
import com.dytstudio.signup.DataModel.ReportModel;
import com.dytstudio.signup.Fragment.CreateListFragment;
import com.dytstudio.signup.Fragment.DasboardFragment;
import com.dytstudio.signup.Fragment.DeleteFragment;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OpenCalender extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView dateDisplay, title, uidkey;
    public static String name;
    String uid;
    private Intent intent;
    private Button addExpenses;
    private EditText des, ammount;
    private String description, Amount, date;
    private String totalincome, currentbalance;
    private int usedBalanceopenCalender = 0;
    private String used_B;
    private TextView remaing_income;
    RemaingModel remaingModel;
    ActionBar actionBar;
    int Current_Balance;
    int fragment_budget;
    CreateListModel createListModel;
    String Value;
    String Value2;
    String Fragment_Balance;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_calender);
        createListModel = new CreateListModel();
        intent = getIntent();
        name = intent.getExtras().getString("categoryList");
        uid = intent.getExtras().getString("uid");
        title = (TextView) findViewById(R.id.titlebar);
        title.setText(capitalize(name));
        dateDisplay = (TextView) findViewById(R.id.selectdate);
        uidkey = (TextView) findViewById(R.id.uid);
        uidkey.setText(uid);
        addExpenses = (Button) findViewById(R.id.addexpenses);
        des = (EditText) findViewById(R.id.description);
        ammount = (EditText) findViewById(R.id.money);
        calendarView = (CalendarView) findViewById(R.id.calender);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(OpenCalender.this, Dashboard.class);
//                startActivity(intent);
            }
        });


        FirebaseDatabase.getInstance().getReference().child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getApplication())).child(capitalize(name)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {

                    createListModel = dataSnapshot.getValue(CreateListModel.class);
                    Value = createListModel.getListRemaining();
                    Value2 = createListModel.getListBudget();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {


//                Toast.makeText(getApplicationContext(), "Selected Date:\n" + "Day = " + i2 + "\n" + "Month = " + i1 + "\n" + "Year = " + i, Toast.LENGTH_LONG).show();
                dateDisplay.setText(i2 + "-" + i1 + "-" + i);
                date = dateDisplay.getText().toString();


            }
        });
        addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentbalance = ammount.getText().toString();
                Current_Balance = Integer.parseInt(currentbalance);
                fragment_budget = Integer.parseInt(Value);


                if (dateDisplay.getText().length() == 0) {
                    dateDisplay.setError("Select date First ");
                } else if (des.getText().length() == 0) {
                    des.setError("Enter Description");
                } else if (ammount.getText().length() == 0) {
                    ammount.setError("Enter Ammount");

                } else {


                    if (Current_Balance > fragment_budget) {
                        ammount.setError("Exceed The Limite Please Check Your Budget");
                    } else {
                        fragment_budget = fragment_budget - Current_Balance;
                        used_B = Integer.toString(fragment_budget);
                        createListModel = new CreateListModel(SharedPreferencesManager.getSomeStringValue(getApplication()), name, Value2, used_B);
                        FirebaseDatabase.getInstance().getReference().child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getApplication())).child(capitalize(name)).setValue(createListModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                ExpensesModel expensesModel = new ExpensesModel(SharedPreferencesManager.getSomeStringValue(getApplication()), date, description = des.getText().toString(), Amount = ammount.getText().toString());
                                FirebaseDatabase.getInstance().getReference().child("Expenses").child(SharedPreferencesManager.getSomeStringValue(getApplication())).child(capitalize(name)).child(capitalize(description)).setValue(expensesModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {


                                        double total_amount = Double.parseDouble(Value2);
                                        double remaining_amount = Double.parseDouble(used_B);
                                        double per = (remaining_amount / total_amount) * 100;
                                        double final_per = 100.0f - per;
                                        String s_per = String.valueOf(final_per);
                                        ReportModel reportModel = new ReportModel(SharedPreferencesManager.getSomeStringValue(getApplication()), capitalize(name), s_per);
                                        FirebaseDatabase.getInstance().getReference().child("Report").child(SharedPreferencesManager.getSomeStringValue(getApplication())).child(capitalize(name)).setValue(reportModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Intent returnIntent = new Intent();
                                                returnIntent.putExtra("result", "abc");
                                                setResult(Activity.RESULT_OK, returnIntent);
                                                finish();
                                                Intent intent= new Intent(getApplication(),Dashboard.class);
                                                startActivity(intent );
//                                                Toast.makeText(OpenCalender.this, "Data Successfully Saved", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }

                }
            }
        });

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


}
