package com.tech.transaction.transactionInput

import dagger.Component

@Component(modules = [
    TransactionInputPresenterModule::class
])
interface TransactionInputPresenterComponent {
    fun inject(transactionInputFragment: TransactionInputFragment)
}