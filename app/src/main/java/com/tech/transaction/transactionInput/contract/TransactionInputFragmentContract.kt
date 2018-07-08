package com.tech.transaction.transactionInput.contract

import android.support.v4.app.FragmentManager
import com.tech.transaction.entities.TransactionStatus.TransactionStatus
import java.math.BigDecimal

interface TransactionInputFragmentContract {
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
    }

    interface Presenter : InteractorOutput {
        fun onBtnSubmit(amount: String)

        companion object {
            /**
             * TODO use different way to solve.
             */
            fun newInstance(view: View, interactor: Interactor): Presenter {
                return TransactionInputPresenterImpl(view = view, interactor = interactor)
            }
        }
    }

    interface Entity

    interface Router {
        fun goToTransactionResult(status: TransactionStatus)

        companion object {
            fun newInstance(supportFragmentManager: FragmentManager): TransactionInputFragmentContract.Router{
                return TransactionInputRouterImpl(supportFragmentManager)
            }
        }
    }
}