package com.android.ssutudy.presentation.create.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivityCreateBinding
import com.android.ssutudy.presentation.base.BaseDataBindingActivity
import com.android.ssutudy.presentation.create.viewmodel.CreateViewModel

class CreateActivity : BaseDataBindingActivity<ActivityCreateBinding>(R.layout.activity_create) {
    private val viewModel by viewModels<CreateViewModel>()
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }
}