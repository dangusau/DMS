package com.dinosoftlabs.uber.HomeScreen.YourTrip;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.YourTrip.Trip_Details.TripDetails;
import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.R;
import com.DMS_Cust.DMS.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class YourTrips_F extends RootFragment implements View.OnClickListener {

    private View view;
    private LinearLayout ll_back;

    private RecyclerView rv;
    private YourTrips_Adapter adp;
    private List<YourTrips_Model> list;

    public YourTrips_F() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.trips_f, container, false);

        Variables.user_id = Variables.mPrefs.getString(Variables.User_id,"null");


        METHOD_findviewbyid();


        return view;

    }







    protected void METHOD_findviewbyid(){
        ll_back = (LinearLayout) view.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);

        rv = (RecyclerView) view.findViewById(R.id.rv_history);
        list = new ArrayList<>();


        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setHasFixedSize(false);

        CallApi_Of_yourRides();

    }







    private void CallApi_Of_yourRides() {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_showCompletedPastTrips, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                if (resp!=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if(respobj.getString("code").equals("200")){
                            JSONArray msg = respobj.optJSONArray("msg");

                            for (int i=0; i<msg.length(); i++){

                                JSONObject data = msg.getJSONObject(i);
                                JSONObject trip = data.optJSONObject("Trip");
                                JSONObject user = data.optJSONObject("User");
                                JSONObject driver = data.optJSONObject("Driver");
                                JSONArray rating_array = data.optJSONArray("UserRating");

                                YourTrips_Model model = new YourTrips_Model();

                                model.id = ""+trip.optString("id");
                                model.driver_id = ""+trip.optString("driver_id");
                                model.vehicle_id = ""+trip.optString("vehicle_id");
                                model.request_id = ""+trip.optString("request_id");
                                model.pickup_location = ""+trip.optString("pickup_location");
                                model.destination_location = ""+trip.optString("destination_location");
                                model.pickup_lat = ""+trip.optString("pickup_lat");
                                model.pickup_long = ""+trip.optString("pickup_long");
                                model.destination_lat = ""+trip.optString("destination_lat");
                                model.destination_long = ""+trip.optString("destination_long");
                                model.pickup_datetime = Functions.Change_date_format("yyyy-MM-dd HH:mm:ss",
                                        "dd MMM hh:mm a", ""+trip.optString("pickup_datetime"));
                                model.destination_datetime = ""+trip.optString("destination_datetime");
                                model.accepted_by_rider = ""+trip.optString("accepted_by_rider");
                                model.cancelled_by_rider = ""+trip.optString("cancelled_by_rider");
                                model.cancelled_by_user = ""+trip.optString("cancelled_by_user");
                                model.completed = ""+trip.optString("completed");
                                model.final_fare = ""+trip.optJSONObject("TripPayment")
                                        .optString("final_fare");
                                model.pay_type = ""+trip.optJSONObject("TripPayment")
                                        .optString("payment_type");
                                model.pay_from_wallet = ""+trip.optJSONObject("TripPayment")
                                        .optString("payment_from_wallet");
                                model.pay_collect_from_wallet = ""+trip.optJSONObject("TripPayment")
                                        .optString("payment_collect_from_wallet");
                                model.pay_collect_from_card = ""+trip.optJSONObject("TripPayment")
                                        .optString("payment_collect_from_card");
                                model.pay_collect_from_cash = ""+trip.optJSONObject("TripPayment")
                                        .optString("payment_collect_from_cash");


                                model.driver_email=""+driver.optString("email");
                                model.driver_first_name=""+driver.optString("first_name");
                                model.driver_last_name=""+driver.optString("last_name");
                                model.driver_phone_no=""+driver.optString("phone_no");
                                model.driver_image=""+driver.optString("image");
                                model.driver_lat=""+driver.optString("lat");
                                model.driver_lng=""+driver.optString("long");

                                if (rating_array.length()>0){
                                    String rating = rating_array.optJSONObject(0).getString("star");
                                    if (rating.equals("null") || rating.equals(""))
                                        model.trip_rating = "0";
                                    else
                                        model.trip_rating = rating;
                                }

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







    private void METHOD_setAdapter() {
        adp = new YourTrips_Adapter(getContext(), list, new Adapter_ClickListener() {
            @Override
            public void On_Item_Click(int postion, Object Model, View view) {
                YourTrips_Model model = (YourTrips_Model) Model;

                TripDetails f = new TripDetails();
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
                Bundle bundle = new Bundle();
                bundle.putSerializable("model", model);
                f.setArguments(bundle);

                ft.replace(R.id.fl_id, f).addToBackStack(null).commit();
            }

            @Override
            public void On_Item_Long_Click(int postion, Object Model, View view) {

            }
        });

        rv.setAdapter(adp);
    }







    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_back:
                getActivity().onBackPressed();
                break;
        }
    }
}
