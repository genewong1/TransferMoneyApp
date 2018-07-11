package com.tech.transaction.transferMoneyInput.module

import com.tech.transaction.transferMoneyInput.contract.InputContract
import com.tech.transaction.transferMoneyInput.contract.InputInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class InputInteractorModule {
    val output : InputContract.InteractorOutput

    constructor(output: InputContract.InteractorOutput) {
        this.output = output
    }

    @Provides
    fun provideTransactionInputContractOutput(): InputContract.InteractorOutput {
        return output
    }

    @Provides
    fun provideTransactionInputContractInteractor(): InputContract.Interactor {
        return InputInteractorImpl(output)
    }

}