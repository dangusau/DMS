package com.dinosoftlabs.uber.HomeScreen.CareemPay.TopUp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dinosoftlabs.uber.R;

public class Topup_A extends AppCompatActivity implements View.OnClickListener {


    LinearLayout back,card,redem_voucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topup_a);



        METHOD_findviewbyid();

        METHOD_setonclicklistener();


    }






    protected void METHOD_findviewbyid(){
        back = (LinearLayout) findViewById(R.id.back_ll_id);
        card = (LinearLayout) findViewById(R.id.card_id);
        redem_voucher = (LinearLayout) findViewById(R.id.redem_voucher_id);
    }






    protected void METHOD_setonclicklistener(){
        back.setOnClickListener(this);
        card.setOnClickListener(this);
        redem_voucher.setOnClickListener(this);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.back_ll_id:
                finish();
                break;


            case R.id.card_id:
                startActivity(new Intent(Topup_A.this, Topup_Card_A.class));
                break;


            case R.id.redem_voucher_id:

                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                View view1 = LayoutInflater.from(getApplicationContext()).inflate(R.layout.alertdialog, null);

                EditText editText = (EditText) view1.findViewById(R.id.editText);

                alert.setTitle("Redeem Voucher");

                alert.setView(view1);

                alert.setPositiveButton("APPLY", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //What ever you want to do with the value
                    }
                });

                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // what ever you want to do with No option.
                    }
                });

                AlertDialog dialog = alert.create();
                dialog.show();

                break;

        }
    }
    
}
