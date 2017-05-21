package com.faizal.bikesmap;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faizal.bikesmap.Model.BikeInfo;

import java.util.List;

/**
 * Created by fpatel on 21/05/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<BikeInfo> bikeList;

    public Adapter(List<BikeInfo> bikeList) {
        this.bikeList = bikeList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bikeinforow, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.name.setText(bikeList.get(position).getName());
        holder.number.setText(bikeList.get(position).getNumber());
        holder.address.setText(bikeList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return bikeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, number, address;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            number = (TextView) itemView.findViewById(R.id.number);
            address = (TextView) itemView.findViewById(R.id.address);

        }
    }
}
