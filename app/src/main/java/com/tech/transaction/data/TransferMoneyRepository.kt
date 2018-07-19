package com.tech.transaction.data

import com.tech.transaction.data.dto.TransferMoneyOutDtoFactory
import com.tech.transaction.data.dto.`in`.TransferMoneyInDto
import com.tech.transaction.transferMoneyInput.api.TransferMoneyService
import rx.Observable
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject



class TransferMoneyRepository @Inject constructor(private var service: TransferMoneyService) {

    fun transferMoney(receivingAccountNumber: BigInteger, amount: BigDecimal): Observable<TransferMoneyInDto> {
        val factory = TransferMoneyOutDtoFactory()
        val transferMoneyOutDto = factory.build(receivingAccountNumber = receivingAccountNumber, amount = amount)
        return service.sendMoney(transferMoneyOutDto = transferMoneyOutDto)
    }

}