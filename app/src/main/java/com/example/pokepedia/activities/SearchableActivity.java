package com.example.pokepedia.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pokepedia.R;
import com.example.pokepedia.databinding.ActivitySearchableBinding;
import com.example.pokepedia.dialogs.LoadingDialog;
import com.example.pokepedia.pokemon_classes.Type;
import com.example.pokepedia.viewModels.PokemonViewModel;

import java.util.Objects;

public class SearchableActivity extends AppCompatActivity {

    private ActivitySearchableBinding binding;

    private PokemonViewModel mViewModel;
    private LoadingDialog dialog;
    private CardView rootCV, nameImageCV, typesCV, heightCV, weightCV, statsCV;

    private TextView pokemonTitle, pokeHeight, pokeWeight;
    private ImageView pokemonImage;

    private String pokemonName;
    private String imageUrl;
    private float height;
    private float weight;

    private Intent intent;

    private boolean loading = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();


        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        init(binding.getRoot());
        loadData(binding.getRoot());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            pokemonName = intent.getStringExtra(SearchManager.QUERY);
            mViewModel.getPokemon(pokemonName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);

        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setQueryHint("Search Pokemon...");
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setSubmitButtonEnabled(true);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
           onBackPressed();
           return true;
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void init(View v) {
        mViewModel = new ViewModelProvider(this).get(PokemonViewModel.class);
        dialog = new LoadingDialog(this);


        rootCV = v.findViewById(R.id.rootCardView);
        nameImageCV = v.findViewById(R.id.pokeNameImgCardView);
        typesCV = v.findViewById(R.id.typesCardView);
        heightCV = v.findViewById(R.id.heightCardView);
        weightCV = v.findViewById(R.id.weightCardView);
        statsCV = v.findViewById(R.id.statsCardView);

        pokemonTitle = v.findViewById(R.id.pokemonName);
        pokemonImage = v.findViewById(R.id.pokemonImage);
        pokeHeight = v.findViewById(R.id.height_m);
        pokeWeight = v.findViewById(R.id.weight_kg);

    }

    public void loadData(View v) {
        if (loading) {
            dialog.startLoadingDialog();
        }

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            pokemonName = intent.getStringExtra(SearchManager.QUERY);
        } else {
            pokemonName = intent.getStringExtra("pokemonName");
        }
        mViewModel.getPokemon(pokemonName);

        mViewModel.get_result().observe(this, pokemon -> {

            if (pokemon != null) {
                imageUrl = pokemon.getSprites().getFront_default();
                weight = (float) pokemon.getWeight() / 10;
                height = (float) pokemon.getHeight() / 10;
                int colorPrimary = R.color.teal_700;
                int colorSecondary = R.color.teal_200;

                Type type = pokemon.getTypes().get(0).getType();


                //change background colors based on type
                switch (type.getName()) {
                    case "bug":
                        colorPrimary = R.color.bugPrimary;
                        colorSecondary = R.color.bugSecondary;
                        break;
                    case "dark":
                        colorPrimary = R.color.darkPrimary;
                        colorSecondary = R.color.darkSecondary;
                        break;
                    case "dragon":
                        colorPrimary = R.color.dragonPrimary;
                        colorSecondary = R.color.dragonSecondary;
                        break;
                    case "electric":
                        colorPrimary = R.color.electricPrimary;
                        colorSecondary = R.color.electricSecondary;
                        break;
                    case "fairy":
                        colorPrimary = R.color.fairyPrimary;
                        colorSecondary = R.color.fairySecondary;
                        break;
                    case "fire":
                        colorPrimary = R.color.firePrimary;
                        colorSecondary = R.color.fireSecondary;
                        break;
                    case "flying":
                        colorPrimary = R.color.flyingPrimary;
                        colorSecondary = R.color.flyingSecondary;
                        break;
                    case "fighting":
                        colorPrimary = R.color.fightingPrimary;
                        colorSecondary = R.color.fightingSecondary;
                        break;
                    case "ghost":
                        colorPrimary = R.color.ghostPrimary;
                        colorSecondary = R.color.ghostSecondary;
                        break;
                    case "grass":

                        colorPrimary = R.color.grassPrimary;
                        colorSecondary = R.color.grassSecondary;
                        System.out.println(colorPrimary);
                        break;
                    case "ground":
                        colorPrimary = R.color.groundPrimary;
                        colorSecondary = R.color.groundSecondary;
                        break;
                    case "ice":
                        colorPrimary = R.color.icePrimary;
                        colorSecondary = R.color.iceSecondary;
                        break;
                    case "normal":
                        colorPrimary = R.color.normalPrimary;
                        colorSecondary = R.color.normalSecondary;
                        break;
                    case "poison":
                        colorPrimary = R.color.poisonPrimary;
                        colorSecondary = R.color.poisonSecondary;
                        break;
                    case "psychic":
                        colorPrimary = R.color.psychicPrimary;
                        colorSecondary = R.color.psychicSecondary;
                        break;
                    case "rock":
                        colorPrimary = R.color.rockPrimary;
                        colorSecondary = R.color.rockSecondary;
                        break;
                    case "steel":
                        colorPrimary = R.color.steelPrimary;
                        colorSecondary = R.color.steelSecondary;
                        break;
                    case "water":
                        colorPrimary = R.color.waterPrimary;
                        colorSecondary = R.color.waterSecondary;
                        break;
                }


                Resources res = getResources();
                String weightKG = String.format(res.getString(R.string.weightKG), weight);
                String heightM = String.format(res.getString(R.string.heightM), height);

                int primary = res.getColor(colorPrimary);
                int secondary = res.getColor(colorSecondary);


                rootCV.setCardBackgroundColor(primary);
                nameImageCV.setCardBackgroundColor(secondary);
                typesCV.setCardBackgroundColor(secondary);
                heightCV.setCardBackgroundColor(secondary);
                weightCV.setCardBackgroundColor(secondary);
                statsCV.setCardBackgroundColor(secondary);

                pokemonTitle.setText(pokemonName);
                Glide.with(v)
                        .load(imageUrl)
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                        .into(pokemonImage);
                pokeHeight.setText(heightM);
                pokeWeight.setText(weightKG);
            } else {
                int primary = getResources().getColor(R.color.grey);
                int secondary = getResources().getColor(R.color.white);

                rootCV.setCardBackgroundColor(primary);
                nameImageCV.setCardBackgroundColor(secondary);
                typesCV.setCardBackgroundColor(secondary);
                heightCV.setCardBackgroundColor(secondary);
                weightCV.setCardBackgroundColor(secondary);
                statsCV.setCardBackgroundColor(secondary);

                pokemonTitle.setText(R.string.pokemon_not_found);
                pokemonImage.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.ic_broken_image));
                pokeHeight.setText("N/A");
                pokeWeight.setText("N/A");
            }

            if (loading) {
                loading = false;
                dialog.dismiss();
            }
        });
    }
}