package com.tech.transaction.transactionInput.component

import com.tech.transaction.transactionInput.TransactionInputFragment
import com.tech.transaction.transactionInput.module.TransactionInputPresenterModule
import dagger.Component

@Component(modules = [
    TransactionInputPresenterModule::class
])
interface TransactionInputPresenterComponent {
    fun inject(transactionInputFragment: TransactionInputFragment)
}