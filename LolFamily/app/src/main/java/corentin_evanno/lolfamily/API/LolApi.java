package corentin_evanno.lolfamily.API;

import java.util.List;

import corentin_evanno.lolfamily.model.Champion;
import corentin_evanno.lolfamily.model.ChampionStaticData;
import corentin_evanno.lolfamily.model.ItemStaticData;
import corentin_evanno.lolfamily.model.Matches;
import corentin_evanno.lolfamily.model.Ranked;
import corentin_evanno.lolfamily.model.StatsRanked;
import corentin_evanno.lolfamily.model.StatsSummary;
import corentin_evanno.lolfamily.model.Summoner;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface LolApi {
    @GET
    Call<Summoner> getSummonerByName(@Url String url, @Query("api_key") String apiKey);

    @GET
    Call<List<StatsRanked>> getRankedSummoner(@Url String url, @Query("api_key") String apiKey);

    @GET
    Call<Ranked> getRankedStats(@Url String url, @Query("season") String season, @Query("api_key") String apiKey);

    @GET
    Call<Champion> getChampionInfo(@Url String url, @Query("champData") String champ, @Query("api_key") String apiKey);

    @GET
    Call<Matches> getRecentMatches(@Url String url, @Query("api_key") String apiKey);

    @GET
    Call<ChampionStaticData> getChampionsData(@Url String url, @Query("api_key") String apiKey, @Query("champData") String champData);

    @GET
    Call<ItemStaticData> getItemsData(@Url String url, @Query("api_key") String apiKey, @Query("itemListData") String champData);
}
