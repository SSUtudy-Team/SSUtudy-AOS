package com.android.ssutudy.presentation.mypage.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.databinding.FragmentMyPageBinding
import com.android.ssutudy.presentation.base.BaseViewBindingFragment
import com.android.ssutudy.presentation.login.view.LoginActivity

class MyPageFragment : BaseViewBindingFragment<FragmentMyPageBinding>() {
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMyPageBinding {
        return FragmentMyPageBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickEvents()
    }

    private fun setClickEvents() {
        setLogoutTvClickEvent()
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