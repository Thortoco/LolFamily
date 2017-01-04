package corentin_evanno.lolfamily.model;

/**
 * Created by corentin on 02/01/2017.
 */

public class Item {
    private String id;

    private String plaintext;

    private String description;

    private String name;

    private PictureChampion image;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getPlaintext ()
    {
        return plaintext;
    }

    public void setPlaintext (String plaintext)
    {
        this.plaintext = plaintext;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
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

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", plaintext = "+plaintext+", description = "+description+", name = "+name+", image = "+image+"]";
    }
}
