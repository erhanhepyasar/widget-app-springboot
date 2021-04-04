package com.demo.controller;

import com.demo.model.Widget;
import com.demo.model.WidgetCreate;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;


@RunWith(SpringRunner.class)
@WebMvcTest(WidgetController.class)
class WidgetControllerTest {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private MockMvc mvc;

    @MockBean
    private WidgetController widgetController;

    @Test
    void createWidget() {
        WidgetCreate widgetCreate = new WidgetCreate();
        widgetCreate.setX(10);
        widgetCreate.setY(10);
        widgetCreate.setZ(1);
        widgetCreate.setHeight(100);
        widgetCreate.setWidth(100);

        Widget widget = modelMapper.map(widgetCreate, Widget.class);
        widget.setId(UUID.randomUUID().toString());
        widget.setLastModified(LocalDateTime.now());
    }

    /*
    @Test
    void updateWidget() {
    }
    */


    @Test
    void removeWidgetById() {
    }

    /*
    @Test
    void findWidget() throws Exception{

        Widget widget = new Widget();
        widget.setX(10);
        widget.setY(10);
        widget.setZ(1);
        widget.setHeight(100);
        widget.setWidth(100);
        widget.setId(UUID.randomUUID().toString());
        widget.setLastModified(LocalDateTime.now());

        ResponseEntity<Widget> widgetResponseEntity = new ResponseEntity<>(widget, HttpStatus.OK);

        given(widgetController.findWidget(widget.getId())).willReturn(widgetResponseEntity);

        mvc.perform(get("/widgets/" + widget.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("x", is(widget.getX())))
                .andExpect(jsonPath("y", is(widget.getY())))
                .andExpect(jsonPath("z", is(widget.getZ())))
                .andExpect(jsonPath("height", is(widget.getHeight())))
                .andExpect(jsonPath("width", is(widget.getWidth())))
                .andExpect(jsonPath("id", is(widget.getId())))
                .andExpect(jsonPath("lastModified", is(widget.getLastModified())));


    }
    */

    /*
    @Test
    void findAllWidgets() throws Exception {
        Widget widget = new Widget();
        widget.setX(10);
        widget.setY(10);
        widget.setZ(1);
        widget.setHeight(100);
        widget.setWidth(100);
        widget.setId(UUID.randomUUID().toString());
        widget.setLastModified(LocalDateTime.now());

        List<Widget> widgets = singletonList(widget);
        ResponseEntity<List<Widget>> widgetsResponseEntity = new ResponseEntity<>(widgets, HttpStatus.OK);

        given(widgetController.findAllWidgets(0, 5)).willReturn(widgetsResponseEntity);

        mvc.perform(get("/widgets")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].x", is(widget.getX())));
                .andExpect(jsonPath("/", hasSize(1)))
                .andExpect(jsonPath("[0].x", is(widget.getX())));


    }

     */
}