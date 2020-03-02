/**
 * Model class for Json Data
 */
package com.example.samplewithall.models

import android.os.Parcel
import android.os.Parcelable

data class DataProperty(
    val title: String? = "Title is empty",
    val rows: List<Row>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.createTypedArrayList(Row)!!
    )

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeTypedList(rows)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataProperty> {
        override fun createFromParcel(parcel: Parcel): DataProperty {
            return DataProperty(parcel)
        }

        override fun newArray(size: Int): Array<DataProperty?> {
            return arrayOfNulls(size)
        }
    }
}