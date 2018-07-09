package com.tech.transaction.data.dto.`in`

import com.google.gson.annotations.SerializedName

class TransferMoneyInDto(
        success: Boolean?,
        amount: Int?
) {
    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("amount")
    var amount: Int? = null

    init {
        this.success = success
        this.amount = amount
    }

    override fun toString(): String {
        return "TransferMoneyInDto(success=$success, amount=$amount)"
    }
}