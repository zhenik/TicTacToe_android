package com.zhenik15.android.tictactoe.model.util;

import android.content.Context;
import android.widget.TableLayout;

import com.zhenik15.android.tictactoe.model.Board;
import com.zhenik15.android.tictactoe.model.Player;
import com.zhenik15.android.tictactoe.model.PlayerStatsService;


 /**
  * Abstract class represent factory for necessary POJO objects:
  *     Player(String name)
  *     PlayerStatsService(Context context)
  *     Board(TableLayout tableLayout)
  *
  * */

public abstract class ApplicationPojoFactory {

    public static Board getBoardInstance(TableLayout tableLayout){
        return new Board(tableLayout);
    }
    
    public static PlayerStatsService getPlayerStatsServiceInstance(Context context){
        return new PlayerStatsService(context);
    }
    public static Player getPlayerInstance(String name){
        return new Player(name);
    }
    public static Player getPlayerInstance(String name, int score){
        return new Player(name, score);
    }
}
