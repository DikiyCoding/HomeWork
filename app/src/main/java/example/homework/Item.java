package example.homework;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {

    private String name;
    private int id;

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeInt(id);
    }

    /**
     * Constructors
     */

    public Item(String name, int id) {
        this.name = name;
        this.id = id;
    }

    protected Item(Parcel in) {
        name = in.readString();
        id = in.readInt();
    }

    /**
     * Getters
     */

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
