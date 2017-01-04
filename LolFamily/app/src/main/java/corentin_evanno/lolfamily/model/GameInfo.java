package corentin_evanno.lolfamily.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by corentin on 20/12/2016.
 */

public class GameInfo {
    private String subType;
    private String createDate;
    private boolean win;
    private String timePlayed;
    private String championId;
    private String spell1;
    private String spell2;
    private String kill;
    private String death;
    private String assist;
    private String largestMultiKill;
    private String level;
    private String cs;
    private String teamId;
    private String item0;
    private String item1;
    private String item2;
    private String item3;
    private String item4;
    private String item5;
    private String item6;
    private String neutralMinion;

    private ArrayList<FellowPlayers> aPlayers;
    private ArrayList<FellowPlayers> bPlayers;

    public GameInfo(Games game) {
        aPlayers = new ArrayList<>();
        bPlayers = new ArrayList<>();
        subType = game.getSubType();
        createDate = game.getCreateDate();
        win = game.getStats().isWin();
        timePlayed = game.getStats().getTimePlayed();
        championId = game.getChampionId();
        spell1 = game.getSpell1();
        spell2 = game.getSpell2();
        kill = game.getStats().getChampionsKilled();
        death = game.getStats().getNumDeaths();
        assist = game.getStats().getAssists();
        largestMultiKill = game.getStats().getLargestMultiKill();
        level = game.getStats().getLevel();
        cs = game.getStats().getMinionsKilled();
        if(game.getStats().getNeutralMinionsKilled() != null)
            neutralMinion = game.getStats().getNeutralMinionsKilled();
        //System.out.println(this.toString());
        if (game.getStats().getItem0() != null)
            item0 = game.getStats().getItem0();
        if (game.getStats().getItem1() != null)
            item1 = game.getStats().getItem1();
        if (game.getStats().getItem2() != null)
            item2 = game.getStats().getItem2();
        if (game.getStats().getItem3() != null)
            item3 = game.getStats().getItem3();
        if (game.getStats().getItem4() != null)
            item4 = game.getStats().getItem4();
        if (game.getStats().getItem5() != null)
            item5 = game.getStats().getItem5();
        if (game.getStats().getItem6() != null)
            item6 = game.getStats().getItem6();
        teamId = game.getTeamId();
        for (int i = 0; i < game.getFellowPlayers().length; i++) {
            if (game.getFellowPlayers()[i].getTeamId().equals(teamId))
                aPlayers.add(game.getFellowPlayers()[i]);
            else
                bPlayers.add(game.getFellowPlayers()[i]);
        }
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public String getChampionId() {
        return championId;
    }

    public void setChampionId(String championId) {
        this.championId = championId;
    }

    public String getSpell1() {
        return spell1;
    }

    public void setSpell1(String spell1) {
        this.spell1 = spell1;
    }

    public String getSpell2() {
        return spell2;
    }

    public void setSpell2(String spell2) {
        this.spell2 = spell2;
    }

    public String getKill() {
        return kill;
    }

    public void setKill(String kill) {
        this.kill = kill;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getAssist() {
        return assist;
    }

    public void setAssist(String assist) {
        this.assist = assist;
    }

    public String getLargestMultiKill() {
        return largestMultiKill;
    }

    public void setLargestMultiKill(String largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public ArrayList<FellowPlayers> getaPlayers() {
        return aPlayers;
    }

    public void setaPlayers(ArrayList<FellowPlayers> aPlayers) {
        this.aPlayers = aPlayers;
    }

    public ArrayList<FellowPlayers> getbPlayers() {
        return bPlayers;
    }

    public void setbPlayers(ArrayList<FellowPlayers> bPlayers) {
        this.bPlayers = bPlayers;
    }

    public String getItem0() {
        return item0;
    }

    public void setItem0(String item0) {
        this.item0 = item0;
    }


    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }


    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public String getItem3() {
        return item3;
    }

    public void setItem3(String item3) {
        this.item3 = item3;
    }

    public String getItem4() {
        return item4;
    }

    public void setItem4(String item4) {
        this.item4 = item4;
    }

    public String getItem5() {
        return item5;
    }

    public void setItem5(String item5) {
        this.item5 = item5;
    }

    public String getItem6() {
        return item6;
    }

    public void setItem6(String item6) {
        this.item6 = item6;
    }

    public String getNeutralMinion() {
        return neutralMinion;
    }

    public void setNeutralMinion(String neutralMinion) {
        this.neutralMinion = neutralMinion;
    }

    @Override
    public String toString() {
        return "Match " + subType + " [" + createDate + "]" + "{\n" +
                "win:" + win +
                "\ntime played:" + timePlayed +
                "\nchampionid:" + championId +
                "\nspell1:" + spell1 +
                "\nspell2:" + spell2 +
                "\nkill:" + kill +
                "\ndeath:" + death +
                "\nassist:" + assist +
                "\nmultikill:" + largestMultiKill +
                "\nlevel:" + level +
                "\ncs:" + cs + "} ";

    }
}
