package com.tech.transaction.transactionResult.module

import com.tech.transaction.transactionResult.contract.TransactionResultContract
import com.tech.transaction.transactionResult.contract.TransactionResultPresenterImpl
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

    @Provides
    fun provideTransactionResultPresenter(): TransactionResultContract.Presenter {
        return TransactionResultPresenterImpl(view)
    }
}