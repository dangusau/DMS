package com.dinosoftlabs.uber.HomeScreen.CareemPay.Credit_Card;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.R;
import com.jcminarro.roundkornerlayout.RoundKornerLinearLayout;
import com.DMS_Cust.DMS.HelpingClasses.Functions;
import com.ybs.countrypicker.Country;
import com.ybs.countrypicker.CountryPicker;
import com.ybs.countrypicker.CountryPickerListener;

import org.json.JSONException;
import org.json.JSONObject;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class Credit_card_A extends AppCompatActivity implements View.OnClickListener {


    private static final int MY_SCAN_REQUEST_CODE = 45 ;
    LinearLayout ll_back;
    RelativeLayout rl_card, rl_date, rl_cvv, rl_name, rl_country;

    ImageView iv_country_flag,iv_card_scanner;

    TextView tv_country_name;

    EditText et_card, et_date, et_cvv, et_name;

    RoundKornerLinearLayout rkl_save;


    String str_card_number, str_exp_month, str_exp_year, str_cvv, str_name;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credit_card_a);

        String fname = Variables.mPrefs.getString("First_name", "null");
        String lname = Variables.mPrefs.getString("Last_name", "null");
        Variables.user_name = fname +" "+lname;

        Variables.user_id = Variables.mPrefs.getString(Variables.User_id,"null");


        METHOD_findviewbyid();
        METHOD_setonclicklistener();


        TelephonyManager tm = (TelephonyManager)this.getSystemService(this.TELEPHONY_SERVICE);
        Country country= Country.getCountryByISO(tm.getSimCountryIso());
        if(country!=null) {
            country.loadFlagByCode(this);
            tv_country_name.setText(country.getName());
            iv_country_flag.setImageResource(country.getFlag());
        }


    }






    protected void METHOD_findviewbyid(){
        ll_back = (LinearLayout) findViewById(R.id.back_ll_id);
        iv_card_scanner = (ImageView) findViewById(R.id.iv_card_scanner);
        rl_card = (RelativeLayout) findViewById(R.id.card_rl_id);
        rl_date = findViewById(R.id.date_rl_id);
        rl_cvv = findViewById(R.id.cvv_rl_id);
        rl_name = findViewById(R.id.name_rl_id);
        rl_country = findViewById(R.id.country_rl_id);

        et_card = (EditText) findViewById(R.id.card_et_id);
        et_date = findViewById(R.id.date_et_id);
        et_cvv = findViewById(R.id.cvv_et_id);
        et_name = findViewById(R.id.name_et_id);
        et_name.setText(Variables.user_name);

        et_date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (et_date.length() == 1 && !et_date.getText().toString().equals("0")){
                    et_date.setText("0"+et_date.getText().toString()+"/");
                    et_date.setSelection(et_date.length());
                }
            }
        });

        iv_country_flag = (ImageView) findViewById(R.id.country_flag_id);
        tv_country_name = (TextView) findViewById(R.id.country_name_id);

        rkl_save =  findViewById(R.id.save_rkl_id);
    }






    protected void METHOD_setonclicklistener(){
        ll_back.setOnClickListener(this);

        rl_card.setOnClickListener(this);
        rl_date.setOnClickListener(this);
        rl_cvv.setOnClickListener(this);
        rl_name.setOnClickListener(this);
        rl_country.setOnClickListener(this);
        iv_card_scanner.setOnClickListener(this);

        rkl_save.setOnClickListener(this);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_ll_id:
                finish();
                break;


            case R.id.ll_card:
                METHOD_setFocusonEdittext(et_card);
                break;


            case R.id.date_rl_id:
                METHOD_setFocusonEdittext(et_date);
                break;


            case R.id.cvv_rl_id:
                METHOD_setFocusonEdittext(et_cvv);
                break;


            case R.id.name_rl_id:
                METHOD_setFocusonEdittext(et_name);
                break;


            case R.id.country_rl_id:

                final CountryPicker picker = CountryPicker.newInstance("Select Country");
                picker.setListener(new CountryPickerListener() {
                    @Override
                    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
                        // Implement your code here
                        iv_country_flag.setImageResource(flagDrawableResID);
                        tv_country_name.setText(name);
                        picker.dismiss();
                    }
                });

                picker.setStyle(DialogFragment.STYLE_NO_FRAME, R.style.countrypicker_style);
                picker.show(getSupportFragmentManager(), "Select Country");
                break;


            case R.id.iv_card_scanner:
                Intent scanIntent = new Intent(this, CardIOActivity.class);

                // customize these values to suit your needs.
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, true);

                // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
                startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
                break;


            case R.id.save_rkl_id:
                METHOD_checkValidation();
                break;
        }
    }




    private void METHOD_setFocusonEdittext(EditText et) {
        et.setFocusableInTouchMode(true);
        et.setFocusable(true);
        et.requestFocus();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;

            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw rl_card number. Avoid displaying it,
                // but if necessary use getFormattedCardNumber()
                resultDisplayStr = scanResult.getRedactedCardNumber() + "\n";
                //et_card.setText(resultDisplayStr.toString());
                et_card.setText(scanResult.getFormattedCardNumber());
                //Do something with the raw number, e.g.
                //myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    String expiredate = scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                    et_date.setText(expiredate);
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    String cvv = scanResult.cvv.length() + " digits.\n";
                    et_cvv.setText(cvv);
                }

                if (scanResult.cardholderName != null) {
                    String name =  scanResult.cardholderName + "\n";
                    et_name.setText(name);
                }

                if (scanResult.postalCode != null)
                    resultDisplayStr += "Postal code: " + scanResult.postalCode + "\n";

            }
            else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultDisplayStr);
        }// else handle other activity results
    }




    private void METHOD_checkValidation(){
        if (et_card.length()==16){
            if (et_date.length()==7) {
                /*if (et_cvv.length() == 4) {*/
                    if (et_name.length() > 0) {
                        if (tv_country_name != null){
                            str_card_number = et_card.getText().toString();
                            String date = et_date.getText().toString();
                            String[] split_string = date.split("/");
                            str_exp_month = split_string[0];
                            str_exp_year = split_string[1];
                            str_cvv = et_cvv.getText().toString();
                            str_name = et_name.getText().toString();

                            METHOD_addPaymentCard(str_card_number,
                                    str_exp_month,
                                    str_exp_year,
                                    str_cvv,
                                    str_name);
                        }else
                            Functions.ShowToast(getApplicationContext(),"Please select rl_country");
                    }else
                        et_name.setError("Name can't be empty!");
                /*}else
                    et_cvv.setError("CVV must be 4 digit code");*/
            }else
                et_date.setError("Incorrect Date");
        }else
            et_card.setError("Incorrect Card Number");

    }




    private void METHOD_addPaymentCard(String cardnumber,String expi_month,String expi_year,
                                       String cvv, String username){
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.user_id);
            sendobj.put("name", username);
            sendobj.put("card", cardnumber);
            sendobj.put("cvc", "123");
            sendobj.put("exp_month", expi_month);
            sendobj.put("exp_year", expi_year);
            sendobj.put("default", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Functions.ShowProgressLoader(Credit_card_A.this,false,false);
        ApiRequest.Call_Api(getApplicationContext(), Api_url.url_addPaymentMethod, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.CancelProgressLoader();

                if (resp !=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").equals("200")){

                            Intent results = new Intent();
                            results.putExtra("resp", "ok");
                            setResult(Credit_card_A.RESULT_OK,results);

                            finish();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

}
