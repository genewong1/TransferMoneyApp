package com.tech.transaction.transferMoneyInput.contract

import android.util.Log
import com.tech.transaction.data.DaggerTransferMoneyComponent
import com.tech.transaction.data.TransferMoneyRepository
import com.tech.transaction.data.module.TransferMoneyModule
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.math.BigDecimal
import javax.inject.Inject

class InputInteractorImpl : InputContract.Interactor {
    private var output: InputContract.InteractorOutput

    @Inject
    protected lateinit var repository: TransferMoneyRepository

    @Inject
    constructor (output: InputContract.InteractorOutput) {
        this.output = output
    }

    init {
        DaggerTransferMoneyComponent
                .builder()
                .transferMoneyModule(TransferMoneyModule())
                .build()
                .inject(this@InputInteractorImpl)
    }

    override fun initiateTransaction(amount: BigDecimal) {
        repository.transferMoney(amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e(TAG, "onError with network call - localizedMessage = ${it.localizedMessage}")
                }.subscribe ({transferMoneyInDto ->
                    output.onTransferRequestComplete(TransferMoneyStatus(
                            success = transferMoneyInDto.success ?: false,
                            amount = amount
                    ))
                }) { throwable ->
                    Log.e(TAG, throwable.toString())

                    output.onTransferRequestError(TransferMoneyStatus(success = false, amount = amount))
                }

    }

    override fun isAmountInputValid(amount: String) {
        if (amount.isNullOrBlank() || BigDecimal(amount) <= BigDecimal.ZERO) {
            output.onAmountInputInvalid()
        } else {
            output.onAmountInputValid()
        }
    }

    companion object {
        private val TAG = InputInteractorImpl::class.java.simpleName
    }
}