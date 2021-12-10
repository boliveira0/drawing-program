package org.boliveira.drawing;


import lombok.extern.log4j.Log4j;
import org.boliveira.drawing.view.MainView;

/**
 * Main class that starts a menu
 */
@Log4j
public class DrawingConsoleListenerApplication {
    public static void main(String[] args) {
        MainView.getDefaultInstance().startMainMenu();
    }
}
