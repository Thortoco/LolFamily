package corentin_evanno.lolfamily.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by corentin on 11/09/2016.
 */
public class Entries
{
    @SerializedName("leaguePoints")
    @Expose
    private int leaguePoints;

    @SerializedName("isHotStreak")
    @Expose
    private boolean isHotStreak;

    @SerializedName("isFreshBlood")
    @Expose
    private boolean isFreshBlood;

    @SerializedName("division")
    @Expose
    private String division;

    @SerializedName("isInactive")
    @Expose
    private boolean isInactive;

    @SerializedName("playstyle")
    @Expose
    private String playstyle;

    @SerializedName("isVeteran")
    @Expose
    private boolean isVeteran;

    @SerializedName("losses")
    @Expose
    private int losses;

    @SerializedName("playerOrTeamName")
    @Expose
    private String playerOrTeamName;

    @SerializedName("playerOrTeamId")
    @Expose
    private String playerOrTeamId;

    @SerializedName("wins")
    @Expose
    private int wins;

    public int getLeaguePoints ()
    {
        return leaguePoints;
    }

    public void setLeaguePoints (int leaguePoints)
    {
        this.leaguePoints = leaguePoints;
    }

    public boolean getIsHotStreak ()
    {
        return isHotStreak;
    }

    public void setIsHotStreak (boolean isHotStreak)
    {
        this.isHotStreak = isHotStreak;
    }

    public boolean getIsFreshBlood ()
    {
        return isFreshBlood;
    }

    public void setIsFreshBlood (boolean isFreshBlood)
    {
        this.isFreshBlood = isFreshBlood;
    }

    public String getDivision ()
    {
        return division;
    }

    public void setDivision (String division)
    {
        this.division = division;
    }

    public boolean getIsInactive ()
    {
        return isInactive;
    }

    public void setIsInactive (boolean isInactive)
    {
        this.isInactive = isInactive;
    }

    public String getPlaystyle ()
    {
        return playstyle;
    }

    public void setPlaystyle (String playstyle)
    {
        this.playstyle = playstyle;
    }

    public boolean getIsVeteran ()
    {
        return isVeteran;
    }

    public void setIsVeteran (boolean isVeteran)
    {
        this.isVeteran = isVeteran;
    }

    public int getLosses ()
    {
        return losses;
    }

    public void setLosses (int losses)
    {
        this.losses = losses;
    }

    public String getPlayerOrTeamName ()
    {
        return playerOrTeamName;
    }

    public void setPlayerOrTeamName (String playerOrTeamName)
    {
        this.playerOrTeamName = playerOrTeamName;
    }

    public String getPlayerOrTeamId ()
    {
        return playerOrTeamId;
    }

    public void setPlayerOrTeamId (String playerOrTeamId)
    {
        this.playerOrTeamId = playerOrTeamId;
    }

    public int getWins ()
    {
        return wins;
    }

    public void setWins (int wins)
    {
        this.wins = wins;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [leaguePoints = "+leaguePoints+", isHotStreak = "+isHotStreak+", isFreshBlood = "+isFreshBlood+", division = "+division+", isInactive = "+isInactive+", playstyle = "+playstyle+", isVeteran = "+isVeteran+", losses = "+losses+", playerOrTeamName = "+playerOrTeamName+", playerOrTeamId = "+playerOrTeamId+", wins = "+wins+"]";
    }
}
