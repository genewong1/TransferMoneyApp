package com.tech.transaction.transactionInput

import com.tech.transaction.transactionInput.contract.TransactionInputContract
import com.tech.transaction.transactionInput.contract.TransactionInputPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class TransactionInputPresenterModule {

    val view : TransactionInputContract.View

    val router: TransactionInputContract.Router

    constructor(view: TransactionInputContract.View, router: TransactionInputContract.Router) {
        this.view = view
        this.router = router
    }

    @Provides
    fun provideTransactionInputContractView(): TransactionInputContract.View {
        return view
    }

    @Provides
    fun provideRouter(): TransactionInputContract.Router {
        return router
    }

    @Provides
    fun provideTransactionInputContractPresenter(): TransactionInputContract.Presenter {
        return TransactionInputPresenterImpl(view, router)
    }
}