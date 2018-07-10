package com.tech.transaction.transactionResult.component

import com.tech.transaction.transactionResult.contract.TransactionResultPresenterImpl
import com.tech.transaction.transactionResult.module.TransactionResultInteractorModule
import dagger.Component

@Component(modules = [
    TransactionResultInteractorModule::class
])
interface TransactionResultInteractorComponent {
    fun inject(transactionResultPresenterImpl: TransactionResultPresenterImpl)
}