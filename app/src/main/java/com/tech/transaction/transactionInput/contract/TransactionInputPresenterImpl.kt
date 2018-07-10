package com.tech.transaction.transactionInput.contract

import com.tech.transaction.transactionInput.DaggerTransactionInputInteractorComponent
import com.tech.transaction.transactionInput.TransactionInputInteractorModule
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

        // Creates interactor
        DaggerTransactionInputInteractorComponent.builder()
                .transactionInputInteractorModule(TransactionInputInteractorModule(this))
                .build()
                .inject(this)

    }

    override fun onTransferRequestComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTransferRequestError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBtnSubmit(amount: String) {
        interactor.initiateTransaction(amount = amount.parseCurrencyString())
    }
}