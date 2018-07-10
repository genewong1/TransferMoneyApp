package com.tech.transaction.transactionResult.contract

import com.tech.transaction.R
import com.tech.transaction.entities.TransactionStatus.TransactionStatus

class TransactionResultInteractorImpl(
        private val output: TransactionResultContract.InteractorOutput
) : TransactionResultContract.Interactor {
    override fun displayStatusAsFormatedString(transactionStatus: TransactionStatus) {
        output.onDisplayStatus(statusStringResId = if (transactionStatus.success) R.string.Transaction_Successful else R.string.Transaction_Unsuccessful)
    }
}
