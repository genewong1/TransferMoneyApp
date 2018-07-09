package com.tech.transaction.transactionInput.api

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TransferMoneyService {
    @GET("transfer")
    fun sendMoney(
            @Path("user") user: String
    ): Call
}