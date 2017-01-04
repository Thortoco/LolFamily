package corentin_evanno.lolfamily.model;

import android.media.Image;

/**
 * Created by corentin on 12/09/2016.
 */

public class Champion
{
    private String id;

    private String title;

    private String name;

    private PictureChampion image;

    private String key;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public PictureChampion getImage ()
    {
        return image;
    }

    public void setImage (PictureChampion image)
    {
        this.image = image;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", title = "+title+", name = "+name+", image = "+image+", key = "+key+"]";
    }
}