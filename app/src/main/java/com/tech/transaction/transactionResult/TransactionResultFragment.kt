package com.tech.transaction.transactionResult

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tech.transaction.R
import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import com.tech.transaction.transactionResult.component.DaggerTransactionResultComponent
import com.tech.transaction.transactionResult.contract.TransactionResultContract
import com.tech.transaction.transactionResult.module.TransactionResultPresenterModule
import kotlinx.android.synthetic.main.fragment_transaction_result.*
import javax.inject.Inject

class TransactionResultFragment : Fragment(), TransactionResultContract.View {

    @Inject
    lateinit var presenter: TransactionResultContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transactionStatus = this.arguments?.getParcelable<TransactionStatus>(KEY_TRANSACTION_STATUS)
                ?: throw IllegalArgumentException("Parcelable KEY_TRANSACTION_STATUS expected")

        DaggerTransactionResultComponent.builder()
                .transactionResultPresenterModule(
                        TransactionResultPresenterModule(this)
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

        fun newInstance(transactionStatus: TransactionStatus) : TransactionResultFragment {
            val transactionResultFragment = TransactionResultFragment()

            val bundle = Bundle()
            bundle.putParcelable(TransactionResultFragment.KEY_TRANSACTION_STATUS, transactionStatus)
            transactionResultFragment.arguments = bundle

            return transactionResultFragment
        }
    }
}