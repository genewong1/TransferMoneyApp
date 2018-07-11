package com.tech.transaction.transferMoneyInput

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.transaction.R
import com.tech.transaction.transferMoneyInput.component.DaggerInputPresenterComponent
import com.tech.transaction.transferMoneyInput.contract.InputContract
import com.tech.transaction.transferMoneyInput.contract.InputRouterImpl
import com.tech.transaction.transferMoneyInput.module.InputPresenterModule
import kotlinx.android.synthetic.main.fragment_transaction.*
import javax.inject.Inject

class InputFragment : Fragment(), InputContract.View {
    @Inject
    lateinit var presenter : InputContract.Presenter

    lateinit var router : InputContract.Router

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        router = InputRouterImpl(this.activity?.supportFragmentManager!!)

        // Creates presenter
        DaggerInputPresenterComponent.builder()
                .transactionInputPresenterModule(
                        InputPresenterModule(
                                view = this,
                                router = router
                        )
                )
                .build()
                .inject(this)

        setupBtnSubmitClickListener({ strAmount ->
            presenter.onBtnSubmit(strAmount)
        })
    }

    override fun setupBtnSubmitClickListener(callback : (strAmount: String)->Unit) {
        btnSubmit.setOnClickListener {
            callback(etAmount.text.toString())
        }
    }

}