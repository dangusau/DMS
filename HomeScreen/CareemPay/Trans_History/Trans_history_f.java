package com.dinosoftlabs.uber.HomeScreen.CareemPay.Trans_History;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Trans_history_f extends RootFragment implements View.OnClickListener {

    private RecyclerView rv_trans_history;
    private Trans_history_Adapter adp;
    private List<Trans_history_Model> list;

    private LinearLayout ll_toolbar;

    private View view;

    public Trans_history_f() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.transaction_history_f, container, false);


        METHOD_findviewbyid();

        return view;
    }






    private void METHOD_findviewbyid() {
        ll_toolbar = view.findViewById(R.id.ll_toolbar);
        ll_toolbar.setOnClickListener(this);

        rv_trans_history = (RecyclerView) view.findViewById(R.id.rv_id);

        list = new ArrayList<>();

        rv_trans_history.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_trans_history.setHasFixedSize(false);

        CallApi_showTrasHistory();

        ImageView iv_down_arrow = (ImageView) view.findViewById(R.id.iv_down_arrow);
        iv_down_arrow.setOnClickListener(this);
    }




    private void CallApi_showTrasHistory(){
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.mPrefs.getString(Variables.User_id,"null"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_showTransactionHistory, sendobj, new Callback() {

            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();

                if (resp!=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")){
                            JSONArray msgarray = respobj.getJSONArray("msg");

                            for (int i = 0; i<msgarray.length(); i++){
                                JSONObject tripPayment = msgarray.getJSONObject(i).getJSONObject("TripPayment");
                                JSONObject trip = msgarray.getJSONObject(i).getJSONObject("Trip");

                                Trans_history_Model model = new Trans_history_Model();

                                String datetime = Functions.Change_date_format("yyyy-MM-dd hh:mm:ss",
                                        "hh:mm a,EEEE dd", ""+trip.optString("destination_datetime"));

                                model.datetime = datetime;
                                model.comparing_date = datetime;


                                String month = Functions.Change_date_format("yyyy-MM-dd hh:mm:ss",
                                        "MMMM", ""+trip.getString("created"));

                                model.month = month;
                                model.comparing_month = month;
                                model.payment_type = tripPayment.getString("payment_type");
                                model.payment_from_wallet = tripPayment.getString("payment_from_wallet");
                                model.final_fare = tripPayment.getString("final_fare");

                                list.add(model);

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




    private void METHOD_setAdapter(){
        adp = new Trans_history_Adapter(getContext(), list);
        rv_trans_history.setAdapter(adp);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_toolbar:
                getActivity().onBackPressed();
                break;
        }
    }

}
