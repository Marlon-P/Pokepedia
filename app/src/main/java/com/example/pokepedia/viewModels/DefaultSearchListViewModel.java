package com.example.pokepedia.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokepedia.api.PokeApiService;
import com.example.pokepedia.api.PokeApiServiceGenerator;
import com.example.pokepedia.api.PokePageResults;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultSearchListViewModel extends ViewModel {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    private MutableLiveData<String> _status = new MutableLiveData<>();
    private MutableLiveData<PokePageResults> _results = new MutableLiveData<>();
    private PokeApiService service;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public DefaultSearchListViewModel() {
        service = PokeApiServiceGenerator.createService(PokeApiService.class);
        getDefaultSearchList();
    }



    private void getDefaultSearchList() {
        Call<PokePageResults> r = service.getPagedSearch(BASE_URL);
        isLoading.setValue(true);
        r.enqueue(new Callback<PokePageResults>() {
            @Override
            public void onResponse(@NotNull Call<PokePageResults> call, @NotNull Response<PokePageResults> response) {

               _results.setValue(response.body());
               _status.setValue("success");
               isLoading.setValue(false);

            }

            @Override
            public void onFailure(@NotNull Call<PokePageResults> call, @NotNull Throwable t) {
                _status.setValue("failure");
                isLoading.setValue(false);
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
