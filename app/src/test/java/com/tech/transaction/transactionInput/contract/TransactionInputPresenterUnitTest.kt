package com.tech.transaction.transactionInput.contract

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TransactionInputPresenterUnitTest {
    @Mock
    private lateinit var view : TransactionInputFragmentContract.View

    @Mock
    private lateinit var interactor : TransactionInputFragmentContract.Interactor

    private lateinit var presenter : TransactionInputFragmentContract.Presenter

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