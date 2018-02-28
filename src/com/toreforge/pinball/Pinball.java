package com.toreforge.pinball;

import com.toreforge.pinball.exception.BoardLoaderException;
import com.toreforge.pinball.exception.BoardNotLoadedException;

import java.awt.*;
import java.io.IOException;

/**
 * To_Reforge Challenge
 * Date: 2018-02-20
 * Author: Rafal Martinez-Marjanski
 * Brief: Calculate the final position of the ball
 * license: GPL
 */

public class Pinball {

    private static int moves = 0;

    public static void main(String[] args)
            throws IOException, BoardLoaderException, BoardNotLoadedException {
        Board b = new Board();
        b.loadBoard();
        proceed(b, b.getBallPosition(), Vector.applyVector(b.getBallPosition(), b.getCurrentVector()), true);
        System.out.println("moves : " + moves);
        b.saveBoard();
    }

    private static void proceed(Board b, Point ballPosition, Point nextPoint, boolean mark) throws BoardNotLoadedException {
        switch (b.getChamber(nextPoint)) {
            case BOTTOM_RIGHT_BOUNCE:
            case RIGHT_ANGLE_BOUNCE:
            case HORIZONTAL_BARRIER:
            case BOTTOM_LEFT_BOUNCE:
            case LEFT_ANGLE_BOUNCE:
            case VERTICAL_BARRIER:
            case TOP_RIGHT_BOUNCE:
            case TOP_LEFT_BOUNCE:
                if (mark)//only mark if previous point wasnt barrier or bounce
                    b.setMarkAt(ballPosition);
                Vector vector = Vector.transformVector(b.getCurrentVector(), b.getChamber(nextPoint));
                b.setCurrentVector(vector);
                proceed(b, nextPoint, Vector.applyVector(nextPoint, b.getCurrentVector()), false);
                break;
            case END:
                if (mark)//only mark if previous point wasnt barrier or bounce
                    b.setMarkAt(ballPosition);
                b.setBallAt(nextPoint);
                moves++;
                break;
            case MARK:
                b.setBallAt(nextPoint);
                proceed(b, b.getBallPosition(), Vector.applyVector(b.getBallPosition(), b.getCurrentVector()), true);
                moves++;
                break;
            case BALL:
                // there is only one ball
            case EMPTY:
                if (mark)//only mark if previous point wasnt barrier or bounce
                    b.setMarkAt(ballPosition);
                b.setBallAt(nextPoint);
                proceed(b, b.getBallPosition(), Vector.applyVector(b.getBallPosition(), b.getCurrentVector()), true);
                moves++;
                break;
        }
    }
}
