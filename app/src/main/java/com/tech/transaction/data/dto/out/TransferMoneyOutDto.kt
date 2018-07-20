package com.tech.transaction.data.dto.out

import java.math.BigDecimal
import java.math.BigInteger

class TransferMoneyOutDto(
        val receivingAccountNumber: BigInteger,
        val amount: BigDecimal
)