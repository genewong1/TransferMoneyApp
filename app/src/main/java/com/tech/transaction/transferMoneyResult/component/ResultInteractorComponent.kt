package com.tech.transaction.transferMoneyResult.component

import com.tech.transaction.transferMoneyResult.contract.ResultPresenterImpl
import com.tech.transaction.transferMoneyResult.module.ResultInteractorModule
import dagger.Component

@Component(modules = [
    ResultInteractorModule::class
])
interface ResultInteractorComponent {
    fun inject(resultPresenterImpl: ResultPresenterImpl)
}