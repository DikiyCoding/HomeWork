package example.homework.classes.comparators;

import java.util.Comparator;

import example.homework.classes.Item;

public class SortByYear implements Comparator<Item> {

    public int compare(Item itemFirst, Item itemSecond) {
        int yearFirst = itemFirst.getFoundingDate();
        int yearSecond = itemSecond.getFoundingDate();

        if (yearFirst > yearSecond) {
            return 1;
        }
        else if (yearFirst < yearSecond) {
            return -1;
        }
        else {
            return 0;
        }
    }
}
