package com.tech.transaction.transactionInput

import com.tech.transaction.transactionInput.contract.TransactionInputContract
import com.tech.transaction.transactionInput.contract.TransactionInputPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class TransactionInputPresenterModule {
    val view : TransactionInputContract.View

    constructor(view: TransactionInputContract.View) {
        this.view = view
    }

    @Provides
    fun provideTransactionInputContractView(): TransactionInputContract.View {
        return view
    }

    @Provides
    fun provideTransactionInputContractPresenter(): TransactionInputContract.Presenter {
        return TransactionInputPresenterImpl(view)
    }
}