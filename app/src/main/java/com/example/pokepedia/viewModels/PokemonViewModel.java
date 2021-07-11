package com.example.pokepedia.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokepedia.api.PokeApiService;
import com.example.pokepedia.api.PokeApiServiceGenerator;
import com.example.pokepedia.pokemon_classes.Pokemon;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonViewModel extends ViewModel {

    private MutableLiveData<String> _status = new MutableLiveData<>();
    private MutableLiveData<Pokemon> _result = new MutableLiveData<>();
    private PokeApiService service;

    public PokemonViewModel() {
        service = PokeApiServiceGenerator.createService(PokeApiService.class);
    }

    public void getPokemon(String pokemonName) {
        Call<Pokemon> pokemon = service.getPokemon(pokemonName);
        pokemon.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(@NotNull Call<Pokemon> call, @NotNull Response<Pokemon> response) {
                _result.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<Pokemon> call, @NotNull Throwable t) {

            }
        });
    }

    public MutableLiveData<Pokemon> get_result() {
        return _result;
    }


}
