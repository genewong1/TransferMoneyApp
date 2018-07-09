package com.tech.transaction.data

import com.tech.transaction.data.module.TransferMoneyModule
import com.tech.transaction.transactionInput.contract.TransactionInputInteractorImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TransferMoneyModule::class])
interface TransferMoneyComponent {
    fun inject(transactionInputInteractorImpl: TransactionInputInteractorImpl)
}