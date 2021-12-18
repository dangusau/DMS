package com.dinosoftlabs.uber.HomeScreen.Main;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Cash_BottomSheet_F;
import com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Details_BottomSheet_F;
import com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Edit_Profile_BottomSheet_F;
import com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Promo_BottomSheet_F;
import com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Select_Vehicle.Select_vehicle_Bts_F;
import com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Select_Vehicle.Vehicle_Model_Class;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.Pay_F;
import com.dinosoftlabs.uber.HomeScreen.Help.Help_F;
import com.dinosoftlabs.uber.HomeScreen.Notifications.Notifications_F;
import com.dinosoftlabs.uber.HomeScreen.Packages.Packages_F;
import com.dinosoftlabs.uber.HomeScreen.Search_Location.Save_Location_F;
import com.dinosoftlabs.uber.HomeScreen.Search_Location.Save_Location_Model;
import com.dinosoftlabs.uber.HomeScreen.Search_Location.Search_F;
import com.dinosoftlabs.uber.HomeScreen.Settings.Settings_F;
import com.dinosoftlabs.uber.HomeScreen.YourTrip.YourTrips_F;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.Interfaces.LocationService_callback;
import com.dinosoftlabs.uber.Interfaces.Route_CallBack;
import com.dinosoftlabs.uber.Map_classes.GpsUtils;
import com.dinosoftlabs.uber.Map_classes.MapWorker;
import com.dinosoftlabs.uber.R;
import com.dinosoftlabs.uber.Service_class.GetLocation_Service;
import com.facebook.drawee.view.SimpleDraweeView;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.maps.model.DirectionsResult;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;
import com.jcminarro.roundkornerlayout.RoundKornerRelativeLayout;
import com.DMS_Cust.DMS.HelpingClasses.Functions;
import com.DMS_Cust.DMS.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main_Map_F extends RootFragment implements View.OnClickListener,
        OnMapReadyCallback,
        LocationService_callback {

    private Context context;
    private View view;


    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference("Current Location");
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("path/to/geofire");

    private String distance, payment_type="cash", payment_method_id="0", wallet;
    private Boolean map_type = false;



    private RelativeLayout rl_pick_drop,rl_cash;
    private RoundKornerRelativeLayout rkl_pickup_loc,rkl_map_type,rkl_current_location;
    private RoundKornerLinearLayout rkl_drop_loc;

    private LinearLayout ll_menu;
    private LinearLayout ll_back;


    private Button btn_confirm_pickup,btn_confirm_dropoff,btn_chalo;

    private TextView tv_details,tv_select_dropoff_loc,tv_dropoff_loc,tv_skip,
            tv_Vehicle_name_when_chalo_visible,tv_estimt_fare,tv_username,tv_title_pickup,
            tv_address_pickup,tv_title_dropof,tv_address_dropof,tv_vehicle_name,tv_Payment_type,
            tv_credit_remaining,tv_time_marker,tv_vehicle_time;


    SimpleDraweeView iv_profile;
    Bitmap bitmap_profpic;



    private DrawerLayout drawer;
    private ImageView iv_center_marker,iv_fav,iv_change_map_type;
    private Save_Location_Model model;

    private CardView chalo_details;
    private CardView select_vehicle_cv;
    private CardView location_cv_id;

    private String fname,lname,image;

    MapView mapFragment;
    private GoogleMap mMap;

    public static LatLng latLng;
    private int i =0;
    private GoogleMap.OnCameraIdleListener onCameraIdleListener;

    private MapWorker mapWorker;
    private Marker pickup_marker,dropoff_marker;
    private LatLng pickup_latlong,drop_off_latlong;
    private Bitmap pick_up_marker,drop_oof_marker;
    private GeoFire geoFire;

    private double d = 0.0;
    private double n = 0.0;
    private String tag;



    private Vehicle_Model_Class selected_vehical_model;
    private List<Vehicle_Model_Class> vehicle_list = new ArrayList<>();
    private ArrayList<LatLng> saved_loc_list;

    public static String which_screen_open = "";
    Gson gson;



    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.map_f, container, false);
        context = getActivity();

        gson = new Gson();

        fname = Variables.mPrefs.getString(Variables.fname, "null");
        lname = Variables.mPrefs.getString(Variables.lname, "null");
        image = Variables.mPrefs.getString(Variables.image, "null");

        Variables.user_id = Variables.mPrefs.getString(Variables.User_id,"null");

        mapFragment = view.findViewById(R.id.map);
        mapFragment.onCreate(savedInstanceState);



        METHOD_setupMap();

        METHOD_findviewbyid();


        return view;

    }






    private void enable_permission(){
        LocationManager locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!GpsStatus) {
            new GpsUtils(getContext()).turnGPSOn(new GpsUtils.onGpsListener() {
                @Override
                public void gpsStatus(boolean isGPSEnable) {
                    enable_permission();
                }
            });
        }
        else if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Bind_Location_service();

            mapFragment.onResume();
            mapFragment.getMapAsync(this);
            METHOD_setupMap();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    Variables.permission_location);
        }
    }






    private void METHOD_setupMap() {
        //Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(context);
        mapFragment.onResume();

        drop_oof_marker = Bitmap.createScaledBitmap(((BitmapDrawable) context.getResources()
                .getDrawable(R.drawable.ic_ic_dropoffmarker)).getBitmap(),
                Functions.convertDpToPx(context,40),
                Functions.convertDpToPx(context,40), false);
        pick_up_marker = Bitmap.createScaledBitmap(((BitmapDrawable) context.getResources()
                        .getDrawable(R.drawable.ic_pickup_marker)).getBitmap(),
                Functions.convertDpToPx(context,40),
                Functions.convertDpToPx(context,40), false);

        configureCameraIdle();
    }







    private void METHOD_findviewbyid(){
        ImageView iv_current_location = (ImageView) view.findViewById(R.id.iv_current_location);
        iv_current_location.setOnClickListener(this);

        iv_change_map_type = view.findViewById(R.id.iv_change_map_type);
        iv_change_map_type.setOnClickListener(this);

        iv_fav = view.findViewById(R.id.fav_iv);
        iv_fav.setOnClickListener(this);
        saved_loc_list = new ArrayList<>();

        iv_center_marker = view.findViewById(R.id.iv_center_marker);






        RelativeLayout car_rl_layout = (RelativeLayout) view.findViewById(R.id.car_rl_layout);
        car_rl_layout.setOnClickListener(this);

        RelativeLayout cash_rl_layout = view.findViewById(R.id.cash_rl_layout);
        cash_rl_layout.setOnClickListener(this);

        RelativeLayout promo_rl_layout = view.findViewById(R.id.promo_rl_layout);
        promo_rl_layout.setOnClickListener(this);

        rl_cash = view.findViewById(R.id.rl_cash);
        rl_pick_drop = view.findViewById(R.id.rl_pick_drop_layout);




        rkl_pickup_loc = (RoundKornerRelativeLayout) view.findViewById(R.id.rkl_pickup_location);
        rkl_pickup_loc.setOnClickListener(this);

        rkl_drop_loc = view.findViewById(R.id.rkl_drop_loc);
        rkl_drop_loc.setOnClickListener(this);

        RoundKornerRelativeLayout rkl_select_vehicle = view.findViewById(R.id.rkl_select_vehicle);
        rkl_select_vehicle.setOnClickListener(this);

        rkl_current_location = view.findViewById(R.id.rkl_current_location);
        rkl_map_type = view.findViewById(R.id.rkl_map_type);




        ll_menu = (LinearLayout) view.findViewById(R.id.ll_menu);
        ll_menu.setOnClickListener(this);

        ll_back = view.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);

        LinearLayout ll_map = view.findViewById(R.id.ll_map);

        LinearLayout ll_pickup_loc = view.findViewById(R.id.current_loc_ll_id);
        ll_pickup_loc.setOnClickListener(this::onClick);




        btn_confirm_pickup = (Button) view.findViewById(R.id.btn_confirm_pickup);
        btn_confirm_pickup.setOnClickListener(this);

        btn_confirm_dropoff = view.findViewById(R.id.btn_dropoff);
        btn_confirm_dropoff.setOnClickListener(this);

        btn_chalo = view.findViewById(R.id.btn_chalo);
        btn_chalo.setOnClickListener(this);





        chalo_details = (CardView) view.findViewById(R.id.chalo_details_cv_id);
        select_vehicle_cv = view.findViewById(R.id.select_vehicle_rkl_cv_id);
        location_cv_id = view.findViewById(R.id.location_cv_id);




        tv_time_marker = (TextView) view.findViewById(R.id.tv_time_marker);
        tv_details = view.findViewById(R.id.details_id);
        tv_details.setOnClickListener(this);

        tv_skip = view.findViewById(R.id.tv_skip);
        tv_skip.setOnClickListener(this);

        tv_select_dropoff_loc = view.findViewById(R.id.tv_select_dropoff_loc);
        tv_dropoff_loc = view.findViewById(R.id.tv_dropoff_loc);
        TextView tv_select_pickup = view.findViewById(R.id.tv_select_pickup);
        tv_address_pickup = view.findViewById(R.id.tv_address_pickup);
        tv_title_pickup = view.findViewById(R.id.tv_title_pickup);
        tv_address_dropof = view.findViewById(R.id.tv_address_dropof);
        tv_title_dropof = view.findViewById(R.id.tv_title_dropof);
        tv_vehicle_name = view.findViewById(R.id.tv_vehicle_name);
        tv_Vehicle_name_when_chalo_visible = view.findViewById(R.id.tv_Vehicle_name_when_chalo_visible);
        tv_estimt_fare = view.findViewById(R.id.tv_estimt_fare);
        tv_vehicle_time = view.findViewById(R.id.tv_vehicle_time);


        tv_credit_remaining = view.findViewById(R.id.tv_credit_remaining);
        tv_Payment_type = view.findViewById(R.id.tv_Payment_type);




        //Drawer Resources Id's
        drawer = (DrawerLayout) view.findViewById(R.id.drawerlayout_id);

        RelativeLayout rl_user_info = (RelativeLayout) view.findViewById(R.id.rl_user_info);
        rl_user_info.setOnClickListener(this);

        iv_profile = view.findViewById(R.id.iv_profile);
        if (Variables.mPrefs.getBoolean(Variables.isloginwithfb,false)){
            Uri uri = Uri.parse(image);
            iv_profile.setImageURI(uri);
        }else {
            Uri uri = Uri.parse(Api_url.url_pic+image);
            iv_profile.setImageURI(uri);
        }
        iv_profile.setOnClickListener(this::onClick);

        tv_username = view.findViewById(R.id.tv_drawer_header_username_id);
        tv_username.setText(""+fname+" "+lname);


        LinearLayout ll_your_rides = (LinearLayout) view.findViewById(R.id.ll_your_rides);
        ll_your_rides.setOnClickListener(this);

        LinearLayout ll_notifi = view.findViewById(R.id.ll_notifi);
        ll_notifi.setOnClickListener(this);

        LinearLayout ll_pay = view.findViewById(R.id.ll_pay);
        ll_pay.setOnClickListener(this);

        LinearLayout ll_packages = view.findViewById(R.id.ll_packages);
        ll_packages.setOnClickListener(this);

        LinearLayout ll_help = view.findViewById(R.id.ll_help);
        ll_help.setOnClickListener(this);

        LinearLayout ll_settings = view.findViewById(R.id.ll_settings);
        ll_settings.setOnClickListener(this);

    }







    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap = googleMap;
        mapWorker = new MapWorker(context,mMap);



        //set clicklistener on Map Marker
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                if (marker.equals(dropoff_marker))
                    METHOD_selectLocation(false);
                else if (marker.equals(pickup_marker))
                    METHOD_selectLocation(true);


                return true;
            }
        });




        if (onCameraIdleListener != null)
            mMap.setOnCameraIdleListener(onCameraIdleListener);


        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setMapToolbarEnabled(false);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(false);

        Zoom_to_Current_location();

    }






    @Override
    public void onStart() {
        super.onStart();
        Bind_Location_service();
    }

    @Override
    public void onResume() {
        super.onResume();
        enable_permission();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Unbind_location_service();
        mapFragment.onDestroy();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapFragment.onLowMemory();
    }









    private void Bind_Location_service(){
        GetLocation_Service locationService = new GetLocation_Service();
        Intent mServiceIntent = new Intent(context, locationService.getClass());
        if (!Functions.isMyServiceRunning(context, locationService.getClass())) {
            context.startService(mServiceIntent);
        }
        context.bindService(mServiceIntent,mConnection , Context.BIND_AUTO_CREATE);
    }

    private void Unbind_location_service(){
        GetLocation_Service locationService = new GetLocation_Service();
        if (Functions.isMyServiceRunning(context, locationService.getClass())) {
            context.unbindService(mConnection);
        }
    }









    private GetLocation_Service mService;
    private boolean mBound = false;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            GetLocation_Service.LocalBinder binder = (GetLocation_Service.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            mService.setCallbacks(Main_Map_F.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }

    };



    @Override
    public void onDataReceived(LatLng data) {
        if(mMap!=null)
            mMap.setMyLocationEnabled(true);

        if(latLng==null)
            Zoom_to_Current_location();
    }



    private void Zoom_to_Current_location(){
         double lat=Double.parseDouble(Variables.mPrefs.getString(Variables.my_current_lat,"0.0"));
         double lng=Double.parseDouble(Variables.mPrefs.getString(Variables.my_current_lng,"0.0"));

         if((lat!=0.0 && lng!=0.0)) {
             latLng = new LatLng(lat, lng);
             pickup_latlong = latLng;
             mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));

             if (geoFire == null)
                 nearby_drivers();

         }

    }








    @SuppressLint("WrongConstant")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_menu:
                drawer.openDrawer(Gravity.START);
                break;

            case R.id.iv_profile:
                Edit_Profile_BottomSheet_F f16 = new Edit_Profile_BottomSheet_F(new Fragment_CallBack() {
                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        Functions.ShowProgressLoader(getContext(),false,false);
                        bitmap_profpic = bundle.getParcelable("bitmap");
                        image = METHOD_bitmap_to_bas64(bitmap_profpic);
                        METHOD_uploadprofileimage(image);
                    }
                });
                f16.show(getChildFragmentManager(), "");
                break;

            case R.id.ll_your_rides:
                METHOD_openfragmentfromDrawer(new YourTrips_F());
                break;

            case R.id.ll_notifi:
                METHOD_openfragmentfromDrawer(new Notifications_F());
                break;

            case R.id.ll_pay:
                METHOD_openfragmentfromDrawer(new Pay_F());
                break;

            case R.id.ll_packages:
                METHOD_openfragmentfromDrawer(new Packages_F());
                break;

            case R.id.ll_help:
                METHOD_openfragmentfromDrawer(new Help_F());
                break;

            case R.id.ll_settings:
                Fragment f6 = new Settings_F(new Fragment_CallBack() {
                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        /*tv_username.setText(Variables.mPrefs.getString(Variables.fname,"")+" "+
                                Variables.mPrefs.getString(Variables.lname,""));*/
                    }
                });
                METHOD_openfragmentfromDrawer(f6);
                break;





            case R.id.ll_back:
                Back_click();
                break;



            case R.id.rkl_select_vehicle:
                Select_vehicle_Bts_F f = new Select_vehicle_Bts_F(vehicle_list,
                        selected_vehical_model.id, new Fragment_CallBack() {
                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        Vehicle_Model_Class model = (Vehicle_Model_Class) bundle.getSerializable("obj");

                        tv_vehicle_name.setText(model.vehicle_name);
                        tv_Vehicle_name_when_chalo_visible.setText(model.vehicle_name);
                        tv_estimt_fare.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+
                                model.estimated_fare);
                        selected_vehical_model = model;
                    }
                });
                f.show(getChildFragmentManager(), "");
                break;




            case R.id.tv_skip:
                tv_select_dropoff_loc.setVisibility(View.GONE);
                btn_confirm_dropoff.setVisibility(View.GONE);
                btn_chalo.setVisibility(View.VISIBLE);
                chalo_details.setVisibility(View.VISIBLE);
                break;




            case R.id.current_loc_ll_id:
                METHOD_selectLocation(true);
                break;


            case R.id.rkl_drop_loc:
                METHOD_selectLocation(false);
                break;





            case R.id.btn_confirm_pickup:
                METHOD_adjustViewsforConfirmPickup();
                break;


            case R.id.fav_iv:
                METHOD_openSaveLocFragment();
                break;




            case R.id.btn_dropoff:
                METHOD_addDropoffLoc();
                break;






            case R.id.car_rl_layout:
                Select_vehicle_Bts_F f11 = new Select_vehicle_Bts_F(vehicle_list,
                        selected_vehical_model.id, new Fragment_CallBack() {
                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        Vehicle_Model_Class model = (Vehicle_Model_Class) bundle.getSerializable("obj");


                        tv_Vehicle_name_when_chalo_visible.setText(model.vehicle_name);
                        tv_vehicle_name.setText(model.vehicle_name);
                        selected_vehical_model = model;
                        Variables.charges_per_kilometer = "0"+model.charges_per_KM;
                        tv_estimt_fare.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+
                                model.estimated_fare);

                    }
                });
                f11.show(getChildFragmentManager(), "");
                break;



            case R.id.cash_rl_layout:
                Boolean wallet;
                if(tv_credit_remaining.getVisibility() == View.VISIBLE)
                    wallet = true;
                else
                    wallet = false;


                Cash_BottomSheet_F f12 = new Cash_BottomSheet_F(wallet,new Fragment_CallBack() {
                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        METHOD_changePaymentMethod(postion, bundle);
                    }
                });
                f12.show(getChildFragmentManager(), "");
                break;


            case R.id.promo_rl_layout:
                Promo_BottomSheet_F f13 = new Promo_BottomSheet_F();
                f13.show(getChildFragmentManager(), "");
                break;


            case R.id.details_id:
                Details_BottomSheet_F f15 = new Details_BottomSheet_F();
                f15.show(getChildFragmentManager(), "");
                break;





            case R.id.iv_change_map_type:
                METHOD_changeMapType();
                break;

            case R.id.iv_current_location:
                METHOD_movetoCurrentLocation();
                break;



            case R.id.btn_chalo:
                CallApi_Of_requestVehicle(
                        selected_vehical_model.id,
                        pickup_marker.getPosition(),
                        dropoff_marker.getPosition());
                break;
        }

    }








    private void METHOD_changePaymentMethod(int pos, Bundle bundle) {
        Boolean switchs = bundle.getBoolean("switch");
        payment_method_id = ""+bundle.getString("card_id");

        if (payment_method_id.equals("null"))
            payment_method_id = "0";

        if (bundle.getBoolean("iscash")) {
            tv_Payment_type.setText("Cash");
            payment_type = "cash";
            payment_method_id = "0";
        }else {
            tv_Payment_type.setText("Card");
            payment_type = "card";
        }


        int pixels = (int) (48 * context.getResources().getDisplayMetrics().density);
        int pixels2 = (int) (70 * context.getResources().getDisplayMetrics().density);

        if(switchs){
            rl_cash.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,pixels2));
            tv_credit_remaining.setVisibility(View.VISIBLE);
        }else {
            rl_cash.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,pixels));
            tv_credit_remaining.setVisibility(View.GONE);
        }

    }



    private void METHOD_changeMapType() {
        if(map_type){
            iv_change_map_type.setImageResource(R.drawable.notmal_map);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            map_type =false;
        }else {
            iv_change_map_type.setImageResource(R.drawable.earth_map);
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            map_type =true;
        }
    }

    private void METHOD_movetoCurrentLocation() {
        double lat = Double.parseDouble(Variables.mPrefs.getString(
                Variables.my_current_lat,"0.0"));
        double lng = Double.parseDouble(Variables.mPrefs.getString(
                Variables.my_current_lng,"0.0"));
        latLng = new LatLng(lat,lng);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
    }








    private void Back_click(){
        if(btn_confirm_pickup.getVisibility() == View.VISIBLE) {
            Log.d("resp", "Btn Confirm Pickup");
            onBackPressed();
        }else if(btn_confirm_dropoff.getVisibility() == View.VISIBLE) {
            Log.d("resp", "btn_confirm_dropoff");
            METHOD_goBackfromConfirmDropoff();
        }else if(btn_chalo.getVisibility() == View.VISIBLE) {
            Log.d("resp", "btn_chalo");
            METHOD_goBackfromChalo();
        }
    }


    public void METHOD_goBackfromConfirmDropoff(){
        which_screen_open = "letsgo";

        mapWorker.Remove_polyline_with_animation();
        rkl_current_location.setVisibility(View.VISIBLE);
        rkl_map_type.setVisibility(View.VISIBLE);

        if(pickup_marker!=null){
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pickup_marker.getPosition(), 18));
            pickup_marker.remove();
        }

        drop_off_latlong=null;
        iv_fav.setVisibility(View.VISIBLE);
        btn_confirm_pickup.setVisibility(View.VISIBLE);
        ll_menu.setVisibility(View.VISIBLE);
        select_vehicle_cv.setVisibility(View.VISIBLE);
        rl_pick_drop.setVisibility(View.VISIBLE);

        tv_details.setVisibility(View.GONE);
        rkl_drop_loc.setVisibility(View.GONE);
        tv_dropoff_loc.setVisibility(View.GONE);
        tv_title_dropof.setVisibility(View.GONE);
        tv_address_dropof.setVisibility(View.GONE);
        ll_back.setVisibility(View.GONE);

        btn_confirm_dropoff.setText(getString(R.string.add_dropoff));
        btn_confirm_dropoff.setVisibility(View.GONE);
        iv_center_marker.setVisibility(View.GONE);
    }

    public void METHOD_goBackfromChalo(){

        Log.d("resp", "METHOD_goBackfromChalo ");
        which_screen_open = "dropoff";

        mapWorker.Remove_polyline_with_animation();
        rkl_current_location.setVisibility(View.VISIBLE);
        rkl_map_type.setVisibility(View.VISIBLE);
        tv_select_dropoff_loc.setVisibility(View.VISIBLE);
        btn_confirm_dropoff.setVisibility(View.VISIBLE);
        iv_center_marker.setVisibility(View.VISIBLE);
        ll_back.setVisibility(View.VISIBLE);

        chalo_details.setVisibility(View.GONE);
        btn_chalo.setVisibility(View.GONE);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dropoff_marker.getPosition(), 18));
        dropoff_marker.remove();
    }

    private void METHOD_adjustViewsforConfirmPickup(){
        which_screen_open = "dropoff";

        ll_menu.setVisibility(View.GONE);
        iv_fav.setVisibility(View.GONE);
        select_vehicle_cv.setVisibility(View.GONE);
        btn_confirm_pickup.setVisibility(View.GONE);
        rl_pick_drop.setVisibility(View.GONE);


        ll_back.setVisibility(View.VISIBLE);
        tv_details.setVisibility(View.VISIBLE);
        rkl_drop_loc.setVisibility(View.VISIBLE);


        btn_confirm_dropoff.setVisibility(View.VISIBLE);
        tv_skip.setVisibility(View.VISIBLE);
        rkl_pickup_loc.setVisibility(View.VISIBLE);
        tv_dropoff_loc.setVisibility(View.VISIBLE);

        iv_center_marker.setVisibility(View.VISIBLE);

        if(pickup_marker == null)
            pickup_marker = mapWorker.add_marker(pickup_latlong,pick_up_marker);
        else {
            pickup_marker.remove();
            pickup_marker = mapWorker.add_marker(pickup_latlong,pick_up_marker);
        }

    }





    private void METHOD_addDropoffLoc(){
        which_screen_open = "letsgo";

        ll_back.setVisibility(View.VISIBLE);
        tv_select_dropoff_loc.setVisibility(View.GONE);

        if(drop_off_latlong != null){
            chalo_details.setVisibility(View.VISIBLE);
            btn_chalo.setVisibility(View.VISIBLE);

            btn_confirm_dropoff.setVisibility(View.GONE);
            iv_center_marker.setVisibility(View.GONE);
            rkl_current_location.setVisibility(View.GONE);
            rkl_map_type.setVisibility(View.GONE);

            if(dropoff_marker == null)
                dropoff_marker = mapWorker.add_marker(drop_off_latlong, drop_oof_marker);
            else {
                dropoff_marker.remove();
                dropoff_marker = mapWorker.add_marker(drop_off_latlong, drop_oof_marker);
            }

            drawRoute(pickup_latlong,drop_off_latlong);

            CallApi_of_vehicleTypes(false, pickup_latlong, drop_off_latlong);
        }else
            METHOD_selectLocation(false);

    }







    private void CallApi_of_vehicleTypes(Boolean is_latlng_empty,
                                         LatLng pickup_latlong, LatLng drop_off_latlong) {
        JSONObject sendobj = new JSONObject();

        if (!is_latlng_empty){
            try {
                sendobj.put("pickup_lat", ""+pickup_latlong.latitude);
                sendobj.put("pickup_long", ""+pickup_latlong.longitude);
                sendobj.put("destination_lat", ""+drop_off_latlong.latitude);
                sendobj.put("destination_long", ""+drop_off_latlong.longitude);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ApiRequest.Call_Api(getContext(), Api_url.url_vehicle_type, sendobj, new Callback() {

            @Override
            public void Responce(String resp) {
                if(resp != null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")) {
                            JSONArray msgobj = respobj.getJSONArray("msg");

                            vehicle_list.clear();
                            for (int i=0; i<=msgobj.length(); i++){
                                JSONObject obj = msgobj.getJSONObject(i).getJSONObject("RideType");

                                Vehicle_Model_Class model = new Vehicle_Model_Class();

                                model.id = ""+obj.optString("id");
                                model.charges_per_KM = ""+obj.optString("charges_per_km");
                                model.vehicle_name = ""+obj.optString("name");
                                model.vehicle_desc = ""+obj.optString("description");
                                model.base_fare = ""+obj.optString("base_fare");
                                model.cost_per_minute = ""+obj.optString("cost_per_minute");
                                model.cost_per_distance = ""+obj.optString("cost_per_distance");
                                model.estimated_fare = ""+obj.optString("estimated_fare");
                                model.time = ""+obj.optString("time");

                                model.avg_speed = ""+"50";

                                Variables.charges_per_kilometer = model.charges_per_KM;
                                vehicle_list.add(model);


                                if (i==0 && tv_vehicle_name.getText().toString().equals(" ")){
                                    if (!model.time.equals(null))
                                        tv_time_marker.setText(""+model.time+"\nmin");

                                    tv_vehicle_name.setText(model.vehicle_name);
                                    tv_vehicle_time.setText(model.time+" min");
                                    tv_Vehicle_name_when_chalo_visible.setText(model.vehicle_name);
                                    tv_estimt_fare.setText(Variables.mPrefs.getString(Variables.currency_unit,"")+
                                            model.estimated_fare);

                                    selected_vehical_model = model;
                                }

                            }
                        }
                    } catch (Exception e) {

                    }
                }
            }

        });
    }




    



    String pay_wallet = "0";
    private void CallApi_Of_requestVehicle(String ride_type,
                                           LatLng pickup_latlong,
                                           LatLng dropoff_latlong) {
        JSONObject sendobj = new JSONObject();

        if (tv_credit_remaining.getVisibility() == View.VISIBLE)
            pay_wallet = "1";
        else
            pay_wallet = "0";

        String estimatefare = tv_estimt_fare.getText().toString();
        estimatefare = estimatefare.replace("$", "");

        try {
            sendobj.put("user_id", Variables.user_id);
            sendobj.put("ride_type", ride_type);
            sendobj.put("estimated_fare", estimatefare);
            sendobj.put("wallet_pay", pay_wallet);
            sendobj.put("payment_type", payment_type);
            sendobj.put("payment_method_id", payment_method_id);
            sendobj.put("pickup_lat", pickup_latlong.latitude);
            sendobj.put("pickup_long", pickup_latlong.longitude);
            sendobj.put("destination_lat", dropoff_latlong.latitude);
            sendobj.put("destination_long", dropoff_latlong.longitude);
        }catch(Exception e){

        }

        Functions.ShowProgressLoader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_show_requestVehicle, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.CancelProgressLoader();

                if (resp != null) {

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")) {

                            JSONObject msgobj = respobj.getJSONObject("msg");
                            JSONObject reqobj = msgobj.getJSONObject("Request");

                            String id = reqobj.getString("id");
                            Variables.mPrefs.edit().putString("request_id", id).commit();


                            Intent intent = new Intent(getActivity(), Confirm_ride_Captain_F.class);
                            intent.putExtra("call", "map");
                            startActivity(intent);

                            which_screen_open = "";
                            getActivity().finish();

                        }

                    } catch (Exception e) {
                        Log.d("resp","error"+e);
                    }
                }
            }
        });

    }






    String locality= "";
    private void configureCameraIdle() {
        onCameraIdleListener = new GoogleMap.OnCameraIdleListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onCameraIdle() {
                if(markers_list != null)
                    cal_of_distance_driver_to_come();


                LatLng latLng2 = mMap.getCameraPosition().target;

                if(rkl_drop_loc.getVisibility() == View.GONE)
                    pickup_latlong = latLng2;
                else {
                    btn_confirm_dropoff.setText(getString(R.string.confirm_dropoff));
                    drop_off_latlong = latLng2;
                }


                new AsyncTask<LatLng, Void, Address>() {

                    @SuppressLint("WrongThread")
                    @Override
                    protected Address doInBackground(LatLng... geoPoints) {

                        try {
                            Geocoder geoCoder = new Geocoder(getContext());
                            double latitude = geoPoints[0].latitude;
                            double longitude = geoPoints[0].longitude;
                            List<Address> addresses = geoCoder
                                    .getFromLocation(latitude, longitude, 1);

                            if (addresses != null &&addresses.size() > 0)
                                return addresses.get(0);

                        } catch (IOException ex) {
                        }

                        return null;

                    }

                    @Override
                    protected void onPostExecute(Address address) {

                        if (address!=null)
                            locality = ""+address.getAddressLine(0);

                        String json = Variables.mPrefs.getString(Variables.saved_loc_list,"");

                        ArrayList<LatLng> list = new Gson().fromJson(json,
                                new TypeToken<ArrayList<LatLng>>(){}.getType());

                        if(list != null){
                            for (int i = 0; i<list.size(); i++){
                                double lat = address.getLatitude();
                                double lng = address.getLongitude();
                                LatLng latLng = new LatLng(lat,lng);

                                double distance = getDistanceFromLatLngInKm(latLng, list.get(i));

                                if (distance < 50)
                                    iv_fav.setImageResource(R.drawable.ic_favorite);
                                else
                                    iv_fav.setImageResource(R.drawable.ic_fav);

                            }
                        }


                        if(address!=null){
                            if(rkl_pickup_loc.getVisibility() == View.VISIBLE
                                    && rkl_drop_loc.getVisibility() == View.VISIBLE){

                                if(btn_confirm_dropoff.getVisibility() == View.VISIBLE){
                                    tv_address_dropof.setVisibility(View.VISIBLE);
                                    tv_title_dropof.setVisibility(View.VISIBLE);
                                    tv_dropoff_loc.setVisibility(View.GONE);
                                    tv_title_dropof.setText(locality);
                                    tv_address_dropof.setText(locality);
                                }

                            }else if(rkl_pickup_loc.getVisibility() == View.VISIBLE) {
                                tv_title_pickup.setText(locality);
                                tv_address_pickup.setText(locality);
                            }else if(rkl_drop_loc.getVisibility() ==View.VISIBLE){
                                tv_title_dropof.setText(locality);
                                tv_address_dropof.setText(locality);
                            }
                        }
                    }
                }.execute(latLng2);

            }
        };
    }







    private ArrayList<Marker> markers_list = new ArrayList<>();
    private ArrayList<String> drivers_ids = new ArrayList<>();
    private LatLng nearest_driver_latlng;
    private double nearest_driver_distance=5000, dist;
    private void nearby_drivers(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Drivers");
        geoFire = new GeoFire(ref);
        GeoQuery geoQuery = geoFire.queryAtLocation(new GeoLocation(pickup_latlong.latitude, pickup_latlong.longitude), 5.0);
        geoQuery.addGeoQueryEventListener(new GeoQueryEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onKeyEntered(String key, GeoLocation location) {
                if(!drivers_ids.contains(key)) {
                   /*if(n < d) {
                       n = d;
                   }*/
                   //d = (getDistanceFromLatLngInKm(latLng, new LatLng(location.latitude, location.longitude)));
                   //tag = d+" km" ;

                    Marker marker = mapWorker.add_marker(key, new LatLng(location.latitude, location.longitude), mapWorker.car_marker);
                    markers_list.add(marker);
                    drivers_ids.add(key);

                    dist = getDistanceFromLatLngInKm(pickup_latlong, new LatLng(location.latitude,location.longitude));

                    if (dist < nearest_driver_distance){
                        nearest_driver_distance = dist;
                        nearest_driver_latlng = new LatLng(location.latitude,location.longitude);
                    }

                }

            }

            @Override
            public void onKeyExited(String key) {
            }

            @Override
            public void onKeyMoved(String key, GeoLocation location) {
                for(int i=0; i<markers_list.size(); i++){
                    if(markers_list.get(i).getTag().equals(key)){
                        Marker updted_marker = markers_list.get(i);

                        mapWorker.rotateMarker(updted_marker,
                                new LatLng(location.latitude,location.longitude),
                                (float)Functions.getBearingBetweenTwoPoints1(new
                                                LatLng(updted_marker.getPosition().latitude,
                                        updted_marker.getPosition().longitude),
                                        new LatLng(location.latitude,location.longitude)), 200);

                        mapWorker.animateMarkerTo(updted_marker,location.latitude,location.longitude);

                    }
                }
            }

            @Override
            public void onGeoQueryReady() {
                CallApi_of_vehicleTypes(false, pickup_latlong, nearest_driver_latlng);
            }

            @Override
            public void onGeoQueryError(DatabaseError error) {
                Log.d("resp",  "There was an error with this query: "+error) ;
            }
        });

    }









    private void drawRoute(LatLng pickup, LatLng dropoff){

        if(pickup != null && dropoff !=null){
            mapWorker.Get_Direction(pickup, dropoff, new Route_CallBack() {
                @Override
                public void Responce(DirectionsResult directionsResult) {
                    if(directionsResult!=null) {
                        mapWorker.addPolyline_with_animation(directionsResult, mMap);
                        distance = mapWorker.get_distance_from_route(directionsResult);
                    }
                }
            });


            LatLng pickup_latlng = pickup_marker.getPosition();
            LatLng dropoff_latlng = dropoff_marker.getPosition();

            LatLngBounds.Builder latlngBuilder = new LatLngBounds.Builder();
            latlngBuilder.include(pickup_latlng);
            latlngBuilder.include(dropoff_latlng);

            mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(latlngBuilder.build(), 300));

        }else {
            Functions.ShowToast(view.getContext(), ""+getString(R.string.missing_location));
        }

    }









    private double route_fare(String distance){
        String hellWrld;
        double est_fare = 0.0;
        if(distance.contains("km")){
            hellWrld = distance.replace("km","");
            est_fare = Double.parseDouble(hellWrld) * Integer.parseInt("20");
        }else {
            hellWrld = distance.replace("m","");
            est_fare = (Double.parseDouble(hellWrld)/1000)* Integer.parseInt("20");
        }
        return est_fare ;
    }


    private double getDistanceFromLatLngInKm(LatLng latLng1, LatLng latLng2){
        double lat1 = latLng1.latitude;
        double lon1 = latLng1.longitude;
        double lat2 = latLng2.latitude;
        double lon2 = latLng2.longitude;

        Location loc1 = new Location("");
        loc1.setLatitude(lat1);
        loc1.setLongitude(lon1);

        Location loc2 = new Location("");
        loc2.setLatitude(lat2);
        loc2.setLongitude(lon2);

        float distanceInMeters = loc1.distanceTo(loc2);

        long R = 6371; //Radius of the earth in km
        double dLat = deg2rad(lat2-lat1); //deg2rad below
        double dLon = deg2rad(lon2-lon1);





        /*double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);

        Log.d("resp", "Distance in miles "+dist);
        dist = dist * 60 * 1.1515;

        Log.d("resp", "Distance in Km "+dist);
        dist = dist * 0.62137;

        Log.d("resp", "Distance in meters "+dist);
        dist = dist * 1000;*/
        /*double a = Math.sin(dLat/2)
                * Math.sin(dLat/2)
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;*/
        return distanceInMeters;
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }










    private void cal_of_distance_driver_to_come(){
        //String tag = n+" km";
        for (i = 0; i<markers_list.size(); i++){
            LatLng nearest_marker_latlong =  markers_list.get(i).getPosition();

            CallApi_of_vehicleTypes(false, pickup_latlong, nearest_marker_latlong);

            /*double nearst_vehicle_distance = getDistanceFromLatLngInKm(pickup_latlong, nearest_marker_latlong);
            double speed = 50;*/

            //tv_time_marker.setText((cal_of_time_driver_to_come(nearst_vehicle_distance, speed)+"\nmin"));
        }
    }










    private int cal_of_time_driver_to_come(double nearst_vehicle_distance, double speed){
        double t = (nearst_vehicle_distance * 1000) / ((speed * 5) / 18);
        return (int)(t /60);
    }








    private void METHOD_selectLocation(Boolean isfrompickup){
        Fragment f = new Search_F(new Fragment_CallBack() {
            @Override
            public void On_Item_Click(int postion, Bundle bundle) {
                which_screen_open = "letsgo";

                String call = bundle.getString("call_back_class");
                double lat = bundle.getDouble("lat", 0.0);
                double lng = bundle.getDouble("lng",0.0);
                LatLng latLng = new LatLng(lat,lng);

                if(Search_F.click){
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));

                    if (isfrompickup){
                        tv_title_pickup.setText(""+bundle.getString("title"));
                        tv_address_pickup.setText(""+bundle.getString("address"));

                        pickup_latlong = latLng;

                        if(rkl_drop_loc.getVisibility() == View.VISIBLE){
                            if(pickup_marker == null)
                                pickup_marker = mapWorker.add_marker(pickup_latlong,pick_up_marker);
                            else {
                                pickup_marker.remove();
                                pickup_marker = mapWorker.add_marker(pickup_latlong,pick_up_marker);
                                mapWorker.Remove_polyline_with_animation();
                            }

                            drawRoute(pickup_latlong, dropoff_marker.getPosition());
                        }
                    }else {
                        tv_title_dropof.setVisibility(View.VISIBLE);
                        tv_address_dropof.setVisibility(View.VISIBLE);

                        tv_title_dropof.setText(""+bundle.getString("title"));
                        tv_address_dropof.setText(""+bundle.getString("address"));

                        drop_off_latlong = latLng;

                        if(dropoff_marker == null)
                            dropoff_marker = mapWorker.add_marker(drop_off_latlong, drop_oof_marker);
                        else {
                            dropoff_marker.remove();
                            dropoff_marker = mapWorker.add_marker(drop_off_latlong, drop_oof_marker);

                            mapWorker.Remove_polyline_with_animation();
                        }


                        chalo_details.setVisibility(View.VISIBLE);
                        btn_chalo.setVisibility(View.VISIBLE);

                        tv_dropoff_loc.setVisibility(View.GONE);
                        btn_confirm_dropoff.setVisibility(View.GONE);
                        iv_center_marker.setVisibility(View.GONE);
                        rkl_current_location.setVisibility(View.GONE);
                        rkl_map_type.setVisibility(View.GONE);
                        tv_select_dropoff_loc.setVisibility(View.GONE);

                        drawRoute(pickup_latlong, drop_off_latlong);
                    }

                    if(call.equals("saved")){
                        iv_fav.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite));
                    }
                }
            }
        });

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
        ft.replace(R.id.fl_id, f).addToBackStack(null).commit();
    }







    //This METHOD convert image Bitmap to Base64
    private String METHOD_bitmap_to_bas64(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] byteArray = baos.toByteArray();
        String base64= Base64.encodeToString(byteArray, Base64.NO_WRAP);
        return base64;
    }

    //METHOD to upload image in Database
    private void METHOD_uploadprofileimage(String image) {
        JSONObject sendobj = new JSONObject();

        image = image.replaceAll("\n","");
        image = image.replaceAll(" ", "").trim();

        try {
            sendobj.put("user_id", Variables.user_id);
            JSONObject file_data = new JSONObject();
            file_data.put("file_data", image);
            sendobj.put("image", file_data);
        }catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequest.Call_Api(getContext(), Api_url.url_editProfile, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.CancelProgressLoader();
                if (resp !=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.optString("code").contains("200")){
                            JSONObject msgobj = respobj.optJSONObject("msg").optJSONObject("User");

                            String image = msgobj.optString("image");
                            Uri uri = Uri.parse(Api_url.url_pic+image);
                            iv_profile.setImageURI(uri);

                            SharedPreferences.Editor edit = Variables.mPrefs.edit();
                            edit.putString(Variables.image, image);
                            edit.putBoolean(Variables.isloginwithfb, false);
                            edit.commit();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }







    @SuppressLint("WrongConstant")
    private void METHOD_openfragmentfromDrawer(Fragment f){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);

        ft.replace(R.id.fl_id, f).addToBackStack(null).commit();
    }



    private void METHOD_openSaveLocFragment(){
        Fragment f8 = new Save_Location_F(tv_title_pickup.getText().toString(),
                tv_address_pickup.getText().toString(),
                Variables.user_id, pickup_latlong, "null", new Fragment_CallBack() {

            @Override
            public void On_Item_Click(int postion, Bundle bundle) {
                if (bundle!=null)
                    model = (Save_Location_Model) bundle.getSerializable("model");

                iv_fav.setImageResource(R.drawable.ic_favorite);
                tv_title_pickup.setText(model.place_name);
                saved_loc_list.add(model.location_latlng);

                gson = new Gson();
                String json = gson.toJson(saved_loc_list);
                Variables.mPrefs.edit().putString(Variables.saved_loc_list, json).commit();

            }

        });
        FragmentTransaction ft8 = getChildFragmentManager().beginTransaction();
        ft8.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);

        ft8.replace(R.id.fl_id, f8).addToBackStack(null).commit();
    }

}

