package org.boliveira.drawing.control;

import lombok.extern.log4j.Log4j;
import org.boliveira.drawing.domain.Canvas;

/**
 * Manages every operation for the respective command in the canvas
 */
@Log4j
class CanvasDrawer implements Commandable<Canvas> {

    private Canvas canvas;
    private static final Character LINE_CHAR = 'x';

    @Override
    public Canvas create(Integer w, Integer h) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Canvas line(Integer x1, Integer y1, Integer x2, Integer y2) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Canvas rectangle(Integer x1, Integer y1, Integer x2, Integer y2) {
        throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public Canvas bucketFill(Integer x, Integer y, Character c) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void checkCanvas() throws CanvasNotInitializedException {
        if (this.canvas == null) {
            throw new CanvasNotInitializedException("Canvas not initialized for the session!");
        }
        log.debug(String.format("Current canvas is %s", canvas));
    }

}
