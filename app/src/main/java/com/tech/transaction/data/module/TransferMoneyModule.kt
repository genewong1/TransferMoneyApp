package com.tech.transaction.data.module

import android.app.Application
import com.tech.transaction.transactionInput.api.TransferMoneyService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
        val BASE_URL = "https://genewong1.github.io/"

        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                //converts Retrofit response into Observable
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

}