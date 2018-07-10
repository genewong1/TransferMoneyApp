package com.tech.transaction.transactionResult.contract

import android.support.annotation.StringRes
import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import com.tech.transaction.transactionResult.component.DaggerTransactionResultInteractorComponent
import com.tech.transaction.transactionResult.module.TransactionResultInteractorModule
import javax.inject.Inject

class TransactionResultPresenterImpl(
    private val view: TransactionResultContract.View
) : TransactionResultContract.Presenter {

    @Inject
    lateinit var interactor: TransactionResultContract.Interactor

    init {
        DaggerTransactionResultInteractorComponent.builder()
                .transactionResultInteractorModule(TransactionResultInteractorModule(this))
                .build()
                .inject(this)
    }

    override fun onViewCreatedAndStatusReceived(transactionStatus: TransactionStatus) {
        interactor.displayStatusAsFormatedString(transactionStatus = transactionStatus)
    }

    override fun onDisplayStatus(@StringRes statusStringResId: Int) {
        view.showStatus(statusStringResId = statusStringResId)
    }
}