package com.tech.transaction.transactionInput.contract

import android.util.Log
import com.tech.transaction.application.TransferMoneyApp
import com.tech.transaction.data.TransferMoneyRepository
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.math.BigDecimal
import javax.inject.Inject

class TransactionInputInteractorImpl : TransactionInputContract.Interactor {
    private var output: TransactionInputContract.InteractorOutput

    @Inject
    protected lateinit var repository: TransferMoneyRepository

    @Inject
    constructor (output: TransactionInputContract.InteractorOutput) {
        this.output = output
    }

    init {
        TransferMoneyApp.app?.get()?.transferMoneyComponent?.inject(this@TransactionInputInteractorImpl)
    }

    override fun initiateTransaction(amount: BigDecimal) {
        repository.transferMoney(amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e(TAG, "onError with network call - localizedMessage = ${it.localizedMessage}")

                    output.onTransferRequestError()

                }.subscribe ({
                    output.onTransferRequestComplete()
                }) { throwable -> Log.e(TAG, throwable.toString()) }

    }

    companion object {
        private val TAG = TransactionInputInteractorImpl::class.java.simpleName
    }
}