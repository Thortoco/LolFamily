package corentin_evanno.lolfamily.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import corentin_evanno.lolfamily.API.StaticDataAPI;
import corentin_evanno.lolfamily.R;
import corentin_evanno.lolfamily.model.GameInfo;

/**
 * Created by corentin on 21/12/2016.
 */

public class AdapterMatchInfo extends RecyclerView.Adapter<AdapterMatchInfo.MyViewHolder> {
    private ArrayList<GameInfo> matchList;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView gameType;
        public TextView gameStart;
        public TextView gameResult;
        public TextView gameLength;
        public ImageView gameChampionIcon;
        private TextView gameChampion;
        public ImageView gameSpell1;
        public ImageView gameSpell2;
        public TextView gameStats;
        public TextView gameKDA;
        public TextView gameMaxKill;
        public TextView gameLevel;
        public TextView gameCs;
        public ImageView item0;
        public ImageView item1;
        public ImageView item2;
        public ImageView item3;
        public ImageView item4;
        public ImageView item5;
        public ImageView item6;
        private ArrayList<ImageView> aTeam;
        private ArrayList<ImageView> bTeam;
        public ImageView summoner1;
        public ImageView summoner2;
        public ImageView summoner3;
        public ImageView summoner4;
        public ImageView summoner5;
        public ImageView summoner6;
        public ImageView summoner7;
        public ImageView summoner8;
        public ImageView summoner9;
        public ImageView summoner10;
        private View delimiter;
        private LinearLayout matchPart2;
        private ImageView buttonExpand;
        private TextView team1;
        private TextView team2;
        private CardView cardview;

