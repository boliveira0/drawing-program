package org.boliveira.drawing.control;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.boliveira.drawing.domain.Canvas;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Holds an instance of {@link Canvas} and performs operations towards it
 */
@Log4j
class CanvasOperationCommand implements Commandable<Canvas> {

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
            this.fillLine(y1 - 1, x1 - 1, x2 - 1, true);
        } else if (x1.equals(x2)) {
            this.fillLine(x1 - 1, y1 - 1, y2 - 1, false);
        } else {
            throw new IllegalArgumentException("Only vertical or horizontal lines are supported!");
        }
        return this.canvas;
    }

    /**
     * Creates a rectangle in the canvas for the given coordinates.
     * The specification defines a domain for a rectangle function as:
     * corner(x1, y1) = top-left.
     * corner(x2, y2) = bottom-right.
     *
     * @param x1 top-left corner x value
     * @param y1 top-left corner y value
     * @param x2 bottom-right corner x value
     * @param y2 bottom-right corner y value
     * @return the canvas held by the application
     * @throws CanvasNotInitializedException if canvas is not initialized
     */
    @Override
    public Canvas rectangle(Integer x1, Integer y1, Integer x2, Integer y2) throws CanvasNotInitializedException {
        this.checkCanvasIsInitialized();
        // supports only if coordinates represent upper-left and lower-right
        if (x1 < x2 && y1 < y2) {
            this.fillLine(y1 - 1, x1 - 1, x2 - 1, true);
            this.fillLine(x1 - 1, y1 - 1, y2 - 2, false);
            this.fillLine(y2 - 1, x1 - 1, x2 - 1, true);
            this.fillLine(x2 - 1, y1 - 1, y2 - 2, false);
            return this.canvas;
        } else {
            throw new IllegalArgumentException("Invalid boundaries for rectangle");
        }

    }

    /**
     * Visits all nodes that were not yet visited to north, south, east and west of the canvas' matrix.
     *
     * @param x coordinate x from canvas
     * @param y coordinate y from canvas
     * @param c colour
     * @return the canvas held by the application
     * @throws CanvasNotInitializedException if canvas is not initialized
     */
    @Override
    public Canvas bucketFill(Integer x, Integer y, Character c) throws CanvasNotInitializedException {
        this.checkCanvasIsInitialized();
        var matrix = canvas.getMatrix();
        var visitMatrix = new int[canvas.getHeight()][canvas.getWidth()];
        Queue<Node> nodesQueue = new ArrayDeque();
        nodesQueue.add(new Node(y - 1, x - 1));
        while (!nodesQueue.isEmpty()) {
            Node node = nodesQueue.poll();
            //If a boundary exists or the node was already visited, it won't get enqueued.
            if (!isBoundary(matrix, node) && !isVisited(visitMatrix, node)) {
                matrix[node.y][node.x] = c;
                this.markVisited(visitMatrix, node);
                nodesQueue.add(new Node(node.y - 1, node.x));
                nodesQueue.add(new Node(node.y, node.x + 1));
                nodesQueue.add(new Node(node.y + 1, node.x));
                nodesQueue.add(new Node(node.y, node.x - 1));
            }
        }
        return this.canvas;
    }

    private boolean isBoundary(Character[][] matrix, Node node) {
        return node.x < 0 || node.y < 0
                || node.x >= canvas.getWidth() || node.y >= canvas.getHeight()
                || (matrix[node.y][node.x] != null && matrix[node.y][node.x].equals(LINE_CHAR));
    }

    private void markVisited(int[][] visitMatrix, Node node) {
        visitMatrix[node.y][node.x] = 1;
    }

    private boolean isVisited(int[][] visitMatrix, Node node) {
        return visitMatrix[node.y][node.x] == 1;
    }


    private void checkCanvasIsInitialized() throws CanvasNotInitializedException {
        if (this.canvas == null) {
            throw new CanvasNotInitializedException("Canvas not initialized for the session!");
        }
        log.debug(String.format("Current canvas is %s", canvas));
    }

    private void fillLine(Integer pos, Integer from, Integer until, Boolean horizontal) {
        var matrix = this.canvas.getMatrix();
        for (int i = from; i <= until; i++) {
            if (horizontal) {
                matrix[pos][i] = LINE_CHAR;
            } else {
                matrix[i][pos] = LINE_CHAR;
            }
        }
    }

    @AllArgsConstructor
    class Node {
        public int y;
        public int x;
    }
}
