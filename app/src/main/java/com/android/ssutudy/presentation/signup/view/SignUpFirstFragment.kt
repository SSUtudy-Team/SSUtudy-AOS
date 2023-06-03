package com.android.ssutudy.presentation.signup.view

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentSignUpFirstBinding
import com.android.ssutudy.listeners.signup.OnSignUpNextButtonClickListener
import com.android.ssutudy.presentation.base.BaseDataBindingFragment
import com.android.ssutudy.presentation.signup.viewmodel.SignUpViewModel

class SignUpFirstFragment :
    BaseDataBindingFragment<FragmentSignUpFirstBinding>(R.layout.fragment_sign_up_first) {
    private var onSignUpNextButtonClickListener: OnSignUpNextButtonClickListener? = null
    private val activityViewModel by activityViewModels<SignUpViewModel>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSignUpNextButtonClickListener = context as? OnSignUpNextButtonClickListener
    }

    override fun bindViewModelWithBinding() {
        binding.vm = activityViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setNextBtnClickEvent()
    }

    private fun observe(liveData: LiveData<Boolean>, action: (Boolean) -> Unit) {
        liveData.observe(viewLifecycleOwner) {
            liveData.value?.let { value -> action.invoke(value) }
        }
    }

    private fun setNextBtnClickEvent() {
        binding.btnSignUpFirst.setOnClickListener {
            onSignUpNextButtonClickListener?.setNextFragment()
                ?: NullPointerException("onSignUpNextButtonClickListener is null")
        }
    }

    private fun isAbleToMoveNextSignUpFragment(): Boolean =
        activityViewModel.isAbleToNavigateNextPage()
}