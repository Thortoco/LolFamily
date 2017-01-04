package corentin_evanno.lolfamily.model;

/**
 * Created by corentin on 15/09/2016.
 */
public class Games
{
    private FellowPlayers[] fellowPlayers;

    private String gameType;

    private StatsMatches stats;

    private String gameId;

    private String ipEarned;

    private String spell1;

    private String teamId;

    private String spell2;

    private String gameMode;

    private String mapId;

    private String level;

    private String invalid;

    private String subType;

    private String createDate;

    private String championId;

    public FellowPlayers[] getFellowPlayers ()
    {
        return fellowPlayers;
    }

    public void setFellowPlayers (FellowPlayers[] fellowPlayers)
    {
        this.fellowPlayers = fellowPlayers;
    }

    public String getGameType ()
    {
        return gameType;
    }

    public void setGameType (String gameType)
    {
        this.gameType = gameType;
    }

    public StatsMatches getStats ()
    {
        return stats;
    }

    public void setStats (StatsMatches stats)
    {
        this.stats = stats;
    }

    public String getGameId ()
    {
        return gameId;
    }

    public void setGameId (String gameId)
    {
        this.gameId = gameId;
    }

    public String getIpEarned ()
    {
        return ipEarned;
    }

    public void setIpEarned (String ipEarned)
    {
        this.ipEarned = ipEarned;
    }

    public String getSpell1 ()
    {
        return spell1;
    }

    public void setSpell1 (String spell1)
    {
        this.spell1 = spell1;
    }

    public String getTeamId ()
    {
        return teamId;
    }

    public void setTeamId (String teamId)
    {
        this.teamId = teamId;
    }

    public String getSpell2 ()
    {
        return spell2;
    }

    public void setSpell2 (String spell2)
    {
        this.spell2 = spell2;
    }

    public String getGameMode ()
    {
        return gameMode;
    }

    public void setGameMode (String gameMode)
    {
        this.gameMode = gameMode;
    }

    public String getMapId ()
    {
        return mapId;
    }

    public void setMapId (String mapId)
    {
        this.mapId = mapId;
    }

    public String getLevel ()
    {
        return level;
    }

    public void setLevel (String level)
    {
        this.level = level;
    }

    public String getInvalid ()
    {
        return invalid;
    }

    public void setInvalid (String invalid)
    {
        this.invalid = invalid;
    }

    public String getSubType ()
    {
        return subType;
    }

    public void setSubType (String subType)
    {
        this.subType = subType;
    }

    public String getCreateDate ()
    {
        return createDate;
    }

    public void setCreateDate (String createDate)
    {
        this.createDate = createDate;
    }

    public String getChampionId ()
    {
        return championId;
    }

    public void setChampionId (String championId)
    {
        this.championId = championId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [fellowPlayers = "+fellowPlayers+", gameType = "+gameType+", stats = "+stats+", gameId = "+gameId+", ipEarned = "+ipEarned+", spell1 = "+spell1+", teamId = "+teamId+", spell2 = "+spell2+", gameMode = "+gameMode+", mapId = "+mapId+", level = "+level+", invalid = "+invalid+", subType = "+subType+", createDate = "+createDate+", championId = "+championId+"]";
    }
}
