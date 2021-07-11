package com.example.pokepedia.api;

import com.example.pokepedia.pokemon_classes.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface PokeApiService {

    @GET("{pokemonName}")
    Call<Pokemon> getPokemon(@Path(value = "pokemonName") String pokemonName);

    @GET
    Call<PokePageResults> getPagedSearch(@Url String url);

}
