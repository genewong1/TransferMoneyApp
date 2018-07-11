package com.tech.transaction.transferMoneyResult

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.transaction.R
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import com.tech.transaction.transferMoneyResult.component.DaggerResultComponent
import com.tech.transaction.transferMoneyResult.contract.ResultContract
import com.tech.transaction.transferMoneyResult.module.ResultPresenterModule
import kotlinx.android.synthetic.main.fragment_transaction_result.*
import javax.inject.Inject

class ResultFragment : Fragment(), ResultContract.View {

    @Inject
    lateinit var presenter: ResultContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionStatus = this.arguments?.getParcelable<TransferMoneyStatus>(KEY_TRANSACTION_STATUS)
                ?: throw IllegalArgumentException("Parcelable KEY_TRANSACTION_STATUS expected")

        DaggerResultComponent.builder()
                .resultPresenterModule(
                        ResultPresenterModule(this)
                )
                .build()
                .inject(this)

        presenter.onViewCreatedAndStatusReceived(transactionStatus)
    }

    override fun showStatus(@StringRes statusStringResId: Int) {
        tvTransactionResult.setText(statusStringResId)
    }

    companion object {
        const val KEY_TRANSACTION_STATUS = "TRANSACTION_STATUS"

        fun newInstance(transferMoneyStatus: TransferMoneyStatus) : ResultFragment {
            val transactionResultFragment = ResultFragment()

            val bundle = Bundle()
            bundle.putParcelable(ResultFragment.KEY_TRANSACTION_STATUS, transferMoneyStatus)
            transactionResultFragment.arguments = bundle

            return transactionResultFragment
        }
    }
}