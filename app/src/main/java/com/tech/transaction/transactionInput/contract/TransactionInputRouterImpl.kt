package com.tech.transaction.transactionInput.contract

import android.support.v4.app.FragmentManager
import com.tech.transaction.R
import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import com.tech.transaction.transactionResult.TransactionResultFragment

class TransactionInputRouterImpl(
    private val fragmentManager: FragmentManager
) : TransactionInputContract.Router {

    override fun goToTransactionResult(transactionStatus: TransactionStatus) {
        this.fragmentManager
            .beginTransaction()
            .addToBackStack("transaction")
            .replace(
                    R.id.fragmentContainer,
                    TransactionResultFragment.newInstance(transactionStatus)
            )
            .commit()
    }
}