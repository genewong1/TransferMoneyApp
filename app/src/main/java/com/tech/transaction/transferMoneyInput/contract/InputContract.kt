package com.tech.transaction.transferMoneyInput.contract

import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import java.math.BigDecimal

interface InputContract {
    interface View {
        fun setupBtnSubmitClickListener(callback : (strAmount: String)->Unit)
    }

    interface Interactor {
        fun initiateTransaction(amount: BigDecimal)
    }

    interface InteractorOutput {
        fun onTransferRequestComplete(transferMoneyStatus: TransferMoneyStatus)
        fun onTransferRequestError(transferMoneyStatus: TransferMoneyStatus)
    }

    interface Presenter : InteractorOutput {
        fun onBtnSubmit(amount: String)
    }

    interface Router {
        fun goToTransactionResult(transferMoneyStatus: TransferMoneyStatus)
    }
}