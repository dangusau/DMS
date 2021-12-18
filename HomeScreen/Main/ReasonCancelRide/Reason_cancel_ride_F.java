package com.dinosoftlabs.uber.HomeScreen.Main.ReasonCancelRide;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.R;


public class Reason_cancel_ride_F extends Fragment implements View.OnClickListener {

    private View view;

    private LinearLayout ll1,ll2,ll3,ll4,ll5,ll6,ll7,ll8;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8;
    private ImageView iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8;

    private TextView btn_submit;

    private String text = "null";

    Fragment_CallBack callBack;


    public Reason_cancel_ride_F() {
        // Required empty public constructor
    }

    public Reason_cancel_ride_F(Fragment_CallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.reason_cancel_ride_f, container, false);


        METHOD_findviewbyid();

        return view;
    }







    private void METHOD_findviewbyid() {
        ll1 = (LinearLayout) view.findViewById(R.id.ll1_id);
        ll1.setOnClickListener(this);
        ll2 = view.findViewById(R.id.ll2_id);
        ll2.setOnClickListener(this);
        ll3 = view.findViewById(R.id.ll3_id);
        ll3.setOnClickListener(this);
        ll4 = view.findViewById(R.id.ll4_id);
        ll4.setOnClickListener(this);
        ll5 = view.findViewById(R.id.ll5_id);
        ll5.setOnClickListener(this);
        ll6 = view.findViewById(R.id.ll6_id);
        ll6.setOnClickListener(this);
        ll7 = view.findViewById(R.id.ll7_id);
        ll7.setOnClickListener(this);
        ll8 = view.findViewById(R.id.ll8_id);
        ll8.setOnClickListener(this);

        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        tv5 = view.findViewById(R.id.tv5);
        tv6 = view.findViewById(R.id.tv6);
        tv7 = view.findViewById(R.id.tv7);
        tv8 = view.findViewById(R.id.tv8);

        iv1 = (ImageView) view.findViewById(R.id.iv_check1);
        iv2 = view.findViewById(R.id.iv_check2);
        iv3 = view.findViewById(R.id.iv_check3);
        iv4 = view.findViewById(R.id.iv_check4);
        iv5 = view.findViewById(R.id.iv_check5);
        iv6 = view.findViewById(R.id.iv_check6);
        iv7 = view.findViewById(R.id.iv_check7);
        iv8 = view.findViewById(R.id.iv_check8);

        btn_submit = view.findViewById(R.id.btn_sumbit);
        btn_submit.setOnClickListener(this);
    }







    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll1_id:
                METHOD_changebackground(ll1,ll2,ll3,ll4,ll5,ll6,ll7,ll8);
                METHOD_changepicture(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8);
                text = tv1.getText().toString();
                break;


            case R.id.ll2_id:
                METHOD_changebackground(ll2,ll1,ll3,ll4,ll5,ll6,ll7,ll8);
                METHOD_changepicture(iv2,iv1,iv3,iv4,iv5,iv6,iv7,iv8);
                text = tv2.getText().toString();
                break;


            case R.id.ll3_id:
                METHOD_changebackground(ll3,ll2,ll1,ll4,ll5,ll6,ll7,ll8);
                METHOD_changepicture(iv3,iv2,iv1,iv4,iv5,iv6,iv7,iv8);
                text = tv3.getText().toString();
                break;


            case R.id.ll4_id:
                METHOD_changebackground(ll4,ll2,ll3,ll1,ll5,ll6,ll7,ll8);
                METHOD_changepicture(iv4,iv2,iv3,iv1,iv5,iv6,iv7,iv8);
                text = tv4.getText().toString();
                break;


            case R.id.ll5_id:
                METHOD_changebackground(ll5,ll2,ll3,ll4,ll1,ll6,ll7,ll8);
                METHOD_changepicture(iv5,iv2,iv3,iv4,iv1,iv6,iv7,iv8);
                text = tv5.getText().toString();
                break;


            case R.id.ll6_id:
                METHOD_changebackground(ll6,ll2,ll3,ll4,ll5,ll1,ll7,ll8);
                METHOD_changepicture(iv6,iv2,iv3,iv4,iv5,iv1,iv7,iv8);
                text = tv6.getText().toString();
                break;


            case R.id.ll7_id:
                METHOD_changebackground(ll7,ll2,ll3,ll4,ll5,ll6,ll1,ll8);
                METHOD_changepicture(iv7,iv2,iv3,iv4,iv5,iv6,iv1,iv8);
                text = tv7.getText().toString();
                break;


            case R.id.ll8_id:
                METHOD_changebackground(ll8,ll2,ll3,ll4,ll5,ll6,ll7,ll1);
                METHOD_changepicture(iv8,iv2,iv3,iv4,iv5,iv6,iv7,iv1);
                text = tv8.getText().toString();
                break;


            case R.id.btn_sumbit:
                Bundle bundle = new Bundle();
                bundle.putString("key", text);
                callBack.On_Item_Click(0,bundle);

                getFragmentManager().popBackStackImmediate();
                break;

        }
    }







    private void METHOD_changebackground(LinearLayout ll1,LinearLayout ll2,LinearLayout ll3,
                                         LinearLayout ll4,LinearLayout ll5,LinearLayout ll6,
                                         LinearLayout ll7,LinearLayout ll8){
        ll1.setBackgroundColor(getResources().getColor(R.color.light_green1));
        ll2.setBackgroundColor(getResources().getColor(R.color.white));
        ll3.setBackgroundColor(getResources().getColor(R.color.white));
        ll4.setBackgroundColor(getResources().getColor(R.color.white));
        ll5.setBackgroundColor(getResources().getColor(R.color.white));
        ll6.setBackgroundColor(getResources().getColor(R.color.white));
        ll7.setBackgroundColor(getResources().getColor(R.color.white));
        ll8.setBackgroundColor(getResources().getColor(R.color.white));
        btn_submit.setBackgroundColor(getResources().getColor(R.color.green));
    }






    private void METHOD_changepicture(ImageView iv1,ImageView iv2,ImageView iv3,
                                      ImageView iv4,ImageView iv5,ImageView iv6,
                                      ImageView iv7,ImageView iv8){
        iv1.setImageResource(R.drawable.ic_check_green);
        iv2.setImageResource(R.drawable.ic_blue_ring);
        iv3.setImageResource(R.drawable.ic_blue_ring);
        iv4.setImageResource(R.drawable.ic_blue_ring);
        iv5.setImageResource(R.drawable.ic_blue_ring);
        iv6.setImageResource(R.drawable.ic_blue_ring);
        iv7.setImageResource(R.drawable.ic_blue_ring);
        iv8.setImageResource(R.drawable.ic_blue_ring);
    }

}
