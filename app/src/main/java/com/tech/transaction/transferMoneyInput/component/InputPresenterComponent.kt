package com.tech.transaction.transferMoneyInput.component

import com.tech.transaction.transferMoneyInput.InputFragment
import com.tech.transaction.transferMoneyInput.module.InputPresenterModule
import dagger.Component

@Component(modules = [
    InputPresenterModule::class
])
interface InputPresenterComponent {
    fun inject(inputFragment: InputFragment)
}