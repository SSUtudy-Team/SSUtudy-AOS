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
import com.android.ssutudy.presentation.mypage.model.MyPageBottomSheetDialogFragmentArgs
import com.android.ssutudy.presentation.mypage.viewmodel.MyPageViewModel
import com.android.ssutudy.util.extensions.makeToastMessage
import com.android.ssutudy.util.publics.PublicString.GET_USER_DATA

class MyPageFragment : BaseDataBindingFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {
    private val viewModel by viewModels<MyPageViewModel>()

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }

    private fun setData() {
        viewModel.getUserData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickEvents()
        setObservers()
        setData()
    }

    private fun setObservers() {
        initErrorResponseObserver()
        initSuccessResponseObserver()
    }

    private fun initSuccessResponseObserver() {
        viewModel.updateInterestingCategorySuccessResponse.observe(viewLifecycleOwner) {
            makeToastMessage(it.message)
            setData()
        }
    }

    private fun initErrorResponseObserver() {
        viewModel.getErrorResponse.observe(viewLifecycleOwner) {
            makeToastMessage(it)
        }
    }

    private fun setClickEvents() {
        setCategoryClickEvent()
        setLogoutTvClickEvent()
    }

    private fun setCategoryClickEvent() {
        binding.layoutMyPageCategory.setOnClickListener {
            val dialog = MyPageBottomSheetDialogFragment()
            val args = Bundle()
            args.putParcelable(GET_USER_DATA, MyPageBottomSheetDialogFragmentArgs {
                viewModel.getUserData()
            })
            dialog.arguments = args
            dialog.show(requireActivity().supportFragmentManager, dialog.tag)
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