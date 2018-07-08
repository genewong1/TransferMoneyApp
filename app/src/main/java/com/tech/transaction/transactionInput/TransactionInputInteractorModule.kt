package com.tech.transaction.transactionInput

import com.tech.transaction.transactionInput.contract.TransactionInputContract
import com.tech.transaction.transactionInput.contract.TransactionInputInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class TransactionInputInteractorModule {
    val output : TransactionInputContract.InteractorOutput

    constructor(output: TransactionInputContract.InteractorOutput) {
        this.output = output
    }

    @Provides
    fun provideTransactionInputContractOutput(): TransactionInputContract.InteractorOutput {
        return output
    }

    @Provides
    fun provideTransactionInputContractInteractor(): TransactionInputContract.Interactor {
        return TransactionInputInteractorImpl(output)
    }

}