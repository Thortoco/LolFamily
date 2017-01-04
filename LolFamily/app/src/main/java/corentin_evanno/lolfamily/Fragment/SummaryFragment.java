package corentin_evanno.lolfamily.Fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import corentin_evanno.lolfamily.API.ApiManager;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.StatsRanked;
import corentin_evanno.lolfamily.model.Summoner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SummaryFragment extends Fragment {

    private Summoner summoner;
    private StatsRanked soloDuo;
    private StatsRanked flex;
    private Map region;
    private TextView pointsRanked;
    private TextView winsRanked;
    private TextView lossesRanked;
    private ImageView iconTier;
    private TextView queueName;
    private TextView pointsRankedFlex;
    private TextView winsRankedFlex;
    private TextView lossesRankedFlex;
    private ImageView iconTierFlex;
    private TextView queueNameFlex;
    private ImageView summonerIcon;
    private TextView winrate;
    private TextView winrateFlex;
    private LinearLayout wrapper;
    private View rootView;
    private View mProgress;
    private boolean networkDone;

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        summoner = (Summoner) bundle.getSerializable("Summoner");
        region = new HashMap();
        networkDone = false;
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_summary, container, false);
        // Inflate the layout for this fragment
        TextView summonerName = (TextView) rootView.findViewById(R.id.summoner_name_summary);
        summonerName.setText(this.summoner.getName());

        TextView server = (TextView) rootView.findViewById(R.id.summoner_server);
        server.setText(region.get(ApiManager.getRegion().toLowerCase()) + " - ");
        TextView summonerLevel = (TextView) rootView.findViewById(R.id.summoner_level);
        summonerLevel.setText("Level " + Long.toString(this.summoner.getSummonerLevel()));

        pointsRanked = (TextView) rootView.findViewById(R.id.points_ranked);
        winsRanked = (TextView) rootView.findViewById(R.id.wins_ranked);
        lossesRanked = (TextView) rootView.findViewById(R.id.losses_ranked);
        iconTier = (ImageView) rootView.findViewById(R.id.icon_tier);
        queueName = (TextView) rootView.findViewById(R.id.queue_name);
        summonerIcon = (ImageView) rootView.findViewById(R.id.icon_summoner);

        winrate = (TextView) rootView.findViewById(R.id.winrate);

        pointsRankedFlex = (TextView) rootView.findViewById(R.id.points_ranked_flex);
        winsRankedFlex = (TextView) rootView.findViewById(R.id.wins_ranked_flex);
        lossesRankedFlex = (TextView) rootView.findViewById(R.id.losses_ranked_flex);
        iconTierFlex = (ImageView) rootView.findViewById(R.id.icon_tier_flex);
        queueNameFlex = (TextView) rootView.findViewById(R.id.queue_name_flex);
        winrateFlex = (TextView) rootView.findViewById(R.id.winrate_flex);

        mProgress = rootView.findViewById(R.id.fragment_summary_progress);
        wrapper = (LinearLayout) rootView.findViewById(R.id.wrapper_summoner_info);
        Picasso.with(getActivity().getApplicationContext()).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/profileicon/" + summoner.getProfileIconId() + ".png").resize(84, 84).into(summonerIcon);

        if (!networkDone)
            getSummonerRanked();
        else {
            if (soloDuo != null) {
                queueName.setText(soloDuo.getTier() + " " + soloDuo.getEntries()[0].getDivision());
                pointsRanked.setText(soloDuo.getEntries()[0].getLeaguePoints() + " LP");
                winsRanked.setText("Wins: " + soloDuo.getEntries()[0].getWins());
                lossesRanked.setText("Losses: " + soloDuo.getEntries()[0].getLosses());
                double wins = soloDuo.getEntries()[0].getWins();
                double totalGame = soloDuo.getEntries()[0].getWins() + soloDuo.getEntries()[0].getLosses();
                DecimalFormat df = new DecimalFormat("###.#");
                double totalWinrate = Math.ceil((100 * wins) / totalGame);
                winrate.setText("Win ratio : " + df.format(totalWinrate) + "%");
                setIcon(soloDuo.getTier(), iconTier);
            }
            if (flex != null) {
                queueNameFlex.setText(flex.getTier() + " " + flex.getEntries()[0].getDivision());
                pointsRankedFlex.setText(flex.getEntries()[0].getLeaguePoints() + " LP");
                winsRankedFlex.setText("Wins: " + flex.getEntries()[0].getWins());
                lossesRankedFlex.setText("Losses: " + flex.getEntries()[0].getLosses());
                double wins = flex.getEntries()[0].getWins();
                double totalGame = flex.getEntries()[0].getWins() + flex.getEntries()[0].getLosses();
                DecimalFormat df = new DecimalFormat("###.#");
                double totalWinrate = Math.ceil((100 * wins) / totalGame);
                winrateFlex.setText("Win ratio : " + df.format(totalWinrate) + "%");
                setIcon(flex.getTier(), iconTierFlex);
            }
            wrapper.setVisibility(View.VISIBLE);
        }
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

    void setIcon(String tier, ImageView icon) {
        if (tier.equals("BRONZE"))
            icon.setImageResource(R.drawable.icon_bronze);
        else if (tier.equals("SILVER"))
            icon.setImageResource(R.drawable.icon_silver);
        else if (tier.equals("GOLD"))
            icon.setImageResource(R.drawable.icon_gold);
        else if (tier.equals("PLATINUM"))
            icon.setImageResource(R.drawable.icon_platinium);
        else if (tier.equals("DIAMOND"))
            icon.setImageResource(R.drawable.icon_diamond);
        else if (tier.equals("MASTER"))
            icon.setImageResource(R.drawable.icon_master);
        else if (tier.equals("CHALLENGER"))
            icon.setImageResource(R.drawable.icon_challenger);
    }

    public void getSummonerRanked() {
        showProgress(true);
        ApiManager.setSummonerId(Long.toString(summoner.getId()));
        String url = "https://" + ApiManager.getRegion() + ".api.pvp.net/api/lol/" + ApiManager.getRegion() + "/v2.5/league/by-summoner/" + summoner.getId() + "/entry";
        ApiManager.get().getRankedSummoner(url, ApiManager.getApiKey()).enqueue(new Callback<List<StatsRanked>>() {
            @Override
            public void onResponse(Call<List<StatsRanked>> call, Response<List<StatsRanked>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().size(); i++) {
                        if (response.body().get(i).getQueue().equals("RANKED_SOLO_5x5")) {
                            soloDuo = response.body().get(i);
                            queueName.setText(response.body().get(i).getTier() + " " + response.body().get(i).getEntries()[0].getDivision());
                            pointsRanked.setText(response.body().get(i).getEntries()[0].getLeaguePoints() + " LP");
                            winsRanked.setText("Wins: " + response.body().get(i).getEntries()[0].getWins());
                            lossesRanked.setText("Losses: " + response.body().get(i).getEntries()[0].getLosses());
                            double wins = response.body().get(i).getEntries()[0].getWins();
                            double totalGame = response.body().get(i).getEntries()[0].getWins() + response.body().get(i).getEntries()[0].getLosses();
                            DecimalFormat df = new DecimalFormat("###.#");
                            double totalWinrate = Math.ceil((100 * wins) / totalGame);
                            winrate.setText("Win ratio : " + df.format(totalWinrate) + "%");
                            setIcon(response.body().get(i).getTier(), iconTier);
                        }
                        if (response.body().get(i).getQueue().equals("RANKED_FLEX_SR")) {
                            flex = response.body().get(i);
                            queueNameFlex.setText(response.body().get(i).getTier() + " " + response.body().get(i).getEntries()[0].getDivision());
                            pointsRankedFlex.setText(response.body().get(i).getEntries()[0].getLeaguePoints() + " LP");
                            winsRankedFlex.setText("Wins: " + response.body().get(i).getEntries()[0].getWins());
                            lossesRankedFlex.setText("Losses: " + response.body().get(i).getEntries()[0].getLosses());
                            double wins = response.body().get(i).getEntries()[0].getWins();
                            double totalGame = response.body().get(i).getEntries()[0].getWins() + response.body().get(i).getEntries()[0].getLosses();
                            DecimalFormat df = new DecimalFormat("###.#");
                            double totalWinrate = Math.ceil((100 * wins) / totalGame);
                            winrateFlex.setText("Win ratio : " + df.format(totalWinrate) + "%");
                            setIcon(response.body().get(i).getTier(), iconTierFlex);
                        }
                    }

                } else {
                    showSnackBar("No data from API");
                    showProgress(false);
                }
                networkDone = true;
                showProgress(false);
                wrapper.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<StatsRanked>> call, Throwable t) {
                showSnackBar("Network problem");
            }
        });
    }

    void showSnackBar(String message) {
        LinearLayout coordinatorLayout = (LinearLayout) rootView.findViewById(R.id.coordinator_layout_summary);
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }


}
