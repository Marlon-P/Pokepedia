package com.example.pokepedia.api;

import com.example.pokepedia.pokemon_classes.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PokeApiService {

    @GET("bulbasaur")
    Call<Pokemon> getPokemon();

    @GET
    Call<PokePageResults> getDefaultSearch(@Url String url);
}
