package com.dinosoftlabs.uber.HomeScreen.Main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.Main.ReasonCancelRide.Reason_cancel_ride_F;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.Interfaces.Route_CallBack;
import com.dinosoftlabs.uber.Map_classes.MapWorker;
import com.dinosoftlabs.uber.R;
import com.dinosoftlabs.uber.Users_Chat.Chat_Activity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.maps.model.DirectionsResult;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;
import com.DMS_Cust.DMS.HelpingClasses.Functions;
import com.DMS_Cust.DMS.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.dinosoftlabs.uber.CodeClasses.Variables.mPrefs;

public class Confirm_ride_Captain_F extends AppCompatActivity implements
        OnMapReadyCallback,
        OnClickListener{

    private Context context;

    private Boolean map_check = false;
    private BottomSheetBehavior bts_behavior;


    private LinearLayout ll_captain_contact, ll_progressbar, ll_chat_open, ll_call_bts;

    private RelativeLayout rl_captain_info,rl_share;

    private CardView cv_manage_ride;

    private ImageView iv_current_location, iv_change_map_type;

    private TextView tv_pickup_title_bts, tv_pickup_address_bts, tv_dropoff_Title_bts,
            tv_dropoff_address_bts, tv_captain_car_bts, tv_vehicle_name_bts, tv_payment_type,
            tv_finding_captain, tv_captain_coming, tv_vehical_info, tv_driver_name;

    private String distance, driver_fname, driver_lname, driver_phone_no, driver_image, call,
            driver_ride_response, user_ride_response, on_the_way, pickup_lat, pickup_long,
            destination_lat, destination_long, request="0", ride_type_id,
            arrive_on_location, start_ride, end_ride, request_id, driver_id, reason, collect_payment;

    private String pickup_address_loc, dropoff_address_loc;

    //Vehicle_info
    private String vehicle_make, vehicle_model,year,license_plate, vehicle_color,lat,lng;



    private MapView mapView;
    private GoogleMap googleMap;
    private double latitude,longitude;

    private MapWorker mapWorker;
    private Marker pickup_marker, dropoff_marker, driver_marker;
    private LatLng latLng, pickup_latlng, dropoff_latlng, driver_latlng;
    private Bitmap pickup_marker_bitmap, dropoof_marker_bitmap, driver_marker_bitmap;


    private Dialog dialog;
    private ProgressBar progressBar;
    private float value = 0;

    private String[] list = {"I don't need a ride anymore",
            "I want to change my booking details",
            "Car / Captain not suitable",
            "I couldn't contact the Captain",
            "Captain isn't moving, or asked me to cancel",
            "Captain couldn't find my location",
            "Captain is too far away",
            "Other"};


    DatabaseReference root_ref;



    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String type = intent.getExtras().getString("type");

            if(type != null && type.equals("request_accepted")) {

                CallApi_showActiveRequest(Variables.mPrefs.getString(Variables.User_id, "null"));

            }else if(type != null && type.equals("ride_cancel")) {

                if(call.equals("map")){

                    startActivity(new Intent(Confirm_ride_Captain_F.this, MainActivity.class));
                    finish();

                }else if(call.equals("splash")) {

                    startActivity(new Intent(Confirm_ride_Captain_F.this, MainActivity.class));
                    finish();

                }
            }
            else if (type != null && type.contains("collect_payment"))
                Complete_Ride_Dialog(Confirm_ride_Captain_F.this);
            else
                CallApi_showActiveRequest(Variables.mPrefs.getString(Variables.User_id,"null"));

        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_ride_captain_f);

        context = getApplicationContext();

        root_ref = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = getIntent().getExtras();
        call = bundle.getString("call");

        Variables.user_id = mPrefs.getString(Variables.User_id,"");


        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);


        METHOD_setupMap();

        METHOD_findviewbyId();

    }








    private void METHOD_setupMap(){
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean GpsStatus = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        @SuppressLint("MissingPermission")
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            latLng = new LatLng(latitude, longitude);
            setupMapIfNeeded();
        }


        dropoof_marker_bitmap = Bitmap.createScaledBitmap(((BitmapDrawable) context.getResources()
                        .getDrawable(R.drawable.ic_ic_dropoffmarker)).getBitmap(),
                Functions.convertDpToPx(context,40),
                Functions.convertDpToPx(context,40), false);
        pickup_marker_bitmap = Bitmap.createScaledBitmap(((BitmapDrawable) context.getResources()
                        .getDrawable(R.drawable.ic_pickup_marker)).getBitmap(),
                Functions.convertDpToPx(context,40),
                Functions.convertDpToPx(context,40), false);
        driver_marker_bitmap = Bitmap.createScaledBitmap(((BitmapDrawable) context.getResources()
                        .getDrawable(R.drawable.ic_car)).getBitmap(),
                Functions.convertDpToPx(context,40),
                Functions.convertDpToPx(context,40), false);

    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        mapWorker = new MapWorker(context,googleMap);

        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 18));

        if(pickup_latlng == null)
            CallApi_showActiveRequest(mPrefs.getString(Variables.User_id,""));


    }






    private void METHOD_findviewbyId(){
        LinearLayout captain_rkl = (LinearLayout) findViewById(R.id.captain_rkl_id);
        ll_captain_contact = findViewById(R.id.ll_captain_contact);
        ll_progressbar = findViewById(R.id.ll_progressbar);
        ll_chat_open = findViewById(R.id.ll_chat_open);
        ll_chat_open.setOnClickListener(this);
        ll_call_bts = findViewById(R.id.ll_call_bts);
        ll_call_bts.setOnClickListener(this);


        RoundKornerLinearLayout cancel_bts_rkl = findViewById(R.id.cancel_bts_rkl);
        cancel_bts_rkl.setOnClickListener(this);
        rl_captain_info = findViewById(R.id.rl_captain_info);
        rl_share = findViewById(R.id.rl_share_details);
        rl_share.setOnClickListener(this);


        cv_manage_ride = (CardView) findViewById(R.id.cv_manage_ride);
        cv_manage_ride.setVisibility(View.VISIBLE);


        tv_pickup_title_bts = (TextView) findViewById(R.id.tv_pickup_title_bts);
        tv_pickup_address_bts = findViewById(R.id.tv_pickup_address_bts);
        tv_dropoff_Title_bts = findViewById(R.id.tv_dropoff_Title_bts);
        tv_dropoff_address_bts = findViewById(R.id.tv_dropoff_address_bts);
        tv_vehical_info = findViewById(R.id.tv_vehical_info);
        tv_captain_car_bts = findViewById(R.id.tv_captain_car_id);
        tv_vehicle_name_bts = findViewById(R.id.tv_vehicle_name);
        tv_payment_type = findViewById(R.id.tv_payment_type);
        tv_captain_coming = findViewById(R.id.tv_captain_coming);
        tv_finding_captain = findViewById(R.id.tv_finding_captain);
        tv_driver_name = findViewById(R.id.tv_driver_name);



        iv_change_map_type = (ImageView) findViewById(R.id.iv_change_map_type);
        iv_change_map_type.setOnClickListener(this);

        iv_current_location = findViewById(R.id.iv_current_location);
        iv_current_location.setOnClickListener(this);


        bts_behavior = BottomSheetBehavior.from(captain_rkl);
        bts_behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
        bts_behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {

            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN)
                    bts_behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {}

        });

    }







    private void setupMapIfNeeded(){
        //Build the map.
        if(googleMap==null) {
            // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
            MapsInitializer.initialize(context);
            mapView.onResume();

            mapView.getMapAsync(this);
        }
    }







    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_change_map_type:
                METHOD_changeMapType();
                break;



            case R.id.iv_current_location:
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
                break;



            case R.id.cancel_bts_rkl:
                METHOD_cancelRide();
                break;


            case R.id.ll_chat_open:
                METHOD_openChatActivity();
                break;


            case R.id.rl_share_details:
                String sharing_details = "Hey I booked a Careem! The Captain is "+driver_fname+" "+driver_lname+", driving a "+vehicle_color+" "+vehicle_make+" "+vehicle_model+", "+license_plate+", mobile number is +971557587722. Follow the link to track the ride ";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sharing_details);
                sendIntent.setType("text/plain");
                sendIntent = Intent.createChooser(sendIntent, null);
                startActivity(sendIntent);

                break;



            case R.id.ll_call_bts:
                if(driver_phone_no != null){
                    Intent intent = new Intent(Intent.ACTION_DIAL,
                            Uri.fromParts("tel", driver_phone_no, null));
                    startActivity(intent);
                }
                break;
        }
    }



    private void METHOD_changeMapType(){
        if(map_check){
            iv_change_map_type.setImageResource(R.drawable.notmal_map);
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map_check = false;
        }else {
            iv_change_map_type.setImageResource(R.drawable.earth_map);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            map_check = true;
        }
    }



    private void METHOD_openChatActivity() {
        Bundle args = new Bundle();
        args.putString("senderid", mPrefs.getString(Variables.User_id,"null"));
        args.putString("Receiverid", driver_id);
        args.putString("Receiver_name", driver_fname + " " + driver_lname);
        args.putString("Receiver_pic", driver_image);

        Chat_Activity f = new Chat_Activity();
        f.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
        ft.replace(R.id.conformation_frg, f).addToBackStack(null).commit();
    }




    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("request_responce");
        registerReceiver(broadcastReceiver,intentFilter);
    }


    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }







    private void CallApi_showActiveRequest(String user_id) {
        String token = FirebaseInstanceId.getInstance().getToken();
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", user_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ApiRequest.Call_Api(getApplicationContext(), Api_url.url_showActiveRequest, sendobj, new Callback() {

            @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
            @Override
            public void Responce(String resp) {
                if (resp != null) {

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")) {
                            JSONObject msgobj = respobj.getJSONObject("msg");
                            JSONObject reqobj = msgobj.getJSONObject("Request");

                            driver_id = ""+reqobj.optString("driver_id");

                            driver_ride_response = ""+reqobj.optString("driver_ride_response");
                            user_ride_response = ""+reqobj.optString("user_ride_response");
                            on_the_way = ""+reqobj.optString("on_the_way");
                            arrive_on_location = ""+reqobj.optString("arrive_on_location");
                            start_ride = ""+reqobj.optString("start_ride");
                            end_ride = ""+reqobj.optString("end_ride");

                            pickup_lat = ""+reqobj.optString("pickup_lat");
                            pickup_long = ""+reqobj.optString("pickup_long");
                            destination_lat = ""+reqobj.optString("destination_lat");
                            destination_long = ""+reqobj.optString("destination_long");

                            request = ""+reqobj.optString("request");
                            request_id = ""+reqobj.optString("id");

                            if (reqobj.optString("payment_type").equals("cash"))
                                tv_payment_type.setText("Cash");
                            else
                                tv_payment_type.setText("Card");

                            //tv_payment_type.setText(""+reqobj.optString("payment_type"));


                            JSONObject driverobj = msgobj.getJSONObject("Driver");

                            driver_fname = ""+driverobj.optString("first_name");
                            driver_lname = ""+driverobj.optString("last_name");
                            driver_phone_no = ""+driverobj.optString("phone_no");
                            driver_image = ""+driverobj.optString("image");


                            JSONObject vehicleobj = msgobj.getJSONObject("Vehicle");

                            ride_type_id = ""+vehicleobj.optString("id");
                            vehicle_make = ""+vehicleobj.optString("make");
                            vehicle_model = ""+vehicleobj.optString("model");
                            year = ""+vehicleobj.optString("year");
                            license_plate = ""+vehicleobj.optString("license_plate");
                            vehicle_color = ""+vehicleobj.optString("color");
                            lat = ""+vehicleobj.optString("lat");
                            lng = ""+vehicleobj.optString("long");

                            Log.d("resp", ""+vehicleobj.optJSONObject("RideType")
                                    .optString("name"));

                            tv_vehicle_name_bts.setText(""+vehicleobj.optJSONObject("RideType")
                                    .optString("name"));

                            METHOD_setAddresses(lat,lng);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }





    private void METHOD_setAddresses(String lat, String lng) {
        driver_latlng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        pickup_latlng = new LatLng(Double.parseDouble(pickup_lat),Double.parseDouble(pickup_long));
        dropoff_latlng = new LatLng(Double.parseDouble(destination_lat),Double.parseDouble(destination_long));

        Address pickup_address = address_cal(new
                LatLng(Double.parseDouble(pickup_lat),Double.parseDouble(pickup_long)));
        Address dropoff_address = address_cal(new
                LatLng(Double.parseDouble(destination_lat),Double.parseDouble(destination_long)));

        if(pickup_address != null && dropoff_address != null) {
            pickup_address_loc = pickup_address.getAddressLine(0);
            dropoff_address_loc = dropoff_address.getAddressLine(0);
        }

        if(pickup_marker == null && pickup_latlng !=null)
            pickup_marker = mapWorker.add_marker(pickup_latlng, pickup_marker_bitmap);
        else {
            pickup_marker.remove();
            pickup_marker = mapWorker.add_marker(pickup_latlng, pickup_marker_bitmap);
        }

        if(driver_marker == null && mapWorker !=null)
            driver_marker = mapWorker.add_marker(driver_latlng, driver_marker_bitmap);
        else {
            driver_marker.remove();
            driver_marker = mapWorker.add_marker(driver_latlng, driver_marker_bitmap);
        }

        METHOD_changeRideStatus();
    }



    private void METHOD_adjustViews(String ride_status, String pickup_address_locality,
                                    String dropoff_address_locality) {

        if (ride_status.contains("1")){
            ll_captain_contact.setVisibility(View.VISIBLE);
            rl_captain_info.setVisibility(View.VISIBLE);
            tv_captain_coming.setVisibility(View.VISIBLE);
            tv_finding_captain.setVisibility(View.GONE);
            ll_progressbar.setVisibility(View.GONE);
        }else {
            ll_captain_contact.setVisibility(View.GONE);
            rl_captain_info.setVisibility(View.GONE);
            tv_captain_coming.setVisibility(View.GONE);
            tv_finding_captain.setVisibility(View.VISIBLE);
            ll_progressbar.setVisibility(View.VISIBLE);
        }


        tv_pickup_title_bts.setText(pickup_address_locality);
        tv_pickup_address_bts.setText(pickup_address_locality);
        tv_dropoff_Title_bts.setText(dropoff_address_locality);
        tv_dropoff_address_bts.setText(dropoff_address_locality);

        tv_driver_name.setText(driver_fname +" "+ driver_lname);

        tv_vehical_info.setText(vehicle_color +" "+ vehicle_model +" "+ year);
        tv_captain_car_bts.setText(license_plate);

    }





    private void METHOD_changeRideStatus() {
        if(!(request.equals("0"))){

            METHOD_adjustViews("1", pickup_address_loc, dropoff_address_loc);

            if((pickup_latlng != null && driver_latlng != null) &&
                    ll_captain_contact.getVisibility() == View.GONE ) {
                drawRoute(pickup_latlng, driver_latlng);
            }

            if(end_ride.equals("1"))
                tv_captain_coming.setText("Your destination arrived, Thank you for using");
            else if(start_ride.equals("1")){
                tv_captain_coming.setText("Started the ride");
                cv_manage_ride.setVisibility(View.GONE);

                METHOD_updateDriverLatlng();
            }
            else if(arrive_on_location.equals("1"))
                tv_captain_coming.setText("Your Captain is Arrived");
            else if(on_the_way.equals("1"))
                tv_captain_coming.setText("Get Ready, Your Captain is on the way");
            else if(request.equals("1"))
                tv_captain_coming.setText("Get Ready, Your Captain has accepted your ride request");

        }else {

            METHOD_adjustViews("0", pickup_address_loc, dropoff_address_loc);

            tv_pickup_title_bts.setText(pickup_address_loc);
            tv_pickup_address_bts.setText(pickup_address_loc);
            tv_dropoff_Title_bts.setText(dropoff_address_loc);
            tv_dropoff_address_bts.setText(dropoff_address_loc);

            if(pickup_marker == null && pickup_latlng !=null)
                pickup_marker = mapWorker.add_marker(pickup_latlng, pickup_marker_bitmap);
            else {
                pickup_marker.remove();
                pickup_marker = mapWorker.add_marker(pickup_latlng, pickup_marker_bitmap);
            }

            if(dropoff_marker == null && dropoff_latlng !=null)
                dropoff_marker = mapWorker.add_marker(dropoff_latlng, dropoof_marker_bitmap);
            else {
                dropoff_marker.remove();
                dropoff_marker = mapWorker.add_marker(dropoff_latlng, dropoof_marker_bitmap);
            }


            LatLngBounds.Builder latlngBuilder = new LatLngBounds.Builder();
            if(pickup_marker.getPosition() != null && dropoff_marker.getPosition() !=null){

                latlngBuilder.include(pickup_marker.getPosition());
                latlngBuilder.include(dropoff_marker.getPosition());

                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBuilder.build(), 300));

            }
        }
    }



    private void METHOD_updateDriverLatlng() {
        Log.d("resp", "METHOD_updateDriverLatlng");

        if (start_ride.equals("1")) {
            root_ref.child("Drivers").child(driver_id).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    double lat = (Double) dataSnapshot.child("l").child("0").getValue();
                    double lng = (Double) dataSnapshot.child("l").child("1").getValue();

                    METHOD_rotateMarker(lat,lng);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void METHOD_rotateMarker(Double lat, Double lng) {
        mapWorker.rotateMarker(driver_marker, new LatLng(lat,lng),
                (float) Functions.getBearingBetweenTwoPoints1(new
                                LatLng(driver_marker.getPosition().latitude,
                                driver_marker.getPosition().longitude),
                        new LatLng(lat,lng)), 1000);

        mapWorker.animateMarkerTo(driver_marker, lat, lng);
    }









    private void METHOD_cancelRide(){
        AlertDialog.Builder builder = new AlertDialog
                .Builder(Confirm_ride_Captain_F.this, , R.style.MyDialogStyle);
        builder.setTitle("Cancel Ride");
        builder.setMessage("Are you sure you want to cancel?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Reason_cancel_ride_F f = new Reason_cancel_ride_F(new Fragment_CallBack() {

                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        if (bundle != null){
                            reason = bundle.getString("key");

                            CallApi_rideCancelled(mPrefs.getString(Variables.User_id,""),
                                    request_id, reason);
                        }
                    }

                });
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
                ft.replace(R.id.conformation_frg, f).addToBackStack(null).commit();

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        });


        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog1.getButton(androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE)
                        .setTextColor(getResources().getColor(R.color.newColorGreen));
                alertDialog1.getButton(AlertDialog.BUTTON_NEGATIVE)
                        .setTextColor(getResources().getColor(R.color.newColorLightHint));
            }

        });
        alertDialog1.show();
    }








    private void CallApi_rideCancelled(String user_id, String request_id, String reason) {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", user_id);
            sendobj.put("request_id", request_id);
            sendobj.put("reason", reason);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Functions.ShowProgressLoader(Confirm_ride_Captain_F.this, false, false);
        ApiRequest.Call_Api(getApplicationContext(), Api_url.url_rideCancelled, sendobj, new Callback() {

            @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
            @Override
            public void Responce(String resp) {
                Functions.CancelProgressLoader();
                if (resp != null) {

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")) {
                            startActivity(new Intent(Confirm_ride_Captain_F.this,
                                    MainActivity.class));

                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }








    private Address address_cal(LatLng latLng){

        Geocoder geoCoder = new Geocoder(this);
        double latitude = latLng.latitude;
        double longitude = latLng.longitude;

        List<Address> addresses = null;

        try {
            addresses = geoCoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses != null &&addresses.size() > 0)
            return addresses.get(0);

        return null;
    }








    private void drawRoute(LatLng pick_up, LatLng drop_off) {
        if (mapWorker != null && (pick_up != null && drop_off != null)){
            mapWorker.Get_Direction(pick_up,
                    drop_off, new Route_CallBack() {
                        @Override
                        public void Responce(DirectionsResult directionsResult) {

                            if (directionsResult != null) {
                                mapWorker.addPolyline(directionsResult, googleMap);

                                distance = mapWorker.get_distance_from_route(directionsResult);
                            }
                        }
                    });


            LatLngBounds.Builder latlngBuilder = new LatLngBounds.Builder();

            latlngBuilder.include(pick_up);
            latlngBuilder.include(drop_off);


            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBuilder.build(), 300));
        } else {
            Functions.ShowToast(this, ""+getString(R.string.missing_location));
        }

        if (dropoff_marker != null)
            dropoff_marker.remove();

    }








    private void Complete_Ride_Dialog(Activity activity){
        dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_rating,null);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        RatingBar rating = view.findViewById(R.id.rating_id);
        TextView tv_driver_name = view.findViewById(R.id.tv_driver_name);
        tv_driver_name.setText(this.tv_driver_name.getText().toString());
        Button btn_ok = view.findViewById(R.id.btn_ok);
        EditText et_comment = view.findViewById(R.id.et_comment);
        progressBar = view.findViewById(R.id.progressbar);

        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                value = rating;
            }
        });


        btn_ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CallApi_giveRatingsToDriver(Variables.user_id, request_id, Float.toString(value),
                        driver_id,""+et_comment.getText().toString());

                btn_ok.setVisibility(View.GONE);
                progressBar.getIndeterminateDrawable()
                        .setColorFilter(ContextCompat.getColor(getApplicationContext(),
                                R.color.newColorBlack), PorterDuff.Mode.SRC_IN );
                progressBar.setVisibility(View.VISIBLE);
            }
        });



        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        //set custom width to dialog box
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayWidth = displayMetrics.widthPixels;

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        int dialogWindowWidth = (int)(displayWidth * 0.9f);
        layoutParams.width = dialogWindowWidth;

        dialog.getWindow().setAttributes(layoutParams);
    }







    private void CallApi_giveRatingsToDriver(String user_id, String trip_id, String star,
                                             String driver_id, String comment) {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("request_id", trip_id);
            sendobj.put("user_id", user_id);
            sendobj.put("driver_id", driver_id);
            sendobj.put("star", star);
            sendobj.put("comment", comment);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ApiRequest.Call_Api(getApplicationContext(), Api_url.url_giveRatingsToDriver, sendobj, new Callback() {
            @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
            @Override
            public void Responce(String resp) {
                if (resp != null) {

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200"))
                            dialog.dismiss();


                        startActivity(new Intent(Confirm_ride_Captain_F.this, MainActivity.class));
                        finish();

                        cv_manage_ride.setVisibility(View.VISIBLE);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}