package com.tech.transaction.transactionInput.contract

class TransactionInputPresenterImpl(
        private val view: TransactionInputContract.View
): TransactionInputContract.Presenter {
    private val interactor: TransactionInputContract.Interactor = TransactionInputInteractorImpl(this)

    override fun onTransferRequestComplete() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBtnSubmit(amount: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}