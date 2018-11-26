package example.homework.classes.comparators;

import java.util.Comparator;

import example.homework.classes.ListItem;

public class SortByYear implements Comparator<ListItem> {

    public int compare(ListItem itemFirst, ListItem itemSecond) {
        int yearFirst = itemFirst.getFoundingDate();
        int yearSecond = itemSecond.getFoundingDate();

        if (yearFirst > yearSecond) {
            return 1;
        } else if (yearFirst < yearSecond) {
            return -1;
        } else {
            return 0;
        }
    }
}
