package com.tech.transaction.transferMoneyInput.api

import com.tech.transaction.data.dto.`in`.TransferMoneyInDto
import com.tech.transaction.data.dto.out.TransferMoneyOutDto
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import me.rozkmin.spekbdd.BddDsl.Given
import me.rozkmin.spekbdd.BddDsl.Scenario
import me.rozkmin.spekbdd.BddDsl.Then
import me.rozkmin.spekbdd.BddDsl.When
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.jetbrains.spek.api.Spek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.math.BigInteger

@RunWith(JUnitPlatform::class)
class TransferMoneyServiceUnitTest : Spek({
    Scenario("Send Money API call") {
        Given("Details required for money transfer") {

            val server = MockWebServer()

            val body = "{\"success\":true}"
            server.enqueue(MockResponse().setBody(body))

            server.start()

            val baseUrl: HttpUrl = server.url("/") //"http://www.mocky.io/v2/5b44ba0e2f00007000420a35/")

            val retrofit: Retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            val service : TransferMoneyService = retrofit.create(TransferMoneyService::class.java)

            val receivingAccountNumber = BigInteger("19219022")
            val amount = BigDecimal("912111")

            val callObservable = service.sendMoney(
                TransferMoneyOutDto(
                    receivingAccountNumber = receivingAccountNumber,
                    amount = amount
                )
            )

            When("Call") {
                Then("Receive result - successful status") {

                    callObservable.test().assertValue(TransferMoneyInDto(true))

                    val request = server.takeRequest()
                    assertNull(request.getHeader("Authorization"))
                    assertEquals("/transfer", request.path)

                    server.shutdown()

                }
            }
        }

        Given("Server return") {

            val server = MockWebServer()

            val body = "{\"success\":false}"
            server.enqueue(MockResponse().setBody(body))

            server.start()

            val baseUrl: HttpUrl = server.url("/") //"http://www.mocky.io/v2/5b44ba0e2f00007000420a35/")

            val retrofit: Retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            val service : TransferMoneyService = retrofit.create(TransferMoneyService::class.java)

            val receivingAccountNumber = BigInteger("19219022")
            val amount = BigDecimal("912111")

            val callObservable = service.sendMoney(
                TransferMoneyOutDto(
                    receivingAccountNumber = receivingAccountNumber,
                    amount = amount
                )
            )

            When("Call") {
                Then("Receive result - unsuccessful status") {

                    callObservable.test().assertValue(TransferMoneyInDto(false))

                    val request = server.takeRequest()
                    assertNull(request.getHeader("Authorization"))
                    assertEquals("/transfer", request.path)

                    server.shutdown()

                }
            }
        }
    }
})