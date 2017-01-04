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
import java.util.HashMap;
import java.util.Map;

import corentin_evanno.lolfamily.API.ApiManager;
import corentin_evanno.lolfamily.Adapter.AdapterMatchInfo;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.GameInfo;
import corentin_evanno.lolfamily.model.Matches;
import corentin_evanno.lolfamily.model.Summoner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchHistoryFragment extends Fragment {
    private Summoner summoner;
    private Map region;
    private ArrayList<GameInfo> matchList;
    private RecyclerView recyclerView;
    private AdapterMatchInfo mAdapterMatch;
    private boolean networkDone;
    private View mProgress;
    private View rootView;

    public MatchHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        summoner = (Summoner) bundle.getSerializable("Summoner");
        region = new HashMap();
        matchList = new ArrayList<>();
        region.put("euw", "Europe West");
        region.put("na", "North America");
        region.put("kr", "Korea");
        region.put("br", "Brazil");
        region.put("tr", "Turkey");
        region.put("oce", "Oceania");
        region.put("eune", "Europe Nordic & East");
        region.put("ru", "Russia");
        region.put("jp", "Oceania");
        region.put("lan", "Latin America North");
        region.put("las", "Latin America South");
        networkDone = false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_match_history, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.games_view);

        mAdapterMatch = new AdapterMatchInfo(matchList, getActivity().getBaseContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapterMatch);
        mProgress = rootView.findViewById(R.id.match_history_progress);
        if (!networkDone)
            getRecentMatches();
        else
            mAdapterMatch.notifyDataSetChanged();
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


    public void getRecentMatches() {
        showProgress(true);
        String url = "https://" + ApiManager.getRegion() + ".api.pvp.net/api/lol/" + ApiManager.getRegion() + "/v1.3/game/by-summoner/" + summoner.getId() + "/recent";
        ApiManager.get().getRecentMatches(url, ApiManager.getApiKey()).enqueue(new Callback<Matches>() {
            @Override
            public void onResponse(Call<Matches> call, Response<Matches> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getGames().length; i++) {
                        matchList.add(new GameInfo(response.body().getGames()[i]));
                    }
                    networkDone = true;
                    showProgress(false);
                    mAdapterMatch.notifyDataSetChanged();
                } else {
                    showProgress(false);
                    showSnackBar("No data from API");
                }
            }

            @Override
            public void onFailure(Call<Matches> call, Throwable t) {
                showProgress(false);
                showSnackBar("Network problem");
            }
        });
    }

    void showSnackBar(String message) {
        LinearLayout coordinatorLayout = (LinearLayout) rootView.findViewById(R.id.coordinator_layout_match_history);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
