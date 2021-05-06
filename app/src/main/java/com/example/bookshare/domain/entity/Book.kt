package com.example.bookshare.domain.entity

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Book (
    val id: UUID? = null,
    val name: String? = null,
    val author: String? = null,
    val book_img: String? = null,
    val description: String? = null,
    val genre: String? = null,
    val sender: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        TODO("id"),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(author)
        parcel.writeString(book_img)
        parcel.writeString(description)
        parcel.writeString(genre)
        parcel.writeString(sender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
