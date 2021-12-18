package com.dinosoftlabs.uber.HomeScreen.CareemPay;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dinosoftlabs.uber.R;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

public class EnterAmount_A extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ll_back,ll_pkr;
    TextView tv_pkr;
    EditText et_pkr;

    RoundKornerLinearLayout confirm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_amount_a);


        METHOD_findviewbyid();

        METHOD_setonclicklistener();

    }






    protected void METHOD_findviewbyid(){
        ll_back = (LinearLayout) findViewById(R.id.ll_back);
        ll_pkr = (LinearLayout) findViewById(R.id.ll_pkr);

        tv_pkr = (TextView) findViewById(R.id.tv_pkr);

        confirm = (RoundKornerLinearLayout) findViewById(R.id.confirm_rkl_id);

        et_pkr = (EditText) findViewById(R.id.et_pkr);


        et_pkr.setFocusableInTouchMode(true);
        et_pkr.setFocusable(true);
        et_pkr.requestFocus();

    }






    protected void METHOD_setonclicklistener(){
        ll_back.setOnClickListener(this);

        ll_pkr.setOnClickListener(this);
        tv_pkr.setOnClickListener(this);
    }






    protected void METHOD_modifyedittext(){
        int len = et_pkr.length();
        if (len>0){
            confirm.setBackgroundColor(getResources().getColor(R.color.green));
        }else {
            confirm.setBackgroundColor(getResources().getColor(R.color.light_green));
        }
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.back_ll_id:
                finish();
                break;

            case R.id.tv_pkr:
                et_pkr.setFocusableInTouchMode(true);
                et_pkr.setFocusable(true);
                et_pkr.requestFocus();


                METHOD_modifyedittext();

                break;

        }
    }

}
