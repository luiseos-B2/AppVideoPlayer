package com.freecast.thatmovieapp

import android.app.Application
import com.freecast.thatmovieapp.core.di.retrofitModule
import com.freecast.thatmovieapp.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ThatMovieAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ThatMovieAppApplication)
            modules(listOf(
                retrofitModule,
                homeModule
            ))
        }

    }

}