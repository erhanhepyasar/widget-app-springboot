package com.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Widget {
    private String id;
    private int x;
    private int y;
    private int z;
    private int width;
    private int height;
    private LocalDateTime lastModified;
}
