package com.dinosoftlabs.uber.HomeScreen.Search_Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Save_Location_Model implements Serializable {

    public String place_id,place_name,location_str;
    public LatLng location_latlng;

}
