package com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Select_Vehicle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.Main.Main_Map_F;
import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<Vehicle_Model_Class> vehicleModelClasses;

    private Adapter_ClickListener itemclick;

    public interface click {
        void onclick(int pos);
    }

    public Adapter(Context context, List<Vehicle_Model_Class> vehicleModelClasses, Adapter_ClickListener click) {
        this.context = context;
        this.vehicleModelClasses = vehicleModelClasses;
        this.itemclick=click;
    }





    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.select_vehicle_item, parent, false);

        return new ViewHolder(view);
    }





    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle_Model_Class model = vehicleModelClasses.get(position);

        holder.tv_vehicle_name.setText(model.vehicle_name);

        holder.tv_vehical_info.setText(model.vehicle_desc);
        if (!model.estimated_fare.equals("") && !Main_Map_F.which_screen_open.equals(""))
            holder.tv_time_or_estimated_fare.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+
                    model.estimated_fare);
        else
            holder.tv_time_or_estimated_fare.setText(model.time + " min");

        if (Select_vehicle_Bts_F.id.equals(model.id))
            holder.tv_time_or_estimated_fare.setVisibility(View.VISIBLE);
        else
            holder.tv_time_or_estimated_fare.setVisibility(View.GONE);

        holder.bind(position, model,itemclick);
    }






    @Override
    public int getItemCount() {
        return vehicleModelClasses.size();
    }






    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_vehicle_name,tv_vehical_info,tv_time_or_estimated_fare;
        View v;
        RelativeLayout rl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_vehicle_name = (TextView) itemView.findViewById(R.id.tv_vehicle_name);
            tv_vehical_info = (TextView) itemView.findViewById(R.id.tv_vehical_info);
            tv_time_or_estimated_fare = (TextView) itemView.findViewById(R.id.tv_time_or_estimated_fare);
            rl = (RelativeLayout) itemView.findViewById(R.id.main_rl_id);
            v = (View) itemView.findViewById(R.id.view_id);

        }

        void bind(final int pos, final Vehicle_Model_Class item, final Adapter_ClickListener onClickListner){
            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListner.On_Item_Click(pos,item,view);
                }
            });
        }
    }
}
