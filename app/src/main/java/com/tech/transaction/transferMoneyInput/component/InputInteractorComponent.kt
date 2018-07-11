package com.tech.transaction.transferMoneyInput.component

import com.tech.transaction.transferMoneyInput.contract.InputPresenterImpl
import com.tech.transaction.transferMoneyInput.module.InputInteractorModule
import dagger.Component

@Component(modules = [
    InputInteractorModule::class
])
interface InputInteractorComponent {
    fun inject(inputPresenterImpl: InputPresenterImpl)
}