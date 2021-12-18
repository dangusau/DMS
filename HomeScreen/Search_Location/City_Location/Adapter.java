package com.dinosoftlabs.uber.HomeScreen.Search_Location.City_Location;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dinosoftlabs.uber.R;
import com.kodmap.library.kmrecyclerviewstickyheader.KmStickyListener;

public class Adapter extends ListAdapter<Model, RecyclerView.ViewHolder> implements KmStickyListener {

    public Adapter() {
        super(ModelDiffUtilCallback);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView;
        if (viewType == ItemType.Header) {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_city_loc_header, viewGroup, false);
            return new HeaderViewHolder(itemView);
        } else {
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_city_loc_child, viewGroup, false);
            return new PostViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (getItemViewType(i) == ItemType.Header) {
            ((HeaderViewHolder) viewHolder).bind(getItem(i));
        } else {
            ((PostViewHolder) viewHolder).bind(getItem(i));
        }
    }


    class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_id);
        }

        void bind(Model model) {
            title.setText(model.title);
        }
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_id);
        }

        void bind(Model model) {
            title.setText(model.title);
        }
    }




    @Override
    public int getItemViewType(int position) {
        return getItem(position).type;
    }




    @Override
    public Integer getHeaderPositionForItem(Integer itemPosition) {
        Integer headerPosition = 0;
        for (Integer i = itemPosition;i > 0 ;i--){
            if (isHeader(i)){
                headerPosition = i;
                return headerPosition;
            }
        }
        return headerPosition;
    }




    @Override
    public Integer getHeaderLayout(Integer headerPosition) {
        return R.layout.search_city_loc_header;
    }




    @Override
    public void bindHeaderData(View header, Integer headerPosition) {
        TextView tv = header.findViewById(R.id.tv_id);
        tv.setText(getItem(headerPosition).title);
    }




    @Override
    public Boolean isHeader(Integer itemPosition) {
        return getItem(itemPosition).type.equals(ItemType.Header);
    }




    private static final DiffUtil.ItemCallback<Model> ModelDiffUtilCallback =
            new DiffUtil.ItemCallback<Model>() {
                @Override
                public boolean areItemsTheSame(@NonNull Model model, @NonNull Model t1) {
                    return model.title.equals(t1.title);
                }

                @Override
                public boolean areContentsTheSame(@NonNull Model model, @NonNull Model t1) {
                    return model.title.equals(t1);
                }
            };
}