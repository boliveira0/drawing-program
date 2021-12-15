package org.boliveira.drawing.view;

import org.boliveira.drawing.domain.Canvas;

import java.util.stream.Stream;

/**
 * Utilities class for printing a canvas with a frame
 */
class CanvasViewUtils {

    private static final String W_FRAME_CHAR = "-";
    private static final String H_FRAME_CHAR = "|";
    private static final String EMPTY_CHAR = " ";

    public static String frameCanvas(Canvas canvas) {
        return frame(canvas.getWidth(), canvas.getMatrix());
    }

    private static String frame(int w, Character[][] matrix) {
        var framedCanvas = new StringBuilder();
        fillCanvas(framedCanvas, w + 2);
        fillCanvasBody(framedCanvas, matrix);
        fillCanvas(framedCanvas, w + 2);
        return framedCanvas.toString().trim();
    }

    private static void fillCanvasBody(StringBuilder framedCanvas, Character[][] matrix) {
        Stream.of(matrix).forEach(characters -> {
            for (int i = 0; i < characters.length; i++) {
                if (i == 0) {
                    framedCanvas.append(H_FRAME_CHAR);
                }
                framedCanvas.append(characters[i] == null ? EMPTY_CHAR : characters[i]);
                if (i == characters.length - 1) {
                    framedCanvas.append(H_FRAME_CHAR);
                    framedCanvas.append(System.lineSeparator());
                }
            }
        });
    }

    private static void fillCanvas(StringBuilder framedCanvas, int i) {
        framedCanvas
                .append(W_FRAME_CHAR.repeat(i))
                .append(System.lineSeparator());
    }

}
