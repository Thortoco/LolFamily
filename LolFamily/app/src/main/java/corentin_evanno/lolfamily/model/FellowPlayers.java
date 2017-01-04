package corentin_evanno.lolfamily.model;

/**
 * Created by corentin on 15/09/2016.
 */
public class FellowPlayers
{
    private String championId;

    private String teamId;

    private String summonerId;

    public String getChampionId ()
    {
        return championId;
    }

    public void setChampionId (String championId)
    {
        this.championId = championId;
    }

    public String getTeamId ()
    {
        return teamId;
    }

    public void setTeamId (String teamId)
    {
        this.teamId = teamId;
    }

    public String getSummonerId ()
    {
        return summonerId;
    }

    public void setSummonerId (String summonerId)
    {
        this.summonerId = summonerId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [championId = "+championId+", teamId = "+teamId+", summonerId = "+summonerId+"]";
    }
}