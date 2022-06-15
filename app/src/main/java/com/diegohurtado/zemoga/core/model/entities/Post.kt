package com.diegohurtado.zemoga.core.model.entities

import android.os.Parcel
import android.os.Parcelable
import com.diegohurtado.zemoga.core.view.adapter.ListAdapterItem

data class Post(
    override val id: Int,
    val userId: Int,
    val title: String,
    val body: String
): ListAdapterItem, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    constructor() : this(0, 0, "", "")

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(userId)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }

}
