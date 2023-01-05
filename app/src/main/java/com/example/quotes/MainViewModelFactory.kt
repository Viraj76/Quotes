package com.example.quotes

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
class MainViewModelFactory(val context: Context) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T = MainViewModel(context) as T
}