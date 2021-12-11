package org.boliveira.drawing.control;

import lombok.extern.log4j.Log4j;
import org.boliveira.drawing.domain.Canvas;

/**
 * Holds an instance of {@link Canvas} and performs operations towards it
 */
@Log4j
class CanvasDrawer implements Commandable<Canvas> {

    private Canvas canvas;
    private static final Character LINE_CHAR = 'x';

    @Override
    public Canvas create(Integer w, Integer h) {
        this.canvas = Canvas.builder().width(w).height(h).matrix(new Character[h][w]).build();
        return this.canvas;
    }

    @Override
    public Canvas line(Integer x1, Integer y1, Integer x2, Integer y2) throws CanvasNotInitializedException {
        this.checkCanvasIsInitialized();

        if (y1.equals(y2)) {
            this.fillLine(y1, x1, x2, true);
        } else if (x1.equals(x2)) {
            this.fillLine(x1, y1, y2, false);
        } else {
            throw new IllegalArgumentException("Only vertical or horizontal lines are supported!");
        }
        return this.canvas;
    }

    private void fillLine(Integer pos, Integer from, Integer until, Boolean horizontal) {
        var matrix = this.canvas.getMatrix();
        for (int i = from - 1; i < until; i++) {
            if(horizontal) {
                matrix[pos - 1][i] = LINE_CHAR;
            }else{
                matrix[i][pos - 1] = LINE_CHAR;
            }
        }
    }

    @Override
    public Canvas rectangle(Integer x1, Integer y1, Integer x2, Integer y2) throws CanvasNotInitializedException {
        this.checkCanvasIsInitialized();
        // supports only if coordinates represent upper-left and lower-right
        if (x1 < x2 && y1 < y2) {
            this.fillLine(y1, x1, x2, true);
            this.fillLine(x1, y1, y2 - 1, false);
            this.fillLine(y2, x1, x2, true);
            this.fillLine(x2, y1, y2 - 1, false);
            return this.canvas;
        } else {
            throw new IllegalArgumentException("Invalid boundaries for rectangle");
        }

    }

    @Override
    public Canvas bucketFill(Integer x, Integer y, Character c) throws CanvasNotInitializedException {
        //TODO maybe dijkstra?
        this.checkCanvasIsInitialized();
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void checkCanvasIsInitialized() throws CanvasNotInitializedException {
        if (this.canvas == null) {
            throw new CanvasNotInitializedException("Canvas not initialized for the session!");
        }
        log.debug(String.format("Current canvas is %s", canvas));
    }
}
