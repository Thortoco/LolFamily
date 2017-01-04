package corentin_evanno.lolfamily.model;

import java.util.Map;

/**
 * Created by corentin on 02/01/2017.
 */

public class ItemStaticData {
    private Map<String, Item> data;

    private String type;

    private String version;

    public Map<String, Item> getData ()
    {
        return data;
    }

    public void setData (Map<String, Item> data)
    {
        this.data = data;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getVersion ()
    {
        return version;
    }

    public void setVersion (String version)
    {
        this.version = version;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", type = "+type+", version = "+version+"]";
    }
}
