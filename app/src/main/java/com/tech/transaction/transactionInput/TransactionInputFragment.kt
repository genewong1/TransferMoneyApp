package com.tech.transaction.transactionInput

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.transaction.R
import com.tech.transaction.transactionInput.contract.TransactionInputContract
import kotlinx.android.synthetic.main.fragment_transaction.*
import javax.inject.Inject

class TransactionInputFragment : Fragment(), TransactionInputContract.View {
    @Inject
    lateinit var presenter : TransactionInputContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Creates presenter
        DaggerTransactionInputPresenterComponent.builder()
                .transactionInputPresenterModule(TransactionInputPresenterModule(this))
                .build()
                .inject(this)

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