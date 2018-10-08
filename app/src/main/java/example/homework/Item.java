package example.homework;

import android.graphics.Bitmap;

public class Item {

    private final Bitmap image;
    private final int imageId;
    private final String name, location, foundingDate, type, keyFigures;

    public Item(Bitmap image, int imageId, String name, String location, String foundingDate, String type, String keyFigures) {
        this.image = image;
        this.imageId = imageId;
        this.name = name;
        this.location = location;
        this.foundingDate = foundingDate;
        this.type = type;
        this.keyFigures = keyFigures;
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

    public String getFoundingDate() {
        return foundingDate;
    }

    public String getType() {
        return type;
    }

    public String getKeyFigures() {
        return keyFigures;
    }
}