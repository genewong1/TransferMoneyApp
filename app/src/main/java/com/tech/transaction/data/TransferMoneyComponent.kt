package com.tech.transaction.data

import android.app.Application
import com.tech.transaction.data.module.TransferMoneyModule
import com.tech.transaction.transferMoneyInput.contract.InputInteractorImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [TransferMoneyModule::class])
interface TransferMoneyComponent {
    fun inject(application: Application)
    fun inject(transactionInputInteractorImpl: InputInteractorImpl)
}