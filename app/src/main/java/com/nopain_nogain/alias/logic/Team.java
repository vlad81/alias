package com.nopain_nogain.alias.logic;

import java.io.Serializable;

public class Team implements Serializable {
    private String teamName;
    private int teamScores = 0;

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public void incrementTeamScores () {
        teamScores++;
    }

    public void deecrementTeamScores () {
        teamScores--;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamScores() {
        return teamScores;
    }
}
