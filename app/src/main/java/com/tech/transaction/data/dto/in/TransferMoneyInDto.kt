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
}