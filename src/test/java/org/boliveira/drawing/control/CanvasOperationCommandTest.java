package org.boliveira.drawing.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanvasOperationCommandTest {

    private CanvasOperationCommand drawer = new CanvasOperationCommand();

    @BeforeEach
    public void setup() {
        this.drawer = new CanvasOperationCommand();
    }

    @Test
    void whenCalledLineWithoutCreate_ExpectCanvasNotInitializedException() {
        assertThrows(CanvasNotInitializedException.class, () -> this.drawer.line(1, 2, 6, 2));
    }

    @Test
    void whenCalledLineWithDiagonalCoordinates_ExpectIllegalArgumentException() {
        this.drawer.create(10, 4);
        assertThrows(IllegalArgumentException.class, () -> this.drawer.line(1, 2, 3, 4));
    }

    @Test
    void whenCalledRectanguleWithoutCreate_ExpectCanvasNotInitializedException() {
        assertThrows(CanvasNotInitializedException.class, () -> this.drawer.rectangle(1, 2, 3, 4));
    }

    @Test
    void whenCalledRectanguleWithInvalidCoordinates_ExpectIllegalArgumentException() {
        this.drawer.create(10, 4);
        assertThrows(IllegalArgumentException.class, () -> this.drawer.rectangle(3, 4, 1, 2));
    }

    @Test
    void whenCalledBucketFillWithoutCreate_ExpectCanvasNotInitializedException() {
        assertThrows(CanvasNotInitializedException.class, () -> this.drawer.bucketFill(10, 3, 'c'));
    }

    @Test
    void whenCalledBucketFillWithInvalidCoordinates_ExpectEqualCanvas() {
        var expected = this.drawer.create(5, 4);
        var result = this.drawer.bucketFill(10, 3, 'c');
        assertEquals(expected, result);
    }

    @Test
    void whenCalledBucketFillWithValidCoordinates_ExpectDifferentCanvas() {
        var expected = this.drawer.create(25, 5);
        var result = this.drawer.bucketFill(10, 3, 'c');
        assertNotEquals(expected, result);
    }

}