package com.tech.transaction.transferMoneyResult.module

import com.tech.transaction.transferMoneyResult.contract.ResultContract
import com.tech.transaction.transferMoneyResult.contract.ResultInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class ResultInteractorModule {
    val output : ResultContract.InteractorOutput

    constructor(output: ResultContract.InteractorOutput) {
        this.output = output
    }

    @Provides
    fun provideTransactionResultContractOutput(): ResultContract.InteractorOutput {
        return output
    }

    @Provides
    fun provideTransactionResultInteractor(): ResultContract.Interactor {
        return ResultInteractorImpl(output)
    }

}