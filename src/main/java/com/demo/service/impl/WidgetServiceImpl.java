package com.demo.service.impl;

import com.demo.model.Widget;
import com.demo.service.WidgetService;
import com.demo.repository.WidgetRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WidgetServiceImpl implements WidgetService {

    @Override
    public Widget createWidget(Widget widget) {
        WidgetRepo.addWidget(widget);
        return widget;
    }

    @Override
    public Widget updateWidget(Widget widget) {
        final Optional<Widget> optionalWidget = removeById(widget.getId());
        if (!optionalWidget.isPresent()) {
            return null;
        }
        return createWidget(widget);
    }

    @Override
    public Optional<Widget> removeById(String id) {
        final Optional<Widget> optionalWidget = WidgetRepo.findById(id);
        if (optionalWidget.isPresent()) {
            WidgetRepo.removeById(id);
        }
        return optionalWidget;
    }

    @Override
    public Optional<Widget> findById(String id) {
        return WidgetRepo.findById(id);
    }

    @Override
    public List<Widget> findAll(int pageNo, int pageSize) {
        return WidgetRepo.findAll(pageNo, pageSize);
    }


}
