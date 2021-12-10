package org.boliveira.drawing.view;

import org.boliveira.drawing.domain.Canvas;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Utilities class for printing a canvas with a frame
 */
public class CanvasViewUtils {

    private static final Character W_FRAME_CHAR = '-';
    private static final Character H_FRAME_CHAR = '|';
    private static final Character EMPTY_CHAR = ' ';

    public static String printCanvas(Canvas canvas) {
        var renderedCanvas = new StringBuilder();
        Stream.of(frame(canvas.getWidth(), canvas.getHeight(), canvas.getMatrix()))
                .map(CanvasViewUtils::rowToString)
                .forEach(renderedCanvas::append);
        return renderedCanvas.toString().trim();
    }

    private static Character[][] frame(int w, int h, Character[][] matrix) {
        Character[][] emptyCanvas = new Character[h + 2][w + 2];
        Arrays.fill(emptyCanvas[0], W_FRAME_CHAR);
        Arrays.fill(emptyCanvas[emptyCanvas.length - 1], W_FRAME_CHAR);
        for (var i = 1; i < emptyCanvas.length - 1; i++) {
            emptyCanvas[i][0] = H_FRAME_CHAR;
            emptyCanvas[i][emptyCanvas[i].length - 1] = H_FRAME_CHAR;
            for (int j = 0; j < matrix[i - 1].length; j++) {
                emptyCanvas[i][j + 1] = matrix[i - 1][j];
            }
        }
        return emptyCanvas;
    }

    private static String rowToString(Character[] row) {
        var rowStringBuilder = new StringBuilder();
        for (var str : row) {
            rowStringBuilder.append(str == null ? EMPTY_CHAR : str);
        }
        rowStringBuilder.append(System.lineSeparator());
        return rowStringBuilder.toString();
    }
}
