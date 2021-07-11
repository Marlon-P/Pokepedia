package com.example.pokepedia.api;

import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class PokeApiServiceGenerator {
    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    private static Moshi moshi = new Moshi.Builder().build();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi));

    private static Retrofit retrofit = builder.build();

    public static <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
