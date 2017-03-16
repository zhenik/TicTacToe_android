package com.zhenik15.android.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by NIK on 16/03/2017.
 */

public class GameActivity extends AppCompatActivity {

    public static final String TAG = "GameActivityTag";
    private String playerName1;
    private String playerName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        initPlayerNames();
    }

    private void initPlayerNames(){
        playerName1 = getIntent().getStringExtra("userName1");
        playerName2 = getIntent().getStringExtra("userName2");
    }
}
