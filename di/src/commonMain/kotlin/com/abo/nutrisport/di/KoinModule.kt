package com.abo.nutrisport.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin


fun initKoin(config: (KoinApplication.() -> Unit)? = null) {
    startKoin {
        config?.invoke(this)
    }
}


