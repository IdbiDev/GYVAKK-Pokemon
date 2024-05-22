package me.idbi;

import java.util.List;

public interface IPokemon {

    String getName();
    List<String> getTypes();
    int getId();
    int getBaseExperience();
    int getWeight();
    int getHeight();
    PokemonStatistics getStatistics();
    List<PokemonAbility> getAbilities();
}
