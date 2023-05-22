package com.android.ssutudy.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseDataBindingActivity<DB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    AppCompatActivity() {

    lateinit var binding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getInflatedLayout())
    }

    private fun getInflatedLayout(): View {
        binding = DataBindingUtil.setContentView(this, layoutResId)
        return binding.root
    }
}