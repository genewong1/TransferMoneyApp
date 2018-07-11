package com.tech.transaction.transferMoneyInput.contract

import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import com.tech.transaction.transferMoneyInput.component.DaggerInputInteractorComponent
import com.tech.transaction.transferMoneyInput.module.InputInteractorModule
import com.tech.transaction.typeConversion.parseCurrencyString
import javax.inject.Inject

class InputPresenterImpl: InputContract.Presenter {
    private var view: InputContract.View
    private var router: InputContract.Router

    @Inject
    lateinit var interactor: InputContract.Interactor

    @Inject
    constructor(view: InputContract.View, router: InputContract.Router) {
        this.view = view
        this.router = router
    }

    init {

        // Inject interactor
        DaggerInputInteractorComponent.builder()
                .InputInteractorModule(InputInteractorModule(this))
                .build()
                .inject(this)

    }

    override fun onTransferRequestComplete(transferMoneyStatus: TransferMoneyStatus) {
        router.goToTransactionResult(transferMoneyStatus = transferMoneyStatus)
    }

    override fun onTransferRequestError(transferMoneyStatus: TransferMoneyStatus) {
        router.goToTransactionResult(transferMoneyStatus = transferMoneyStatus)
    }

    override fun onBtnSubmit(amount: String) {
        interactor.initiateTransaction(amount = amount.parseCurrencyString())
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