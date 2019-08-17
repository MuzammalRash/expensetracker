package com.dytstudio.signup;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dytstudio.signup.Adapter.ExpensesAdapter;
import com.dytstudio.signup.DataModel.ExpensesModel;
import com.dytstudio.signup.Fragment.ExpensesFragment;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowExpensesDetail extends AppCompatActivity  {


    ArrayList<ExpensesModel> arrayList = new ArrayList<>();
    ExpensesAdapter recyclerAdapter;
    RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    ExpensesModel expensesModel;
    private Intent intent;
    private String name;
    private TextView title;
    private ProgressDialog progressDialog;
    ImageView back;



    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_expenses_detail);

        intent = getIntent();
        name = intent.getExtras().getString("categoryListname");
        title=(TextView)findViewById(R.id.titlebarview);
        title.setText(capitalize(name));
        progressDialog = new ProgressDialog(this);
        recyclerView = (RecyclerView) findViewById(R.id.expensesDetailSheet);
        back=(ImageView)findViewById(R.id.back2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));
        progressDialog.setMessage("Loading...");


        progressDialog.show();
        FirebaseDatabase.getInstance().getReference().child("Expenses").child(SharedPreferencesManager.getSomeStringValue(getApplication())).child(capitalize(name)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    expensesModel = snapshot.getValue(ExpensesModel.class);
                    progressDialog.dismiss();
                    if (expensesModel != null) {
                        arrayList.add(expensesModel);
                    }
                }
                recyclerAdapter = new ExpensesAdapter(getApplication(), arrayList);
                recyclerView.setAdapter(recyclerAdapter);
                recyclerView.setLayoutManager(layoutManager = new GridLayoutManager(getApplication(), 1));
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
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
