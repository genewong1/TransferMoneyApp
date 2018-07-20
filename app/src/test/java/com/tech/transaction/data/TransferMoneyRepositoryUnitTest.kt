package com.tech.transaction.data

import com.nhaarman.mockito_kotlin.verify
import com.tech.transaction.data.dto.`in`.TransferMoneyInDto
import com.tech.transaction.data.dto.out.TransferMoneyOutDto
import com.tech.transaction.transferMoneyInput.api.TransferMoneyService
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import me.rozkmin.spekbdd.BddDsl.Given
import me.rozkmin.spekbdd.BddDsl.Scenario
import me.rozkmin.spekbdd.BddDsl.Then
import me.rozkmin.spekbdd.BddDsl.When
import org.jetbrains.spek.api.Spek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import rx.Observable
import java.math.BigDecimal
import java.math.BigInteger

@RunWith(JUnitPlatform::class)
class TransferMoneyRepositoryUnitTest : Spek({

    Scenario("start to transfer money") {

        val transferMoneyService: TransferMoneyService = mock(TransferMoneyService::class.java)

        val transferMoneyRepository = TransferMoneyRepository(
            transferMoneyService
        )

        Given("Receiving Account Number, Amount") {
            When("call transfer money") {
                Then("send money") {

                    val receivingAccountNumber = BigInteger("122")
                    val amount = BigDecimal(102.12)

                    val observable = transferMoneyRepository.transferMoney(
                        receivingAccountNumber = receivingAccountNumber,
                        amount = amount
                    )

                    verify(transferMoneyService).sendMoney(ArgumentMatchers.any(TransferMoneyOutDto::class.java))

                    assertTrue(observable is Observable<TransferMoneyInDto>)
                }
            }
        }
    }

    Scenario("Create TransferMoneyOutDto") {

        val transferMoneyService: TransferMoneyService = mock(TransferMoneyService::class.java)

        val transferMoneyRepository = TransferMoneyRepository(
                transferMoneyService
        )

        Given("Receiving Account Number, Amount") {
            When("call getTransferMoneyOutDto") {
                Then("return getTransferMoneyOutDto with receivingAccountNumber and amount") {

                    val receivingAccountNumber = BigInteger("112322")
                    val amount = BigDecimal(102123.12)

                    val transferMoneyInDto = transferMoneyRepository.getTransferMoneyOutDto(
                            receivingAccountNumber = receivingAccountNumber,
                            amount = amount
                    )

                    assertEquals(receivingAccountNumber, transferMoneyInDto.receivingAccountNumber)
                    assertEquals(amount, transferMoneyInDto.amount)
                }
            }
        }
    }


})