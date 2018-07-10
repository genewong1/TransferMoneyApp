package com.tech.transaction.transactionResult.contract

import android.support.annotation.StringRes
import com.tech.transaction.entities.TransactionStatus.TransactionStatus

interface TransactionResultContract {
    interface View {
        fun showStatus(@StringRes statusStringResId: Int)
    }

    interface Interactor {
        fun displayStatusAsFormatedString(transactionStatus: TransactionStatus)
    }

    interface Presenter : InteractorOutput {
        fun onViewCreatedAndStatusReceived(transactionStatus: TransactionStatus)
    }

    interface InteractorOutput {
        fun onDisplayStatus(@StringRes statusStringResId: Int)
    }
}