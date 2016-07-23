package corentin_evanno.lolfamily.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by corentin on 13/07/2016.
 */
public class StatsSummary {

    @SerializedName("summonerId")
    @Expose
    private Integer summonerId;
    @SerializedName("playerStatSummaries")
    @Expose
    private ArrayList<PlayerStatsSummary> playerStatSummaries = new ArrayList<PlayerStatsSummary>();

    /**
     *
     * @return
     * The summonerId
     */
    public Integer getSummonerId() {
        return summonerId;
    }

    /**
     *
     * @param summonerId
     * The summonerId
     */
    public void setSummonerId(Integer summonerId) {
        this.summonerId = summonerId;
    }

    /**
     *
     * @return
     * The playerStatSummaries
     */
    public ArrayList<PlayerStatsSummary> getPlayerStatSummaries() {
        return playerStatSummaries;
    }

    /**
     *
     * @param playerStatSummaries
     * The playerStatSummaries
     */
    public void setPlayerStatSummaries(ArrayList<PlayerStatsSummary> playerStatSummaries) {
        this.playerStatSummaries = playerStatSummaries;
    }

}