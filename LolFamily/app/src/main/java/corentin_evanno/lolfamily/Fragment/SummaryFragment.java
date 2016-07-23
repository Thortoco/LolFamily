package corentin_evanno.lolfamily.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import corentin_evanno.lolfamily.API.ApiManager;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.View.ProfilActivity;
import corentin_evanno.lolfamily.model.StatsSummary;
import corentin_evanno.lolfamily.model.Summoner;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class SummaryFragment extends Fragment {

    private Summoner summoner;
    private Map region;

    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        summoner = (Summoner) bundle.getSerializable("Summoner");
        region = new HashMap();

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
        View rootView = inflater.inflate(R.layout.fragment_summary, container, false);
        // Inflate the layout for this fragment
        TextView summonerName = (TextView) rootView.findViewById(R.id.summoner_name_summary);
        summonerName.setText(this.summoner.getName());

        TextView server = (TextView) rootView.findViewById(R.id.summoner_server);
        server.setText((String) region.get(ApiManager.getRegion()) + " - ");
        TextView summonerLevel = (TextView) rootView.findViewById(R.id.summoner_level);
        summonerLevel.setText("Level " + Long.toString(this.summoner.getSummonerLevel()));

        getSummonerStats();
        return (rootView);
    }


    public void getSummonerStats() {
        ApiManager.get().getStatsSummoner(ApiManager.getRegion(), summoner.getId(), "SEASON2016", ApiManager.getApiKey())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StatsSummary>() {
                    @Override
                    public final void onCompleted() {
                        // do nothing
                    }

                    @Override
                    public final void onError(Throwable e) {
                        System.out.println("Response = " + e.getMessage() + "|" + e.getLocalizedMessage());

                    }

                    @Override
                    public final void onNext(StatsSummary response) {
                        System.out.println("Response = " + response.getPlayerStatSummaries().get(8).getWins());
                        /*System.out.println("{Name = \"" + response.getName() + "\",");
                        System.out.println("Id = \"" + response.getId() + "\",");
                        System.out.println("ProfileIconId = \"" + response.getProfileIconId() + "\",");
                        System.out.println("RevisionDate = \"" + response.getRevisionDate() + "\",");
                        System.out.println("SumonnerLevel = \"" + response.getSummonerLevel() + "\"}");*/
                        /*Intent mIntent = new Intent(getApplicationContext(), ProfilActivity.class);
                        mIntent.putExtra("Summoner", response);
                        startActivity(mIntent);*/
                    }
                });

    }

}
