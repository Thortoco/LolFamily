
package corentin_evanno.lolfamily.model;


import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class Summoner  implements Serializable {
    @Expose
    private long id;
    @Expose
    private String name;
    @Expose
    private int profileIconId;
    @Expose
    private int summonerLevel;
    @Expose
    private long revisionDate;

    /**
     *
     * @return
     * The id
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The profileIconId
     */
    public int getProfileIconId() {
        return profileIconId;
    }

    /**
     *
     * @param profileIconId
     * The profileIconId
     */
    public void setProfileIconId(int profileIconId) {
        this.profileIconId = profileIconId;
    }

    /**
     *
     * @return
     * The summonerLevel
     */
    public int getSummonerLevel() {
        return summonerLevel;
    }

    /**
     *
     * @param summonerLevel
     * The summonerLevel
     */
    public void setSummonerLevel(int summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    /**
     *
     * @return
     * The revisionDate
     */
    public long getRevisionDate() {
        return revisionDate;
    }

    /**
     *
     * @param revisionDate
     * The revisionDate
     */
    public void setRevisionDate(long revisionDate) {
        this.revisionDate = revisionDate;
    }

}