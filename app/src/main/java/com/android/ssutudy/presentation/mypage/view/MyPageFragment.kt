package com.android.ssutudy.presentation.mypage.view

import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.ssutudy.databinding.FragmentMyPageBinding
import com.android.ssutudy.presentation.base.BaseViewBindingFragment

class MyPageFragment : BaseViewBindingFragment<FragmentMyPageBinding>() {
    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentMyPageBinding {
        return FragmentMyPageBinding.inflate(inflater, container, false)
    }

}