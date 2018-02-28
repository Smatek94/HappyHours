package com.toreforge.pinball;

import java.awt.*;

import static com.toreforge.pinball.Chamber.*;

/**
 * Vector wrapper
 * represents direction of the ball
 */
public class Vector {
    private short x;
    private short y;

    /**
     * Initializes vector with initial horizontal
     * and vertical movement speed.
     * @param horizontal speed x
     * @param vertical speed y
     */
    public Vector(short horizontal, short vertical) {
        x = horizontal;
        y = vertical;
    }

    /**
     * Returns new coordinates affected by the vector
     *
     * @param coords current coordinates
     * @param vec vector to apply
     * @return new position
     */
    public static Point applyVector(Point coords, Vector vec) {
        return new Point(coords.x + vec.x, coords.y + vec.y);
    }

    /**
     * @return left vector
     */
    public static Vector left() {
        return new Vector(Short.valueOf("-1"), Short.valueOf("0"));
    }

    /**
     * @return right vector
     */
    public static Vector right() {
        return new Vector(Short.valueOf("1"), Short.valueOf("0"));
    }

    /**
     * @return up vector
     */
    public static Vector up() {
        return new Vector(Short.valueOf("0"), Short.valueOf("-1"));
    }

    /**
     * @return down vector
     */
    public static Vector down() {
        return new Vector(Short.valueOf("0"), Short.valueOf("1"));
    }

    /**
     * @return up and right vector
     */
    public static Vector upRight() {
        return new Vector(Short.valueOf("1"), Short.valueOf("-1"));
    }

    /**
     * @return up and left vector
     */
    public static Vector upLeft() {
        return new Vector(Short.valueOf("-1"), Short.valueOf("1"));
    }

    /**
     * @return down and right vector
     */
    public static Vector downRight() {
        return new Vector(Short.valueOf("1"), Short.valueOf("1"));
    }

    /**
     * @return down and left vector
     */
    public static Vector downLeft() {
        return new Vector(Short.valueOf("-1"), Short.valueOf("1"));
    }

    /**
     * This method takes into account the collision
     * and calculates new vector to be applied to the ball
     *
     * @param input current ball vector
     * @return vector adjusted based on the collision
     */
    public static Vector transformVector(Vector input, Chamber collision) {
        switch (collision) {
            case BOTTOM_RIGHT_BOUNCE:
                if(input.x == 0 && input.y == -1){//from down
                    input.x = 1;
                } else if(input.x == 0 && input.y == 1){ //from up
                    input.y = -1;
                } else if(input.y == 0){ //from left or right
                    input.x = (short) -input.x;
                    input.y = 1;
                } else {
                    input.x = 0;
                    input.y = -1;
                }
                return input;
            case RIGHT_ANGLE_BOUNCE:
                if(input.x == -1 && input.y == -1){
                    return input;
                }
                short temp2 = (short) input.x;
                input.x = (short) input.y;
                input.y = temp2;
                return input;
            case HORIZONTAL_BARRIER:
                if(input.y == 0){
                    return input;
                }
                input.y = (short) -input.y;
                return input;
            case BOTTOM_LEFT_BOUNCE:
                if(input.x == 0 && input.y == -1){//from down
                    input.x = -1;
                } else if(input.x == 0 && input.y == 1){ //from up
                    input.x = -1;
                    input.y = 0;
                } else if(input.y == 0){ //from left or right
                    input.x = (short) -input.x;
                    input.y = -1;
                } else {
                    input.x = 0;
                    input.y = -1;
                }
                return input;
            case LEFT_ANGLE_BOUNCE:
                if(input.x == 1 && input.y == -1){
                    return input;
                }
                short temp = (short) -input.x;
                input.x = (short) -input.y;
                input.y = temp;
                return input;
            case VERTICAL_BARRIER:
                if(input.x == 0){
                    return input;
                }
                input.x = (short) -input.x;
                return input;
            case TOP_RIGHT_BOUNCE:
                if(input.x == 0 && input.y == 1){//from up
                    input.x = 1;
                } else if(input.x == 0 && input.y == -1){ //from up
                    input.x = 1;
                    input.y = 0;
                } else if(input.y == 0){ //from left or right
                    input.x = (short) -input.x;
                    input.y = -1;
                } else {
                    input.x = 0;
                    input.y = -1;
                }
                return input;
            case TOP_LEFT_BOUNCE:
                if(input.x == 0 && input.y == 1){//from up
                    input.x = -1;
                } else if(input.x == 0 && input.y == -1){ //from up
                    input.x = -1;
                    input.y = 0;
                } else if(input.y == 0){ //from left or right
                    input.x = (short) -input.x;
                    input.y = -1;
                } else {
                    input.x = 0;
                    input.y = -1;
                }
                return input;
        }
        return input;
    }
}
