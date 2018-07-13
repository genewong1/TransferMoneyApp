package com.tech.transaction.transferMoneyInput.contract

import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class TransactionInputInteractorUnitTest {

    @Mock
    lateinit var output: InputContract.InteractorOutput

    @Mock
    lateinit var presenter: InputContract.Presenter

    lateinit var interactor: InputInteractorImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun isAmountInputValid_givenValidAmount() {
        interactor = InputInteractorImpl(output)

        val amount = "10"
        interactor.isAmountInputValid(amount = amount)

        verify(output, times(1)).onAmountInputValid()
    }

    @Test
    fun isAmountInputValid_givenInvalidNegativeAmount() {
        interactor = InputInteractorImpl(output)

        val amount = "-102"
        interactor.isAmountInputValid(amount = amount)

        verify(output, times(1)).onAmountInputInvalid()
    }

    @Test
    fun isAmountInputValid_givenInvalidBlankAmount() {
        interactor = InputInteractorImpl(output)

        val amount = ""
        interactor.isAmountInputValid(amount = amount)

        verify(output, times(1)).onAmountInputInvalid()
    }

    @Test
    fun isAmountInputValid_givenInvalidZeroAmount() {
        interactor = InputInteractorImpl(output)

        val amount = "0"
        interactor.isAmountInputValid(amount = amount)

        verify(output, times(1)).onAmountInputInvalid()
    }

    /**
     * todo Requires mocking to be testable.
     */
//    @Test
    fun initiateTransaction_ValidAmount() {
        interactor = InputInteractorImpl(output)

        val receivingAccountNumber = 1233
        val amount = "320"
        interactor.initiateTransaction(receivingAccountNumber, BigDecimal(amount))

        verify(output, times(1))
        .onTransferRequestComplete(transferMoneyStatus =
            TransferMoneyStatus(
                success = false,
                amount = BigDecimal(320)
            )
        )
    }

}