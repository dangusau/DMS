package com.dinosoftlabs.uber.HomeScreen.Search_Location;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONObject;


public class Save_Location_F extends RootFragment implements View.OnClickListener {

    private View view;
    private TextView tv_add_details,save,tv_title,tv_address;
    private EditText et_loc_name,et_pickup_details;

    private String Place_name, address, User_id,google_places_id, title;

    private LatLng latLng;
    private LinearLayout ll_back;

    private Fragment_CallBack callback;
    private SimpleDraweeView iv_map;


    public Save_Location_F(String title,
                           String address,
                           String User_id,
                           LatLng latLng,
                           String google_places_id,
                           Fragment_CallBack callback) {
        // Required empty public constructor
        this.address=address;
        this.title=title;
        this.latLng=latLng;
        this.User_id=User_id;
        this.google_places_id=google_places_id;
        this.callback = callback;
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.save_location_f, container, false);


        METHOD_findviewbyid();
        METHOD_modifyedittext();


        String lat = Double.toString(latLng.latitude);
        String lng = Double.toString(latLng.longitude);


        String uri = Functions.Get_static_map_view_url(getContext(),lat,lng);


        iv_map.setImageURI(uri);
        tv_title.setText(title);
        tv_address.setText(address);


        return view;

    }






    protected void METHOD_findviewbyid(){
        tv_add_details = (TextView) view.findViewById(R.id.tv_add_details);
        tv_add_details.setOnClickListener(this);


        iv_map = view.findViewById(R.id.iv_map);
        tv_title = view.findViewById(R.id.tv_title);
        tv_address = view.findViewById(R.id.tv_address);
        et_loc_name = (EditText) view.findViewById(R.id.et_loc_name);
        et_pickup_details = (EditText) view.findViewById(R.id.et_pickup_details);


        ll_back = (LinearLayout) view.findViewById(R.id.ll_back);
        ll_back.setOnClickListener(this);

        save = (TextView) view.findViewById(R.id.save_id);
        save.setOnClickListener(this);
    }







    private void METHOD_modifyedittext(){
        et_loc_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int len = et_loc_name.length();
                if (len > 0){
                    save.setBackgroundColor(getResources().getColor(R.color.green));
                    save.setEnabled(true);
                }else {
                    save.setBackgroundColor(getResources().getColor(R.color.light_green));
                    save.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }







    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_back:
                Functions.hideSoftKeyboard(getActivity());
                getActivity().onBackPressed();
                break;


            case R.id.tv_add_details:
                tv_add_details.setVisibility(View.GONE);
                et_pickup_details.setVisibility(View.VISIBLE);
                break;


            case R.id.save_id:
                Place_name = et_loc_name.getText().toString();
                CallApi_Of_AddUserPlace(User_id,latLng,title,google_places_id,Place_name);
                break;
        }
    }










    private void CallApi_Of_AddUserPlace(String userid,
                                         LatLng latLng,
                                         String location_string,
                                         String google_places_id,
                                         String Place_name) {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("google_place_id", google_places_id);
            sendobj.put("name", Place_name);
            sendobj.put("location_string", location_string);
            sendobj.put("user_id", userid);
            sendobj.put("lat", latLng.latitude);
            sendobj.put("long", latLng.longitude);

        } catch (Exception e) {
        }


        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_addUserPlace, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                if (resp != null) {

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")) {

                            JSONObject placeobj = respobj.getJSONObject("msg").getJSONObject("UserPlace");

                            Save_Location_Model model = new Save_Location_Model();

                            model.place_id = ""+placeobj.optString("id");
                            model.place_name = ""+placeobj.optString("name");
                            model.location_str = ""+placeobj.optString("location_string");
                            model.location_latlng = latLng;

                            Bundle bundle = new Bundle();
                            bundle.putSerializable("model", model);
                            callback.On_Item_Click(0,bundle);

                            getActivity().onBackPressed();
                        }
                    } catch (Exception e) {
                    }

                }

            }
        });
    }
}
