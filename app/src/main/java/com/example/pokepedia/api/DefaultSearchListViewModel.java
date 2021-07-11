package com.example.pokepedia.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultSearchListViewModel extends ViewModel {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    private MutableLiveData<String> _status = new MutableLiveData<>();
    private MutableLiveData<PokePageResults> _results = new MutableLiveData<>();
    private PokeApiService service;

    public DefaultSearchListViewModel() {
        service = PokeApiServiceGenerator.createService(PokeApiService.class);
        getDefaultSearchList();
    }



    private void getDefaultSearchList() {
        Call<PokePageResults> r = service.getPagedSearch(BASE_URL);
        r.enqueue(new Callback<PokePageResults>() {
            @Override
            public void onResponse(@NotNull Call<PokePageResults> call, @NotNull Response<PokePageResults> response) {

               _results.setValue(response.body());
               _status.setValue("success");

            }

            @Override
            public void onFailure(@NotNull Call<PokePageResults> call, @NotNull Throwable t) {
                _status.setValue("failure");
            }
        });
    }

    public String get_status() {

        return _status.getValue();
    }

    public LiveData<PokePageResults> get_results() {
        return _results;
    }


}
