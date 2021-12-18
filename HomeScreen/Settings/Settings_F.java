package com.dinosoftlabs.uber.HomeScreen.Settings;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.dinosoftlabs.uber.CodeClasses.ApiRequest;
import com.dinosoftlabs.uber.CodeClasses.Api_url;
import com.dinosoftlabs.uber.CodeClasses.Functions;
import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.Interfaces.Callback;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.Login.Email_A;
import com.dinosoftlabs.uber.Login.Login_A;
import com.dinosoftlabs.uber.Login.Name_A;
import com.dinosoftlabs.uber.Login.VerifyNumber_A;
import com.dinosoftlabs.uber.R;
import com.DMS_Cust.DMS.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.dinosoftlabs.uber.CodeClasses.Variables.mPrefs;


public class Settings_F extends RootFragment implements View.OnClickListener {

    private View view;

    private LinearLayout ll_back;

    private RelativeLayout rl_name,rl_mbl_num,rl_email,rl_change_pass,rl_gender,rl_dob,rl_language;

    private TextView tv_username,tv_phone_no,tv_email,tv_gender,tv_new_gender,tv_dob,tv_new_dob,
            tv_language,tv_logout;

    private String selection,gender,dob;
    private SharedPreferences.Editor prefsEditor;
    private String[] item_list = {"Male","Female","Prefer not to specify"};

    final Calendar myCalendar = Calendar.getInstance();

    Fragment_CallBack callBack;

    public Settings_F() {
    }

    public Settings_F(Fragment_CallBack callBack) {
        this.callBack = callBack;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.settings_f, container, false);

        gender = ""+Variables.mPrefs.getString(Variables.gender,"");
        dob = ""+Variables.mPrefs.getString(Variables.dob,"");

        prefsEditor = Variables.mPrefs.edit();



        METHOD_findviewbyid();

        METHOD_setonclicklistener();



