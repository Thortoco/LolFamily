package corentin_evanno.lolfamily.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by corentin on 11/09/2016.
 */
public class StatsRanked{

    @SerializedName("queue")
    @Expose
    private String queue;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("tier")
    @Expose
    private String tier;

    @SerializedName("entries")
    @Expose
    private Entries[] entries;

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Entries[] getEntries() {
        return entries;
    }

    public void setEntries(Entries[] entries) {
        this.entries = entries;
    }

    @Override
    public String toString() {
        return "ClassPojo [queue = " + queue + ", name = " + name + ", tier = " + tier + ", entries = " + entries + "]";
    }
}
