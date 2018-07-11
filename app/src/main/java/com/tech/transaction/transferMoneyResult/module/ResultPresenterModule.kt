package com.tech.transaction.transferMoneyResult.module

import com.tech.transaction.transferMoneyResult.contract.ResultContract
import com.tech.transaction.transferMoneyResult.contract.ResultPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class ResultPresenterModule {
    val view : ResultContract.View

    constructor(view: ResultContract.View) {
        this.view = view
    }

    @Provides
    fun provideTransactionResultContractView(): ResultContract.View {
        return view
    }

    @Provides
    fun provideTransactionResultPresenter(): ResultContract.Presenter {
        return ResultPresenterImpl(view)
    }
}