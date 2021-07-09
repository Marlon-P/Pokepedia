package com.example.pokepedia.api;

import androidx.annotation.NonNull;

import com.example.pokepedia.pokemon_classes.NameUrl;

import java.util.List;

public class PokePageResults {
    private int count;
    private String next;
    private String previous;
    private List<NameUrl> results;

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<NameUrl> getResults() {
        return results;
    }

    @NonNull
    @Override
    public String toString() {
        return "PokePageResults{" +
                "count=" + count +
                "\nnext='" + next + '\'' +
                "\nprevious='" + previous + '\'' +
                "\nresults=" + results +
                '}';
    }
}
