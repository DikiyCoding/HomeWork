package example.homework.classes.comparators;

import java.util.Comparator;

import example.homework.classes.ListItem;

public class SortByLocation implements Comparator<ListItem> {

    public int compare(ListItem itemFirst, ListItem itemSecond) {
        String locationFirst = itemFirst.getLocation();
        String locationSecond = itemSecond.getLocation();
        return locationFirst.compareTo(locationSecond);
    }
}
