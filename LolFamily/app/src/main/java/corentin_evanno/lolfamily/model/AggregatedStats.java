package corentin_evanno.lolfamily.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by corentin on 13/07/2016.
 */
public class AggregatedStats {

    @SerializedName("totalChampionKills")
    @Expose
    private Integer totalChampionKills;
    @SerializedName("totalMinionKills")
    @Expose
    private Integer totalMinionKills;
    @SerializedName("totalTurretsKilled")
    @Expose
    private Integer totalTurretsKilled;
    @SerializedName("totalNeutralMinionsKilled")
    @Expose
    private Integer totalNeutralMinionsKilled;
    @SerializedName("totalAssists")
    @Expose
    private Integer totalAssists;

    /**
     *
     * @return
     * The totalChampionKills
     */
    public Integer getTotalChampionKills() {
        return totalChampionKills;
    }

    /**
     *
     * @param totalChampionKills
     * The totalChampionKills
     */
    public void setTotalChampionKills(Integer totalChampionKills) {
        this.totalChampionKills = totalChampionKills;
    }

    /**
     *
     * @return
     * The totalMinionKills
     */
    public Integer getTotalMinionKills() {
        return totalMinionKills;
    }

    /**
     *
     * @param totalMinionKills
     * The totalMinionKills
     */
    public void setTotalMinionKills(Integer totalMinionKills) {
        this.totalMinionKills = totalMinionKills;
    }

    /**
     *
     * @return
     * The totalTurretsKilled
     */
    public Integer getTotalTurretsKilled() {
        return totalTurretsKilled;
    }

    /**
     *
     * @param totalTurretsKilled
     * The totalTurretsKilled
     */
    public void setTotalTurretsKilled(Integer totalTurretsKilled) {
        this.totalTurretsKilled = totalTurretsKilled;
    }

    /**
     *
     * @return
     * The totalNeutralMinionsKilled
     */
    public Integer getTotalNeutralMinionsKilled() {
        return totalNeutralMinionsKilled;
    }

    /**
     *
     * @param totalNeutralMinionsKilled
     * The totalNeutralMinionsKilled
     */
    public void setTotalNeutralMinionsKilled(Integer totalNeutralMinionsKilled) {
        this.totalNeutralMinionsKilled = totalNeutralMinionsKilled;
    }

    /**
     *
     * @return
     * The totalAssists
     */
    public Integer getTotalAssists() {
        return totalAssists;
    }

    /**
     *
     * @param totalAssists
     * The totalAssists
     */
    public void setTotalAssists(Integer totalAssists) {
        this.totalAssists = totalAssists;
    }

}