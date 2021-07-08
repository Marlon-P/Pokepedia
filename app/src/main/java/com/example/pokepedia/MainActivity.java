package com.example.pokepedia;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokepedia.api.PokeApiService;
import com.example.pokepedia.api.PokePageResults;
import com.squareup.moshi.Moshi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Moshi moshi = new Moshi.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/pokemon/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();

        TextView txt = findViewById(R.id.textView);

        PokeApiService apiService = retrofit.create(PokeApiService.class);
        Call<PokePageResults> results = apiService.getDefaultSearch("https://pokeapi.co/api/v2/pokemon/");
        results.enqueue(new Callback<PokePageResults>() {
            @Override
            public void onResponse(@NonNull Call<PokePageResults> call, @NonNull Response<PokePageResults> response) {
                if (response.code() != 200) {
                    txt.setText(R.string.check_connection);
                    return;
                }

                String json;
                PokePageResults r = response.body();
                assert r != null;



                txt.setText(r.toString());
            }


            @Override
            public void onFailure(@NonNull Call<PokePageResults> call, @NonNull Throwable t) {

            }
        });
    }
}