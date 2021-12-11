package org.boliveira.drawing.view;

import lombok.extern.log4j.Log4j;
import org.boliveira.drawing.control.CanvasCommandOrchestrator;
import org.boliveira.drawing.control.Orchestrable;
import org.boliveira.drawing.domain.Canvas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Root navigation view
 */
@Log4j
public class MainView extends Navigable {

    private static MainView INSTANCE;
    private Boolean active;
    private Navigable currentView;
    private final Orchestrable<Canvas> canvasCommandOrchestrator;
    private Map<String, Navigable> viewsMap;

    private MainView() {
        this.active = true;
        this.canvasCommandOrchestrator = new CanvasCommandOrchestrator();
        this.mapViews();
        this.currentView = this;
    }

    private void mapViews() {
        this.viewsMap = new HashMap<>();
        this.viewsMap.put("1", new CreateCanvasView(this.canvasCommandOrchestrator, this));
        this.viewsMap.put("2", new CommandView(this.canvasCommandOrchestrator, this));
    }

    public static MainView getDefaultInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MainView();
        }
        return INSTANCE;
    }


    public void startMainMenu() {
        try (var reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (this.active) {
                System.out.print(currentView.menuAsText());
                var input = reader.readLine();
                var output = this.currentView.handleInput(input.trim());
                if (!output.isEmpty()) {
                    System.out.println(output.get());
                }
            }
        } catch (IOException e) {
            log.error("Error while waiting for input", e);
        }
    }

    @Override
    public void quit() {
        this.active = false;
    }

    @Override
    public void back() {
        this.currentView = this.currentView.parentView;
    }

    @Override
    public String menuAsText() {
        return "1. Create a new canvas" + System.lineSeparator()
                + "2. Start drawing on the canvas by issuing various commands" + System.lineSeparator()
                + "3. Quit" + System.lineSeparator();
    }

    @Override
    public Optional<String> handleInput(String input) {
        if (input.equals("3")) {
            this.quit();
        } else {
            this.currentView = this.viewsMap.get(input.trim());
            if (this.currentView == null) {
                this.currentView = this;
                return Optional.of("Invalid Option");
            }
        }
        return Optional.empty();
    }

}
