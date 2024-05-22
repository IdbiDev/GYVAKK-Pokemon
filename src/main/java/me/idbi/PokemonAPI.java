package me.idbi;

public class PokemonAPI {

    public static Pokemon getPokemon(String name) {
        return new Pokemon(name);
    }

    public static Pokemon getPokemon(int id) {
        return new Pokemon(id);
    }
}
