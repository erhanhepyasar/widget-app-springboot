package com.demo.repository;

import com.demo.model.Widget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class WidgetRepo {
    private static List<Widget> widgets = new ArrayList<>();

/*
    Adds widget in correct order according to the z-index
 */
    public static void addWidget(Widget newWidget) {
        if(newWidget.getZ() == 0) {
            newWidget.setZ(widgets.get(widgets.size() - 1).getZ() + 1);
        }
        int size = widgets.size();
        if (size == 0) {
            widgets.add(newWidget);
            return;
        }

        int newZ = newWidget.getZ();
        int z;
        Widget widget;

        for (int i = 0; i < size; i++) {
             widget = widgets.get(i);
             z = widget.getZ();
            if (newZ <= z) {
                widgets.add(i, newWidget);
                sortByZ(i + 1);
                return;
            }
        }
        widgets.add(newWidget);
    }

/*
    Sorts Widget list by z-index
 */
    public static void sortByZ(int startIndex) {
        final int SIZE = widgets.size();
        if (SIZE < 2 || SIZE <= startIndex) {
            return;
        }

        Widget widgetPre, widget;
        for (int i = startIndex; i < SIZE; i++) {
            widgetPre = widgets.get((i - 1));
            widget = widgets.get((i));
            if (widgetPre.getZ() == widget.getZ()) {
                widget.setZ(widget.getZ() + 1);
                widgets.remove(i);
                widgets.add(i , widget);
            }
        }
    }

/*
    Finds widget by z-index
 */
    public static Optional<Widget> findById(String id) {
        return  widgets.stream()
                    .filter(w -> w.getId().equals(id))
                    .findFirst();
    }

/*
    Returns Widget list
 */
    public static List<Widget> findAll(int page, int pageSize) {
        return getPage(widgets, page, pageSize);
    }

/*
    Removes Widget from list by widget id
 */
    public static void removeById(String id) {
        widgets = widgets.stream()
                .filter(w -> !w.getId().equals(id))
                .collect(Collectors.toList());
    }


    /**
     * returns a view (not a new list) of the sourceList for the
     * range based on pageNo and pageSize
     * @param sourceList source list
     * @param pageNo, page number should start from 0
     * @param pageSize page size
     * @return paginated list
     */
    public static <T> List<T> getPage(List<T> sourceList, int pageNo, int pageSize) {
        if(pageSize <= 0 || pageNo < 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = pageNo * pageSize;
        if(sourceList == null || sourceList.size() <= fromIndex){
            return Collections.emptyList();
        }

        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }
}
