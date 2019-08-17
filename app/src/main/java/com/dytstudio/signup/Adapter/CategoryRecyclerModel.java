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
import com.dytstudio.signup.OpenCalender;
import com.dytstudio.signup.R;

import java.util.ArrayList;

public class CategoryRecyclerModel extends RecyclerView.Adapter<CategoryRecyclerModel.ViewHolder> {

    Context context;
    ArrayList<CreateListModel>arrayList;
    Back back;

    public CategoryRecyclerModel(Context context, ArrayList<CreateListModel> arrayList,Back b) {
        this.context = context;
        this.arrayList = arrayList;
        this.back=b;
    }

    @NonNull
    @Override
    public CategoryRecyclerModel.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list, viewGroup, false);
        CategoryRecyclerModel.ViewHolder viewHolder = new CategoryRecyclerModel.ViewHolder(view);
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
    public void onBindViewHolder(@NonNull CategoryRecyclerModel.ViewHolder viewHolder, int i) {

        final  CreateListModel createListModel = arrayList.get(i);
        viewHolder.name.setText(this.capitalize(createListModel.getList()));
        viewHolder.key.setText(this.capitalize(createListModel.getUid()));
        viewHolder.budget.setText(this.capitalize(createListModel.getListBudget()));
        viewHolder.RemianingBudget.setText(this.capitalize(createListModel.getListRemaining()));


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( context, OpenCalender.class );
                intent.putExtra( "categoryList", createListModel.getList());
                intent.putExtra( "uid", createListModel.getUid());
                back.backPress(createListModel);
//                context.startActivity( intent );



            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,key,budget,RemianingBudget;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.categoryName);
            key = itemView.findViewById(R.id.key);
            budget = itemView.findViewById(R.id.BudgetList);
            RemianingBudget = itemView.findViewById(R.id.budgetlistremaining);
        }
    }


    public interface Back{

        public void backPress(CreateListModel createListModel);

    }
}
