package corentin_evanno.lolfamily.API;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import corentin_evanno.lolfamily.GsonAdapter.ItemTypeAdapterFactory;
import corentin_evanno.lolfamily.GsonAdapter.itemTypeIdAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiManager {
    private static LolApi restClient;
    public static final String apiKey = "corentin.lol.api.API_KEY";
    private static String apiKeyStr = "";
    private static Gson gson;
    private static String region;

    private static Retrofit retrofit;

    static {
        setupRestClient();
    }

    private ApiManager() {
    }

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
        retrofit = new Retrofit.Builder()
                .baseUrl("https://euw.api.pvp.net/")
                .build();
        region = "euw";
        restClient = retrofit.create(LolApi.class);
    }

    public static void setSummonerName(String name) {
        gson = new GsonBuilder().registerTypeAdapterFactory(new ItemTypeAdapterFactory(name.toLowerCase())).create();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://euw.api.pvp.net/")
                .build();
        restClient = retrofit.create(LolApi.class);
    }

    public static void setSummonerId(String id) {
        gson = new GsonBuilder().registerTypeAdapterFactory(new itemTypeIdAdapterFactory(id)).create();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("https://euw.api.pvp.net/")
                .build();
        restClient = retrofit.create(LolApi.class);
    }


    public static String getRegion() {
        return (region);
    }

    public static void setRegion(String newRegion) {
        region = newRegion;
    }

    public static String getApiKey() {
        return (apiKeyStr);
    }
}