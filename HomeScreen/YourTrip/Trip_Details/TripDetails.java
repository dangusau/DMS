package com.dinosoftlabs.uber.HomeScreen.YourTrip.Trip_Details;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.YourTrip.YourTrips_Model;
import com.dinosoftlabs.uber.Interfaces.Route_CallBack;
import com.dinosoftlabs.uber.Map_classes.MapWorker;
import com.dinosoftlabs.uber.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.model.DirectionsResult;


public class TripDetails extends RootFragment implements View.OnClickListener {

    private SimpleDraweeView iv_map,iv_driver_pic;
    private TextView tv_amount,tv_cash_paid,tv_driver_name,tv_wallet_credit,
            tv_vehicle_details,tv_vehicle_number,tv_datetime;

    private RelativeLayout rl_wallet_credit;

    private YourTrips_Model model;
    private String map_path;
    private View v;

    public TripDetails() {
        // Required empty public constructor
    }


    private MapWorker mapWorker;
    private LatLng pickup_latlng,dropoff_latlng;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.trip_details_f, container, false);

        Bundle bundle = getArguments();
        if (bundle !=null){
            Object obj = bundle.getSerializable("model");
            model = (YourTrips_Model) obj;
        }

        //METHOD_drawMappicturewithroute();
        METHOD_findviewbyid();

        return v;
    }






    private void METHOD_drawMappicturewithroute(){
        mapWorker = new MapWorker(getContext());
        pickup_latlng = new LatLng(Double.parseDouble(model.pickup_lat),Double.parseDouble(model.pickup_long));
        dropoff_latlng=new LatLng(Double.parseDouble(model.destination_lat),Double.parseDouble(model.destination_long));
        mapWorker.Get_Direction(pickup_latlng, dropoff_latlng, new Route_CallBack() {
            @Override
            public void Responce(DirectionsResult directionsResult) {

                LatLng midLatLng= Functions.midPoint(pickup_latlng.latitude,pickup_latlng.longitude,dropoff_latlng.latitude,dropoff_latlng.longitude);
                map_path = "https://maps.googleapis.com/maps/api/staticmap?" +
                        "center="+midLatLng.latitude+","+midLatLng.longitude+
                        "&size=1000x500" +
                        "&maptype=roadmap" +
                        "&markers=size:mid%7Ccolor:0x1036fc%7Clabel:P%7C".concat(model.pickup_lat).concat(",").concat(model.pickup_long) +
                        "&markers=size:mid%7Ccolor:0x1036fc%7Clabel:D%7C".concat(model.destination_lat).concat(",").concat(model.destination_long) +
                        "&path=color:0x0000ff%7Cweight:5%7Cenc:" +
                        directionsResult.routes[0].overviewPolyline.getEncodedPath() +
                        "&format=jpg" +
                        "&key=".concat(getResources().getString(R.string.google_api_key));

            }
        });
    }






    private void METHOD_findviewbyid() {
        ImageView iv_back = v.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this::onClick);

        iv_map = v.findViewById(R.id.iv_map);
        //Uri uri = Uri.parse(map_path);
        //iv_map.setImageURI(uri);
        iv_driver_pic = v.findViewById(R.id.iv_driver_pic);

        tv_datetime = v.findViewById(R.id.tv_datetime);
        tv_datetime.setText(model.pickup_datetime);

        tv_amount = v.findViewById(R.id.tv_amount);
        tv_amount.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+model.final_fare);
        tv_cash_paid = v.findViewById(R.id.tv_cash_paid);
        tv_cash_paid.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+model.pay_collect_from_cash);
        tv_wallet_credit = v.findViewById(R.id.tv_wallet_credit);

        rl_wallet_credit = v.findViewById(R.id.rl_wallet_credit);

        tv_driver_name = v.findViewById(R.id.tv_driver_name);
        tv_driver_name.setText(model.driver_first_name+" "+model.driver_last_name);
        iv_driver_pic = v.findViewById(R.id.iv_driver_pic);

        tv_vehicle_number = v.findViewById(R.id.tv_vehicle_number);
        tv_vehicle_details = v.findViewById(R.id.tv_vehicle_details);

        RatingBar driver_rating = v.findViewById(R.id.driver_rating);
        driver_rating.setRating(Float.parseFloat((model.trip_rating)));

    }





    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                getActivity().onBackPressed();
                break;
        }
    }
}
