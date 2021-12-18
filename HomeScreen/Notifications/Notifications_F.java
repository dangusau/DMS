package com.dinosoftlabs.uber.HomeScreen.Notifications;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


public class Notifications_F extends RootFragment implements View.OnClickListener {

    private View view;

    private RecyclerView rv_notification;
    private Adapter adp;
    private List<ModelClass> list;

    public Notifications_F() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.notifications_f, container, false);

        Variables.user_id = Variables.mPrefs.getString(Variables.User_id,"");


        METHOD_findviewbyid();


        return view;
    }




    protected void METHOD_findviewbyid(){
        LinearLayout back_ll = (LinearLayout) view.findViewById(R.id.back_ll);
        back_ll.setOnClickListener(this);


        rv_notification = (RecyclerView) view.findViewById(R.id.noti_rv_id);

        rv_notification.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_notification.setHasFixedSize(false);

        list = new ArrayList<>();

        METHOD_CallApi_showNotification();
    }






    private void METHOD_CallApi_showNotification() {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id",Variables.user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_showNotification, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                if (resp!=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")){
                            JSONArray msgarray = respobj.getJSONArray("msg");

                            for (int i = 0; i<msgarray.length(); i++){
                                JSONObject obj = msgarray.optJSONObject(i).getJSONObject("Notification");
                                ModelClass model = new ModelClass();

                                model.datetime = ""+obj.optString("created");
                                String date = Functions.Change_date_format("yyyy-MM-dd hh:mm:ss",
                                        "EEE, MMM dd",""+obj.optString("created"));
                                String month = Functions.Change_date_format("yyyy-MM-dd hh:mm:ss",
                                        "MMMM", model.datetime);
                                model.comparing_date = date;
                                model.comparing_month = month;
                                model.notification = ""+obj.optString("text");

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
        adp = new Adapter(getContext(),list);
        rv_notification.setAdapter(adp);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_ll:
                getActivity().onBackPressed();
                break;
        }
    }

}
