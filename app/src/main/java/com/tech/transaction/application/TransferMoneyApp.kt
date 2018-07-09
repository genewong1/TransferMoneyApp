package com.tech.transaction.application

import android.app.Application
import com.tech.transaction.data.DaggerTransferMoneyComponent
import com.tech.transaction.data.TransferMoneyComponent
import com.tech.transaction.data.module.TransferMoneyModule
import java.lang.ref.WeakReference
import javax.inject.Inject

class TransferMoneyApp : Application() {
    @Inject
    lateinit var transferMoneyComponent : TransferMoneyComponent

    override fun onCreate() {
        super.onCreate()
        app = WeakReference(this@TransferMoneyApp)

        initDataComponent()

        transferMoneyComponent.inject(this)
    }

    private fun initDataComponent() {
        transferMoneyComponent = DaggerTransferMoneyComponent.builder()
                .transferMoneyModule(TransferMoneyModule(this))
                .build()
    }

    companion object {
        /**
         * TODO Use DI.
         */
        var app: WeakReference<TransferMoneyApp>? = null
    }
}