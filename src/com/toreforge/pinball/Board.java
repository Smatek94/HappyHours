package com.toreforge.pinball;

import com.toreforge.pinball.exception.BoardLoaderException;
import com.toreforge.pinball.exception.BoardNotLoadedException;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class Board {

    /**
     * Represents how many times did the
     * ball bounce. Use bounce() method
     * to modify this value
     */
    private int bounceCount = 0;

    /**
     * Map loaded from file
     */
    private HashMap<Point, Chamber> chambers = new HashMap<>();

    /**
     * Current ball position updated at load time
     */
    private Point currentBallPosition;

    /**
     * Size of loaded board
     */
    private Point boardSize;

    /**
     * Initial vector for the ball
     */
    private Vector currentVector;

    /**
     * Loads board using BoardLoader which loads
     * all the lines of the file into a hash map
     * which is used by this method to build a board
     *
     * @throws Exception in case of I/O errors or invalid input
     */
    public void loadBoard() throws IOException, BoardLoaderException {
        BoardLoader loader = new BoardLoader("input.txt");
        // load vector
        currentVector = new Vector(
                getVectorX(loader.getLine(0)),
                getVectorY(loader.getLine(0))
        );

        int columns = loader.getLine(1).length();
        boardSize = new Point(columns,loader.getLineCount() - 1);

        for (int row = 0; row < loader.getLineCount() - 1; row++) {
            // first line in the input file is a vector
            // however first row should start with 0 instead of 1
            // thus we need to shift lines by 1
            String line = loader.getLine(row + 1);
            for (int position = 0; position < line.length(); position++) {
                Point c = new Point(position, row);
                Chamber chamberType =
                        Chamber.fromString(String.valueOf(line.charAt(position)));
                chambers.put(c, chamberType);
                if (chamberType.equals(Chamber.BALL))
                    currentBallPosition = new Point(position, row);
            }
        }
    }

    /**
     * Extracts X value from the input vector
     *
     * @param literalVector vector from the input
     * @return X of the vector
     */
    private short getVectorX(String literalVector) {
        String v = literalVector.replace("(", "");
        return Short.valueOf(v.split(",")[0]);
    }

    /**
     * Extracts Y value from the input vector
     *
     * @param literalVector vector from the input
     * @return Y of the vector
     */
    private short getVectorY(String literalVector) {
        String v = literalVector.replace(")", "");
        return Short.valueOf(v.split(",")[1]);
    }

    public boolean isBoardLoaded() {
        return !chambers.isEmpty();
    }

    private void validateBoard()
            throws BoardNotLoadedException {
        if (!isBoardLoaded())
            throw new BoardNotLoadedException();
    }

    public Chamber getChamber(Point coords)
            throws BoardNotLoadedException {
        validateBoard();
        return chambers.get(coords);
    }

    public void setBallAt(Point position)
            throws BoardNotLoadedException {
        validateBoard();
        chambers.put(position, Chamber.BALL);
        currentBallPosition = position;
    }

    public void setMarkAt(Point position)
            throws BoardNotLoadedException {
        validateBoard();
        chambers.put(position, Chamber.MARK);
    }

    public Point getBallPosition()
            throws BoardNotLoadedException {
        validateBoard();
        return currentBallPosition;
    }

    public void saveBoard() throws IOException {
        BoardWriter.saveBoard(boardSize, chambers);
    }

    public void bounce() {
        bounceCount++;
    }

    public void bounce(int count) {
        bounceCount += count;
    }

    public int getBounceCount() {
        return bounceCount;
    }

    public Vector getCurrentVector() {
        return currentVector;
    }

    public void setCurrentVector(Vector currentVector) {
        this.currentVector = currentVector;
    }
}
