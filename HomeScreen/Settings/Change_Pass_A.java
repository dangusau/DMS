package com.dinosoftlabs.uber.HomeScreen.Settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.R;
import com.DMS_Cust.DMS.HelpingClasses.Functions;
import com.DMS_Cust.DMS.R;

import org.json.JSONException;
import org.json.JSONObject;

import static com.dinosoftlabs.uber.CodeClasses.Variables.mPrefs;

public class Change_Pass_A extends AppCompatActivity implements View.OnClickListener {

    LinearLayout back;

    EditText et_old_pass,et_new_pass,et_retype_pass;

    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pass_a);

        Variables.mPrefs = getSharedPreferences(Variables.pref_name, MODE_PRIVATE);

        METHOD_findviewbyid();


    }




    protected void METHOD_findviewbyid(){
        back = (LinearLayout) findViewById(R.id.back_ll_id);
        back.setOnClickListener(this);

        et_old_pass = findViewById(R.id.et_old_pass);
        et_new_pass = findViewById(R.id.et_new_pass);
        et_retype_pass = findViewById(R.id.et_retype_pass);

        update = (Button) findViewById(R.id.update_id);
        update.setOnClickListener(this);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_ll_id:
                finish();
                break;


            case R.id.update_id:
                if (et_old_pass.length()>5){
                    if (et_new_pass.length()>5){
                        if (et_retype_pass.getText().toString().equals(et_new_pass.getText().toString())){
                            METHOD_changePass();
                        }else {
                            et_retype_pass.setError("Incorrect Re-type Password !");
                        }
                    }else {
                        et_new_pass.setError("Incorrect New Password !");
                    }
                }else {
                    et_old_pass.setError("Incorrect Current Password !");
                }
                break;
        }
    }



    private void METHOD_changePass() {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", mPrefs.getString(Variables.User_id,""));
            sendobj.put("old_password", et_old_pass.getText().toString());
            sendobj.put("new_password", et_new_pass.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Functions.ShowProgressLoader(Change_Pass_A.this, false, false);
        ApiRequest.Call_Api(Change_Pass_A.this, Api_url.url_changePassword, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.CancelProgressLoader();
                if (resp!=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200"))
                            finish();
                        else
                            Functions.ShowToast(Change_Pass_A.this, ""+respobj.getJSONObject("msg").toString());


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

    }


}
