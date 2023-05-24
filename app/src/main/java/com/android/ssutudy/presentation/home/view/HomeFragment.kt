package com.android.ssutudy.presentation.home.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentHomeBinding
import com.android.ssutudy.presentation.base.BaseDataBindingFragment
import com.android.ssutudy.presentation.home.viewmodel.HomeViewModel

class HomeFragment : BaseDataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spinnerHomeRecommendation.adapter =
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinner_ssutudy_recommendation,
                R.layout.item_sign_up_second_spinner_major
            )
    }

}