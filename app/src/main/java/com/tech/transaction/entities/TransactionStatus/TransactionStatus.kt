package com.tech.transaction.entities.TransactionStatus

import android.os.Parcel
import com.tech.transaction.data.*
import java.math.BigDecimal

class TransactionStatus(
    var success: Boolean,
    var amount: BigDecimal?
) : KParcelable {

    constructor(p: Parcel) : this(
        success = p.readBoolean(),
        amount = p.readBigDecimal()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeBoolean(success)
        dest.writeBigDecimal(amount)
    }

    override fun toString(): String {
        return "TransactionStatus(success=$success, amount=$amount)"
    }

    companion object {
        @JvmField
        val CREATOR = parcelableCreator(::TransactionStatus)
    }

}