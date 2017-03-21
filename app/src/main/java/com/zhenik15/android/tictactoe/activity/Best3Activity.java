package com.zhenik15.android.tictactoe.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.model.Player;
import com.zhenik15.android.tictactoe.model.PlayerStatsService;

import java.util.ArrayList;
import java.util.List;


public class Best3Activity extends AppCompatActivity {

    public static final String TAG = "Best3Activity:> ";
    private List<TextView> names;
    private List<TextView> scores;
    private PlayerStatsService pss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best3_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initTextViews();
        pss = new PlayerStatsService(getApplicationContext());
        setNamesScore();
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void initTextViews(){
        names= new ArrayList<>();
        names.add((TextView)findViewById(R.id.best3_player1_name));
        names.add((TextView)findViewById(R.id.best3_player2_name));
        names.add((TextView)findViewById(R.id.best3_player3_name));

        scores = new ArrayList<>();
        scores.add((TextView)findViewById(R.id.best3_player1_score));
        scores.add((TextView)findViewById(R.id.best3_player2_score));
        scores.add((TextView)findViewById(R.id.best3_player3_score));
    }

    private void setNamesScore(){
        ArrayList<Player> players = (ArrayList<Player>) pss.getPlayerListFromFile();
        // LOG
        for (Player p : players){
            Log.i(TAG, p.getName()+" | "+p.getScore());
        }

        if (players.size()==0 || players==null){return;}

        if (players.size()==1){
            names.get(0).setText(players.get(0).getName());
            scores.get(0).setText(String.valueOf(players.get(0).getScore()));
            return;
        }

        if (players.size()==2){
            names.get(0).setText(players.get(0).getName());
            scores.get(0).setText(String.valueOf(players.get(0).getScore()));
            names.get(1).setText(players.get(1).getName());
            scores.get(1).setText(String.valueOf(players.get(1).getScore()));
            return;
        }

        for (int i=0;i<3;i++){
            names.get(i).setText(players.get(i).getName());
            scores.get(i).setText(String.valueOf(players.get(i).getScore()));
        }
    }

}
