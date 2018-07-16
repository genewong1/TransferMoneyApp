package com.tech.transaction.transferMoneyInput.contract

import com.tech.transaction.entities.TransferMoneyStatus.TransferMoneyStatus
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.math.BigInteger

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

        val receivingAccountNumber = "1022031012398484"
        val amount = "10"
        interactor.isInputValid(receivingAccountNumber = receivingAccountNumber, amount = amount)

        verify(output, times(1)).onInputValid()
    }

    @Test
    fun isAmountInputValid_givenInvalidNegativeAmount() {
        interactor = InputInteractorImpl(output)

        val receivingAccountNumber = "302020204949"
        val amount = "-102"
        interactor.isInputValid(receivingAccountNumber = receivingAccountNumber, amount = amount)

        verify(output, times(1)).onInputInvalid()
    }

    @Test
    fun isAmountInputValid_givenInvalidBlankAmount() {
        interactor = InputInteractorImpl(output)

        val receivingAccountNumber = "20103010"
        val amount = ""
        interactor.isInputValid(receivingAccountNumber = receivingAccountNumber, amount = amount)

        verify(output, times(1)).onInputInvalid()
    }

    @Test
    fun isAmountInputValid_givenInvalidZeroAmount() {
        interactor = InputInteractorImpl(output)

        val receivingAccountNumber = "1020201"
        val amount = "0"
        interactor.isInputValid(receivingAccountNumber = receivingAccountNumber, amount = amount)

        verify(output, times(1)).onInputInvalid()
    }

    /**
     * todo Requires mocking to be testable.
     */
//    @Test
    fun initiateTransaction_ValidAmount() {
        interactor = InputInteractorImpl(output)

        val receivingAccountNumber: BigInteger = BigInteger("1233")
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