package com.tech.transaction.transactionInput.component

import com.tech.transaction.transactionInput.contract.TransactionInputPresenterImpl
import com.tech.transaction.transactionInput.module.TransactionInputInteractorModule
import dagger.Component

@Component(modules = [
    TransactionInputInteractorModule::class
])
interface TransactionInputInteractorComponent {
    fun inject(transactionInputPresenterImpl: TransactionInputPresenterImpl)
}