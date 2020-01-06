package com.nopain_nogain.alias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nopain_nogain.alias.logic.RoundOptions;
import com.nopain_nogain.alias.logic.TeamsHolder;


public class SetGameOptionsActivity extends AppCompatActivity {
    private boolean switchMinuses;
    private int roundTime;
    private int pointsToWin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_game_options);

        Switch swich = findViewById(R.id.switchDifId);
        swich.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    switchMinuses = true;
            }
        });

        final EditText roundPeriod = findViewById(R.id.periodOfTime);
        final EditText pointsToWinField = findViewById(R.id.pointsToWin);

        Button startRoundButton = findViewById(R.id.startRoundBtn);
        startRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundTime = Integer.parseInt(roundPeriod.getText().toString());
                pointsToWin = Integer.parseInt(pointsToWinField.getText().toString());
                if (roundTime != 0) {
                    Intent intent = new Intent(getApplicationContext(), GameRoundActivity.class);
                    TeamsHolder teamsHolder = (TeamsHolder) getIntent().getSerializableExtra("teams_holder");
                    //Toast.makeText(getApplicationContext(), teamsHolder.getTeams()[0].getTeamName(),Toast.LENGTH_SHORT).show();
                    RoundOptions roundOptions = new RoundOptions();
                    roundOptions.setPointsToWin(pointsToWin);
                    roundOptions.setRoundDuration(roundTime*1000);
                    roundOptions.setSwitchMinuses(switchMinuses);
                    roundOptions.setTeamsHolder(teamsHolder);
                    intent.putExtra("round_options",roundOptions);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Time should be great than 0", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
