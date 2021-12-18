package com.dinosoftlabs.uber.HomeScreen.CareemPay.TopUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card.Credit_card_A;
import com.dinosoftlabs.uber.HomeScreen.CareemPay.EnterAmount_A;
import com.dinosoftlabs.uber.R;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;

public class Topup_Card_A extends AppCompatActivity implements View.OnClickListener {


    LinearLayout back;

    RoundKornerLinearLayout add_card,pkr1,pkr2,pkr3,pkr4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topup_card_a);



        METHOD_findviewbyid();
        METHOD_setonclicklistener();

    }






    protected void METHOD_findviewbyid(){
        back = (LinearLayout) findViewById(R.id.back_ll_id);
        add_card = (RoundKornerLinearLayout) findViewById(R.id.add_card_rkl_id);
        pkr1 = (RoundKornerLinearLayout) findViewById(R.id.pkr1_rkl_id);
        pkr2 = (RoundKornerLinearLayout) findViewById(R.id.pkr2_rkl_id);
        pkr3 = (RoundKornerLinearLayout) findViewById(R.id.pkr3_rkl_id);
        pkr4 = (RoundKornerLinearLayout) findViewById(R.id.pkr4_rkl_id);
    }






    protected void METHOD_setonclicklistener(){
        back.setOnClickListener(this);
        add_card.setOnClickListener(this);
        pkr1.setOnClickListener(this);
        pkr2.setOnClickListener(this);
        pkr3.setOnClickListener(this);
        pkr4.setOnClickListener(this);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.back_ll_id:
                finish();
                break;

            case R.id.add_card_rkl_id:
                startActivity(new Intent(Topup_Card_A.this, Credit_card_A.class));
                break;

            case R.id.pkr1_rkl_id:
                pkr1.setBackgroundColor(getResources().getColor(R.color.green));
                pkr2.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr3.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr4.setBackgroundColor(getResources().getColor(R.color.offwhite));
                break;

            case R.id.pkr2_rkl_id:
                pkr1.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr2.setBackgroundColor(getResources().getColor(R.color.green));
                pkr3.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr4.setBackgroundColor(getResources().getColor(R.color.offwhite));
                break;

            case R.id.pkr3_rkl_id:
                pkr1.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr2.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr3.setBackgroundColor(getResources().getColor(R.color.green));
                pkr4.setBackgroundColor(getResources().getColor(R.color.offwhite));
                break;

            case R.id.pkr4_rkl_id:
                pkr1.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr2.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr3.setBackgroundColor(getResources().getColor(R.color.offwhite));
                pkr4.setBackgroundColor(getResources().getColor(R.color.green));

                startActivity(new Intent(Topup_Card_A.this, EnterAmount_A.class));

                break;

        }
    }

}
