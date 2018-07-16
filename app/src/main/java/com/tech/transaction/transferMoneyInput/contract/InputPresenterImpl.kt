package com.tech.transaction.transferMoneyInput.contract

import android.support.annotation.VisibleForTesting
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import com.tech.transaction.transferMoneyInput.component.DaggerInputInteractorComponent
import com.tech.transaction.transferMoneyInput.module.InputInteractorModule
import com.tech.transaction.typeConversion.parseCurrencyString
import javax.inject.Inject

class InputPresenterImpl @Inject constructor(
        private var view: InputContract.View,
        private var router: InputContract.Router
) : InputContract.Presenter {

    @Inject
    lateinit var interactor: InputContract.Interactor

    init {
        // Inject interactor
        DaggerInputInteractorComponent.builder()
                .inputInteractorModule(InputInteractorModule(this))
                .build()
                .inject(this)
    }

    /**
     * TODO find a better way to use injected interactor for testing.
     */
    @VisibleForTesting
    constructor(view: InputContract.View, router: InputContract.Router, interactor: InputContract.Interactor) : this(view, router) {
        this.view = view
        this.router = router
        this.interactor = interactor
    }

    override fun onTransferRequestStart() {
        view.startProgressBar()
    }

    override fun onTransferRequestComplete(transferMoneyStatus: TransferMoneyStatus) {
        router.goToTransactionResult(transferMoneyStatus = transferMoneyStatus)
        view.stopProgressBar()
    }

    override fun onTransferRequestError(transferMoneyStatus: TransferMoneyStatus) {
        router.goToTransactionResult(transferMoneyStatus = transferMoneyStatus)
        view.stopProgressBar()
    }

    override fun onBtnSubmit(receivingAccountNumber: String, amount: String) {
        interactor.initiateTransaction(
                receivingAccountNumber = receivingAccountNumber.toBigInteger(),
                amount = amount.parseCurrencyString()
        )
    }

    override fun onAmountInputValid() {
        view.enableBtnSubmit(true)
    }

    override fun onAmountInputInvalid() {
        view.enableBtnSubmit(false)
    }

    override fun onEtAmountFieldChanged(amount: String) {
        interactor.isAmountInputValid(amount)
    }
}