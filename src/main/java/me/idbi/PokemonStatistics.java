package me.idbi;

import lombok.Getter;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PokemonStatistics {

    public static enum StatisticType {
        HEALTH,
        ATTACK,
        DEFENSE,
        SPECIAL_ATTACK,
        SPECIAL_DEFENSE;

        public static StatisticType getTypeByName(String name) {
            if(name.equalsIgnoreCase("hp")) name = "health";
            for (StatisticType value : values()) {
                if(value.name().equalsIgnoreCase(name)) return value;
            }
            return null;
        }
    }
    
    private final Map<StatisticType, Integer> statistics;

    public PokemonStatistics(JSONObject data) {
        this.statistics = new HashMap<StatisticType, Integer>();

        for (Object stats : data.getJSONArray("stats")) {
            JSONObject stat = (JSONObject) stats;
            JSONObject stat2 = stat.getJSONObject("stat");
            StatisticType type = StatisticType.getTypeByName(stat2.getString("name"));
            if(type == null) continue;
            this.statistics.put(type, stat.getInt("base_stat"));
        }
    }

    public int getStatistics(StatisticType type) {
        if(!this.statistics.containsKey(type)) return 0;
        return this.statistics.get(type);
    }

    public int getHealth() {
        return getStatistics(StatisticType.HEALTH);
    }

    public int getAttack() {
        return getStatistics(StatisticType.ATTACK);
    }

    public int getDefense() {
        return getStatistics(StatisticType.DEFENSE);
    }

    public int getSpecialAttack() {
        return getStatistics(StatisticType.SPECIAL_ATTACK);
    }

    public int getSpecialDefense() {
        return getStatistics(StatisticType.SPECIAL_DEFENSE);
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("health", getHealth());
        obj.put("attack", getAttack());
        obj.put("defense", getDefense());
        obj.put("special_attack", getSpecialAttack());
        obj.put("special_defense", getSpecialDefense());
        return obj.toString();
    }
}
