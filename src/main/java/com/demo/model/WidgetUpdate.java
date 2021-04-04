package com.demo.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class WidgetUpdate {

    private String id;

    @Min(value = Constants.EDGE_MIN, message = Constants.X + Constants.COORD_MSG)
    @Max(value = Constants.EDGE_MAX, message = Constants.X + Constants.COORD_MSG)
    private int x;

    @Min(value = Constants.EDGE_MIN, message = Constants.Y + Constants.COORD_MSG)
    @Max(value = Constants.EDGE_MAX, message = Constants.Y + Constants.COORD_MSG)
    private int y;

    private int z;

    @Positive(message = Constants.WIDTH + Constants.EDGE_MSG)
    private int width;

    @Positive(message = Constants.HEIGHT + Constants.EDGE_MSG)
    private int height;
}
