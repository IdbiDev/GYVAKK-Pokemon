package me.idbi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class Main
{
    private static final boolean debugGUI = true;
    public static void main( String[] args )
    {
        while (true) {
            System.out.print("Pokemon neve:");
            Scanner in = new Scanner(System.in);
            if(in.hasNextLine()) {
                String line = in.nextLine();
                if(Objects.equals(line, "exit")){
                    break;
                }
                Pokemon p = new Pokemon(line);
                if(!p.isLoaded()) {
                    System.out.println("Nem található Pokemon!");
                    continue;
                }
                System.out.println("==========Statistics==========");
                printPokemon(p);
                System.out.println("==============================================");
            }

        }
    }

    public static void printPokemon(IPokemon pokemon) {
        List<String> lines = new ArrayList<String>();
        lines.add("name: " + pokemon.getName());
        lines.add("id: " + pokemon.getId());
        lines.add("types: " + pokemon.getTypes());
        lines.add("weight: " + pokemon.getWeight());
        lines.add("height: " + pokemon.getHeight());
        lines.add("base_experience: " + pokemon.getBaseExperience());
        lines.add("statistics:");
        lines.add("\t- health: " + pokemon.getStatistics().getHealth());
        lines.add("\t- attack: " + pokemon.getStatistics().getAttack());
        lines.add("\t- defense: " + pokemon.getStatistics().getDefense());
        lines.add("\t- special_attack: " + pokemon.getStatistics().getSpecialAttack());
        lines.add("\t- special_defense: " + pokemon.getStatistics().getSpecialDefense());
        lines.add("abilities:");
        for (PokemonAbility ability : pokemon.getAbilities()) {
            lines.add("\t- name: " + ability.getName());
            lines.add("\t\t* effect: " + ability.getEffect());
            lines.add("\t\t* short_effect: " + ability.getShortEffect());
        }
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
