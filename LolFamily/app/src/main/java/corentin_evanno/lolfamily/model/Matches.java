package corentin_evanno.lolfamily.model;

/**
 * Created by corentin on 15/09/2016.
 */
public class Matches
{
    private Games[] games;

    private String summonerId;

    public Games[] getGames ()
    {
        return games;
    }

    public void setGames (Games[] games)
    {
        this.games = games;
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
        return "ClassPojo [games = "+games+", summonerId = "+summonerId+"]";
    }
}
