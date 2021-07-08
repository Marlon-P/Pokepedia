package com.example.pokepedia.pokemon_classes;

import androidx.annotation.NonNull;

public class NameUrl {
    String name;
    String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @NonNull
    @Override
    public String toString() {
        return "NameUrl{" +
                "\nname='" + name + '\'' +
                "\nurl='" + url + '\'' +
                "}\n";
    }
}
