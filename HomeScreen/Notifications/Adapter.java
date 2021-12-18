package com.dinosoftlabs.uber.HomeScreen.Notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<ModelClass> list;


    public Adapter(Context context, List<ModelClass> list)
    {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.notification_item, parent, false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelClass model = list.get(position);

        String month = Functions
                .Change_date_format("yyyy-MM-dd hh:mm:ss","MMMM", model.datetime);
        String date = Functions
                .Change_date_format("yyyy-MM-dd hh:mm:ss", "EEEE, MMMM dd", model.datetime);
        String time = Functions
                .Change_date_format("yyyy-MM-dd hh:mm:ss","hh:mm aa", model.datetime);

        holder.tv_month.setText(month);
        holder.tv_date.setText(date);
        holder.tv_time.setText(time);
        holder.tv_notification.setText(model.notification);

        if(position==0){
            holder.tv_month.setVisibility(View.VISIBLE);
            holder.tv_date.setVisibility(View.VISIBLE);
        }else{
            if (model.comparing_month.equals(list.get(position-1).comparing_month)){
                holder.tv_month.setVisibility(View.GONE);
            }else {
                holder.tv_month.setVisibility(View.VISIBLE);
            }
            if(model.comparing_date.equals(list.get(position-1).comparing_date))
                holder.tv_date.setVisibility(View.GONE);
            else
                holder.tv_date.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_month,tv_date,tv_time,tv_notification;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_month = itemView.findViewById(R.id.tv_month);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_notification = itemView.findViewById(R.id.tv_notification);

        }

    }
}
