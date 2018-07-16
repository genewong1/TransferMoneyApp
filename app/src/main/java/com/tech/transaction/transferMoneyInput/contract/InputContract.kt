package com.tech.transaction.transferMoneyInput.contract

import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import java.math.BigDecimal
import java.math.BigInteger

interface InputContract {
    interface View {
        fun setupBtnSubmitClickListener(callback : (receiverAccountNumber: String, strAmount: String)->Unit)
        fun enableBtnSubmit(enable: Boolean)

        fun startProgressBar()
        fun stopProgressBar()
    }

    interface Interactor {
        fun initiateTransaction(receivingAccountNumber: BigInteger, amount: BigDecimal)
        fun isInputValid(receivingAccountNumber: String, amount: String)
    }

    interface InteractorOutput : TransferRequestCallback, InputValidCallback

    interface InputValidCallback {
        fun onInputValid()
        fun onInputInvalid()
    }

    interface TransferRequestCallback {
        fun onTransferRequestStart()
        fun onTransferRequestComplete(transferMoneyStatus: TransferMoneyStatus)
        fun onTransferRequestError(transferMoneyStatus: TransferMoneyStatus)
    }

    interface Presenter : InteractorOutput {
        fun onFieldsChanged(receivingAccountNumber: String, amount: String)
        fun onBtnSubmit(receivingAccountNumber: String, amount: String)
    }

    interface Router {
        fun goToTransactionResult(transferMoneyStatus: TransferMoneyStatus)
    }
}