        public MyViewHolder(View view) {
            super(view);
            aTeam = new ArrayList<>();
            bTeam = new ArrayList<>();
            gameType = (TextView) view.findViewById(R.id.game_type);
            gameStart = (TextView) view.findViewById(R.id.game_start);
            gameResult = (TextView) view.findViewById(R.id.game_result);
            gameLength = (TextView) view.findViewById(R.id.game_length);
            gameChampionIcon = (ImageView) view.findViewById(R.id.game_champion_icon);
            gameChampion = (TextView) view.findViewById(R.id.game_champion);
            gameSpell1 = (ImageView) view.findViewById(R.id.game_spell1);
            gameSpell2 = (ImageView) view.findViewById(R.id.game_spell2);
            gameStats = (TextView) view.findViewById(R.id.game_stats);
            gameKDA = (TextView) view.findViewById(R.id.game_kda);
            gameMaxKill = (TextView) view.findViewById(R.id.game_max_kill);
            gameLevel = (TextView) view.findViewById(R.id.game_level);
            gameCs = (TextView) view.findViewById(R.id.game_cs);
            item0 = (ImageView) view.findViewById(R.id.game_item0);
            item1 = (ImageView) view.findViewById(R.id.game_item1);
            item2 = (ImageView) view.findViewById(R.id.game_item2);
            item3 = (ImageView) view.findViewById(R.id.game_item3);
            item4 = (ImageView) view.findViewById(R.id.game_item4);
            item5 = (ImageView) view.findViewById(R.id.game_item5);
            item6 = (ImageView) view.findViewById(R.id.game_item6);
            summoner1 = (ImageView) view.findViewById(R.id.game_team1_1);
            summoner2 = (ImageView) view.findViewById(R.id.game_team1_2);
            summoner3 = (ImageView) view.findViewById(R.id.game_team1_3);
            summoner4 = (ImageView) view.findViewById(R.id.game_team1_4);
            summoner5 = (ImageView) view.findViewById(R.id.game_team1_5);
            summoner6 = (ImageView) view.findViewById(R.id.game_team2_1);
            summoner7 = (ImageView) view.findViewById(R.id.game_team2_2);
            summoner8 = (ImageView) view.findViewById(R.id.game_team2_3);
            summoner9 = (ImageView) view.findViewById(R.id.game_team2_4);
            summoner10 = (ImageView) view.findViewById(R.id.game_team2_5);
            delimiter = (View) view.findViewById(R.id.delimiter_match_card);
            matchPart2 = (LinearLayout) view.findViewById(R.id.match_card_part_2);
            buttonExpand = (ImageView) view.findViewById(R.id.button_expand_part2);
            aTeam.add(summoner1);
            aTeam.add(summoner2);
            aTeam.add(summoner3);
            aTeam.add(summoner4);
            aTeam.add(summoner5);
            bTeam.add(summoner6);
            bTeam.add(summoner7);
            bTeam.add(summoner8);
            bTeam.add(summoner9);
            bTeam.add(summoner10);
            team1 = (TextView) view.findViewById(R.id.team1);
            team2 = (TextView) view.findViewById(R.id.team2);
            cardview = (CardView) view.findViewById(R.id.card_view_summoner_info);
        }
    }


    public AdapterMatchInfo(ArrayList<GameInfo> newMatchList, Context context) {
        this.matchList = newMatchList;
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.match_info_row, parent, false);

        return new AdapterMatchInfo.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AdapterMatchInfo.MyViewHolder holder, int position) {

        if (StaticDataAPI.getInstance().getMap().containsKey(matchList.get(position).getSubType()))
            holder.gameType.setText(StaticDataAPI.getInstance().getMap().get(matchList.get(position).getSubType()));
        Date dateObj = new Date(Long.valueOf(matchList.get(position).getCreateDate()));
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(dateObj);
        holder.gameStart.setText(text);
        if (matchList.get(position).isWin()) {
            holder.gameResult.setText("Win");
            holder.gameResult.setTextColor(ContextCompat.getColor(mContext, (R.color.green)));
        } else {
            holder.gameResult.setText("Defeat");
            holder.gameResult.setTextColor(ContextCompat.getColor(mContext, (R.color.red)));
        }
        holder.gameChampion.setText(StaticDataAPI.getInstance().getChampionById(matchList.get(position).getChampionId()).getName());
        Integer timePlayed = Integer.parseInt(matchList.get(position).getTimePlayed());
        String sec = Integer.toString(timePlayed % 60);
        String min = Integer.toString(timePlayed / 60);
        holder.gameLength.setText(min + "m " + sec + "s");
        Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + StaticDataAPI.getInstance().getChampionById(matchList.get(position).getChampionId()).getImage().getFull()).resize(84, 84).into(holder.gameChampionIcon);
        if (StaticDataAPI.getInstance().getSummonerSpells().containsKey(matchList.get(position).getSpell1()))
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/" + StaticDataAPI.getInstance().getSummonerSpells().get(matchList.get(position).getSpell1()) + ".png").resize(84, 84).into(holder.gameSpell1);
        if (StaticDataAPI.getInstance().getSummonerSpells().containsKey(matchList.get(position).getSpell2()))
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/spell/" + StaticDataAPI.getInstance().getSummonerSpells().get(matchList.get(position).getSpell2()) + ".png").resize(84, 84).into(holder.gameSpell2);

        double totalKill = 0;
        double totalDeath = 0;
        double totalAssist = 0;
        if (matchList.get(position).getKill() != null)
            totalKill = Integer.parseInt(matchList.get(position).getKill());
        else
            matchList.get(position).setKill("0");
        if (matchList.get(position).getDeath() != null)
            totalDeath = Integer.parseInt(matchList.get(position).getDeath());
        else
            matchList.get(position).setDeath("0");
        if (matchList.get(position).getAssist() != null)
            totalAssist = Integer.parseInt(matchList.get(position).getAssist());
        else
            matchList.get(position).setAssist("0");
        DecimalFormat df2 = new DecimalFormat("###.##");
        if (totalDeath != 0) {
            double kda = (totalKill + totalAssist) / totalDeath;
            holder.gameKDA.setText(df2.format(kda) + ":1 KDA");
        } else
            holder.gameKDA.setText("Perfect");
        holder.gameLevel.setText("Level " + matchList.get(position).getLevel());
        holder.gameStats.setText(matchList.get(position).getKill() + "/" + matchList.get(position).getDeath() + "/" + matchList.get(position).getAssist());
        Integer csNeutral = 0;
        Integer cs = Integer.parseInt(matchList.get(position).getCs());
        if (matchList.get(position).getNeutralMinion() != null) {
            csNeutral = Integer.parseInt(matchList.get(position).getNeutralMinion());
        }
        cs += csNeutral;
        holder.gameCs.setText(String.valueOf(cs) + " CS");
        if (matchList.get(position).getLargestMultiKill() != null && matchList.get(position).getLargestMultiKill().equals("2")) {
            holder.gameMaxKill.setText("Double kill");
        } else if (matchList.get(position).getLargestMultiKill() != null && matchList.get(position).getLargestMultiKill().equals("3")) {
            holder.gameMaxKill.setText("Triple kill");
        } else if (matchList.get(position).getLargestMultiKill() != null && matchList.get(position).getLargestMultiKill().equals("4")) {
            holder.gameMaxKill.setText("Quadra kill");
        } else if (matchList.get(position).getLargestMultiKill() != null && matchList.get(position).getLargestMultiKill().equals("5")) {
            holder.gameMaxKill.setText("Penta kill");
        }
        Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + StaticDataAPI.getInstance().getChampionById(matchList.get(position).getChampionId()).getImage().getFull()).resize(84, 84).into(holder.aTeam.get(0));
        for (int i = 0; i < matchList.get(position).getaPlayers().size(); i++) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + StaticDataAPI.getInstance().getChampionById(matchList.get(position).getaPlayers().get(i).getChampionId()).getImage().getFull()).resize(84, 84).into(holder.aTeam.get(i + 1));
        }
        for (int i = 0; i < matchList.get(position).getbPlayers().size(); i++) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/champion/" + StaticDataAPI.getInstance().getChampionById(matchList.get(position).getbPlayers().get(i).getChampionId()).getImage().getFull()).resize(84, 84).into(holder.bTeam.get(i));
        }
        if (matchList.get(position).getTeamId().equals("100")) {
            holder.team1.setText("Blue team");
            holder.team1.setTextColor(ContextCompat.getColor(mContext, (R.color.colorAccentDark)));
            holder.team2.setText("Red team");
            holder.team2.setTextColor(ContextCompat.getColor(mContext, (R.color.red)));
        } else {
            holder.team1.setText("Red team");
            holder.team1.setTextColor(ContextCompat.getColor(mContext, (R.color.red)));
            holder.team2.setText("Blue team");
            holder.team2.setTextColor(ContextCompat.getColor(mContext, (R.color.colorAccentDark)));
        }
        holder.buttonExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.matchPart2.getVisibility() == View.VISIBLE) {
                    if (android.os.Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        TransitionManager.endTransitions(holder.cardview);
                    }
                    holder.matchPart2.setVisibility(View.GONE);
                    holder.delimiter.setVisibility(View.GONE);
                    holder.buttonExpand.setImageResource(R.drawable.ic_expand_more);
                } else {
                    TransitionManager.beginDelayedTransition(holder.cardview);
                    holder.matchPart2.setVisibility(View.VISIBLE);
                    holder.delimiter.setVisibility(View.VISIBLE);
                    holder.buttonExpand.setImageResource(R.drawable.ic_expand_less);
                }
            }
        });
        if (matchList.get(position).getItem0() != null && StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem0()) != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" +
                    StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem0()).getImage().getFull()).resize(84, 84).into(holder.item0);
        }
        if (matchList.get(position).getItem1() != null && StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem1()) != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" +
                    StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem1()).getImage().getFull()).resize(84, 84).into(holder.item1);
        }
        if (matchList.get(position).getItem2() != null && StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem2()) != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" +
                    StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem2()).getImage().getFull()).resize(84, 84).into(holder.item2);
        }
        if (matchList.get(position).getItem3() != null && StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem3()) != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" +
                    StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem3()).getImage().getFull()).resize(84, 84).into(holder.item3);
        }
        if (matchList.get(position).getItem4() != null && StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem4()) != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" +
                    StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem4()).getImage().getFull()).resize(84, 84).into(holder.item4);
        }
        if (matchList.get(position).getItem5() != null && StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem5()) != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" +
                    StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem5()).getImage().getFull()).resize(84, 84).into(holder.item5);
        }
        if (matchList.get(position).getItem6() != null && StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem6()) != null) {
            Picasso.with(mContext).load("http://ddragon.leagueoflegends.com/cdn/6.24.1/img/item/" +
                    StaticDataAPI.getInstance().getItemById(matchList.get(position).getItem6()).getImage().getFull()).resize(84, 84).into(holder.item6);
        }
    }

    @Override
    public int getItemCount() {
        return this.matchList.size();
    }
}