        return view;

    }







    protected void METHOD_findviewbyid(){
        ll_back = (LinearLayout) view.findViewById(R.id.ll_back);

        rl_name = (RelativeLayout) view.findViewById(R.id.rl_name);
        rl_mbl_num = view.findViewById(R.id.rl_mbl_num);
        rl_email = view.findViewById(R.id.rl_email);
        rl_change_pass = view.findViewById(R.id.rl_change_pass);
        rl_gender = view.findViewById(R.id.rl_gender);
        rl_dob = view.findViewById(R.id.rl_dob);
        rl_language = view.findViewById(R.id.rl_language);



        tv_username = (TextView) view.findViewById(R.id.tv_username);
        tv_username.setText(Variables.mPrefs.getString(Variables.fname,"")+" "+
                Variables.mPrefs.getString(Variables.lname,""));

        tv_email = view.findViewById(R.id.tv_email);
        tv_email.setText(Variables.mPrefs.getString(Variables.email,""));

        tv_phone_no = view.findViewById(R.id.tv_phone_no);
        tv_phone_no.setText(Variables.mPrefs.getString(Variables.phone_no,""));

        tv_language = view.findViewById(R.id.tv_language);
        if (Variables.mPrefs.getString(Variables.setlocale,"").equals("en"))
            tv_language.setText(getString(R.string.language_en));
        else
            tv_language.setText(getString(R.string.language_ar));

        tv_logout = view.findViewById(R.id.tv_logout);
        tv_dob = view.findViewById(R.id.tv_dob);
        tv_new_dob = view.findViewById(R.id.tv_new_dob);
        tv_gender = view.findViewById(R.id.tv_gender);
        tv_new_gender = view.findViewById(R.id.tv_new_gender);

        if (gender != null && !gender.isEmpty()) {
            tv_gender.setText(gender);
            tv_new_gender.setVisibility(View.GONE);
        }else {
            tv_gender.setText("Not specified");
            tv_new_gender.setVisibility(View.VISIBLE);
        }

        if (!dob.equals("0000-00-00")) {
            String formated_dob = Functions.Change_date_format("yyyy-MM-dd",
                    "dd MMMM yyyy", dob);

            tv_dob.setText(formated_dob);
            tv_new_dob.setVisibility(View.GONE);
        }else {
            tv_dob.setText("Not Added");
            tv_new_dob.setVisibility(View.VISIBLE);
        }
    }









    private void METHOD_setonclicklistener(){
        ll_back.setOnClickListener(this);

        rl_name.setOnClickListener(this);
        rl_mbl_num.setOnClickListener(this);
        rl_email.setOnClickListener(this);
        rl_change_pass.setOnClickListener(this);
        rl_gender.setOnClickListener(this);
        rl_dob.setOnClickListener(this);
        rl_language.setOnClickListener(this);

        tv_logout.setOnClickListener(this);
    }







    private void METHOD_openLanguageDialog() {
        CharSequence[] language_list = {getString(R.string.language_ar),getString(R.string.language_en)};
        Functions.open_popup_with_list(getActivity(), "Select Language",
                language_list, new Callback() {
            @Override
            public void Responce(String resp) {
                if (resp.equalsIgnoreCase(getString(R.string.language_ar))){
                    setLocale("ar");
                }else {
                    setLocale("en");
                }
            }
        });
    }

    private void setLocale(String lang) {
        Variables.mPrefs.edit().putString(Variables.setlocale, lang).commit();
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = new Configuration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        onConfigurationChanged(conf);
        getActivity().recreate();
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.ll_back:
                getActivity().onBackPressed();
                break;


            case R.id.rl_name:
                METHOD_openFragment(new Name_A(new Fragment_CallBack() {
                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        tv_username.setText(Variables.mPrefs.getString(Variables.fname,"")+" "
                                +Variables.mPrefs.getString(Variables.lname,""));
                    }
                }));
                break;


            case R.id.rl_mbl_num:
                METHOD_openFragment(new VerifyNumber_A());
                break;


            case R.id.rl_email:
                METHOD_openFragment(new Email_A(new Fragment_CallBack() {
                    @Override
                    public void On_Item_Click(int postion, Bundle bundle) {
                        tv_email.setText(Variables.mPrefs.getString(Variables.email,""));
                    }
                }));
                break;


            case R.id.rl_change_pass:
                startActivity(new Intent(getActivity(), Change_Pass_A.class));
                break;


            case R.id.rl_gender:
                METHOD_selectGender();
                break;


            case R.id.rl_dob:
                METHOD_selectDateofBirth();
                break;


            case R.id.rl_language:
                METHOD_openLanguageDialog();
                break;


            case R.id.tv_logout:
                METHOD_clearSharedPref();

                Intent intent = new Intent(getActivity(), Login_A.class);
                Login_A.login = false;
                Login_A.account = false;
                startActivity(intent);

                getActivity().finish();
                break;
        }
    }



    int item_checked = 2;
    private void METHOD_selectGender(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MyDialogStyle);
        builder.setTitle("Your gender");

        if (gender.equals("Male"))
            item_checked = 0;
        else if (gender.equals("Female"))
            item_checked = 1;
        else
            item_checked = 2;

        builder.setSingleChoiceItems( item_list, item_checked, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selection = item_list[item];
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv_gender.setText(selection);
                gender = ""+selection;

                if (!selection.equals("Prefer not to specify")) {
                    METHOD_editProfile(true, selection, "");
                    tv_new_gender.setVisibility(View.GONE);
                }else
                    tv_new_gender.setVisibility(View.VISIBLE);


                dialogInterface.dismiss();
            }

        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }

        });

        AlertDialog alertDialog1= builder.create();
        alertDialog1.show();
    }




    private void METHOD_selectDateofBirth(){
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabel();
            }

        };

        DatePickerDialog dob_dialog = new DatePickerDialog(getContext(), R.style.datepicker,
                date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dob_dialog.show();

    }



    private void updateLabel() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        tv_dob.setText(sdf.format(myCalendar.getTime()));


        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf_ = new SimpleDateFormat(format, Locale.getDefault());

        String dob = sdf_.format(myCalendar.getTime());
        if (!tv_dob.getText().toString().equals("Not Added")) {
            METHOD_editProfile(false, "", dob);
            tv_new_dob.setVisibility(View.GONE);
        }else
            tv_new_dob.setVisibility(View.VISIBLE);
    }




    private void METHOD_editProfile(Boolean check, String gender, String dob) {
        JSONObject sendobj = new JSONObject();

        try {
            sendobj.put("user_id", Variables.mPrefs.getString(Variables.User_id, null));
            if (check)
                sendobj.put("gender", gender);
            else
                sendobj.put("dob", dob);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        Functions.show_loader(getContext(),false,false);
        ApiRequest.Call_Api(getContext(), Api_url.url_editProfile, sendobj, new Callback() {
            @Override
            public void Responce(String resp) {
                Functions.cancel_loader();
                if (resp !=null){

                    try {
                        JSONObject respobj = new JSONObject(resp);

                        if (respobj.getString("code").contains("200")){
                            JSONObject userobj = respobj.getJSONObject("msg").getJSONObject("User");

                            if (check) {
                                String gender = ""+userobj.optString("gender");
                                prefsEditor.putString(Variables.gender, gender).commit();
                            }else {
                                String dob = ""+userobj.optString("dob");
                                prefsEditor.putString(Variables.dob, dob).commit();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }





    private void METHOD_clearSharedPref(){
        prefsEditor.putBoolean("login", false);
        prefsEditor.remove(Variables.User_id);
        prefsEditor.remove(Variables.fname);
        prefsEditor.remove(Variables.lname);
        prefsEditor.remove(Variables.email);
        prefsEditor.remove(Variables.image);
        prefsEditor.remove(Variables.phone_no);
        prefsEditor.remove("Device_token");
        prefsEditor.remove("Role");
        prefsEditor.commit();
    }





    private void METHOD_openFragment(Fragment f){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.in_from_right, R.anim.out_to_left, R.anim.in_from_left, R.anim.out_to_right);

        ft.replace(R.id.fl_id, f).addToBackStack(null).commit();
    }


}
