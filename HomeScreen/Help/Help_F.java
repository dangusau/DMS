package com.dinosoftlabs.uber.HomeScreen.Help;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dinosoftlabs.uber.CodeClasses.RootFragment;
import com.dinosoftlabs.uber.R;


public class Help_F extends RootFragment implements View.OnClickListener {

    private View view;

    public Help_F() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.help_f, container, false);

        METHOD_findviewbyid();

        return view;
    }






    protected void METHOD_findviewbyid(){
        LinearLayout back_ll = (LinearLayout) view.findViewById(R.id.back_ll_id);
        back_ll.setOnClickListener(this);
    }






    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_ll_id:
                getActivity().onBackPressed();
                break;

        }
    }
}
