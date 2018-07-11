package com.tech.transaction.transferMoneyResult.contract

import com.tech.transaction.R
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus

class ResultInteractorImpl(
        private val output: ResultContract.InteractorOutput
) : ResultContract.Interactor {
    override fun displayStatusAsFormatedString(transferMoneyStatus: TransferMoneyStatus) {
        output.onDisplayStatus(statusStringResId = if (transferMoneyStatus.success) R.string.Transaction_Successful else R.string.Transaction_Unsuccessful)
    }
}
