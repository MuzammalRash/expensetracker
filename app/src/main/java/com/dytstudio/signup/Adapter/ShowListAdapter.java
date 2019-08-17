package com.dytstudio.signup.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.R;
import com.dytstudio.signup.ShowExpensesDetail;

import java.util.ArrayList;

public class ShowListAdapter  extends RecyclerView.Adapter<ShowListAdapter.ViewHolder> {

    Context context;
    ArrayList<CreateListModel> arrayList;

    public ShowListAdapter(Context context, ArrayList<CreateListModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ShowListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.showlist_item, viewGroup, false);
        ShowListAdapter.ViewHolder viewHolder = new ShowListAdapter.ViewHolder(view);
        return viewHolder;
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


    @Override
    public void onBindViewHolder(@NonNull ShowListAdapter.ViewHolder viewHolder, int i) {

        final  CreateListModel createListModel = arrayList.get(i);
        viewHolder.name.setText(this.capitalize(createListModel.getList()));
        viewHolder.key.setText(this.capitalize(createListModel.getUid()));


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( context, ShowExpensesDetail.class );
                intent.putExtra( "categoryListname", createListModel.getList());


                context.startActivity( intent );



            }
        });



    }

    @Override
    public int getItemCount() {
       return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,key;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.categoryNameshow);
            key = itemView.findViewById(R.id.keyid);
        }
    }
}
