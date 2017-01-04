package corentin_evanno.lolfamily.model;

/**
 * Created by corentin on 11/09/2016.
 */
public class Champions
{
    private String id;

    private Stats stats;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Stats getStats ()
    {
        return stats;
    }

    public void setStats (Stats stats)
    {
        this.stats = stats;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", stats = "+stats+"]";
    }
}
