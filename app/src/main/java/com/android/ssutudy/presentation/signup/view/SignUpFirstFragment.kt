package com.android.ssutudy.presentation.signup.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentSignUpFirstBinding
import com.android.ssutudy.listeners.signup.OnSignUpNextButtonClickListener
import com.android.ssutudy.presentation.signup.viewmodel.SignUpViewModel
import com.android.ssutudy.util.extensions.makeToastMessage

class SignUpFirstFragment : Fragment() {
    private var _binding: FragmentSignUpFirstBinding? = null
    private val binding: FragmentSignUpFirstBinding
        get() = requireNotNull(_binding)
    private var onSignUpNextButtonClickListener: OnSignUpNextButtonClickListener? = null
    private val activityViewModel by activityViewModels<SignUpViewModel>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        onSignUpNextButtonClickListener = context as? OnSignUpNextButtonClickListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return initDataBinding(inflater, container)
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sign_up_first, container, false
        )
        binding.vm = activityViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        setNextBtnClickEvent()
    }

    private fun initObservers() {
        observe(activityViewModel.isNameValid) {
            setNextBtnColor()
        }
        observe(activityViewModel.isIdValid) {
            setNextBtnColor()
        }
        observe(activityViewModel.isPwValid) {
            setNextBtnColor()
        }
        observe(activityViewModel.isPwCheckSameWithPw) {
            setNextBtnColor()
        }
    }

    private fun observe(liveData: LiveData<Boolean>, action: (Boolean) -> Unit) {
        liveData.observe(viewLifecycleOwner) {
            liveData.value?.let { value -> action.invoke(value) }
        }
    }

    private fun setNextBtnColor() {
        if (activityViewModel.isAbleToNavigateNextPage()) binding.btnSignUpFirst.background =
            ContextCompat.getDrawable(
                requireActivity(), R.drawable.bg_solid_sky_9cd6d3_radius_10
            )
        else binding.btnSignUpFirst.background = ContextCompat.getDrawable(
            requireActivity(), R.drawable.bg_stroke_gray_d5d5d5_1_radius_10
        )
    }

    private fun setNextBtnClickEvent() {
        binding.btnSignUpFirst.setOnClickListener {
            if (isAbleToMoveNextSignUpFragment()) onSignUpNextButtonClickListener?.setNextFragment()
                ?: makeToastMessage("onSignUpNextButtonClickListener is null")
            else makeToastMessage("Not Yet ...")
        }
    }

    private fun isAbleToMoveNextSignUpFragment(): Boolean =
        activityViewModel.isAbleToNavigateNextPage()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}