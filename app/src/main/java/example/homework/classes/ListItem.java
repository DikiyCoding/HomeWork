package example.homework.classes;

import android.graphics.Bitmap;

public class ListItem {

    private Bitmap image;
    private final int itemId;
    private int imageId, foundingDate;
    private String name, location, type, keyFigures;

    public ListItem(int itemId, Bitmap image, int imageId, String name, String location, int foundingDate, String type, String keyFigures) {
        this.itemId = itemId;
        this.image = image;
        this.imageId = imageId;
        this.name = name;
        this.location = location;
        this.foundingDate = foundingDate;
        this.type = type;
        this.keyFigures = keyFigures;
    }

    public int getItemId() {
        return itemId;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getFoundingDate() {
        return foundingDate;
    }

    public String getType() {
        return type;
    }

    public String getKeyFigures() {
        return keyFigures;
    }

    public void setName(String name) {
        this.name = name;
    }
}
