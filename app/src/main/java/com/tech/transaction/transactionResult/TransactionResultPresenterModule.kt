package com.tech.transaction.transactionResult

import com.tech.transaction.transactionResult.contract.TransactionResultContract
import dagger.Module
import dagger.Provides

@Module
class TransactionResultPresenterModule {
    val view : TransactionResultContract.View

    constructor(view: TransactionResultContract.View) {
        this.view = view
    }

    @Provides
    fun provideTransactionResultContractView(): TransactionResultContract.View {
        return view
    }

}