package com.toreforge.pinball;

import com.toreforge.pinball.exception.BoardLoaderException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class BoardLoader {

    private BufferedReader reader;
    private HashMap<Integer, String> lines = new HashMap<>();
    private Integer lineCount;

    BoardLoader(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        Integer lineNo = 0;
        String line = "";
        while ((line = reader.readLine()) != null) {
            lines.put(lineNo, line);
            lineNo++;
        }
        lineCount = lineNo;
        reader.close();
    }

        public String getLine(Integer line) throws BoardLoaderException {
        if (!lines.containsKey(line))
            throw new BoardLoaderException(String.format("Invalid line number %s", line));
        return lines.get(line);
    }

    public Integer getLineCount() {
        return lineCount;
    }
}
