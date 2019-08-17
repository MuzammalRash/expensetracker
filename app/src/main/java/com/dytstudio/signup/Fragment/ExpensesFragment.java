package com.dytstudio.signup.Fragment;

import android.annotation.SuppressLint;
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
import com.dytstudio.signup.Adapter.ShowListAdapter;
import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.R;
import com.dytstudio.signup.SharePerfernce.SharedPreferencesManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class ExpensesFragment extends Fragment {

//    private Intent intent;
//    private String name, uid;
    ArrayList<CreateListModel> arrayList = new ArrayList<>();
    ShowListAdapter recyclerAdapter;
    RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    CreateListModel createListModel;

    @SuppressLint("WrongConstant")
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.expensesfragment, container, false);


        getActivity().setTitle("Expenses Category");

//
        recyclerView = (RecyclerView)view.findViewById(R.id.expensesrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        FirebaseDatabase.getInstance().getReference().child("Create-List").child(SharedPreferencesManager.getSomeStringValue(getContext())).addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    createListModel = snapshot.getValue( CreateListModel.class );
                    if (createListModel != null) {
                        arrayList.add( createListModel );
                    }
                }
                recyclerAdapter = new ShowListAdapter( getActivity(), arrayList );
                recyclerView.setAdapter( recyclerAdapter );
                recyclerView.setLayoutManager(layoutManager = new GridLayoutManager(getContext(), 3));
                recyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        } );

        return view;
    }
}
