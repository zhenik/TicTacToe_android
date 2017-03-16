package com.zhenik15.android.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity {

    public static final String TAG = "GameActivityTag";
    private String playerName1;
    private String playerName2;
    private TextView player1;
    private TextView player2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        initPlayerNames();
    }

    private void initPlayerNames(){
        playerName1 = getIntent().getStringExtra("userName1");
        playerName2 = getIntent().getStringExtra("userName2");
        Log.i(TAG, " names:"+playerName1);
        Log.i(TAG, " names:"+playerName2);
        player1 = (TextView)findViewById(R.id.game_player1_name);
        player2 = (TextView)findViewById(R.id.game_player2_name);
        player1.setText(playerName1);
        player2.setText(playerName2);
    }

}
