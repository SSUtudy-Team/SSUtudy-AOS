package com.android.ssutudy.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentSignUpSecondBinding
import com.android.ssutudy.presentation.signup.viewmodel.SignUpViewModel
import com.android.ssutudy.util.extensions.submitList

class SignUpSecondFragment : Fragment() {
    private var _binding: FragmentSignUpSecondBinding? = null
    private val binding: FragmentSignUpSecondBinding
        get() = requireNotNull(_binding)
    private val activityViewModel by activityViewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignUpSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFlexboxLayout()
        initGradeSpinner()
        initMajorSpinner()
        observeCategoryCount()
        initSignUpBtnClickEvent()
    }

    private fun initSignUpBtnClickEvent() {
        binding.btnSignUpSecond.setOnClickListener {
            if (it.isSelected) {
                //TODO: SIGN UP 로직 구현
            }
        }
    }

    private fun observeCategoryCount() {
        activityViewModel.countCategory.observe(viewLifecycleOwner) {
            binding.btnSignUpSecond.isSelected = it >= 3
        }
    }

    private fun initMajorSpinner() {
        binding.spinnerSignUpSecondMajor.adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.spinner_major, R.layout.item_sign_up_second_spinner_major
        )
    }

    private fun initGradeSpinner() {
        binding.spinnerSignUpSecondGrade.adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.spinner_grade, R.layout.item_sign_up_second_spinner_grade
        )
    }

    private fun initFlexboxLayout() {
        binding.layoutSignUpSecondCategory.submitList(
            clickable = true,
            itemList = resources.getStringArray(R.array.ssutudy_category),
            plusCategoryCount = activityViewModel.plusCountCategoryOne,
            minusCategoryCount = activityViewModel.minusCountCategoryOne
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}