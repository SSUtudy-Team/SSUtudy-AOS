package com.android.ssutudy.presentation.mypage.view

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentMyPageBottomSheetBinding
import com.android.ssutudy.presentation.mypage.model.MyPageBottomSheetDialogFragmentArgs
import com.android.ssutudy.presentation.mypage.viewmodel.MyPageBottomSheetDialogViewModel
import com.android.ssutudy.util.extensions.getParcelableFromBundle
import com.android.ssutudy.util.extensions.getSelectedItems
import com.android.ssutudy.util.extensions.makeToastMessage
import com.android.ssutudy.util.extensions.submitList
import com.android.ssutudy.util.publics.PublicString.GET_USER_DATA
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MyPageBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentMyPageBottomSheetBinding? = null
    private val binding: FragmentMyPageBottomSheetBinding
        get() = requireNotNull(_binding)
    private val viewModel by viewModels<MyPageBottomSheetDialogViewModel>()
    private var getDataAtMyPageFragment: (() -> Unit)? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getDataAtMyPageFragment = arguments?.getParcelableFromBundle(
            GET_USER_DATA, MyPageBottomSheetDialogFragmentArgs::class.java
        )?.getData
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyPageBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setObservers()
        setClickEvents()
    }

    private fun setClickEvents() {
        setCategoryBtnClickEvent()
    }

    private fun setCategoryBtnClickEvent() {
        binding.btnMyPageBottomSheet.setOnClickListener {
            val newCategoryList = binding.layoutMyPageBottomSheet.getSelectedItems()
            viewModel.updateInterestingCategory(newCategoryList)
        }
    }

    private fun setObservers() {
        setCategoryCountObserver()
        setUpdateCategorySuccessResponseObserver()
        setUpdateCategoryErrorResponseObserver()
    }

    private fun setUpdateCategoryErrorResponseObserver() {
        viewModel.getErrorResponse.observe(viewLifecycleOwner) {
            makeToastMessage(it)
        }
    }

    private fun setUpdateCategorySuccessResponseObserver() {
        viewModel.updateInterestingCategorySuccessResponse.observe(viewLifecycleOwner) {
            makeToastMessage(it.message)
            dismiss()
        }
    }

    private fun setCategoryCountObserver() {
        viewModel.categoryCount.observe(viewLifecycleOwner) {
            binding.btnMyPageBottomSheet.isEnabled = it >= 3
        }
    }

    private fun initViews() {
        initCategoryLayout()
    }

    private fun initCategoryLayout() {
        binding.layoutMyPageBottomSheet.submitList(
            true,
            resources.getStringArray(R.array.ssutudy_category).toList(),
            plusCategoryCount = viewModel.plusCountCategoryOne,
            minusCategoryCount = viewModel.minusCountCategoryOne
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        getDataAtMyPageFragment?.invoke()
    }
}