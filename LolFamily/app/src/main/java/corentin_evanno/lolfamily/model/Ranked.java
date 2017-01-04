package corentin_evanno.lolfamily.model;

/**
 * Created by corentin on 11/09/2016.
 */
public class Ranked
{
    private String modifyDate;

    private Champions[] champions;

    private String summonerId;

    public String getModifyDate ()
    {
        return modifyDate;
    }

    public void setModifyDate (String modifyDate)
    {
        this.modifyDate = modifyDate;
    }

    public Champions[] getChampions ()
    {
        return champions;
    }

    public void setChampions (Champions[] champions)
    {
        this.champions = champions;
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
        return "ClassPojo [modifyDate = "+modifyDate+", champions = "+champions+", summonerId = "+summonerId+"]";
    }
}
