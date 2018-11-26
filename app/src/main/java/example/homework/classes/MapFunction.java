package example.homework.classes;

import io.reactivex.functions.Function;

public class MapFunction implements Function {

    @Override
    public Object apply(Object object) {

        ListItem newItem, oldItem;

        oldItem = (ListItem) object;
        newItem = new ListItem(oldItem.getItemId(),
                               oldItem.getImage(),
                               oldItem.getImageId(),
                               oldItem.getName(),
                               oldItem.getLocation(),
                               oldItem.getFoundingDate(),
                               oldItem.getType(),
                               oldItem.getKeyFigures());

        String name = newItem.getName();
        newItem.setName(name + " " + name.length());
        return newItem;
    }
}
