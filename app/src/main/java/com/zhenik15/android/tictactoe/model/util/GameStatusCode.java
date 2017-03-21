package com.zhenik15.android.tictactoe.model.util;

import java.io.Serializable;

public abstract class GameStatusCode implements Serializable {
    public static final int DRAW = -1;
    public static final int CONTINUE = 0;
    public static final int X_WIN = 1;
    public static final int O_WIN = 2;
}