package com.tech.transaction.transferMoneyInput.contract

import android.util.Log
import com.tech.transaction.data.DaggerTransferMoneyComponent
import com.tech.transaction.data.TransferMoneyRepository
import com.tech.transaction.data.module.TransferMoneyModule
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class InputInteractorImpl @Inject constructor(
        private var output: InputContract.InteractorOutput
) : InputContract.Interactor {

    @Inject
    protected lateinit var repository: TransferMoneyRepository

    init {
        DaggerTransferMoneyComponent
                .builder()
                .transferMoneyModule(TransferMoneyModule())
                .build()
                .inject(this@InputInteractorImpl)
    }

    override fun initiateTransaction(receivingAccountNumber: BigInteger, amount: BigDecimal) {
        output.onTransferRequestStart()

        repository.transferMoney(receivingAccountNumber, amount)
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

    private fun isReceivingAccountNumberValid(receivingAccountNumber: String) : Boolean {
        return receivingAccountNumber.isBlank() || BigInteger(receivingAccountNumber) <= BigInteger.ZERO
    }

    private fun isAmountValid(amount: String) : Boolean {
        return amount.isBlank() || BigDecimal(amount) <= BigDecimal.ZERO
    }

    override fun isInputValid(receivingAccountNumber: String, amount: String) {
        if (isReceivingAccountNumberValid(receivingAccountNumber) || isAmountValid(amount))
            output.onInputInvalid()
        else
            output.onInputValid()
    }

    companion object {
        private val TAG = InputInteractorImpl::class.java.simpleName
    }
}