package com.dinosoftlabs.uber.HomeScreen.BottomSheetDialoge_F.Select_Vehicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.Interfaces.Fragment_CallBack;
import com.dinosoftlabs.uber.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Select_vehicle_Bts_F extends BottomSheetDialogFragment {

    private View view;

    private RecyclerView rv;
    private Adapter adp;
    private List<Vehicle_Model_Class> list = new ArrayList<>();

    public static String id;

    Fragment_CallBack callback;


    public Select_vehicle_Bts_F(List<Vehicle_Model_Class> list,String id,
                                Fragment_CallBack callback){
        this.list.clear();
        this.list = list;
        this.id = id;
        this.callback = callback;
    }




    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.select_vehicle_bts, container, false);


        METHOD_findviewbyid();


        return view;
    }






    protected void METHOD_findviewbyid(){
        rv = view.findViewById(R.id.rv_id);

        rv.setHasFixedSize(false);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        METHOD_recyclerview();
    }






    private void METHOD_recyclerview(){

        adp = new Adapter(getContext(), list, new Adapter_ClickListener() {
            @Override
            public void On_Item_Click(int postion, Object Model, View view) {
                View id = view.findViewById(R.id.view_id);
                id.setVisibility(View.VISIBLE);

                Bundle bundle = new Bundle();
                bundle.putSerializable("obj", (Serializable) Model);
                callback.On_Item_Click(postion,bundle);

                dismiss();
            }

            @Override
            public void On_Item_Long_Click(int postion, Object Model, View view) {

            }
        });

        rv.setAdapter(adp);
    }

}
