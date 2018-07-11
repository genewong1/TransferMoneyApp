package com.tech.transaction.transferMoneyInput

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
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
                .inputPresenterModule(
                        InputPresenterModule(
                                view = this,
                                router = router
                        )
                )
                .build()
                .inject(this)

        etAmount.addTextChangedListener(
                object: TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                    }
                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                        presenter.onEtAmountFieldChanged(p0.toString())

                        //Close InputConnection.
                        //If not, causes
                        // java.lang.NullPointerException:
                        // Attempt to invoke interface method
                        // 'void android.view.inputmethod.InputConnection.closeConnection()'
                        // on a null object reference
                        etAmount?.clearFocus()
                    }
                }
        )

        btnSubmit.isEnabled = true

        setupBtnSubmitClickListener({ strAmount ->
            btnSubmit.isEnabled = false
            presenter.onBtnSubmit(strAmount)
        })

    }

    override fun setupBtnSubmitClickListener(callback : (strAmount: String)->Unit) {
        btnSubmit.setOnClickListener {
            callback(etAmount.text.toString())
        }
    }

    override fun enableBtnSubmit(enable: Boolean) {
        btnSubmit.isEnabled = enable
    }
}