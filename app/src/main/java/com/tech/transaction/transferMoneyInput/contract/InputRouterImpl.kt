package com.tech.transaction.transferMoneyInput.contract

import android.support.v4.app.FragmentManager
import com.tech.transaction.R
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import com.tech.transaction.transferMoneyResult.ResultFragment

class InputRouterImpl(
    private val fragmentManager: FragmentManager
) : InputContract.Router {

    override fun goToTransactionResult(transferMoneyStatus: TransferMoneyStatus) {
        this.fragmentManager
            .beginTransaction()
            .addToBackStack("transaction")
            .replace(
                    R.id.fragmentContainer,
                    ResultFragment.newInstance(transferMoneyStatus)
            )
            .commit()
    }
}