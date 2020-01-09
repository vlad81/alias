package com.nopain_nogain.alias;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nopain_nogain.alias.logic.RoundOptions;
import com.nopain_nogain.alias.logic.Team;
import com.nopain_nogain.alias.logic.TeamAdapter;

import java.util.ArrayList;

public class GameRoundActivity extends AppCompatActivity {
    private EditText questionFieldTxt;
    private boolean roundTimeStatus = true;
    private RoundOptions roundOptions;
    private int teamsCounter = 0;
    private Team winner = null;
    int roundPeriod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_round);
        questionFieldTxt = findViewById(R.id.questionFieldTxt);

        roundOptions = (RoundOptions) getIntent().getSerializableExtra("round_options");
        roundPeriod = roundOptions.getRoundDuration();

        startBtnInit();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void controlAnswerBtnInit() {
        initFailBtn();
        initOkBtn();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initFailBtn () {
        Button failAnswerBtn = findViewById(R.id.failQuestionBtn);
        failAnswerBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        failAnswerListener();
                        return true;
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initOkBtn () {
        Button okAnswerBtn = findViewById(R.id.rightQuestionBtn);
        okAnswerBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        okAnswerListenr();
                        return true;
                }
                return false;
            }
        });
    }

    private void failAnswerListener () {
        //TODO: show next question
        if (roundOptions.isSwitchMinuses())
            roundOptions.getTeamsHolder().getTeams().get(teamsCounter).deecrementTeamScores();
        if (!roundTimeStatus) {
            roundEndProcessing();
        }
    }

    private void okAnswerListenr () {
        //TODO: show next question
        if (!roundTimeStatus) {
            roundOptions.getTeamsHolder().getTeam(teamsCounter).incrementTeamScores();
            roundEndProcessing();
            roundOptions.getTeamsHolder().getTeams().get(teamsCounter).deecrementTeamScores();
        }

        roundOptions.getTeamsHolder().getTeam(teamsCounter).incrementTeamScores();
    }

    private void roundEndProcessing () {
        winner = checkWinner();
        if (winner == null) {
            if (teamsCounter == roundOptions.getTeamsHolder().getTeams().size()-1)
                teamsCounter = 0;
            else
                teamsCounter++;
            roundTimeStatus = true;
            initRoundResults();
        }
        else {
            finishGame();
        }
    }

    private void finishGame () {
        setContentView(R.layout.activity_finish_game);
        TextView winnerTxt = findViewById(R.id.winnerTxt);
        winnerTxt.setText(winner.getTeamName());

        Button finishBtn = findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private Team checkWinner () {
        for (Team team : roundOptions.getTeamsHolder().getTeams()) {
            if (team.getTeamScores() >= roundOptions.getPointsToWin())
                return team;
        }

        return null;
    }

    private void startRound (final int roundPeriod) {
        //Toast.makeText(getApplicationContext(),""+teamsCounter,Toast.LENGTH_SHORT).show();
        controlAnswerBtnInit();
        final long SECOND = 1000;
        CountDownTimer timer = new CountDownTimer(roundPeriod, SECOND) {
            public void onTick(long millisUntilFinished) {
                //TODO : make cool timer
                Toast.makeText(getApplicationContext(),"Before Finsh : " + millisUntilFinished / 1000, Toast.LENGTH_SHORT).show();
            }

            public void onFinish() {
                roundTimeStatus = false;
            }
        }.start();
    }

    private void initRoundResults () {
        setContentView(R.layout.activity_game_round_results);
        ListView teamsResultsListView = findViewById(R.id.teamsResultsListView);
        ArrayList<Team> teamArrayList = roundOptions.getTeamsHolder().getTeams();
        TeamAdapter adapter = new TeamAdapter(this, teamArrayList);
        teamsResultsListView.setAdapter(adapter);


        Button nextRoundBtn = findViewById(R.id.nextRoundBtn);
        nextRoundBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_game_round);
                startBtnInit();
            }
        });

    }

    private void startBtnInit () {
        Button startBtn = findViewById(R.id.startRoundBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_game_round_proces);
                startRound(roundPeriod);
            }
        });
    }
}

