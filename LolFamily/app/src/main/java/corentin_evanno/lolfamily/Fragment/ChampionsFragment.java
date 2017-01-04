package corentin_evanno.lolfamily.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import corentin_evanno.lolfamily.API.ApiManager;
import corentin_evanno.lolfamily.Adapter.AdapterChampionInfo;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.Champion;
import corentin_evanno.lolfamily.model.Champions;
import corentin_evanno.lolfamily.model.Ranked;
import corentin_evanno.lolfamily.model.Summoner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by corentin on 18/12/2016.
 */

public class ChampionsFragment extends Fragment {

    private RecyclerView recyclerView;
    private Summoner summoner;
    private AdapterChampionInfo mAdapterChampion;
    private ArrayList<Champions> championsArray = new ArrayList<>();
    private ArrayList<Champion> championArray = new ArrayList<>();
    private HashMap<String, Integer> networkRequests = new HashMap<>();
    private View mProgress;
    private boolean networkDone;
    private View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        summoner = (Summoner) bundle.getSerializable("Summoner");
        networkDone = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_champions, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.best_champion);

        mAdapterChampion = new AdapterChampionInfo(championsArray, championArray, getActivity().getBaseContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapterChampion);
        mProgress = rootView.findViewById(R.id.fragment_champions_progress);
        if (!networkDone)
            getRankedStats();
        else
            mAdapterChampion.notifyDataSetChanged();
        return (rootView);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgress.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgress.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }

    public void getRankedStats() {
        showProgress(true);
        ApiManager.setSummonerId(Long.toString(summoner.getId()));
        String url = "https://" + ApiManager.getRegion() + ".api.pvp.net/api/lol/" + ApiManager.getRegion() + "/v1.3/stats/by-summoner/" + summoner.getId() + "/ranked";
        ApiManager.get().getRankedStats(url, "SEASON2017", ApiManager.getApiKey()).enqueue(new Callback<Ranked>() {
            @Override
            public void onResponse(Call<Ranked> call, Response<Ranked> response) {
                if (response.isSuccessful()) {
                    if (response.body().getChampions().length != 0) {
                        for (int i = 0; i < response.body().getChampions().length; i++) {
                            if (!response.body().getChampions()[i].getId().equals("0")) {
                                championsArray.add(response.body().getChampions()[i]);
                            }
                        }
                        Collections.sort(championsArray, new Comparator<Champions>() {

                            public int compare(Champions s, Champions s2) {
                                int playedS = Integer.parseInt(s.getStats().getTotalSessionsPlayed());
                                int playedS2 = Integer.parseInt(s2.getStats().getTotalSessionsPlayed());
                                return playedS2 - playedS;
                            }
                        });
                        for (int i = 0; (i < championsArray.size()) && (i < 10); i++) {
                            getChampionInfo(championsArray.get(i).getId());
                        }
                    }
                } else {
                    showSnackBar("No data from API");
                    showProgress(false);
                }
            }

            @Override
            public void onFailure(Call<Ranked> call, Throwable t) {
                showSnackBar("Network problem");
            }
        });
    }

    void getChampionInfo(final String championId) {
        String url = "https://global.api.pvp.net/api/lol/static-data/" + ApiManager.getRegion() + "/v1.2/champion/" + championId;
        networkRequests.put(championId, 0);
        ApiManager.get().getChampionInfo(url, "image", ApiManager.getApiKey()).enqueue(new Callback<Champion>() {
            @Override
            public void onResponse(Call<Champion> call, Response<Champion> response) {
                if (response.isSuccessful()) {
                    championArray.add(response.body());
                    networkRequests.put(championId, 1);
                } else {
                    showSnackBar("No data from API");
                }
                isNetworkOver();
            }

            @Override
            public void onFailure(Call<Champion> call, Throwable t) {
                showSnackBar("Network problem");
            }
        });
    }

    public boolean containsList() {
        for (Map.Entry<String, Integer> value : networkRequests.entrySet()) {
            if (value.getValue() != 1) {
                return false;
            }
        }
        return true;
    }

    private void isNetworkOver() {
        if (containsList()) {
            showProgress(false);
            networkDone = true;
            mAdapterChampion.notifyDataSetChanged();
        }
    }

    void showSnackBar(String message) {
        LinearLayout coordinatorLayout = (LinearLayout) rootView.findViewById(R.id.coordinator_layout_champions);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
