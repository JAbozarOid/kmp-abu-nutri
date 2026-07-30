package com.abo.nutrisport.di

import com.abo.nutrisport.auth.viewmodel.AuthViewModel
import com.abo.nutrisport.data.CustomerRepositoryImpl
import com.abo.nutrisport.data.domain.CustomerRepository
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> { CustomerRepositoryImpl() } // koin automatically inject the implementation into the view model
    viewModelOf(::AuthViewModel)
}
fun initKoin(config: (KoinApplication.() -> Unit)? = null) {
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }
}


