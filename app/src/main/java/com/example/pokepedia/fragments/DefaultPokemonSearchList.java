package com.example.pokepedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.pokepedia.R;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DefaultPokemonSearchList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DefaultPokemonSearchList extends Fragment {


    public static DefaultPokemonSearchList newInstance(String param1, String param2) {

        return new DefaultPokemonSearchList();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_default_pokemon_search_list, container, false);
        setUpApi();
        init(v);
        return v;
    }


    private void setUpApi() {
        Moshi moshi = new Moshi.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }

    private void init(View v) {

    }
}