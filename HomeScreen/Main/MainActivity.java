package com.dinosoftlabs.uber.HomeScreen.Main;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.Login.User_info_Model_Class;
import com.dinosoftlabs.uber.Map_classes.GpsUtils;
import com.dinosoftlabs.uber.Map_classes.MapWorker;
import com.dinosoftlabs.uber.R;
import com.dinosoftlabs.uber.Service_class.GetLocation_Service;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.DMS_Cust.DMS.HelpingClasses.Functions;
import com.DMS_Cust.DMS.R;

public class MainActivity extends AppCompatActivity {

    User_info_Model_Class user_info_model_class;
    MapWorker mapWorker;
    GetLocation_Service mService;
    boolean mBound = false;
    Main_Map_F f;

    long mBackPressed = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeclass);

        Fresco.initialize(this);

        Variables.mPrefs = getSharedPreferences(Variables.pref_name, MODE_PRIVATE);
        Variables.download_sharedPreferences = getSharedPreferences(Variables.download_pref, MODE_PRIVATE);



        if (savedInstanceState == null)
            reload();
        else
            f = (Main_Map_F) getSupportFragmentManager().getFragments().get(0);

    }



    public void reload(){
        f = new Main_Map_F();

        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_id, f).commit();
    }






    @Override
    protected void onResume() {
        super.onResume();
        //enable_permission();
    }

    @Override
    public void onBackPressed() {
        if (!f.onBackPressed()) {
            int count = this.getSupportFragmentManager().getBackStackEntryCount();
            if (count == 0) {
                if(f.which_screen_open.equals("letsgo"))
                    f.METHOD_goBackfromChalo();
                else if(f.which_screen_open.equals("dropoff")) {
                    f.METHOD_goBackfromConfirmDropoff();
                    f.which_screen_open = "";
                }
                else if (mBackPressed + 2000 > System.currentTimeMillis()) {
                    super.onBackPressed();
                    return;
                } else {
                    Functions.ShowToast(getBaseContext(), ""+MainActivity.this.getString(R.string.tap_again_to_exit));
                    mBackPressed = System.currentTimeMillis();
                }
            } else {
                super.onBackPressed();
            }
        }
    }






    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Variables.permission_location )
            enable_permission();

    }









    private void enable_permission(){
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if(!GpsStatus) {
            new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
                @Override
                public void gpsStatus(boolean isGPSEnable) {
                    enable_permission();
                }
            });
        }
        else if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Bind_Location_service();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    Variables.permission_location);
        }
    }








    public void Bind_Location_service(){
        GetLocation_Service locationService = new GetLocation_Service();
        Intent mServiceIntent = new Intent(this, locationService.getClass());
        if (Functions.isMyServiceRunning(this, locationService.getClass())) {
            this.startService(mServiceIntent);
        }
    }


}

