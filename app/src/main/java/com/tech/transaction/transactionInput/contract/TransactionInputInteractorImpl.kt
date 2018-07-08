package com.tech.transaction.transactionInput.contract

import java.math.BigDecimal
import javax.inject.Inject

class TransactionInputInteractorImpl : TransactionInputContract.Interactor {
    private var output: TransactionInputContract.InteractorOutput

    @Inject
    constructor (output: TransactionInputContract.InteractorOutput) {
        this.output = output
    }

    init {

    }

    override fun initiateTransaction(amount: BigDecimal) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}