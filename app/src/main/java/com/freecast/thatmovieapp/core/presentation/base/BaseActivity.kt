package com.freecast.thatmovieapp.core.presentation.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    abstract fun updateContent(showProgress: Boolean)
}