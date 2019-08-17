package com.dytstudio.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dytstudio.signup.DataModel.ReportModel;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class GrpahicFragment extends Fragment {

    PieChart pieChart;
    ArrayList<String> arrayListName = new ArrayList<String>();
    ArrayList<String> arrayListPer = new ArrayList<String>();
    ReportModel reportModel;
    float [] floatValues;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graphical_report, container, false);
        getActivity().setTitle("Graphic Report");


        FirebaseDatabase.getInstance().getReference().child("Report").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    reportModel = snapshot.getValue(ReportModel.class);


                    arrayListName.add(reportModel.getName());
                    arrayListPer.add(reportModel.getPercent());
                    Toast.makeText(getContext(), ""+ arrayListName+""+arrayListPer, Toast.LENGTH_SHORT).show();

//                    floatValues  = new float[arrayList.size()];
//
//                    for (int i = 0; i < arrayList.size(); i++) {
//                        floatValues[i] = Float.parseFloat(arrayList.get(i));
//
//                        Toast.makeText(getContext(), "D " + floatValues[i], Toast.LENGTH_SHORT).show();
//
//                    }


               }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


        pieChart = view.findViewById(R.id.piechart);
        pieChart.setClickable(true);
        pieChart.setTouchEnabled(true);
        ArrayList<Entry> Percentage = new ArrayList<Entry>();



//        for(int j =0 ; j <= arrayList.size();j++){
//
//            Percentage.add(new Entry(floatValues[j],j));
//        }


        Percentage.add(new Entry(200f, 0));
        Percentage.add(new Entry(200f, 1));
        Percentage.add(new Entry(200f, 2));
        final PieDataSet dataSet = new PieDataSet(Percentage, "Character Type Security Password");
        dataSet.setDrawValues(false);
        final ArrayList<String> Name = new ArrayList<String>();




//            Toast.makeText(getContext(), "" +arrayList.get(0), Toast.LENGTH_SHORT).show();

        Name.add("@");
        Name.add("#");
        Name.add("%");


        PieData data = new PieData(Name, dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(5000, 5000);
        pieChart.getLegend().setEnabled(false);
        pieChart.setDescription("Graphical Report of Expensive");
        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {


//                int position =  e.getXIndex();
//                loginsymbol= year.get( position );

            }

            @Override
            public void onNothingSelected() {

            }
        });

        return view;
    }

}
