package com.dytstudio.signup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dytstudio.signup.DataModel.CreateListModel;
import com.dytstudio.signup.DataModel.ReportModel;
import com.dytstudio.signup.R;

import java.util.ArrayList;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    Context context;
    ArrayList<ReportModel> arrayList;

    public ReportAdapter(Context context, ArrayList<ReportModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.report_item, parent, false);
        ReportAdapter.ViewHolder viewHolder = new ReportAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final  ReportModel reportModel = arrayList.get(position);
        holder.name.setText(reportModel.getName());
        holder.percentage.setText(reportModel.getPercent());

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,percentage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.cataname);
            percentage=itemView.findViewById(R.id.percentvalue);
        }
    }
}
