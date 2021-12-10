package org.boliveira.drawing.view;

import org.boliveira.drawing.control.CanvasCommandOrchestrator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class CreateCanvasViewTest {

    private Navigable parent;
    private CreateCanvasView createCanvasView;

    @BeforeEach
    public void setup() {
        this.parent = Mockito.mock(Navigable.class);
        this.createCanvasView = new CreateCanvasView(new CanvasCommandOrchestrator(), parent);
        doNothing().when(parent).back();

    }
}