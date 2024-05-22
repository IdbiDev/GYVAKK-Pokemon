package me.idbi;

import lombok.Getter;
import org.json.JSONObject;

@Getter
public class PokemonAbility {

    private final String name;
    private final String effect;
    private final String shortEffect;

    public PokemonAbility(String name, String effect, String shortEffect) {
        this.name = name;
        this.effect = effect;
        this.shortEffect = shortEffect;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("effect", effect);
        obj.put("short_effect", shortEffect);
        return obj.toString();
    }
}
