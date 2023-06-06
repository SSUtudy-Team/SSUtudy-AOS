package com.android.ssutudy.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentSignUpSecondBinding
import com.android.ssutudy.presentation.signup.viewmodel.SignUpViewModel
import com.android.ssutudy.util.extensions.getSelectedItems
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
                activityViewModel.signUp(
                    binding.layoutSignUpSecondCategory.getSelectedItems()
                )
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
        binding.spinnerSignUpSecondMajor.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                activityViewModel.major.value = resources.getStringArray(R.array.spinner_major)[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

    private fun initGradeSpinner() {
        binding.spinnerSignUpSecondGrade.adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.spinner_grade, R.layout.item_sign_up_second_spinner_grade
        )
        binding.spinnerSignUpSecondGrade.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                activityViewModel.grade.value = p2 + 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }

    private fun initFlexboxLayout() {
        binding.layoutSignUpSecondCategory.submitList(
            clickable = true,
            itemList = resources.getStringArray(R.array.ssutudy_category).toList(),
            plusCategoryCount = activityViewModel.plusCountCategoryOne,
            minusCategoryCount = activityViewModel.minusCountCategoryOne
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}