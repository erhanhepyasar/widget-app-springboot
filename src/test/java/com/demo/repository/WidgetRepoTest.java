package com.demo.repository;

import com.demo.model.Widget;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class WidgetRepoTest {

    @Test
    void addWidget() {
        //given
        Widget widget = new Widget();
        widget.setX(10);
        widget.setY(10);
        widget.setZ(1);
        widget.setHeight(50);
        widget.setWidth(50);
        widget.setId(UUID.randomUUID().toString());
        widget.setLastModified(LocalDateTime.now());

        //when
        WidgetRepo.addWidget(widget);

        //then
        Widget testWidget = (WidgetRepo.findById(widget.getId())).get();

        assertThat(testWidget.getX()).isEqualTo(widget.getX());
        assertThat(testWidget.getY()).isEqualTo(widget.getY());
        assertThat(testWidget.getZ()).isEqualTo(widget.getZ());
        assertThat(testWidget.getHeight()).isEqualTo(widget.getHeight());
        assertThat(testWidget.getWidth()).isEqualTo(widget.getWidth());
        assertThat(testWidget.getId()).isEqualTo(widget.getId());
        assertThat(testWidget.getLastModified()).isEqualTo(widget.getLastModified());
    }

    @Test
    void sortByZ() {
    }

    @Test
    void findById() {
        //given
        Widget widget = new Widget();
        widget.setX(10);
        widget.setY(10);
        widget.setZ(1);
        widget.setHeight(50);
        widget.setWidth(50);
        widget.setId(UUID.randomUUID().toString());
        widget.setLastModified(LocalDateTime.now());

        WidgetRepo.addWidget(widget);

        //when
        Widget testWidget = (WidgetRepo.findById(widget.getId())).get();

        //then
        assertThat(testWidget.getX()).isEqualTo(widget.getX());
        assertThat(testWidget.getY()).isEqualTo(widget.getY());
        assertThat(testWidget.getZ()).isEqualTo(widget.getZ());
        assertThat(testWidget.getHeight()).isEqualTo(widget.getHeight());
        assertThat(testWidget.getWidth()).isEqualTo(widget.getWidth());
        assertThat(testWidget.getId()).isEqualTo(widget.getId());
        assertThat(testWidget.getLastModified()).isEqualTo(widget.getLastModified());
    }

    @Test
    void findAll() {
        //given
        Widget firstWidget = new Widget();
        firstWidget.setX(10);
        firstWidget.setY(10);
        firstWidget.setZ(1);
        firstWidget.setHeight(50);
        firstWidget.setWidth(50);
        firstWidget.setId(UUID.randomUUID().toString());
        firstWidget.setLastModified(LocalDateTime.now());

        Widget secondWidget = new Widget();
        secondWidget.setX(20);
        secondWidget.setY(20);
        secondWidget.setZ(2);
        secondWidget.setHeight(100);
        secondWidget.setWidth(100);
        secondWidget.setId(UUID.randomUUID().toString());
        secondWidget.setLastModified(LocalDateTime.now());

        WidgetRepo.addWidget(firstWidget);
        WidgetRepo.addWidget(secondWidget);

        //when
        List<Widget> arrivals = WidgetRepo.findAll(0, 10);

        //then
        assertThat(arrivals.size()).isEqualTo(2);
        assertThat(arrivals.get(0)).isEqualTo(firstWidget);
        assertThat(arrivals.get(1)).isEqualTo(secondWidget);
    }

    @Test
    void removeById() {
        //given
        Widget widget = new Widget();
        widget.setX(10);
        widget.setY(10);
        widget.setZ(1);
        widget.setHeight(50);
        widget.setWidth(50);
        widget.setId(UUID.randomUUID().toString());
        widget.setLastModified(LocalDateTime.now());

        WidgetRepo.addWidget(widget);

        //when
        WidgetRepo.removeById(widget.getId());
        final Optional<Widget> testWidget = WidgetRepo.findById(widget.getId());

        //then
        assertThat(testWidget).isEqualTo(Optional.empty());

    }


    @Test
    void getPage() {
    }
}