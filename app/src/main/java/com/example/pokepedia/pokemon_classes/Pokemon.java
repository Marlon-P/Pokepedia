package com.example.pokepedia.pokemon_classes;

import java.util.List;

public class Pokemon {
    private List<Abilities> abilities;
    private int base_experience;
    private int height;
    private int id;
    private List<Moves> moves;
    private String name;
    private Sprites sprites;
    private List<Stats> stats;
    private List<Types> types;
    private int weight;

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public List<Moves> getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public List<Types> getTypes() {
        return types;
    }

    public int getWeight() {
        return weight;
    }
}

