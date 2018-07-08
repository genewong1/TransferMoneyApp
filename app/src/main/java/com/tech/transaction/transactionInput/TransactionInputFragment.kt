package com.tech.transaction.transactionInput

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.transaction.R
import com.tech.transaction.transactionInput.contract.TransactionInputFragmentContract
import kotlinx.android.synthetic.main.fragment_transaction.*

class TransactionInputFragment : Fragment(), TransactionInputFragmentContract.View {

    private lateinit var presenter : TransactionInputFragmentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TransactionInputFragmentContract.Presenter.newInstance(
                this,
                TransactionInputFragmentContract.Interactor.newInstance(presenter)
        )

        setupBtnSubmitClickListener({strAmount ->
            presenter.onBtnSubmit(strAmount)
        })
    }

    override fun setupBtnSubmitClickListener(callback : (strAmount: String)->Unit) {
        btnSubmit.setOnClickListener {
            callback(etAmount.text.toString())
        }
    }
}