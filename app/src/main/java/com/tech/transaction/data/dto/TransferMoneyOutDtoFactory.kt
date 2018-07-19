package com.tech.transaction.data.dto

import com.tech.transaction.data.dto.out.TransferMoneyOutDto
import java.math.BigDecimal
import java.math.BigInteger

class TransferMoneyOutDtoFactory {
    fun build(receivingAccountNumber: BigInteger, amount: BigDecimal) : TransferMoneyOutDto{
        return TransferMoneyOutDto(receivingAccountNumber = receivingAccountNumber, amount = amount)
    }
}