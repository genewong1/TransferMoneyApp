package com.tech.transaction.transferMoneyInput.contract

import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.math.BigDecimal


class TransactionInputPresenterUnitTest {
    @Mock
    private lateinit var view : InputContract.View

    @Mock
    lateinit var interactor : InputContract.Interactor

    @Mock
    private lateinit var router: InputContract.Router

    private lateinit var presenter : InputContract.Presenter

    @Before
    fun setup() {
        // When use @Mock annotation, we need to trigger the creation of annotated objects by calling iniMocks() method.
        MockitoAnnotations.initMocks(this)

        presenter = InputPresenterImpl(view, router)

    }

    @Test
    fun onBtnSubmit_CallsInitiateTransaction() {
        val strAmount = "201"
        val bdAmount = BigDecimal("201")

        presenter = InputPresenterImpl(view, router, interactor)

        presenter.onBtnSubmit(strAmount)

        verify(interactor).initiateTransaction(bdAmount)

    }

    @Test
    fun onTransferRequestComplete_TransitionsToResultFragment() {
        val strAmount = "2331"
        val bdAmount = BigDecimal(strAmount)

        presenter = InputPresenterImpl(view, router)

        val transferMoneyStatus = TransferMoneyStatus(true, bdAmount)
        presenter.onTransferRequestComplete(transferMoneyStatus = transferMoneyStatus)
        verify(router).goToTransactionResult(transferMoneyStatus)

        val transferMoneyStatusSuccessFalse = TransferMoneyStatus(false, bdAmount)
        presenter.onTransferRequestComplete(transferMoneyStatus = transferMoneyStatusSuccessFalse)
        verify(router).goToTransactionResult(transferMoneyStatusSuccessFalse)

    }

    @Test
    fun onTransferRequestError_TransitionsToResultFragment() {
        val strAmount = "23311"
        val bdAmount = BigDecimal(strAmount)

        presenter = InputPresenterImpl(view, router)

        val transferMoneyStatus = TransferMoneyStatus(true, bdAmount)
        presenter.onTransferRequestError(transferMoneyStatus = transferMoneyStatus)
        verify(router).goToTransactionResult(transferMoneyStatus)

        val transferMoneyStatusSuccessFalse = TransferMoneyStatus(false, bdAmount)
        presenter.onTransferRequestError(transferMoneyStatus = transferMoneyStatusSuccessFalse)
        verify(router).goToTransactionResult(transferMoneyStatusSuccessFalse)
    }

    @Test
    fun onAmountInputValid_CallsEnableBtnSubmit() {
        presenter = InputPresenterImpl(view, router)

        presenter.onAmountInputValid()
        verify(view).enableBtnSubmit(true)
    }

    @Test
    fun onAmountInputInvalid_CallsEnableBtnSubmit() {
        presenter = InputPresenterImpl(view, router)

        presenter.onAmountInputInvalid()
        verify(view).enableBtnSubmit(false)
    }

    @Test
    fun onEtAmountFieldChanged_GivenAmount() {
        val amount = "201"

        presenter = InputPresenterImpl(view, router, interactor)

        presenter.onEtAmountFieldChanged(amount)
        verify(interactor).isAmountInputValid(amount)
    }

}