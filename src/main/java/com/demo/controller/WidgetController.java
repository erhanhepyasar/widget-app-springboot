package com.demo.controller;

import com.demo.model.Widget;
import com.demo.model.WidgetCreate;
import com.demo.model.WidgetUpdate;
import com.demo.service.WidgetService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/widgets")
public class WidgetController {
    ModelMapper modelMapper = new ModelMapper();
    Logger logger = LoggerFactory.getLogger(WidgetController.class);

    @Autowired
    WidgetService widgetService;

    @PostMapping
    public ResponseEntity<Widget> createWidget(@Valid @RequestBody WidgetCreate widgetCreate) {
        Widget widget = modelMapper.map(widgetCreate, Widget.class);
        widget.setId(UUID.randomUUID().toString());
        widget.setLastModified(LocalDateTime.now());
        Widget widgetResponse =  widgetService.createWidget(widget);
        logger.info("Widget created: " + widgetResponse.toString());

        return new ResponseEntity<>(widgetResponse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Widget> updateWidget(@Valid @RequestBody WidgetUpdate widgetUpdate) {
        Widget widget = modelMapper.map(widgetUpdate, Widget.class);
        widget.setLastModified(LocalDateTime.now());
        Widget widgetResponse =  widgetService.updateWidget(widget);
        HttpStatus httpStatus = HttpStatus.OK;
        if (widgetResponse == null) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        logger.info("Widget updated: " + widgetResponse.toString());

        return new ResponseEntity<>(widgetResponse, httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Widget> removeWidgetById(@PathVariable("id") String id) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        Widget widget = null;
        Optional<Widget> widgetResponse =  widgetService.removeById(id);
        if(widgetResponse.isPresent()) {
            httpStatus = HttpStatus.OK;
            widget = widgetResponse.get();
            logger.info("Widget removed: " + widget.toString());
        }

        return new ResponseEntity<>(widget, httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Widget> findWidget(@PathVariable("id") String id) {
        Optional<Widget> widgetResponse =  widgetService.findById(id);
        HttpStatus httpStatus = HttpStatus.OK;
        if(!widgetResponse.isPresent()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        logger.info("Widget found: " + widgetResponse.get().toString());

        return new ResponseEntity<>(widgetResponse.get(), httpStatus);
    }

    @GetMapping
    public ResponseEntity<List<Widget>> findAllWidgets(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        List<Widget> widgets = new ArrayList<>();
        if(pageNo < 0 || pageSize < 1 || pageSize > 500) {
            return new ResponseEntity<>(widgets, HttpStatus.BAD_REQUEST);
        }

        widgets =  widgetService.findAll(pageNo, pageSize);
        logger.info("Widget(s) found: " + widgets.size() + ", PageNo: " + pageNo + ", PageSize: " + pageSize);

        return new ResponseEntity<>(widgets, HttpStatus.OK);
    }

}
