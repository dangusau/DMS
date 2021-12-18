package com.dinosoftlabs.uber.HomeScreen.CareemPay;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card.Card_Adapter;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card.Card_Model;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card.Credit_card_A;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.TopUp.Topup_A;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Trans_History.Trans_history_f;
import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.R;
import com.DMS_Cust.DMS.HelpingClasses.Functions;
import com.DMS_Cust.DMS.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Pay_F extends RootFragment implements View.OnClickListener {

    private View view;

    TextView tv_price;

    private RecyclerView rv_card,rv;
    private Card_Adapter card_adp;
    private List<Card_Model> card_list;


    public Pay_F() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pay_f, container, false);

        Variables.user_id = Variables.mPrefs.getString(Variables.User_id,"null");


        METHOD_findviewbyid();

        METHOD_showUserDetails();

        return view;
    }


    private void METHOD_showUserDetails() {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ApiRequest.Call_Api(getContext(), Api_url.url_showUserDetails, sendobj, new Callback() {

            @Override
            public void Responce(String resp) {
                if (resp!=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")){
                            JSONObject obj = respobj.getJSONObject("msg").getJSONObject("User");

                            tv_price.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+
                                    obj.getString("wallet"));

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }

        });

    }




    protected void METHOD_findviewbyid(){
        LinearLayout ll_back = (LinearLayout) view.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);

        LinearLayout ll_card = view.findViewById(R.id.ll_card);
        ll_card.setOnClickListener(this);


        TextView tv_topup = (TextView) view.findViewById(R.id.tv_topup);
        tv_topup.setOnClickListener(this);


        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.rl_id);

        RelativeLayout trans_his = view.findViewById(R.id.trans_his_id);
        trans_his.setOnClickListener(this);


        tv_price = (TextView) view.findViewById(R.id.price_id);

        rv_card = (RecyclerView) view.findViewById(R.id.rv_card);
        card_list = new ArrayList<>();

        rv_card.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_card.setHasFixedSize(false);

        CallApi_Of_showPaymentmethod();



        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) rl.getLayoutParams();
        lp.height = (int) (Variables.height / 2.5);

        rl.setLayoutParams(lp);
    }







    private void CallApi_Of_showPaymentmethod() {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Functions.ShowProgressLoader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_showPaymentMethod, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.CancelProgressLoader();
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

            }

            @Override
            public void On_Item_Long_Click(int postion, Object Model, View view) {
                Card_Model model = (Card_Model) Model;

                METHOD_showDeleteCardDialog(model.card_id,postion,model);
            }

        });

        rv_card.setAdapter(card_adp);
    }






    private void METHOD_showDeleteCardDialog(String id, int pos, Card_Model model){
        final CharSequence[] options = { "Delete card", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MyDialogStyle);
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

        Functions.ShowProgressLoader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_deletePaymentMethod, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.CancelProgressLoader();
                if (resp !=null){
                    try {
                        JSONObject respobj = new JSONObject(resp);
                        if (respobj.getString("code").equals("200")){
                            Functions.ShowToast(view.getContext(), ""+respobj.optString("msg"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_back:
                getActivity().onBackPressed();
                break;


            case R.id.tv_topup:
                startActivity(new Intent(getActivity(), Topup_A.class));
                break;


            case R.id.ll_card:
                Intent intent = new Intent(getActivity(), Credit_card_A.class);
                startActivityForResult(intent, 2);
                break;


            case R.id.trans_his_id:
                Trans_history_f f = new Trans_history_f();
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
                ft.replace(R.id.fl_id, f).addToBackStack(null).commit();
                break;
        }
    }





    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2)
            CallApi_Of_showPaymentmethod();


    }

}
