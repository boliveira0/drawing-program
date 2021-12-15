package org.boliveira.drawing.control;

import org.boliveira.drawing.domain.Drawable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface Commandable<T extends Drawable> {

    @Command("C")
    T create(Integer w, Integer h);

    @Command("L")
    T line(Integer x1, Integer y1, Integer x2, Integer y2);

    @Command("R")
    T rectangle(Integer x1, Integer y1, Integer x2, Integer y2);

    @Command("B")
    T bucketFill(Integer x, Integer y, Character c);

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface Command {
        String value();
    }
}
