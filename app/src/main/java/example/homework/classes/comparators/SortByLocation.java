package example.homework.classes.comparators;

import java.util.Comparator;

import example.homework.classes.Item;

public class SortByLocation implements Comparator<Item> {

    public int compare(Item itemFirst, Item itemSecond) {
        String locationFirst = itemFirst.getLocation();
        String locationSecond = itemSecond.getLocation();
        return locationFirst.compareTo(locationSecond);
    }
}
