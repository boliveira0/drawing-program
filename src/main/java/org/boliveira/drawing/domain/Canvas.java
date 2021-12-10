package org.boliveira.drawing.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Canvas implements Drawable {
    private Integer height;
    private Integer width;
    private Character[][] matrix;
}
