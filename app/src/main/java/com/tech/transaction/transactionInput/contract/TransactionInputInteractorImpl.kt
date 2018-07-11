package com.tech.transaction.transactionInput.contract

import android.util.Log
import com.tech.transaction.data.DaggerTransferMoneyComponent
import com.tech.transaction.data.TransferMoneyRepository
import com.tech.transaction.data.module.TransferMoneyModule
import com.tech.transaction.entities.TransactionStatus.TransactionStatus
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
        DaggerTransferMoneyComponent
                .builder()
                .transferMoneyModule(TransferMoneyModule())
                .build()
                .inject(this@TransactionInputInteractorImpl)
    }

    override fun initiateTransaction(amount: BigDecimal) {
        repository.transferMoney(amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.e(TAG, "onError with network call - localizedMessage = ${it.localizedMessage}")
                }.subscribe ({transferMoneyInDto ->
                    output.onTransferRequestComplete(TransactionStatus(
                            success = transferMoneyInDto.success ?: false,
                            amount = amount
                    ))
                }) { throwable ->
                    Log.e(TAG, throwable.toString())

                    output.onTransferRequestError(TransactionStatus(success = false, amount = amount))
                }

    }

    companion object {
        private val TAG = TransactionInputInteractorImpl::class.java.simpleName
    }
}