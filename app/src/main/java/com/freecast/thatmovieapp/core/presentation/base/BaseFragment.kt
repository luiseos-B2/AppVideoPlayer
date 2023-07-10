package com.freecast.thatmovieapp.core.presentation.base

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract fun updateContent(showProgress: Boolean)

}