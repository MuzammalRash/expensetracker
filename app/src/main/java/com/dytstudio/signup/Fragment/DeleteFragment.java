package com.dytstudio.signup.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dytstudio.signup.Adapter.ReportAdapter;
import com.dytstudio.signup.Dashboard;
import com.dytstudio.signup.DataModel.ExpensesModel;
import com.dytstudio.signup.DataModel.ReportModel;
import com.dytstudio.signup.R;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteFragment  extends Fragment {

    Button deletebtn;
    DatabaseReference mdatabasereference;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delete_layout, container, false);
        getActivity().setTitle("Delete History");
        deletebtn=(Button)view.findViewById(R.id.deletebtn);
        mdatabasereference = FirebaseDatabase.getInstance().getReference();
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mdatabasereference.child("Expenses").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            snapshot.getRef().removeValue();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                } );


                mdatabasereference.child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            snapshot.getRef().removeValue();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                } );

                mdatabasereference.child("Report").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            snapshot.getRef().removeValue();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                } );


                mdatabasereference.child("Add-Income").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            snapshot.getRef().removeValue();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                } );


                mdatabasereference.child("Remaining-Income").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            snapshot.getRef().removeValue();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                } );


                mdatabasereference.child("Notification").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            snapshot.getRef().removeValue();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                } );

                Toast.makeText(getContext(), "Data Successfully Deleted", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(getActivity(),Dashboard.class);
//                startActivity(intent);

            }
        });




        return view;
    }
}
