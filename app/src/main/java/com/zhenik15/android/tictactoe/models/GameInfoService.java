package com.zhenik15.android.tictactoe.models;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhenik15.android.tictactoe.R;
import com.zhenik15.android.tictactoe.models.Player;
import com.zhenik15.android.tictactoe.view.activity.GameActivity;

import java.io.Serializable;


public class GameInfoService implements Serializable {

    private Player player1;
    private Player player2;
    private View view;

    private Activity activity;

    private View navBar;


    public GameInfoService(Player p1, Player p2, View view, Activity activity){
        this.player1=p1;
        this.player2=p2;
        this.view=view;
        this.activity=activity;

//        initNames();
//        initScore();
//        hideNavigationButtons();
//        showNavigationButtons();
    }

    public void initNavigation(){
        navBar=activity.findViewById(R.id.game_nav_panel);
    }
    public void initNames(){
//        ((TextView)(activity.findViewById(R.id.game_player1_name))).setText(player1.getName());
//        ((GameActivity)activity).playerScore1.setText(player1.getName());
//        ((TextView)(activity.findViewById(R.id.game_player2_name))).setText(player2.getName());
    }
    public void initScore(){
        ((TextView)((Activity)view.getContext()).findViewById(R.id.game_player1_score)).setText("Score: "+player1.getScore());
        ((TextView)((Activity)view.getContext()).findViewById(R.id.game_player2_score)).setText("Score: "+player2.getScore());
    }

    public void showNavigationButtons(){
        navBar.setVisibility(View.VISIBLE);

    }
    public void hideNavigationButtons(){
        navBar.setVisibility(View.INVISIBLE);
    }

}

