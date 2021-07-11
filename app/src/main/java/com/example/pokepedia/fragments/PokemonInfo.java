package com.example.pokepedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pokepedia.R;
import com.example.pokepedia.viewModels.PokemonViewModel;

public class PokemonInfo extends Fragment {

    private PokemonViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        View v = inflater.inflate(R.layout.pokemon_info_fragment, container, false);

        TextView tv = v.findViewById(R.id.pokemonName);
        assert getArguments() != null;
        tv.setText(getArguments().getString("pokemonName"));
        return v;
    }



}