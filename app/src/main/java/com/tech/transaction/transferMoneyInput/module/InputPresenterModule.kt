package com.tech.transaction.transferMoneyInput.module

import com.tech.transaction.transferMoneyInput.contract.InputContract
import com.tech.transaction.transferMoneyInput.contract.InputPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class InputPresenterModule {

    val view : InputContract.View

    private val router: InputContract.Router

    constructor(view: InputContract.View, router: InputContract.Router) {
        this.view = view
        this.router = router
    }

    @Provides
    fun provideTransactionInputContractView(): InputContract.View {
        return view
    }

    @Provides
    fun provideRouter(): InputContract.Router {
        return router
    }

    @Provides
    fun provideTransactionInputContractPresenter(): InputContract.Presenter {
        return InputPresenterImpl(view, router)
    }
}