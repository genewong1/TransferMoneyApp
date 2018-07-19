package com.tech.transaction.data.dto.`in`

import com.google.gson.annotations.SerializedName

class TransferMoneyInDto(
        success: Boolean?
) {
    @SerializedName("success")
    var success: Boolean? = null

    init {
        this.success = success
    }

    override fun toString(): String {
        return "TransferMoneyInDto(success=$success)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TransferMoneyInDto

        if (success != other.success) return false

        return true
    }

    override fun hashCode(): Int {
        return success?.hashCode() ?: 0
    }


}