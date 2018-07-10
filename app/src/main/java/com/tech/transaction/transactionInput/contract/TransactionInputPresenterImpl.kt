package com.tech.transaction.transactionInput.contract

import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import com.tech.transaction.transactionInput.component.DaggerTransactionInputInteractorComponent
import com.tech.transaction.transactionInput.module.TransactionInputInteractorModule
import com.tech.transaction.typeConversion.parseCurrencyString
import javax.inject.Inject

class TransactionInputPresenterImpl: TransactionInputContract.Presenter {
    private var view: TransactionInputContract.View
    private var router: TransactionInputContract.Router

    @Inject
    lateinit var interactor: TransactionInputContract.Interactor

    @Inject
    constructor(view: TransactionInputContract.View, router: TransactionInputContract.Router) {
        this.view = view
        this.router = router
    }

    init {

        // Inject interactor
        DaggerTransactionInputInteractorComponent.builder()
                .transactionInputInteractorModule(TransactionInputInteractorModule(this))
                .build()
                .inject(this)

    }

    override fun onTransferRequestComplete(transactionStatus: TransactionStatus) {
        router.goToTransactionResult(transactionStatus = transactionStatus)
    }

    override fun onTransferRequestError(transactionStatus: TransactionStatus) {
        router.goToTransactionResult(transactionStatus = transactionStatus)
    }

    override fun onBtnSubmit(amount: String) {
        interactor.initiateTransaction(amount = amount.parseCurrencyString())
    }
}