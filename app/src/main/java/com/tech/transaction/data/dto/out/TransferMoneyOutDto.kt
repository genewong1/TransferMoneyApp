package com.tech.transaction.data.dto.out

import java.math.BigDecimal
import java.math.BigInteger

class TransferMoneyOutDto(
        private val receivingAccountNumber: BigInteger,
        private val amount: BigDecimal
)