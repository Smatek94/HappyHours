package com.toreforge.pinball;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class BoardWriter {

    public static void saveBoard(Point size, HashMap<Point, Chamber> board)
            throws IOException {
        BufferedWriter writer =
                new BufferedWriter(
                        new FileWriter("output.txt")
                );
        for (int row = 0; row < size.y; row++) {
            for (int column = 0; column < size.x; column++) {
                writer.write(board.get(new Point(column, row)).toString());
            }
            writer.newLine();
        }
        writer.close();
    }
}
