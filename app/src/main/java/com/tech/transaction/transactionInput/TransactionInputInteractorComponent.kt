package com.tech.transaction.transactionInput

import com.tech.transaction.transactionInput.contract.TransactionInputPresenterImpl
import dagger.Component

@Component(modules = [
    TransactionInputInteractorModule::class
])
interface TransactionInputInteractorComponent {
    fun inject(transactionInputPresenterImpl: TransactionInputPresenterImpl)
}