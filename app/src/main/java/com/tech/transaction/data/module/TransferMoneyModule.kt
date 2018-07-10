package com.tech.transaction.data.module

import android.app.Application
import com.tech.transaction.transactionInput.api.TransferMoneyService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class TransferMoneyModule {
    var application: Application? = null

    constructor(application: Application) {
        this.application = application
    }

    @Provides
    fun provideTransferMoneyService(retrofit: Retrofit) : TransferMoneyService {
        return retrofit.create(TransferMoneyService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        //Subject to checking every 24 hours to ensure that it remains.
        val baseUrl = "http://www.mocky.io/v2/5b44ba0e2f00007000420a35/"

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(okHttpClient)
                //converts Retrofit response into Observable
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        val connectTimeout: Long = 5
        val readTimeout: Long = 5
        val writeTimeout: Long = 5
        val timeoutUnit: TimeUnit = TimeUnit.SECONDS

        val okClientBuilder = OkHttpClient().newBuilder()

        //Create logging interceptor
        val interceptor = HttpLoggingInterceptor()

        //Set interceptor logging level.
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okClientBuilder.addInterceptor(interceptor)

        okClientBuilder.connectTimeout(connectTimeout, timeoutUnit)
        okClientBuilder.readTimeout(readTimeout, timeoutUnit)
        okClientBuilder.writeTimeout(writeTimeout, timeoutUnit)

        return okClientBuilder.build()
    }

}