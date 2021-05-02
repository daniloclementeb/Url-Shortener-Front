package com.example.urlshortener.di.module

import com.example.urlshortener.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single { MainRepository(get()) }
}