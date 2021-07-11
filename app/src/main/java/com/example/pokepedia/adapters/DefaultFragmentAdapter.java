package com.example.pokepedia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokepedia.R;
import com.example.pokepedia.pokemon_classes.NameUrl;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DefaultFragmentAdapter extends RecyclerView.Adapter<DefaultFragmentAdapter.ViewHolder> {


    private List<NameUrl> data = new ArrayList<>();

    public void setData(List<NameUrl> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public DefaultFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull DefaultFragmentAdapter.ViewHolder holder, int position) {
        NameUrl nameUrl = data.get(position);
        holder.pokemonName.setText(nameUrl.getName());
        holder.itemView.setOnClickListener(v -> Toast.makeText(v.getContext(), "Loading Pokemon info", Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView pokemonName;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.list_item_tv);
        }
    }
}
