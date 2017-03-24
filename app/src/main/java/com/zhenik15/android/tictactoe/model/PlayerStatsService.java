package com.zhenik15.android.tictactoe.model;

import android.content.Context;
import android.util.Log;

import com.zhenik15.android.tictactoe.model.util.ApplicationPojoFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;


/**
 * Class represent service
 * responsible for managing player statistic
 * */
// http://startandroid.ru/ru/uroki/vse-uroki-spiskom/138-urok-75-hranenie-dannyh-rabota-s-fajlami.html
public class PlayerStatsService {
    public static final String LOG_TAG = "PlayerStatsService:> ";
    public static final String FILENAME = "file";
    private Context context;

    public PlayerStatsService(Context context) {
        this.context = context;
    }

    /**
     * Append to file new lines ->
     * line format -> 'Player name' / 'Player score'
     * */
    public void appendToFile(Player player, String fileName) {

        // open stream in try-with-resources
        try (BufferedWriter bw =
                     new BufferedWriter(
                             new OutputStreamWriter(
                                     context.openFileOutput(fileName, MODE_PRIVATE | MODE_APPEND)))
        ){

            // append
            bw.append(player.getName() + "/" + player.getScore() + "\n");
            Log.d(LOG_TAG, "File has written");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Optimize file :
     *      sort players by score DESC
     *      delete rest lines if more than 3 (recreate file with only 3 lines)
     * */
    public void optimizeFile(String fileName) {
        List<Player> list = getPlayerListFromFile(fileName);
        Collections.sort(list);
        context.deleteFile(fileName);
        if (list.size() < 3) {
            for (Player p : list) {
                appendToFile(p, fileName);
            }
        } else {
            for (int i = 0; i < 3; i++)
                appendToFile(list.get(i), fileName);
        }
    }

    /**
     * Parser for file, return list of players
     * line format -> 'Player name' / 'Player score'
     * */
    public List<Player> getPlayerListFromFile(String fileName) {
        String str;
        List<Player> players = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)))) {
            while ((str = br.readLine()) != null) {
                Log.d(LOG_TAG, str);
                String[] l = str.split("/");
                if (l.length == 2) {
                    Player player = ApplicationPojoFactory.getPlayerInstance(l[0], Integer.parseInt(l[1]));
                    players.add(player);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }
}
