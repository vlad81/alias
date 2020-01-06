package com.nopain_nogain.alias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.nopain_nogain.alias.logic.Team;
import com.nopain_nogain.alias.logic.TeamsHolder;

import java.util.ArrayList;

public class CreateTeamsActivity extends AppCompatActivity {
    private LinearLayout parentLinearLayout;
    private boolean firstViewed = true;

    private ArrayList<Team> teamsList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_teams);
        parentLinearLayout = findViewById(R.id.parentLinearLayout);

        final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (firstViewed) {
            final View rowView = inflater.inflate(R.layout.team_text_view, null);
            parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);
            firstViewed = false;
        }
        Button addNewTeamBtn = findViewById(R.id.addTeamBtn);
        addNewTeamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View rowView = inflater.inflate(R.layout.team_text_view,null);
                parentLinearLayout.addView(rowView,parentLinearLayout.getChildCount() - 1);
            }
        });

        final Button finishTeamsCreating = findViewById(R.id.finishTeamsCreating);
        finishTeamsCreating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTeamCreating();
            }
        });
    }

    private void finishTeamCreating() {
        if (isFieldRight()) {
            Intent intent = new Intent(getApplicationContext(),SetGameOptionsActivity.class);
            intent.putExtra("teams_holder", new TeamsHolder(teamsList));
            startActivity(intent);
        }
        else {
            Toast.makeText(getApplicationContext(),"Make sure that you filled all fields",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isFieldRight() {
        for (int i = 1; i < parentLinearLayout.getChildCount() - 1; i++) {
            View rowView = parentLinearLayout.getChildAt(i);
            EditText teamNameField = rowView.findViewById(R.id.team_name);
            String teamName = teamNameField.getText().toString();
            if (teamName.equals("")) {
                return false;
            }
            teamsList.add(new Team(teamName));
        }
        return true;
    }
}
