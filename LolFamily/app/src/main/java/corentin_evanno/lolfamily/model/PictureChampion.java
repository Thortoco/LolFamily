package corentin_evanno.lolfamily.model;

/**
 * Created by corentin on 12/09/2016.
 */
public class PictureChampion
{
    private String w;

    private String full;

    private String sprite;

    private String group;

    private String y;

    private String h;

    private String x;

    public String getW ()
    {
        return w;
    }

    public void setW (String w)
    {
        this.w = w;
    }

    public String getFull ()
    {
        return full;
    }

    public void setFull (String full)
    {
        this.full = full;
    }

    public String getSprite ()
    {
        return sprite;
    }

    public void setSprite (String sprite)
    {
        this.sprite = sprite;
    }

    public String getGroup ()
    {
        return group;
    }

    public void setGroup (String group)
    {
        this.group = group;
    }

    public String getY ()
    {
        return y;
    }

    public void setY (String y)
    {
        this.y = y;
    }

    public String getH ()
    {
        return h;
    }

    public void setH (String h)
    {
        this.h = h;
    }

    public String getX ()
    {
        return x;
    }

    public void setX (String x)
    {
        this.x = x;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [w = "+w+", full = "+full+", sprite = "+sprite+", group = "+group+", y = "+y+", h = "+h+", x = "+x+"]";
    }
}