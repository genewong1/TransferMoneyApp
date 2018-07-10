package com.tech.transaction.transactionInput.contract

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.tech.transaction.R
import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import com.tech.transaction.transactionResult.TransactionResultFragment

class TransactionInputRouterImpl(
    private val fragmentManager: FragmentManager
) : TransactionInputContract.Router {

    override fun goToTransactionResult(transactionStatus: TransactionStatus) {
        val transactionResultFragment = TransactionResultFragment()

        val bundle = Bundle()
        bundle.putParcelable(TransactionResultFragment.KEY_TRANSACTION_STATUS, transactionStatus)
        transactionResultFragment.arguments = bundle

        this.fragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, transactionResultFragment)
            .commit()
    }
}