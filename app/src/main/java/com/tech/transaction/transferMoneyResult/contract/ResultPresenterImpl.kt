package com.tech.transaction.transferMoneyResult.contract

import android.support.annotation.StringRes
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import com.tech.transaction.transferMoneyResult.component.DaggerResultInteractorComponent
import com.tech.transaction.transferMoneyResult.module.ResultInteractorModule
import javax.inject.Inject

class ResultPresenterImpl(
    private val view: ResultContract.View
) : ResultContract.Presenter {

    @Inject
    lateinit var interactor: ResultContract.Interactor

    init {
        DaggerResultInteractorComponent.builder()
                .resultInteractorModule(ResultInteractorModule(this))
                .build()
                .inject(this)
    }

    override fun onViewCreatedAndStatusReceived(transferMoneyStatus: TransferMoneyStatus) {
        interactor.displayStatusAsFormatedString(transferMoneyStatus = transferMoneyStatus)
    }

    override fun onDisplayStatus(@StringRes statusStringResId: Int) {
        view.showStatus(statusStringResId = statusStringResId)
    }
}