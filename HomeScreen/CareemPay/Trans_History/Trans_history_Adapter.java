package com.dinosoftlabs.uber.HomeScreen.CareemPay.Trans_History;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.R;

import java.util.List;

public class Trans_history_Adapter extends RecyclerView.Adapter<Trans_history_Adapter.ViewHolder> {

    private Context context;
    private List<Trans_history_Model> list;


    public Trans_history_Adapter(Context context, List<Trans_history_Model> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trans_history_item, parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Trans_history_Model model = list.get(position);

        holder.tv_month.setText(model.month);
        holder.tv_datetime.setText(model.datetime);
        holder.tv_final_fare.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+model.final_fare);

        String text_cash = "You have paid "+holder.tv_final_fare.getText().toString()+" amount through cash.";
        String text_cash_wallet = "You have paid "+holder.tv_final_fare.getText().toString()+" amount through cash and "+Variables.mPrefs.getString(Variables.currency_unit,"")+model.payment_from_wallet+" from Wallet.";

        String text_card = "You have paid "+holder.tv_final_fare.getText().toString()+" amount from card.";
        String text_card_wallet = "You have paid "+holder.tv_final_fare.getText().toString()+" amount through card and "+Variables.mPrefs.getString(Variables.currency_unit,"")+model.payment_from_wallet+" from Wallet.";


        if (model.payment_type.equals("cash")) {

            if (model.payment_from_wallet.equals("0"))
                holder.tv_cash_paid.setText("CASH"+"\n"+text_cash);
            else
                holder.tv_cash_paid.setText("CASH & WALLET"+"\n"+text_cash_wallet);

        }else if (model.payment_type.equals("card")){

            if (model.payment_from_wallet.equals("0"))
                holder.tv_cash_paid.setText("CARD"+"\n"+text_card);
            else
                holder.tv_cash_paid.setText("CARD & WALLET"+"\n"+text_card_wallet);

        }


        if(position==0){
            holder.tv_month.setVisibility(View.VISIBLE);
            holder.tv_datetime.setVisibility(View.VISIBLE);
        }else{
            if (model.comparing_month.equals(list.get(position-1).comparing_month)){
                holder.tv_month.setVisibility(View.GONE);
            }else {
                holder.tv_month.setVisibility(View.VISIBLE);
            }
            if(model.comparing_date.equals(list.get(position-1).comparing_date))
                holder.tv_datetime.setVisibility(View.GONE);
            else
                holder.tv_datetime.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_month,tv_datetime,tv_final_fare,tv_cash_paid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_cash_paid = (TextView) itemView.findViewById(R.id.tv_cash_paid);

            tv_month = (TextView) itemView.findViewById(R.id.tv_month);
            tv_datetime = (TextView) itemView.findViewById(R.id.tv_datetime);
            tv_final_fare = (TextView) itemView.findViewById(R.id.tv_final_fare);

        }
    }
}
