package com.tech.transaction.transferMoneyInput.contract

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TransactionInputPresenterUnitTest {
    @Mock
    private lateinit var view : InputContract.View

    @Mock
    private lateinit var interactor : InputContract.Interactor

    private lateinit var presenter : InputContract.Presenter

    @Before
    fun setup() {
        // When use @Mock annotation, we need to trigger the creation of annotated objects by calling iniMocks() method.
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onBtnSubmit_CallsInitiateTransaction() {
        //todo
    }

    @Test
    fun onTransferRequestComplete_TransitionsToResultFragment() {
        //todo
    }

}