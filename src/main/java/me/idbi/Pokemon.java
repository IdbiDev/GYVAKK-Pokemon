package me.idbi;

import lombok.Getter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Pokemon implements IPokemon {
    private String name;
    private List<String> types;
    private int id;
    private int baseExperience;
    private int weight;
    private int height;
    private PokemonStatistics statistics;
    private List<PokemonAbility> abilities;

    private boolean loaded;

    public Pokemon(String name) {
        this.abilities = new ArrayList<>();
        this.types = new ArrayList<>();
        name = name.toLowerCase();
        JSONObject data = URLManager.getResponse("https://pokeapi.co/api/v2/pokemon/%s".formatted(name));
        if (data != null)
            load(data);
    }
    public Pokemon(int id) {
        JSONObject data = URLManager.getResponse("http://pokeapi.co/api/v2/pokemon/%d".formatted(id));
        if(data != null)
            load(data);
    }

    private void load(JSONObject data) {
        this.loaded = true;
        this.name = data.getString("name");
        this.id = data.getInt("id");
        this.baseExperience = data.getInt("base_experience");
        this.weight = data.getInt("weight");
        this.height = data.getInt("height");

        for (Object o : data.getJSONArray("types")) {
            JSONObject xd = (JSONObject) o;
            this.types.add(xd.getJSONObject("type").getString("name"));
        }

        this.statistics = new PokemonStatistics(data);

        for (Object o : data.getJSONArray("abilities")) {
            JSONObject ability = (JSONObject) o;
            String name = ability.getJSONObject("ability").getString("name");
            String url = ability.getJSONObject("ability").getString("url");

            JSONObject abilityData = URLManager.getResponse(url);
            for (Object effectEntries : abilityData.getJSONArray("effect_entries")) {
                JSONObject effEnt = (JSONObject) effectEntries;
                if (!effEnt.getJSONObject("language").getString("name").equalsIgnoreCase("en"))
                    continue;

                PokemonAbility ab = new PokemonAbility(name, effEnt.getString("effect"), effEnt.getString("short_effect"));
                this.abilities.add(ab);
            }
        }
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("id", this.id);
        obj.put("types", this.types);
        obj.put("weight", this.weight);
        obj.put("height", this.height);
        obj.put("base_experience", this.baseExperience);
        obj.put("statistics", this.statistics);
        obj.put("abilities", this.abilities);
        return obj.toString();
    }
}
