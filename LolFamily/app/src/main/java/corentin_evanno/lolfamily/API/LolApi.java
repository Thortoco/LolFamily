package corentin_evanno.lolfamily.API;

import corentin_evanno.lolfamily.model.StatsSummary;
import corentin_evanno.lolfamily.model.Summoner;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface LolApi {
    @GET("/api/lol/{region}/v1.4/summoner/by-name/{name}")
    Observable<Summoner> getSummonerByName(@Path("region") String region, @Path("name") String name, @Query("api_key") String apiKey);

    @GET("/api/lol/{region}/v1.3/stats/by-summoner/{summonerId}/summary")
    Observable<StatsSummary> getStatsSummoner(@Path("region") String region, @Path("summonerId") Long summonerId, @Query("season") String season, @Query("api_key") String apiKey);
}
