package com.tech.transaction.transferMoneyResult.contract

import android.support.annotation.StringRes
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus

interface ResultContract {
    interface View {
        fun showStatus(@StringRes statusStringResId: Int)
    }

    interface Interactor {
        fun displayStatusAsFormatedString(transferMoneyStatus: TransferMoneyStatus)
    }

    interface Presenter : InteractorOutput {
        fun onViewCreatedAndStatusReceived(transferMoneyStatus: TransferMoneyStatus)
    }

    interface InteractorOutput {
        fun onDisplayStatus(@StringRes statusStringResId: Int)
    }
}