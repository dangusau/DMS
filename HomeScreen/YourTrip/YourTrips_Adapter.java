package com.dinosoftlabs.uber.HomeScreen.YourTrip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.R;

import java.util.List;

public class YourTrips_Adapter extends RecyclerView.Adapter<YourTrips_Adapter.ViewHolder> {

    Context context;
    List<YourTrips_Model> list;

    Adapter_ClickListener clickListener;


    public YourTrips_Adapter(Context context, List<YourTrips_Model> list, Adapter_ClickListener clickListener) {
        this.context = context;
        this.list = list;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.trips_item,null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v) ;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YourTrips_Model model = list.get(position);

        holder.pickup_time_tv.setText(model.pickup_datetime);
        holder.pickup_location_txt.setText(model.pickup_location);
        holder.dropoff_location_tv.setText(model.destination_location);
        holder.tv_payment.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+model.final_fare);

        if(model.completed.equals("1"))
            holder.status_tv.setText("Completed");

        holder.bind(position,model,clickListener);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pickup_time_tv,pickup_location_txt,dropoff_location_tv,status_tv, tv_payment;
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pickup_time_tv=itemView.findViewById(R.id.pickup_time_tv);
            pickup_location_txt=itemView.findViewById(R.id.pickup_location_tv);
            dropoff_location_tv=itemView.findViewById(R.id.dropoff_location_tv);
            status_tv=itemView.findViewById(R.id.status_tv);
            tv_payment = itemView.findViewById(R.id.amount_tv);

        }

        public void bind(final int pos, final YourTrips_Model item,
                         final Adapter_ClickListener listener ){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.On_Item_Click(pos,item,view);
                }
            });

        }

    }
}
