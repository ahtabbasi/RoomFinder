package com.ahtabbasi.roomdetail

import android.os.Parcel
import android.os.Parcelable
import com.ahtabbasi.domain.models.Room

class RoomDetailUIModel(
    val name: String,
    val spots: Int,
    val photo: String,
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(spots)
        parcel.writeString(photo)
    }

    override fun describeContents(): Int {
        return this.hashCode()
    }

    companion object CREATOR : Parcelable.Creator<RoomDetailUIModel> {
        override fun createFromParcel(parcel: Parcel): RoomDetailUIModel {
            return RoomDetailUIModel(parcel)
        }

        override fun newArray(size: Int): Array<RoomDetailUIModel?> {
            return arrayOfNulls(size)
        }

        fun fromDomainModel(room: Room) = RoomDetailUIModel(
            name = room.name,
            spots = room.spots,
            photo = room.photo
        )
    }

}
