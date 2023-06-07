package com.android.ssutudy.presentation.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.databinding.FragmentMyPageBinding
import com.android.ssutudy.presentation.base.BaseDataBindingFragment
import com.android.ssutudy.presentation.login.view.LoginActivity
import com.android.ssutudy.presentation.mypage.viewmodel.MyPageViewModel

class MyPageFragment : BaseDataBindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel by viewModels<MyPageViewModel>()
    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickEvents()
        setData()
    }

    private fun setData() {

    }

    private fun setClickEvents() {
        setCategoryClickEvent()
        setLogoutTvClickEvent()
    }

    private fun setCategoryClickEvent() {
        binding.layoutMyPageCategory.setOnClickListener {

        }
    }

    private fun setLogoutTvClickEvent() {
        binding.tvMyPageLogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        SharedPreferences.clear()
        startActivity(Intent(requireActivity(), LoginActivity::class.java))
        if (!requireActivity().isFinishing) requireActivity().finish()
    }
}