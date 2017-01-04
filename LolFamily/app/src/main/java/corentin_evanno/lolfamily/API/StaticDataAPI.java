package corentin_evanno.lolfamily.API;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import corentin_evanno.lolfamily.model.Champion;
import corentin_evanno.lolfamily.model.ChampionStaticData;
import corentin_evanno.lolfamily.model.Item;
import corentin_evanno.lolfamily.model.ItemStaticData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corentin on 21/12/2016.
 */

public class StaticDataAPI {
    private static StaticDataAPI instance = null;
    private HashMap<String, String> summonerSpells;
    private HashMap<String, String> map;
    private ArrayList<Champion> championList;
    private ArrayList<Item> itemList;

    public static StaticDataAPI getInstance() {
        return instance != null ? instance : (instance = new StaticDataAPI());
    }

    public StaticDataAPI() {
        summonerSpells = new HashMap<>();
        map = new HashMap<>();
        championList = new ArrayList<>();
        itemList = new ArrayList<>();
        summonerSpells.put("1", "SummonerBoost");
        summonerSpells.put("12", "SummonerTeleport");
        summonerSpells.put("30", "SummonerPoroRecall");
        summonerSpells.put("6", "SummonerHaste");
        summonerSpells.put("14", "SummonerDot");
        summonerSpells.put("32", "SummonerSnowball");
        summonerSpells.put("7", "SummonerHeal");
        summonerSpells.put("31", "SummonerProThrow");
        summonerSpells.put("11", "SummonerSmite");
        summonerSpells.put("3", "SummonerExhaust");
        summonerSpells.put("13", "SummonerMana");
        summonerSpells.put("21", "SummonerBarrier");
        summonerSpells.put("4", "SummonerFlash");
        map.put("NORMAL", "Normal");
        map.put("BOT", "Bot");
        map.put("RANKED_SOLO_5x5", "Ranked solo");
        map.put("RANKED_PREMADE_3x3", "Ranked 3x3");
        map.put("RANKED_PREMADE_5x5", "Ranked 5x5");
        map.put("ODIN_UNRANKED", "Odin");
        map.put("RANKED_TEAM_3x3", "Ranked team 3x3");
        map.put("RANKED_TEAM_5x5", "Ranked team 5x5");
        map.put("NORMAL_3x3", "Normal 3x3");
        map.put("BOT_3x3", "Bot 3x3");
        map.put("CAP_5x5", "CAP 3x3");
        map.put("ARAM_UNRANKED_5x5", "ARAM");
        map.put("ONEFORALL_5x5", "One for all");
        map.put("FIRSTBLOOD_1x1", "Firstblood 1x1");
        map.put("FIRSTBLOOD_2x2", "Firstblood 2x2");
        map.put("SR_6x6", "Hexakill");
        map.put("URF", "URF");
        map.put("URF_BOT", "URF BOT");
        map.put("NIGHTMARE_BOT", "Nightmare Bot");
        map.put("ASCENSION", "Ascension");
        map.put("HEXAKILL", "Hexakill");
        map.put("KING_PORO", "Poro king");
        map.put("COUNTER_PICK", "Counter pick");
        map.put("BILGEWATER", "Bilgewater");
        map.put("SIEGE", "Siege");
        map.put("RANKED_FLEX_SR", "Ranked flex");
        map.put("RANKED_FLEX_TT", "Ranked flex");
        getChampionStaticData();
        getItemsStaticData();
    }

    private void getChampionStaticData() {
        String url = "https://global.api.pvp.net/api/lol/static-data/euw/v1.2/champion/";
        ApiManager.get().getChampionsData(url, ApiManager.getApiKey(), "image").enqueue(new Callback<ChampionStaticData>() {
            @Override
            public void onResponse(Call<ChampionStaticData> call, Response<ChampionStaticData> response) {
                if (response.isSuccessful()) {
                    for (Map.Entry<String, Champion> entry : response.body().getData().entrySet()) {
                        championList.add(entry.getValue());
                    }
                } else {
                    System.out.println("[Not successfull]");
                }
            }

            @Override
            public void onFailure(Call<ChampionStaticData> call, Throwable t) {
                System.out.println("[Failure] getSummonersRanked : " + t.getMessage());
            }
        });

    }

    private void getItemsStaticData() {
        String url = "https://global.api.pvp.net/api/lol/static-data/euw/v1.2/item/";
        ApiManager.get().getItemsData(url, ApiManager.getApiKey(), "image").enqueue(new Callback<ItemStaticData>() {
            @Override
            public void onResponse(Call<ItemStaticData> call, Response<ItemStaticData> response) {
                if (response.isSuccessful()) {
                    for (Map.Entry<String, Item> entry : response.body().getData().entrySet()) {
                        itemList.add(entry.getValue());
                    }
                } else {
                    System.out.println("[Not successfull]");
                }
            }

            @Override
            public void onFailure(Call<ItemStaticData> call, Throwable t) {
                System.out.println("[Failure] getSummonersRanked : " + t.getMessage());
            }
        });

    }

    public Champion getChampionById(String id) {
        for (Champion champ : championList) {
            if (champ.getId().equals(id)) {
                return champ;
            }
        }
        return null;
    }

    public Item getItemById(String id) {
        for (Item item : itemList) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    public HashMap<String, String> getSummonerSpells() {
        return summonerSpells;
    }

    public HashMap<String, String> getMap() {
        return map;
    }
}
