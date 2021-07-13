package com.example.pokepedia.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pokepedia.R;
import com.example.pokepedia.dialogs.LoadingDialog;
import com.example.pokepedia.viewModels.PokemonViewModel;

public class PokemonInfo extends Fragment {

    private PokemonViewModel mViewModel;
    private LoadingDialog dialog;
    private TextView pokemonTitle, pokeHeight, pokeWeight;
    private ImageView pokemonImage;

    private String pokemonName;
    private String imageUrl;
    private float height;
    private float weight;

    private boolean loading = true;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        assert getArguments() != null;
        pokemonName = getArguments().getString("pokemonName");
        View v = inflater.inflate(R.layout.pokemon_info_fragment, container, false);

        init(v);
        loadData(v);

        return v;
    }

    public void init(View v) {
        mViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        dialog = new LoadingDialog(getActivity());
        mViewModel.getPokemon(pokemonName);

        pokemonTitle = v.findViewById(R.id.pokemonName);
        pokemonImage = v.findViewById(R.id.pokemonImage);
        pokeHeight = v.findViewById(R.id.height_m);
        pokeWeight = v.findViewById(R.id.weight_kg);
    }

    public void loadData(View v) {
        if (loading) {
            dialog.startLoadingDialog();
        }

        mViewModel.get_result().observe(getViewLifecycleOwner(), pokemon -> {

            imageUrl = pokemon.getSprites().getFront_default();
            weight = (float) pokemon.getWeight() / 10;
            height = (float) pokemon.getHeight() / 10;

            Resources res = getResources();
            String weightKG = String.format(res.getString(R.string.weightKG), weight);
            String heightM = String.format(res.getString(R.string.heightM), height);

            pokemonTitle.setText(pokemonName);
            Glide.with(v).load(imageUrl).into(pokemonImage);
            pokeHeight.setText(heightM);
            pokeWeight.setText(weightKG);
            if (loading) {
                loading = false;
                dialog.dismiss();
            }
        });
    }




}