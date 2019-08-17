package com.dytstudio.signup;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.Fragment.DeleteFragment;
import com.dytstudio.signup.Fragment.ReminderFragment;
import com.dytstudio.signup.Fragment.ReportFragment;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.dytstudio.signup.DataModel.UserProfile;
import com.dytstudio.signup.Fragment.CreateListFragment;
import com.dytstudio.signup.Fragment.DasboardFragment;
import com.dytstudio.signup.Fragment.ExpensesFragment;
import com.dytstudio.signup.Fragment.IncomeFragment;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DatabaseReference mDatabase;
    TextView name, email;
    private FirebaseAuth firebaseAuth;
    public static String Value, Value2;

    DasboardFragment dasboardFragment;
    DeleteFragment deleteFragment;
    ReportFragment reportFragment;
    ReminderFragment reminderFragment;
    ExpensesFragment expensesFragment;
    CreateListFragment createList;
    IncomeFragment icomeFragment;
    GrpahicFragment grpahicFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        dasboardFragment = new DasboardFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.expensive, dasboardFragment).commit();



//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        View v = navigationView.getHeaderView(0);
        name = (TextView) v.findViewById(R.id.Showname);
        email = (TextView) v.findViewById(R.id.Showemail);

        mDatabase.child("Users").child(SharedPreferencesManager.getSomeStringValue(getBaseContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                UserProfile model = dataSnapshot.getValue(UserProfile.class);


                name.setText(model.getName());
                email.setText(model.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

        } else {
            Intent intent = new Intent(Dashboard.this, InternetConnection.class);
            startActivity(intent);
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Logout) {

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signOut();
            startActivity(new Intent(this, Login.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addincome) {

            icomeFragment = new IncomeFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.expensive, icomeFragment).commit();
        } else if (id == R.id.createlist) {

            createList = new CreateListFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.expensive, createList).commit();

        } else if (id == R.id.expenditure) {

            expensesFragment = new ExpensesFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.expensive, expensesFragment).commit();


        } else if (id == R.id.dashboard) {


            dasboardFragment = new DasboardFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.expensive, dasboardFragment).commit();
        } else if (id == R.id.reminder) {


            reminderFragment = new ReminderFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.expensive, reminderFragment).commit();
        } else if (id == R.id.report) {


            reportFragment = new ReportFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.expensive, reportFragment).commit();

        } else if (id == R.id.DeleteH) {

            deleteFragment = new DeleteFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.expensive, deleteFragment).commit();
        }


        else if (id == R.id.ReportFinal) {


            Intent intent= new Intent(getApplication(),MainActivity.class);
            startActivity(intent);

//            grpahicFragment = new GrpahicFragment();
//            FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
//            fm.replace(R.id.expensive, grpahicFragment).commitAllowingStateLoss();


        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void callActivity() {
        dasboardFragment = new DasboardFragment();
        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        fm.replace(R.id.expensive, dasboardFragment).commitAllowingStateLoss();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {




//                String uidRetrun = data.getStringExtra("uid");
//                String  listRetrun = data.getStringExtra("categoryList");
//                String budgetRetrun = data.getStringExtra("bugdet");
//                String remaingRetrun = data.getStringExtra("remainingBudget");
//                String returnedResult = data.getData().toString();
                // OR
                // String returnedResult = data.getDataString();


            }
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.expensive, dasboardFragment).commit();
        Toast.makeText(this, "Resume", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onStart() {
        super.onStart();

        dasboardFragment = new DasboardFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.expensive, dasboardFragment).commit();
        Toast.makeText(this, "ONActiivty", Toast.LENGTH_SHORT).show();

    }




    //    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        dasboardFragment = new DasboardFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.expensive, dasboardFragment).commit();
//    }
}
