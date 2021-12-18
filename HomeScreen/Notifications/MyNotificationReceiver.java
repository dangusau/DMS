package com.dinosoftlabs.uber.HomeScreen.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class MyNotificationReceiver extends BroadcastReceiver {

    SharedPreferences sharedPreferences;
    DatabaseReference rootref;
    public Context context;

    @Override
    public void onReceive(final Context context, Intent intent) {
        this.context=context;
        Bundle extras = intent.getExtras();
        rootref= FirebaseDatabase.getInstance().getReference();

        if (extras!=null) {
            sharedPreferences=context.getSharedPreferences(Variables.pref_name,MODE_PRIVATE);

            String type=extras.getString("type");
            if(type!=null && extras.getString("type").equals("request_accepted")){

                Log.d("resp","yes  broadcast in notification Receiver request accepted");
                Intent intent1=new Intent();
                intent1.setAction("request_responce");
                intent1.putExtras(intent.getExtras());
                context.sendBroadcast(intent1);

            }else if(type != null && intent.getExtras().getString("type").equals("ride_cancel")){
                Intent intent1=new Intent();
                intent1.setAction("request_responce");
                intent1.putExtras(intent.getExtras());
                context.sendBroadcast(intent1);
                Log.d("resp","yes received broadcast in notification Receiver but not accepted and type is "+type);
            }else {
                Intent intent1=new Intent();
                intent1.setAction("request_responce");
                intent1.putExtras(intent.getExtras());
                context.sendBroadcast(intent1);
                Log.d("resp","yes received broadcast in notification Receiver but not accepted and type is "+type);
            }
        }
    }
}





