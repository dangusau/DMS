package com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card.Card_Adapter;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card.Card_Model;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card.Credit_card_A;
import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Cash_BottomSheet_F extends BottomSheetDialogFragment implements View.OnClickListener {

    private Switch switch_careem_pay;
    private Boolean switchs_state;

    private ImageView iv_check;
    private LinearLayout ll_cash,ll_add_card;

    private RecyclerView rv_payment_card;
    private Card_Adapter card_adp;
    private List<Card_Model> card_list;

    public static int pos = 0;

    private View view;
    private Fragment_CallBack fragmentCallBack;

    public Cash_BottomSheet_F(Boolean switchs_state, Fragment_CallBack fragmentCallBack){
        this.fragmentCallBack = fragmentCallBack;
        this.switchs_state=switchs_state;
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cash_bts_f, container, false);

        Variables.user_id = Variables.mPrefs.getString(Variables.User_id,"null");


        METHOD_findviewbyid();


        return view;
    }





    private void METHOD_findviewbyid(){
        switch_careem_pay = view.findViewById(R.id.switch_careem_pay);
        switch_careem_pay.setOnClickListener(this::onClick);

        if(switchs_state){
            switch_careem_pay.setChecked(true);
        }else {
            switch_careem_pay.setChecked(false);
        }

        ll_add_card = view.findViewById(R.id.ll_add_card);
        ll_add_card.setOnClickListener(this::onClick);

        ll_cash = view.findViewById(R.id.ll_cash);
        ll_cash.setOnClickListener(this::onClick);

        iv_check = view.findViewById(R.id.iv_check);
        if (pos == 0)
            iv_check.setImageResource(R.drawable.ic_check_green);
        else
            iv_check.setImageResource(R.drawable.ic_check_gray);

        rv_payment_card = view.findViewById(R.id.rv_payment_card);
        card_list = new ArrayList<>();

        rv_payment_card.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_payment_card.setHasFixedSize(false);

        CallApi_Of_showPaymentmethod();
    }






    private void CallApi_Of_showPaymentmethod() {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ApiRequest.Call_Api(getContext(), Api_url.url_showPaymentMethod, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                if (resp !=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")){
                            JSONArray msgarray = respobj.getJSONArray("msg");
                            card_list.clear();

                            for (int i=0; i<msgarray.length(); i++){
                                JSONObject obj = msgarray.getJSONObject(i);
                                Card_Model model = new Card_Model();

                                model.card_id = ""+obj.getJSONObject("PaymentMethod").optString("id");
                                model.card_number = ""+obj.optString("last4");
                                model.brand = ""+obj.optString("brand");

                                card_list.add(model);
                            }

                            METHOD_setAdapter();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }







    private void METHOD_setAdapter() {
        card_adp = new Card_Adapter(getContext(), card_list, new Adapter_ClickListener() {
            @Override
            public void On_Item_Click(int postion, Object Model, View view) {
                Card_Model model = (Card_Model) Model;

                card_list.remove(postion);


                iv_check.setImageResource(R.drawable.ic_check_gray);
                pos = postion+1;

                card_list.add(postion,model);
                card_adp.notifyItemChanged(postion);
                card_adp.notifyDataSetChanged();

                Bundle bundle = new Bundle();
                if (pos == 0) {
                    bundle.putBoolean("iscash", true);
                    bundle.putString("card_id", "0");
                }
                else {
                    bundle.putBoolean("iscash", false);
                    bundle.putString("card_id", model.card_id);
                }

                if (switch_careem_pay.isChecked())
                    bundle.putBoolean("switch", switch_careem_pay.isChecked());
                else
                    bundle.putBoolean("switch", false);

                fragmentCallBack.On_Item_Click(0,bundle);
                dismiss();
            }

            @Override
            public void On_Item_Long_Click(int postion, Object Model, View view) {
                Card_Model model = (Card_Model) Model;

                METHOD_showDeleteCardDialog(model.card_id,postion,model);
            }
        });

        rv_payment_card.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_payment_card.setHasFixedSize(false);

        rv_payment_card.setAdapter(card_adp);
    }






    private void METHOD_showDeleteCardDialog(String id, int pos, Card_Model model){
        final CharSequence[] options = { "Delete card", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(),R.style.AlertDialogCustom);
        builder.setTitle(null);
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Delete card")) {
                    METHOD_deletePaymentCard(id);
                    card_list.remove(model);
                    card_adp.notifyItemRemoved(pos);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });

        builder.show();
    }





    private void METHOD_deletePaymentCard(String id){
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.user_id);
            sendobj.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_deletePaymentMethod, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                if (resp !=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")){

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        });
    }







    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.switch_careem_pay:
                Bundle bundle = new Bundle();
                bundle.putBoolean("switch", switch_careem_pay.isChecked());
                if (pos == 0)
                    bundle.putBoolean("iscash", true);
                else
                    bundle.putBoolean("iscash", false);

                fragmentCallBack.On_Item_Click(0,bundle);
                dismiss();
                break;

            case R.id.ll_add_card:
                Intent intent = new Intent(getActivity(), Credit_card_A.class);
                startActivityForResult(intent, 2);
                break;

            case R.id.ll_cash:
                Bundle bundle1 = new Bundle();
                pos = 0;
                card_adp.notifyDataSetChanged();

                iv_check.setImageResource(R.drawable.ic_check_green);

                bundle1.putBoolean("iscash", true);
                if (switch_careem_pay.isChecked())
                    bundle1.putBoolean("switch", switch_careem_pay.isChecked());
                else
                    bundle1.putBoolean("switch", false);

                fragmentCallBack.On_Item_Click(0,bundle1);
                dismiss();
                break;
        }
    }






    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2){
            CallApi_Of_showPaymentmethod();
        }

    }
}
