package com.zhenik15.android.tictactoe.model;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class PlayerStatsServiceTest {

    public static final String TAG ="TEST:> ";
    private Context context;
    private PlayerStatsService service;
    private final String FILENAME = "test";


    @Before
    public void setup() {
        context = InstrumentationRegistry.getTargetContext();
        service= new PlayerStatsService(context);
    }
    @After
    public void tearDown(){
        service=null;
        context=null;
    }

    private List<String> readFromFile(){
        List<String> list = new ArrayList<String>();
        String str;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(context.openFileInput(FILENAME)))) {
            while ((str = br.readLine()) != null) {
                list.add(str);
                Log.d(TAG, str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Test
    public void appendToFile() throws InterruptedException {
        // Arrange
        Player p1 = new Player("A", 5);
        Player p2 = new Player("B", 6);
        List<String> list;

        // Act
        service.appendToFile(p1,FILENAME);
        service.appendToFile(p2,FILENAME);
        list=readFromFile();
        context.deleteFile(FILENAME);

        // Assert
        assertEquals(2, list.size());
        assertEquals("A/5", list.get(0));
        assertEquals("B/6", list.get(1));
    }


    @Test
    public void optimizeFile() throws Exception {
        // Arrange
        Player p1 = new Player("A", 3);
        Player p2 = new Player("B", 7);
        Player p3 = new Player("C", 5);
        Player p4 = new Player("D", 12);
        List<String> list;

        // Act
        service.appendToFile(p1,FILENAME);
        service.appendToFile(p2,FILENAME);
        service.appendToFile(p3,FILENAME);
        service.appendToFile(p4,FILENAME);
/**
 * Optimization START
 * */
        service.optimizeFile(FILENAME);
/**
 * Optimization END
 * */
        list=readFromFile();
        context.deleteFile(FILENAME);

        // Assert
        assertEquals(3, list.size());
        assertEquals("D/12", list.get(0));
        assertEquals("B/7", list.get(1));
        assertEquals("C/5", list.get(2));
    }

    @Test
    public void getPlayerListFromFile() throws Exception {
        // Arrange
        Player p1 = new Player("A", 3);
        Player p2 = new Player("B", 7);
        Player p3 = new Player("C", 5);
        Player p4 = new Player("D", 12);
        List<Player> list;

        // Act
        service.appendToFile(p1,FILENAME);
        service.appendToFile(p2,FILENAME);
        service.appendToFile(p3,FILENAME);
        service.appendToFile(p4,FILENAME);

        list=service.getPlayerListFromFile(FILENAME);

        context.deleteFile(FILENAME);

        // Assert
        assertEquals(4, list.size());
        assertEquals(p1.getName(), list.get(0).getName());

    }

}