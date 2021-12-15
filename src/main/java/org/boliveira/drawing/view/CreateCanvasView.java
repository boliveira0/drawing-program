package org.boliveira.drawing.view;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.boliveira.drawing.control.Orchestrable;
import org.boliveira.drawing.domain.Canvas;

import java.util.Optional;

/**
 * Menu #1 Create a new canvas menu
 */
@Log4j
class CreateCanvasView extends Navigable {

    private final Orchestrable<Canvas> canvasCommandOrchestrator;

    CreateCanvasView(Orchestrable<Canvas> canvasCommandOrchestrator,
                     @NonNull Navigable parentView) {
        super(parentView);
        this.canvasCommandOrchestrator = canvasCommandOrchestrator;
    }

    @Override
    public String menuAsText() {
        return "enter width and height separated by <SPACE>: ";
    }

    @Override
    public Optional<String> handleInput(String input) {
        try {
            super.back();
            var canvas = this.canvasCommandOrchestrator.execute(String.format("C %s", input));
            return Optional.of(CanvasViewUtils.frameCanvas(canvas));
        } catch (Exception e) {
            return Optional.of(e.getMessage());
        }
    }

}
