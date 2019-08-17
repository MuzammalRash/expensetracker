package com.dytstudio.signup.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dytstudio.signup.Dashboard;
import com.dytstudio.signup.DataModel.IncomeModel;
import com.dytstudio.signup.DataModel.RemaingModel;
import com.dytstudio.signup.R;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IncomeFragment extends Fragment {

    EditText addincome;
    IncomeModel incomeModel;
    RemaingModel remaingModel;
    DatabaseReference databaseReference;
    Button incomebtn;
    int limit = 70000;
    String income;
    int result;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.incomefragment, container, false);
        getActivity().setTitle("Add Income");
        addincome = (EditText) view.findViewById(R.id.income);
        incomebtn = (Button) view.findViewById(R.id.sendincome);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        incomebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result = Integer.parseInt(addincome.getText().toString());

                if (addincome.getText().length() == 0) {
                    addincome.setError("Enter Income !");

                } else if (result >= limit) {
                    addincome.setError("Cross The limit Please Enter Below Ammount From 70,000");
                } else {
                    income = addincome.getText().toString();
                    incomeModel = new IncomeModel(SharedPreferencesManager.getSomeStringValue(getContext()), income);
                    databaseReference.child("Add-Income").child(SharedPreferencesManager.getSomeStringValue(getContext())).setValue(incomeModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            remaingModel = new RemaingModel(SharedPreferencesManager.getSomeStringValue(getContext()), income);
                            databaseReference.child("Remaining-Income").child(SharedPreferencesManager.getSomeStringValue(getContext())).setValue(incomeModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
//                                    AddIncome = income + income;
//                                    incomeModel = new IncomeModel(SharedPreferencesManager.getSomeStringValue(getContext()), AddIncome);
//                                    databaseReference.child("Add-Income").child(SharedPreferencesManager.getSomeStringValue(getContext())).setValue(incomeModel);
                                    Toast.makeText(getContext(), "Income Successfully Added", Toast.LENGTH_SHORT).show();
                                    ((Dashboard) getActivity()).callActivity();
                                }
                            });


                        }
                    });


                }
            }
        });

        return view;
    }

    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        addincome.setText("");


    }
}
