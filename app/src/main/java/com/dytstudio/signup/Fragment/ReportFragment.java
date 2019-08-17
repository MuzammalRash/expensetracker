package com.dytstudio.signup.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dytstudio.signup.Adapter.ReportAdapter;
import com.dytstudio.signup.Adapter.ShowListAdapter;
import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.DataModel.ReportModel;
import com.dytstudio.signup.OpenCalender;
import com.dytstudio.signup.R;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportFragment extends Fragment {

    ArrayList<ReportModel> arrayList = new ArrayList<>();
    ReportAdapter recyclerAdapter;
    RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    ReportModel reportModel;


    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_layout, container, false);
        getActivity().setTitle("Budget Report");

        recyclerView = (RecyclerView)view.findViewById(R.id.ReportRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        FirebaseDatabase.getInstance().getReference().child("Report").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    reportModel = snapshot.getValue( ReportModel.class );
                    if (reportModel != null) {
                        arrayList.add( reportModel );
                    }
                }
                recyclerAdapter = new ReportAdapter( getActivity(), arrayList );
                recyclerView.setAdapter( recyclerAdapter );
                recyclerView.setLayoutManager(layoutManager = new GridLayoutManager(getContext(), 1));
                recyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        } );


        return view;

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
