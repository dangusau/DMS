package com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Cash_BottomSheet_F;
import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class Card_Adapter extends RecyclerView.Adapter<Card_Adapter.ViewHolder> {

    private List<Card_Model> list;
    private Adapter_ClickListener clickListener;

    public Card_Adapter(Context context, List<Card_Model> list, Adapter_ClickListener clickListener) {
        this.list = list;
        this.clickListener = clickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item,null);
        v.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(v) ;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Card_Model model = list.get(position);

        holder.card_number.setText("**** **** **** "+model.card_number);

        if (model.brand.equals("Visa")){
            holder.iv.setActualImageResource(R.drawable.ic_visa_card);
        }else {
            holder.iv.setActualImageResource(R.drawable.ic_master_card);
        }

        if (Cash_BottomSheet_F.pos != 0){
            if (position == (Cash_BottomSheet_F.pos-1))
                holder.iv_check.setImageResource(R.drawable.ic_check_green);
            else
                holder.iv_check.setImageResource(R.drawable.ic_check_gray);
        }else {
            holder.iv_check.setImageResource(R.drawable.ic_check_gray);
        }


        holder.bind(position,model,clickListener);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_main;
        SimpleDraweeView iv;
        TextView card_number;
        ImageView iv_check;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ll_main = itemView.findViewById(R.id.ll_main);
            iv = itemView.findViewById(R.id.iv_card);
            card_number = itemView.findViewById(R.id.tv_card_number);
            iv_check = itemView.findViewById(R.id.iv_check);

        }



        void bind(final int item, final Card_Model model, final Adapter_ClickListener listener) {

            ll_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.On_Item_Click(item,model,view);
                }
            });

            ll_main.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.On_Item_Long_Click(item,model,v);
                    return true;
                }
            });

        }
    }
}
