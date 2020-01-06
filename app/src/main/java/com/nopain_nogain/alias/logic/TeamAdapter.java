package com.nopain_nogain.alias.logic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.nopain_nogain.alias.R;

import java.util.ArrayList;
import java.util.List;

public class TeamAdapter extends ArrayAdapter<Team> {
    private Context context;
    private List<Team> teamsList = new ArrayList<>();

    public TeamAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Team> list) {
        super(context, 0, list);
        this.context = context;
        this.teamsList = list;
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.team_result_item,parent,false);

        Team team = teamsList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.teamNameTxt);
        name.setText(team.getTeamName());

        TextView result = (TextView) listItem.findViewById(R.id.teamResultTxt);
        result.setText(String.format(" %d", team.getTeamScores()));

        return listItem;
    }
}
