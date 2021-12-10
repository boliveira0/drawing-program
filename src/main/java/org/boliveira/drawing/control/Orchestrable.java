package org.boliveira.drawing.control;

import org.boliveira.drawing.domain.Drawable;

public interface Orchestrable <T extends Drawable>{
    T execute(String commandLine);
}
