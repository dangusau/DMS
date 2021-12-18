package com.dinosoftlabs.uber.HomeScreen.Packages;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.CodeClasses.Variables;
import com.dinosoftlabs.uber.R;
import com.jcminarro.roundkornerlayout.RoundKornerRelativeLayout;


public class Packages_F extends RootFragment implements View.OnClickListener {

    private View view;

    private RelativeLayout three,six,ten,twenty;

    private LinearLayout back_ll;

    private RoundKornerRelativeLayout three_rides,six_rides,ten_rides,twenty_rides;
    private RoundKornerRelativeLayout
            three_rides_details,
            six_rides_details,
            ten_rides_details,
            twenty_rides_details;


    public Packages_F() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.packages_f, container, false);



        METHOD_findviewbyid();
        METHOD_customheightwidht();
        METHOD_onclicklistner();



        return view;

    }






    private void METHOD_findviewbyid(){
        three = (RelativeLayout) view.findViewById(R.id.three_rides_rl_id);
        six = view.findViewById(R.id.six_rides_rl_id);
        ten = view.findViewById(R.id.ten_rides_rl_id);
        twenty = view.findViewById(R.id.twenty_rides_rl_id);

        three_rides = (RoundKornerRelativeLayout) view.findViewById(R.id.three_rides_rkl_id);
        six_rides = view.findViewById(R.id.six_rides_rkl_id);
        ten_rides = view.findViewById(R.id.ten_rides_rkl_id);
        twenty_rides = view.findViewById(R.id.twenty_rides_rkl_id);

        three_rides_details = (RoundKornerRelativeLayout) view.findViewById(R.id.three_rides_details_id);
        six_rides_details = view.findViewById(R.id.six_rides_details_id);
        ten_rides_details = view.findViewById(R.id.ten_rides_details_id);
        twenty_rides_details = view.findViewById(R.id.twenty_rides_details_id);


        back_ll = (LinearLayout) view.findViewById(R.id.back_ll_id);
    }





    private void METHOD_customheightwidht(){
        double var = (Variables.height / 6.8) ;

        RelativeLayout.LayoutParams lp1 = (RoundKornerRelativeLayout.LayoutParams) three_rides.getLayoutParams();
        lp1.height = (int) var;

        three_rides.setLayoutParams(lp1);

        RelativeLayout.LayoutParams lp2 = (RoundKornerRelativeLayout.LayoutParams) six_rides.getLayoutParams();
        lp2.height = (int) var;

        six_rides.setLayoutParams(lp2);

        RelativeLayout.LayoutParams lp3 = (RoundKornerRelativeLayout.LayoutParams) ten_rides.getLayoutParams();
        lp3.height = (int) var;

        ten_rides.setLayoutParams(lp3);

        RelativeLayout.LayoutParams lp4 = (RoundKornerRelativeLayout.LayoutParams) twenty_rides.getLayoutParams();
        lp4.height = (int) var;

        twenty_rides.setLayoutParams(lp4);

    }






    private void METHOD_onclicklistner(){
        three.setOnClickListener(this);
        six.setOnClickListener(this);
        ten.setOnClickListener(this);
        twenty.setOnClickListener(this);
        back_ll.setOnClickListener(this);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_ll_id:
                getActivity().onBackPressed();
                break;


            case R.id.three_rides_rl_id:
                    three_rides.setVisibility(View.GONE);
                    six_rides.setVisibility(View.VISIBLE);
                    ten_rides.setVisibility(View.VISIBLE);
                    twenty_rides.setVisibility(View.VISIBLE);
                    three_rides_details.setVisibility(View.VISIBLE);
                    six_rides_details.setVisibility(View.GONE);
                    ten_rides_details.setVisibility(View.GONE);
                    twenty_rides_details.setVisibility(View.GONE);
                break;


            case R.id.six_rides_rl_id:
                    three_rides.setVisibility(View.VISIBLE);
                    six_rides.setVisibility(View.GONE);
                    ten_rides.setVisibility(View.VISIBLE);
                    twenty_rides.setVisibility(View.VISIBLE);
                    three_rides_details.setVisibility(View.GONE);
                    six_rides_details.setVisibility(View.VISIBLE);
                    ten_rides_details.setVisibility(View.GONE);
                    twenty_rides_details.setVisibility(View.GONE);
                break;


            case R.id.ten_rides_rl_id:
                    three_rides.setVisibility(View.VISIBLE);
                    six_rides.setVisibility(View.VISIBLE);
                    ten_rides.setVisibility(View.GONE);
                    twenty_rides.setVisibility(View.VISIBLE);
                    three_rides_details.setVisibility(View.GONE);
                    six_rides_details.setVisibility(View.GONE);
                    ten_rides_details.setVisibility(View.VISIBLE);
                    twenty_rides_details.setVisibility(View.GONE);
                break;


            case R.id.twenty_rides_rl_id:
                    three_rides.setVisibility(View.VISIBLE);
                    six_rides.setVisibility(View.VISIBLE);
                    ten_rides.setVisibility(View.VISIBLE);
                    twenty_rides.setVisibility(View.GONE);
                    three_rides_details.setVisibility(View.GONE);
                    six_rides_details.setVisibility(View.GONE);
                    ten_rides_details.setVisibility(View.GONE);
                    twenty_rides_details.setVisibility(View.VISIBLE);
                break;

        }
    }

}
