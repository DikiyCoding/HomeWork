package example.homework

import android.os.Parcel
import android.os.Parcelable

class Item : Parcelable {

    /**
     * Getters
     */

    var name: String? = null
        private set
    var id: Int = 0
        private set

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeString(name)
        out.writeInt(id)
    }

    /**
     * Constructors
     */

    constructor(name: String, id: Int) {
        this.name = name
        this.id = id
    }

    protected constructor(`in`: Parcel) {
        name = `in`.readString()
        id = `in`.readInt()
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
