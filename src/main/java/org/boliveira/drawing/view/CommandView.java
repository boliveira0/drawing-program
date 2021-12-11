package org.boliveira.drawing.view;

import lombok.NonNull;
import org.boliveira.drawing.control.Orchestrable;
import org.boliveira.drawing.domain.Canvas;

import java.util.Optional;

/**
 * Menu #2
 */
class CommandView extends Navigable {
    private final Orchestrable<Canvas> canvasCommandOrchestrator;

    CommandView(Orchestrable<Canvas> canvasCommandOrchestrator,
                @NonNull Navigable parentMenu) {
        super(parentMenu);
        this.canvasCommandOrchestrator = canvasCommandOrchestrator;
    }

    @Override
    public String menuAsText() {
        return "enter command: ";
    }

    @Override
    public Optional<String> handleInput(String input) {
        if (input.equals("Q")) {
            super.quit();
            return Optional.empty();
        } else {
            try {
                return Optional.of(this.executeCommandForResult(input));
            } catch (Exception e) {
                return Optional.of(e.getMessage());
            }
        }
    }

    private String executeCommandForResult(String commandLine) {
        var canvas = this.canvasCommandOrchestrator.execute(commandLine);
        return CanvasViewUtils.frameCanvas(canvas);
    }

}
