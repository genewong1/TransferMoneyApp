package com.tech.transaction.transactionResult.contract

import com.tech.transaction.entities.TransactionStatus.TransactionStatus

interface TransactionResultContract {
    interface View {
        fun showStatus(statusString: String)
    }

    interface Interactor {
        fun displayStatusAsFormatedString(transactionStatus: TransactionStatus)
    }

    interface Presenter : InteractorOutput {
        fun onViewCreatedAndStatusReceived(transactionStatus: TransactionStatus)
    }

    interface InteractorOutput {
        fun onDisplayStatus(statusString: String)
    }

    interface Router
}