package corentin_evanno.lolfamily.API;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Observable;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import corentin_evanno.lolfamily.GsonAdapter.ItemTypeAdapterFactory;
import corentin_evanno.lolfamily.model.Summoner;
import okhttp3.OkHttpClient;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import rx.Observer;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;


public class ApiManager {
    private static LolApi restClient;
    public static final String apiKey = "corentin.lol.api.API_KEY";
    private static String apiKeyStr = "";
    private static CustomEndPoint endPoint;
    private static Gson gson;
    private static RestAdapter.Builder builder;
    private static RestAdapter restAdapter;

    static {
        setupRestClient();
    }

    private ApiManager() {}

    public static LolApi get() {
        return (restClient);
    }

    public static void init(Context context) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            apiKeyStr = bundle.getString(apiKey);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private static void setupRestClient() {
        endPoint = new CustomEndPoint();
        endPoint.setUrl("euw");

        gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory("")).create();


        builder = new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson))
                .setEndpoint(endPoint);

        restAdapter = builder.build();
        restClient = restAdapter.create(LolApi.class);
    }

    public static void setRegion(String region) {
        endPoint.setUrl(region);
    }

    public static String getRegion() {
        return (endPoint.getName());
    }

    public static void setSummonerName(String name) {
        gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory(name.toLowerCase())).create();
        builder = new RestAdapter.Builder()
                .setConverter(new GsonConverter(gson))
                .setEndpoint(endPoint);

        restAdapter = builder.build();
        restClient = restAdapter.create(LolApi.class);
    }

    public static String getApiKey() {
        return (apiKeyStr);
    }

    /*public static Observable<Summoner> getSummonerByName(final String region, String name) {
        return Observable.create(new Observable.OnSubscribeFunc<Summoner>() {
            @Override
            public Subscription onSubscribe(Observer<? super Summoner> observer) {
                try {
                    /*Summoner member = apiManager.getMember(username);
                    observer.onNext(member);
                    observer.onCompleted();*/
               /* } catch (Exception e) {
                    observer.onError(e);
                }
                return Subscriptions.empty();
            }
        }).subscribeOn(Schedulers.threadPoolForIO());*/
        /*
        final Summoner newSummoner = new Summoner();
        ApiManager.get().getSummonerByName(region, name, apiKeyStr, new Callback<Summoner>() {
            @Override
            public void success(Summoner summoner, Response response2) {
                Log.d("APP", "reponse = " + response2.getUrl());
                System.out.println(summoner.getName());
                System.out.println(summoner.getId());
                System.out.println(summoner.getProfileIconId());
                System.out.println(summoner.getRevisionDate());
                System.out.println(summoner.getSummonerLevel());

                newSummoner.setId(summoner.getId());
                newSummoner.setName(summoner.getName());
                newSummoner.setProfileIconId(summoner.getProfileIconId());
                newSummoner.setRevisionDate(summoner.getRevisionDate());
                newSummoner.setSummonerLevel(summoner.getSummonerLevel());

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("APP", "response = " + error.getMessage());
                Log.d("APP", "response = " + error.getUrl());
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))  {

                }
            }
        });
            return (newSummoner);*/
   // }
}