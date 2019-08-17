package com.dytstudio.signup.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dytstudio.signup.DataModel.ExpensesModel;
import com.dytstudio.signup.R;

import java.util.ArrayList;

public class ExpensesAdapter  extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {


    Context context;
    ArrayList<ExpensesModel> arrayList;

    public ExpensesAdapter (Context context, ArrayList<ExpensesModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.expenses_item, viewGroup, false);
        ExpensesAdapter.ViewHolder viewHolder = new ExpensesAdapter.ViewHolder(view);
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

      final ExpensesModel expensesModel= arrayList.get(i);
        viewHolder.date.setText(this.capitalize(expensesModel.getDate()));
        viewHolder.des.setText(this.capitalize(expensesModel.getDescription()));
        viewHolder.amount.setText(this.capitalize(expensesModel.getAmount()));



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView date,des,amount;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            date=itemView.findViewById(R.id.date);
            des=itemView.findViewById(R.id.des);
            amount=itemView.findViewById(R.id.expensemoney);
        }
    }
}
