package com.dinosoftlabs.uber.HomeScreen.YourTrip;

import java.io.Serializable;

public class YourTrips_Model implements Serializable {

    public String id, driver_id, vehicle_id, request_id,
            pickup_location, destination_location, pickup_lat, pickup_long,
            destination_lat, destination_long, pickup_datetime, destination_datetime,
            accepted_by_rider, cancelled_by_rider, cancelled_by_user, completed, final_fare,
            pay_type,pay_from_wallet,pay_collect_from_wallet,pay_collect_from_card,
            pay_collect_from_cash;

    public String driver_email, driver_password ,driver_first_name, driver_last_name,
            driver_phone_no, driver_image, driver_device_token, driver_role,
            driver_online, driver_lat, driver_lng, trip_rating;

}
