package com.tech.transaction.transactionInput.contract

import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import java.math.BigDecimal

interface TransactionInputFragmentContract {
    interface View {
    }

    interface Interactor {
        fun initiateTransaction(amount: BigDecimal)
    }

    interface InteractorOutput {
        fun onTransferRequestComplete()
    }

    interface Presenter : InteractorOutput {
        fun onBtnSubmit(amount: String)
    }

    interface Entity

    interface Router {
        fun goToTransactionResult(status: TransactionStatus)
    }
}