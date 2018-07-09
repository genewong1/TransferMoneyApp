package com.tech.transaction.transactionInput.api

import com.tech.transaction.data.dto.`in`.TransferMoneyInDto
import com.tech.transaction.data.dto.out.TransferMoneyOutDto
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

interface TransferMoneyService {
    @POST("transfer")
    fun sendMoney(
        @Body transferMoneyOutDto: TransferMoneyOutDto
    ): Observable<TransferMoneyInDto>
}