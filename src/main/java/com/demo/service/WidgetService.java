package com.demo.service;

import com.demo.model.Widget;

import java.util.List;
import java.util.Optional;

public interface WidgetService {

    Widget createWidget(Widget widget);
    Widget updateWidget(Widget widget);
    Optional<Widget> removeById(String id);
    Optional<Widget> findById(String id);
    List<Widget> findAll(int pageNo, int pageSize);

}
