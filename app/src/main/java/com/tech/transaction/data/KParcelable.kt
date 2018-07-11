package com.tech.transaction.data

import android.os.Parcel
import android.os.Parcelable
import java.math.BigDecimal
import java.math.BigInteger


interface KParcelable : Parcelable {
    override fun describeContents() = 0
    override fun writeToParcel(dest: Parcel, flags: Int)
}

// Creator factory functions

inline fun <reified T> parcelableCreator(
        crossinline create: (Parcel) -> T) =
        object : Parcelable.Creator<T> {
            override fun createFromParcel(source: Parcel) = create(source)
            override fun newArray(size: Int) = arrayOfNulls<T>(size)
        }

// Parcel extensions

fun Parcel.readBoolean() = readInt() != 0

fun Parcel.writeBoolean(value: Boolean) = writeInt(if (value) 1 else 0)

inline fun <T> Parcel.readNullable(reader: () -> T) =
        if (readInt() != 0) reader() else null

inline fun <T> Parcel.writeNullable(value: T?, writer: (T) -> Unit) {
    if (value != null) {
        writeInt(1)
        writer(value)
    } else {
        writeInt(0)
    }
}

fun Parcel.readBigDecimal() =
        readNullable { BigDecimal(BigInteger(createByteArray()), readInt()) }

fun Parcel.writeBigDecimal(value: BigDecimal?) = writeNullable(value) {
    writeByteArray(it.unscaledValue().toByteArray())
    writeInt(it.scale())
}