package com.tech.transaction.transferMoneyInput.contract

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TransactionInputViewUnitTest {

    @Mock
    private lateinit var presenter : InputContract.Presenter

    private lateinit var view : InputContract.View

    @Before
    public fun setup() {
        // When use @Mock annotation, we need to trigger the creation of annotated objects by calling iniMocks() method.
        MockitoAnnotations.initMocks(this)
    }

}