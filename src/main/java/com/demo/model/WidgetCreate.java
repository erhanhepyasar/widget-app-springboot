package com.demo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class WidgetCreate {

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
