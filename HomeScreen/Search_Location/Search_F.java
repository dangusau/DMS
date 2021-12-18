package com.dinosoftlabs.uber.HomeScreen.Search_Location;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.HomeScreen.Main.Main_Map_F;
import com.dinosoftlabs.uber.HomeScreen.Search_Location.Nearby_Location.Nearby_Adapter;
import com.dinosoftlabs.uber.HomeScreen.Search_Location.Nearby_Location.Nearby_Model_Class;
import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.R;
import com.google.android.gms.maps.model.LatLng;
import com.kodmap.library.kmrecyclerviewstickyheader.KmHeaderItemDecoration;
import com.kodmap.library.kmrecyclerviewstickyheader.KmRecyclerView;
import com.DMS_Cust.DMS.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_F extends RootFragment implements View.OnClickListener {

    private View view;
    private String Keyword ="";
    private LinearLayout back_ll;
    private RelativeLayout city_loc_rl,rl_nearyby_locations,rl_recent_locations,rl_saved_locations;

    private RecyclerView rc_Nearby_location,rc_saved_location,rc_recent_location;
    private Nearby_Adapter nearby_adapter,saved_adapter;
    private List<Nearby_Model_Class> saved_list = new ArrayList<>();
    private List<Nearby_Model_Class> nearby_list = new ArrayList<>();

    List<String> ids = new ArrayList<>();
    private List<LatLng> latLngs_saved = new ArrayList<>();
    public List<LatLng> latLngs_nearby = new ArrayList<>();

    private EditText et_text_watcher;


    private KmRecyclerView city_rv;
    com.dinosoftlabs.uber.HomeScreen.Search_Location.City_Location.Adapter adapter;
    KmHeaderItemDecoration km;
    Boolean check = true;

    LatLng latLng = null;
    public static int click_position;
    public static boolean click=false;
    final Fragment_CallBack fragmentCallBack;







    public Search_F(final Fragment_CallBack fragmentCallBack ) {
        // Required empty public constructor
        this.fragmentCallBack=fragmentCallBack;
    }









    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.search_f, container, false);


        METHOD_findviewbyid();
        METHOD_setonclicklistener();
        METHOD_set_recyclerview();


        CallApi_Of_showUserPlaces(Variables.user_id);

        nearby_list.clear();
        ids.clear();
        latLngs_nearby.clear();
        runit();


        et_text_watcher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                nearby_list.clear();
                latLngs_nearby.clear();
                ids.clear();
                runit();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // filter your list from your input
                filter(editable.toString());
            }
        });

        recyclerview_setup();

        return view;
    }











    public void METHOD_findviewbyid(){
        back_ll = (LinearLayout) view.findViewById(R.id.back_ll_id);
        rc_Nearby_location = (RecyclerView) view.findViewById(R.id.rc_Nearby_location);
        city_rv = (KmRecyclerView) view.findViewById(R.id.city_loc_rv_id);
        city_loc_rl = (RelativeLayout) view.findViewById(R.id.city_loc_rl_id);
        rl_saved_locations = (RelativeLayout) view.findViewById(R.id.rl_saved_locations);
        rl_recent_locations = (RelativeLayout) view.findViewById(R.id.rl_recent_locations);
        rl_nearyby_locations = (RelativeLayout) view.findViewById(R.id.rl_nearyby_locations);
        rc_saved_location = (RecyclerView) view.findViewById(R.id.rc_saved_location);
        rc_recent_location = (RecyclerView) view.findViewById(R.id.rc_recent_location);
        et_text_watcher = view.findViewById(R.id.et_text_watcher);
    }












    private void runit(){
        Keyword = et_text_watcher.getText().toString();
       // Location =  MainActivity.mMarkerPoints.get(0);

        Double latitude = Main_Map_F.latLng.latitude;
        Double longitude = Main_Map_F.latLng.longitude;


        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="
                +latitude+","
                +longitude+"&radius=5000&type=&keyword="
                +Keyword+"&key=AIzaSyB1KYD3v6h1TyQFWahPQaahk-pLgT9QeMc";


        ApiRequest.Call_Api(getContext(), url, new JSONObject(), new Callback() {
            @Override
            public void Responce(String resp) {
                if (resp !=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);
                        JSONArray jsonArray = respobj.getJSONArray("results");

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String Formated_address = jsonObject.getString("name");
                            String vicinity = jsonObject.getString("vicinity");
                            String place_id = jsonObject.getString("place_id");
                            JSONObject jsonObject2 = jsonObject.getJSONObject("geometry");
                            JSONObject jsonObject3 = jsonObject2.getJSONObject("location");

                            String lat = ""+jsonObject3.optString("lat");
                            String lng = ""+jsonObject3.optString("lng");

                            double la = Double.parseDouble(lat);
                            double lo = Double.parseDouble(lng);

                            latLng = new LatLng(la, lo);
                            latLngs_nearby.add(latLng);

                            Nearby_Model_Class  model = new Nearby_Model_Class();

                            model.Title = Formated_address;
                            model.Address = vicinity;
                            model.lat = Double.parseDouble(lat);
                            model.lng = Double.parseDouble(lng);
                            model.latLng = latLng;
                            model.place_id = place_id;

                            nearby_list.add(model);
                        }

                        METHOD_setAdapter();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }







    private void METHOD_setAdapter(){
        nearby_adapter = new Nearby_Adapter(nearby_list, ids, getContext(), new Adapter_ClickListener() {
            @Override
            public void On_Item_Click(int postion, Object Model, View view) {
                Nearby_Model_Class model = nearby_list.get(postion);

                switch (view.getId()) {
                    case R.id.iv_fav:
                        Fragment f8 = new Save_Location_F(""+model.Title, ""+model.Address,
                                Variables.user_id, model.latLng, model.place_id,
                                new Fragment_CallBack() {
                                    @Override
                                    public void On_Item_Click(int postion, Bundle bundle) {
                                        Boolean saved = bundle.getBoolean("save_location_f");
                                        if (saved){
                                            CallApi_Of_showUserPlaces(Variables.user_id);
                                        }
                                    }
                                });

                        FragmentTransaction ft8 = getChildFragmentManager().beginTransaction();
                        ft8.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);
                        ft8.replace(R.id.rl_id, f8).addToBackStack(null).commit();
                        break;


                    case R.id.rl_fav:
                        click_position = postion;
                        click = true;

                        Bundle bundle = new Bundle();
                        bundle.putString("title", model.Title);
                        bundle.putString("address", model.Address);
                        bundle.putDouble("lat", model.lat);
                        bundle.putDouble("lng", model.lng);
                        bundle.putString("call_back_class", "nearby");

                        fragmentCallBack.On_Item_Click(0,bundle);

                        getActivity().onBackPressed();
                        break;

                }
            }

            @Override
            public void On_Item_Long_Click(int postion, Object Model, View view) {

            }
        });

        rc_Nearby_location.setLayoutManager(new LinearLayoutManager(getContext()));
        rc_Nearby_location.setHasFixedSize(true);
        rc_Nearby_location.setAdapter(nearby_adapter);
    }









    private void METHOD_set_recyclerview(){
        city_rv.setHasFixedSize(false);
        city_rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new com.dinosoftlabs.uber.HomeScreen.Search_Location.City_Location.Adapter();

        km = new KmHeaderItemDecoration(adapter);
        city_rv.setAdapter(adapter);
    }






    public void METHOD_setonclicklistener(){
        back_ll.setOnClickListener(this);
        city_loc_rl.setOnClickListener(this);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_ll_id:
                Functions.hideSoftKeyboard(getActivity());
                getActivity().onBackPressed();
                break;


            case R.id.city_loc_rl_id:
                if (check){
                    city_rv.setVisibility(View.VISIBLE);
                    check = false;
                }else {
                    city_rv.setVisibility(View.GONE);
                    check = true;
                }
                break;
        }
    }







    private void CallApi_Of_showUserPlaces(String userid) {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", userid);
        } catch (Exception ignored) {

        }


        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_showUserPlaces, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                if (resp != null) {

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")) {
                            JSONArray msgarray = respobj.getJSONArray("msg");

                            saved_list.clear();
                            for(int i=0; i<msgarray.length(); i++){
                                JSONObject msgobj = msgarray.getJSONObject(i);
                                JSONObject userobj = msgobj.getJSONObject("UserPlace");

                                String name = userobj.getString("name");
                                String lat = userobj.getString("lat");
                                String lng = userobj.getString("long");
                                String id = userobj.getString("id");
                                String location_string = userobj.getString("location_string");
                                String google_place_id = userobj.getString("google_place_id");

                                double latitude = Double.parseDouble(lat);
                                double longitude = Double.parseDouble(lng);
                                LatLng latlng = new LatLng(latitude, longitude);

                                latLngs_saved.add(latlng);

                                Nearby_Model_Class model = new Nearby_Model_Class();

                                model.Title = name;
                                model.Address = location_string;
                                model.place_id = google_place_id;
                                model.id = id;
                                model.latLng = latlng;
                                model.lat = latitude;
                                model.lng = longitude;

                                ids.add(google_place_id);

                                saved_list.add(model);
                            }

                            saved_adapter.notifyDataSetChanged();
                            rc_Nearby_location.getAdapter().notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        });
    }








    private void recyclerview_setup(){
        if(saved_list!=null)
            rl_saved_locations.setVisibility(View.VISIBLE);


        rc_saved_location.setLayoutManager(new LinearLayoutManager(getContext()));
        rc_saved_location.setHasFixedSize(true);

        saved_adapter = new Nearby_Adapter(saved_list,null, getContext(), new Adapter_ClickListener() {
            @Override
            public void On_Item_Click(int postion, Object Model, View view) {
                Nearby_Model_Class model = saved_list.get(postion);

                switch (view.getId()){
                    case R.id.iv_fav:

                        Functions.Show_Alert(getActivity(), "",
                                "Are you sure you want to remove this location?", new Callback() {
                            @Override
                            public void Responce(String resp) {
                                if(resp.equals("yes")){
                                    Nearby_Model_Class model = saved_list.get(postion);

                                    CallApi_Of_delete_place(model.id);

                                    saved_list.remove(postion);
                                    ids.remove(postion);

                                    saved_adapter.notifyDataSetChanged();
                                    nearby_adapter.notifyDataSetChanged();

                                }
                            }
                        });
                        break;


                    case R.id.rl_fav:
                        click_position = postion;
                        click = true;


                        Bundle bundle = new Bundle();
                        bundle.putString("title", model.Title);
                        bundle.putString("address", model.Address);
                        bundle.putDouble("lat", model.lat);
                        bundle.putDouble("lng", model.lng);
                        bundle.putString("call_back_class", "saved");

                        fragmentCallBack.On_Item_Click(0,bundle);

                        getActivity().onBackPressed();
                        break;
                }
            }

            @Override
            public void On_Item_Long_Click(int postion, Object Model, View view) {

            }
        });

        rc_saved_location.setAdapter(saved_adapter);
    }




    private void filter(String text){
        List<Nearby_Model_Class> temp = new ArrayList();
        for(Nearby_Model_Class d: saved_list){
            //or use .equal(text) with you want equal match
            if(d.Title.toLowerCase().contains(text) || d.Address.toLowerCase().contains(text)){
                temp.add(d);
            }
        }

        //update recyclerview
        saved_adapter.updateList(temp);
    }






    private void CallApi_Of_delete_place(String place_id) {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("id", place_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_deleteUserPlace, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                if (resp != null) {

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")) {
                            ids.clear();
                            CallApi_Of_showUserPlaces(Variables.user_id);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


}
