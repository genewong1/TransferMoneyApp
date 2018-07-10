package com.tech.transaction.transactionResult

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.transaction.R
import com.tech.transaction.entities.TransactionStatus.TransactionStatus

class TransactionResultFragment  : Fragment() {

    private val transactionStatus = this.arguments?.getParcelable<TransactionStatus>(KEY_TRANSACTION_STATUS)
                                ?: throw IllegalArgumentException("Parcelable KEY_TRANSACTION_STATUS expected")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        const val KEY_TRANSACTION_STATUS = "TRANSACTION_STATUS"
    }
}