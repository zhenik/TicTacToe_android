package com.zhenik15.android.tictactoe.models;

import java.io.Serializable;

/**
 * Created by NIK on 18/03/2017.
 */

public class GameStatusCode implements Serializable {
    public static final int DRAW = -1;
    public static final int CONTINUE = 0;
    public static final int X_WIN = 1;
    public static final int O_WIN = 2;
}
