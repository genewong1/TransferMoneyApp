package com.tech.transaction.transactionInput.contract

import android.support.v4.app.FragmentManager
import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import java.math.BigDecimal

interface TransactionInputContract {
    interface View {
        fun setupBtnSubmitClickListener(callback : (strAmount: String)->Unit)
    }

    interface Interactor {
        fun initiateTransaction(amount: BigDecimal)

        companion object {
            fun newInstance(presenter: Presenter) : Interactor {
                return TransactionInputInteractorImpl(presenter)
            }
        }
    }

    interface InteractorOutput {
        fun onTransferRequestComplete()
        fun onTransferRequestError()
    }

    interface Presenter : InteractorOutput {
        fun onBtnSubmit(amount: String)

        companion object {
            /**
             * TODO use different way to solve.
             */
            fun newInstance(view: View): Presenter {
                return TransactionInputPresenterImpl(view = view)
            }
        }
    }

    interface Entity

    interface Router {
        fun goToTransactionResult(transactionStatus: TransactionStatus)

        companion object {
            fun newInstance(supportFragmentManager: FragmentManager): TransactionInputContract.Router{
                return TransactionInputRouterImpl(supportFragmentManager)
            }
        }
    }
}