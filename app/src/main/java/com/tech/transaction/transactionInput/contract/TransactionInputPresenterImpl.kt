package com.tech.transaction.transactionInput.contract

import com.tech.transaction.transactionInput.DaggerTransactionInputInteractorComponent
import com.tech.transaction.transactionInput.TransactionInputInteractorModule
import javax.inject.Inject

class TransactionInputPresenterImpl: TransactionInputContract.Presenter {
    private var view: TransactionInputContract.View

    @Inject
    lateinit var interactor: TransactionInputContract.Interactor

    @Inject
    constructor(view: TransactionInputContract.View) {
        this.view = view
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

    override fun onBtnSubmit(amount: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}