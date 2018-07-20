package com.tech.transaction.data

import android.support.annotation.VisibleForTesting
import com.tech.transaction.data.dto.TransferMoneyOutDtoFactory
import com.tech.transaction.data.dto.`in`.TransferMoneyInDto
import com.tech.transaction.data.dto.out.TransferMoneyOutDto
import com.tech.transaction.transferMoneyInput.api.TransferMoneyService
import rx.Observable
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject



class TransferMoneyRepository @Inject constructor(private var service: TransferMoneyService) {

    /**
     * TODO should be only visible for testing
     */
    @VisibleForTesting
    fun getTransferMoneyOutDto(receivingAccountNumber: BigInteger, amount: BigDecimal) : TransferMoneyOutDto {
        val factory = TransferMoneyOutDtoFactory()
        return factory.build(receivingAccountNumber = receivingAccountNumber, amount = amount)
    }

    fun transferMoney(receivingAccountNumber: BigInteger, amount: BigDecimal): Observable<TransferMoneyInDto> {
        val transferMoneyOutDto = getTransferMoneyOutDto(receivingAccountNumber, amount)
        return service.sendMoney(transferMoneyOutDto = transferMoneyOutDto)
    }

}