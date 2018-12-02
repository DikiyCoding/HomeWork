package example.homework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Item(val name: String, val id: Int) : Parcelable
