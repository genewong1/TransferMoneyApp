package com.tech.transaction.transferMoneyResult.component

import com.tech.transaction.transferMoneyResult.ResultFragment
import com.tech.transaction.transferMoneyResult.module.ResultPresenterModule
import dagger.Component

@Component(modules = [
    ResultPresenterModule::class
])
interface ResultComponent {
    fun inject(resultFragment: ResultFragment)
}