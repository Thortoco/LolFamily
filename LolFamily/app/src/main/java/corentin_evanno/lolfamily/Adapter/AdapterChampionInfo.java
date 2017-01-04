package corentin_evanno.lolfamily.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.Champion;
import corentin_evanno.lolfamily.model.Champions;

/**
 * Created by corentin on 12/09/2016.
 */


public class AdapterChampionInfo extends RecyclerView.Adapter<AdapterChampionInfo.MyViewHolder> {

    private ArrayList<Champions> championsList;
    private ArrayList<Champion> championList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView championIcon;
        public TextView championName;
        public TextView championCreeps;
        public TextView championKda;
        public TextView championRatio;
        public TextView championWinrate;
        public TextView championPlayed;
        public View hr;

        public MyViewHolder(View view) {
            super(view);
            championIcon = (ImageView) view.findViewById(R.id.champion_icon);
            championName = (TextView) view.findViewById(R.id.champion_name);
            championCreeps = (TextView) view.findViewById(R.id.champion_creeps);
            championKda = (TextView) view.findViewById(R.id.champion_kda);
            championRatio = (TextView) view.findViewById(R.id.champion_ratio);
            championWinrate = (TextView) view.findViewById(R.id.champion_winrate);
            championPlayed = (TextView) view.findViewById(R.id.champion_played);
            hr = view.findViewById(R.id.best_champ_hr);
        }
    }


    public AdapterChampionInfo(ArrayList<Champions> championsList, ArrayList<Champion> championList, Context context) {
        this.championsList = championsList;
        this.championList = championList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.best_champion_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (position == 0)
            holder.hr.setVisibility(View.GONE);
        Champions champion = championsList.get(position);
        Champion championInfo = new Champion();
        int pos = 0;
        for (; pos < championList.size(); pos++) {
            if (championList.get(pos).getId().equals(champion.getId())) {
                championInfo = championList.get(pos);
                break;
            }
        }
        if (championInfo.getName() != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + championInfo.getImage().getFull()).resize(84, 84).into(holder.championIcon);
        }
        holder.championName.setText(championInfo.getName());
        double totalGame = Double.parseDouble(champion.getStats().getTotalSessionsPlayed());
        double creeps = Double.parseDouble(champion.getStats().getTotalMinionKills()) / totalGame;
        double totalKill = Integer.parseInt(champion.getStats().getTotalChampionKills());
        double totalDead = Integer.parseInt(champion.getStats().getTotalDeathsPerSession());
        double totalAssist = Integer.parseInt(champion.getStats().getTotalAssists());
        totalKill /= totalGame;
        totalDead /= totalGame;
        totalAssist /= totalGame;
        double winrate = Math.ceil((100 * Double.parseDouble(champion.getStats().getTotalSessionsWon()) / totalGame));
        DecimalFormat df1 = new DecimalFormat("###.#");
        DecimalFormat df2 = new DecimalFormat("###.##");
        DecimalFormat df3 = new DecimalFormat("###.#");
        double kda = (totalKill + totalAssist) / totalDead;
        holder.championCreeps.setText(df1.format(creeps) + " creeps");
        holder.championKda.setText(df2.format(kda) + ":1 KDA");
        holder.championRatio.setText(df1.format((totalKill)) + "/" + df3.format(totalDead) + "/" + df3.format(totalAssist));
        holder.championWinrate.setText(df1.format(winrate) + "%");
        holder.championPlayed.setText(champion.getStats().getTotalSessionsPlayed() + " Played");
    }

    @Override
    public int getItemCount() {
        return championList.size();
    }
}