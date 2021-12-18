package com.dinosoftlabs.uber.HomeScreen.Search_Location.Nearby_Location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.Interfaces.Adapter_ClickListener;
import com.dinosoftlabs.uber.R;

import java.util.List;

public class Nearby_Adapter extends RecyclerView.Adapter<Nearby_Adapter.ViewHolder> {

    private List<Nearby_Model_Class> list;
    private Adapter_ClickListener onitemclick;
    private List<String> ids;

    public Nearby_Adapter(List<Nearby_Model_Class> list,
                          List<String> ids,
                          Context context,
                          Adapter_ClickListener onClickListner){
        this.list = list;
        this.onitemclick = onClickListner;
        this.ids = ids;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_child_item,null);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

        Nearby_Model_Class model = list.get(i);

        viewHolder.tv_loc.setText(model.Title);
        viewHolder.tv_location_desc.setText(model.Address);

        viewHolder.bind(i, model, onitemclick);

        if(ids!=null){
            if(ids.contains(model.place_id))
                viewHolder.iv_fav.setImageResource(R.drawable.ic_favorite);
        }else
            viewHolder.iv_fav.setImageResource(R.drawable.ic_favorite);

    }


    public void updateList(List<Nearby_Model_Class> sorted_list){
        list = sorted_list;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_loc,tv_location_desc;
        ImageView iv_fav;
        RelativeLayout rl_fav;

        ViewHolder(View itemView) {
            super(itemView);

            tv_loc = itemView.findViewById(R.id.tv_loc);

            tv_location_desc =itemView.findViewById(R.id.tv_location_desc);
            iv_fav =itemView.findViewById(R.id.iv_fav);
            rl_fav = itemView.findViewById(R.id.rl_fav);

        }




        void bind(final int pos, final Nearby_Model_Class item, final Adapter_ClickListener listener){

            iv_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.On_Item_Click(pos,item,view);
                }
            });

            rl_fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.On_Item_Click(pos,item,view);
                }
            });

        }
    }
}
