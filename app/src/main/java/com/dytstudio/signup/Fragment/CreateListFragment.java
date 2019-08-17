package com.dytstudio.signup.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dytstudio.signup.Dashboard;
import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.DataModel.RemaingModel;
import com.dytstudio.signup.R;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateListFragment extends Fragment {

    private EditText list, listbudget;
    private Button sendList;
    private String listname;
    private String Budget;
    private int i;
    RemaingModel remaingModel;
    int budget;
    int totalint;
    int list_remaining;
    String list_remianing_string;
    String usedBudget;
    public static String BudgetMove;
    CreateListModel createListModel;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.creatlist, container, false);
        getActivity().setTitle("Create List");

        list = (EditText) view.findViewById(R.id.list);
        listbudget = (EditText) view.findViewById(R.id.Listbudget);
        sendList = (Button) view.findViewById(R.id.sendlist);


        sendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalint = Integer.parseInt(DasboardFragment.Remaing_Inco);
                if (list.getText().length() == 0) {
                    list.setError("Enter List Name !");
                } else if (listbudget.getText().length() == 0) {
                    listbudget.setError("Enter Budget");
                } else {
                    Budget = listbudget.getText().toString();
                    budget = Integer.parseInt(Budget);
                    if (budget > totalint) {
                        listbudget.setError("Exceed The Limite Please Check Your Income");
                    } else {

                        listname = list.getText().toString();
                        usedBudget = "0";
                        createListModel = new CreateListModel(SharedPreferencesManager.getSomeStringValue(getContext()), capitalize(listname), Budget, usedBudget);
                        FirebaseDatabase.getInstance().getReference().child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getContext())).child(capitalize(listname)).setValue(createListModel).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                list_remaining = totalint - budget;
                                list_remianing_string = Integer.toString(list_remaining);
                                remaingModel = new RemaingModel(SharedPreferencesManager.getSomeStringValue(getContext()), list_remianing_string);
                                FirebaseDatabase.getInstance().getReference().child("Remaining-Income").child(SharedPreferencesManager.getSomeStringValue(getContext())).setValue(remaingModel);
                                FirebaseDatabase.getInstance().getReference().child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getContext())).child(capitalize(listname))
                                        .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.getValue() != null) {

                                            createListModel = dataSnapshot.getValue(CreateListModel.class);
                                            createListModel.getListBudget();
                                            BudgetMove = createListModel.getListBudget();
                                            createListModel = new CreateListModel(SharedPreferencesManager.getSomeStringValue(getContext()), capitalize(listname), Budget, BudgetMove);
                                            FirebaseDatabase.getInstance().getReference().child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getContext())).child(capitalize(listname)).setValue(createListModel);

                                            Toast.makeText(getContext(), "List Successfully Created", Toast.LENGTH_SHORT).show();
//                                            ((Dashboard) getActivity()).callListActivity();
                                            Intent intent =new Intent(getActivity(),Dashboard.class);
                                            startActivity(intent);

//

                                        } else {

                                            BudgetMove = "0.00";
                                            Toast.makeText(getContext(), "Budget" + BudgetMove, Toast.LENGTH_SHORT).show();
                                        }


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });


                            }
                        });

                    }
                }
            }
        });


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