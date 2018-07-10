package com.tech.transaction.transactionResult.component

import com.tech.transaction.transactionResult.TransactionResultFragment
import com.tech.transaction.transactionResult.module.TransactionResultPresenterModule
import dagger.Component

@Component(modules = [
    TransactionResultPresenterModule::class
])
interface TransactionResultComponent {
    fun inject(transactionResultFragment: TransactionResultFragment)
}