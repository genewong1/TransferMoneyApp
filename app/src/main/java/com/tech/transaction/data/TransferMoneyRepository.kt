package com.tech.transaction.data

import com.tech.transaction.data.dto.TransferMoneyOutDtoFactory
import com.tech.transaction.data.dto.`in`.TransferMoneyInDto
import com.tech.transaction.transferMoneyInput.api.TransferMoneyService
import rx.Observable
import java.math.BigDecimal
import javax.inject.Inject



class TransferMoneyRepository {
    private var service: TransferMoneyService

    @Inject
    constructor(service: TransferMoneyService) {
        this.service = service
    }

    fun transferMoney(amount: BigDecimal): Observable<TransferMoneyInDto> {
        val factory = TransferMoneyOutDtoFactory()
        val transferMoneyOutDto = factory.build(amount = amount)
        return service.sendMoney(transferMoneyOutDto = transferMoneyOutDto)
    }

}