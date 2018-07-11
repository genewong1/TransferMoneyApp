package com.tech.transaction.transferMoneyInput.contract

import android.support.v4.app.FragmentManager
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import java.math.BigDecimal

interface InputContract {
    interface View {
        fun setupBtnSubmitClickListener(callback : (strAmount: String)->Unit)
    }

    interface Interactor {
        fun initiateTransaction(amount: BigDecimal)

        companion object {
            fun newInstance(presenter: Presenter) : Interactor {
                return InputInteractorImpl(presenter)
            }
        }
    }

    interface InteractorOutput {
        fun onTransferRequestComplete(transferMoneyStatus: TransferMoneyStatus)
        fun onTransferRequestError(transferMoneyStatus: TransferMoneyStatus)
    }

    interface Presenter : InteractorOutput {
        fun onBtnSubmit(amount: String)

        companion object {
            /**
             * TODO use different way to solve.
             */
            fun newInstance(view: View, router: Router): Presenter {
                return InputPresenterImpl(view = view, router = router)
            }
        }
    }

    interface Router {
        fun goToTransactionResult(transferMoneyStatus: TransferMoneyStatus)

        companion object {
            fun newInstance(supportFragmentManager: FragmentManager): InputContract.Router{
                return InputRouterImpl(supportFragmentManager)
            }
        }
    }
}