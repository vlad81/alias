package com.nopain_nogain.alias.logic;

import java.io.Serializable;
import java.util.ArrayList;

public class TeamsHolder implements Serializable {
    private ArrayList<Team> teams;

    public TeamsHolder(ArrayList<Team> teams) {
        this.teams = teams;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public Team getTeam (int count) {return teams.get(count);}
}
