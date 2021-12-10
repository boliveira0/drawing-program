package org.boliveira.drawing.view;

import lombok.NonNull;

import java.util.Optional;

abstract class Navigable {
    Navigable parentView;

    Navigable(@NonNull Navigable parentView) {
        this.parentView = parentView;
    }

    Navigable() { }

    abstract String menuAsText();

    abstract Optional<String> handleInput(String input);

    protected void quit() {
        this.parentView.quit();
    }

    protected void back() {
        this.parentView.back();
    }
}
