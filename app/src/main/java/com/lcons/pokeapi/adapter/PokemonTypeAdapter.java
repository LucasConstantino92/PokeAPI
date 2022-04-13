package com.lcons.pokeapi.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.lcons.pokeapi.Interface.ItemClickListener;
import com.lcons.pokeapi.R;
import com.lcons.pokeapi.common.Common;

import java.util.List;

public class PokemonTypeAdapter extends RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder> {
    Context context;
    List<String> typeList;

    public PokemonTypeAdapter(Context context, List<String> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.chip.setText(typeList.get(position));
        holder.chip.setChipBackgroundColor(ColorStateList.valueOf(Common.getColorByType(typeList.get(position))));

    }

    @Override
    public int getItemCount() {
        return typeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        Chip chip;
        ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = (Chip)itemView.findViewById(R.id.chip);
            chip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(view, getAdapterPosition());
                }
            });
        }
    }
}
