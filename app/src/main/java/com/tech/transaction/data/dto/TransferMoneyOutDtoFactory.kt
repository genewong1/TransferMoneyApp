package com.tech.transaction.data.dto

import com.tech.transaction.data.dto.out.TransferMoneyOutDto
import java.math.BigDecimal

class TransferMoneyOutDtoFactory {
    fun build(amount: BigDecimal) : TransferMoneyOutDto{
        return TransferMoneyOutDto(amount = amount)
    }
}