package com.tech.transaction.transferMoneyInput.contract

import android.support.v4.app.FragmentManager
import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class TransactionInputRouterUnitTest {

    @Mock
    lateinit var fragmentManager: FragmentManager

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test(expected=NullPointerException::class)
    fun goToTransactionResult() {
        //Given status: TransferMoneyStatus, go to transaction result.

        val router = InputRouterImpl(fragmentManager)

        router.goToTransactionResult(TransferMoneyStatus(true, BigDecimal.ONE))

        verify(fragmentManager).beginTransaction()
    }
}