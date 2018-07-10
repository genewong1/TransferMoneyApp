package com.tech.transaction.transactionResult.module

import com.tech.transaction.transactionResult.contract.TransactionResultContract
import com.tech.transaction.transactionResult.contract.TransactionResultInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class TransactionResultInteractorModule {
    val output : TransactionResultContract.InteractorOutput

    constructor(output: TransactionResultContract.InteractorOutput) {
        this.output = output
    }

    @Provides
    fun provideTransactionResultContractOutput(): TransactionResultContract.InteractorOutput {
        return output
    }

    @Provides
    fun provideTransactionResultInteractor(): TransactionResultContract.Interactor {
        return TransactionResultInteractorImpl(output)
    }

}