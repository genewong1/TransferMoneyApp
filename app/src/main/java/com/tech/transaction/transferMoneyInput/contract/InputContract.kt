package com.tech.transaction.transferMoneyInput.contract

import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import java.math.BigDecimal
import java.math.BigInteger

interface InputContract {
    interface View {
        fun setupBtnSubmitClickListener(callback : (strAmount: String)->Unit)

        fun enableBtnSubmit(enable: Boolean)
    }

    interface Interactor {
        fun initiateTransaction(receivingAccountNumber: BigInteger, amount: BigDecimal)
        fun isAmountInputValid(amount: String)
    }

    interface InteractorOutput : TransferRequestCallback, AmountInputValidCallback

    interface AmountInputValidCallback {
        fun onAmountInputValid()
        fun onAmountInputInvalid()
    }

    interface TransferRequestCallback {
        fun onTransferRequestComplete(transferMoneyStatus: TransferMoneyStatus)
        fun onTransferRequestError(transferMoneyStatus: TransferMoneyStatus)
    }

    interface Presenter : InteractorOutput {
        fun onEtAmountFieldChanged(amount: String)
        fun onBtnSubmit(receivingAccountNumber: BigInteger, amount: String)
    }

    interface Router {
        fun goToTransactionResult(transferMoneyStatus: TransferMoneyStatus)
    }
}