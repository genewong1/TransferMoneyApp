package com.tech.transaction.entities.TransactionStatus

import com.google.gson.annotations.SerializedName

class TransactionStatus(
        success: Boolean?,
        amount: Int?,
        statusDescription: String?
) {
    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("amount")
    var amount: Int? = null

    @SerializedName("status_description")
    var statusDescription: String? = null

    init {
        this.success = success
        this.amount = amount
        this.statusDescription = statusDescription
    }

    override fun toString(): String {
        return "TransactionStatus(success=$success, amount=$amount, statusDescription=$statusDescription)"
    }
}