package com.nopain_nogain.alias.logic;

import java.io.Serializable;

public class RoundOptions implements Serializable {
    private TeamsHolder teamsHolder;
    private int roundDuration;
    private boolean switchMinuses;
    private int pointsToWin;

    public void setPointsToWin(int pointsToWin) {
        this.pointsToWin = pointsToWin;
    }

    public void setTeamsHolder(TeamsHolder teamsHolder) {
        this.teamsHolder = teamsHolder;
    }

    public void setRoundDuration(int roundDuration) {
        this.roundDuration = roundDuration;
    }

    public void setSwitchMinuses(boolean switchMinuses) {
        this.switchMinuses = switchMinuses;
    }

    public TeamsHolder getTeamsHolder() {
        return teamsHolder;
    }

    public int getRoundDuration() {
        return roundDuration;
    }

    public int getPointsToWin() {
        return pointsToWin;
    }

    public boolean isSwitchMinuses() {
        return switchMinuses;
    }
}
