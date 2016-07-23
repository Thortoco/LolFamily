package corentin_evanno.lolfamily.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by corentin on 13/07/2016.
 */
public class PlayerStatsSummary {

    @SerializedName("playerStatSummaryType")
    @Expose
    private String playerStatSummaryType;
    @SerializedName("wins")
    @Expose
    private Integer wins;
    @SerializedName("modifyDate")
    @Expose
    private Integer modifyDate;
    @SerializedName("aggregatedStats")
    @Expose
    private AggregatedStats aggregatedStats;
    @SerializedName("losses")
    @Expose
    private Integer losses;

    /**
     *
     * @return
     * The playerStatSummaryType
     */
    public String getPlayerStatSummaryType() {
        return playerStatSummaryType;
    }

    /**
     *
     * @param playerStatSummaryType
     * The playerStatSummaryType
     */
    public void setPlayerStatSummaryType(String playerStatSummaryType) {
        this.playerStatSummaryType = playerStatSummaryType;
    }

    /**
     *
     * @return
     * The wins
     */
    public Integer getWins() {
        return wins;
    }

    /**
     *
     * @param wins
     * The wins
     */
    public void setWins(Integer wins) {
        this.wins = wins;
    }

    /**
     *
     * @return
     * The modifyDate
     */
    public Integer getModifyDate() {
        return modifyDate;
    }

    /**
     *
     * @param modifyDate
     * The modifyDate
     */
    public void setModifyDate(Integer modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     *
     * @return
     * The aggregatedStats
     */
    public AggregatedStats getAggregatedStats() {
        return aggregatedStats;
    }

    /**
     *
     * @param aggregatedStats
     * The aggregatedStats
     */
    public void setAggregatedStats(AggregatedStats aggregatedStats) {
        this.aggregatedStats = aggregatedStats;
    }

    /**
     *
     * @return
     * The losses
     */
    public Integer getLosses() {
        return losses;
    }

    /**
     *
     * @param losses
     * The losses
     */
    public void setLosses(Integer losses) {
        this.losses = losses;
    }

}