package com.example.pokepedia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokepedia.R;
import com.example.pokepedia.adapters.DefaultFragmentAdapter;
import com.example.pokepedia.dialogs.LoadingDialog;
import com.example.pokepedia.viewModels.DefaultSearchListViewModel;


public class DefaultPokemonSearchList extends Fragment {

    private DefaultSearchListViewModel viewModel;
    private LoadingDialog dialog;
    private boolean loading = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dialog = new LoadingDialog(getActivity());
        View v = inflater.inflate(R.layout.fragment_default_pokemon_search_list, container, false);

        initRecyclerView(v);
        return v;
    }




    private void initRecyclerView(View v) {
        RecyclerView rv = v.findViewById(R.id.defaultSearchRV);
        DefaultFragmentAdapter adapter = new DefaultFragmentAdapter();


        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);


        viewModel = new ViewModelProvider(this).get(DefaultSearchListViewModel.class);

        if(loading) {
            dialog.startLoadingDialog();
        }

        viewModel.get_results().observe(getViewLifecycleOwner(), result ->
                {
                    adapter.setData(result.getNameUrls());
                    if (loading) {
                        loading = false;
                        dialog.dismiss();
                    }
                }

        );

    }
}