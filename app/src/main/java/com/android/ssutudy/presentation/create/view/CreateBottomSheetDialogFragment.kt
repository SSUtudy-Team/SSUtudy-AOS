package com.android.ssutudy.presentation.create.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentCreateBottomSheetBinding
import com.android.ssutudy.presentation.create.viewmodel.CreateViewModel
import com.android.ssutudy.util.extensions.getSelectedItems
import com.android.ssutudy.util.extensions.makeToastMessage
import com.android.ssutudy.util.extensions.submitList
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CreateBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentCreateBottomSheetBinding? = null
    private val binding: FragmentCreateBottomSheetBinding
        get() = requireNotNull(_binding)
    private val viewModel by activityViewModels<CreateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreateBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.resetCountCategory()
        initViews()
        setObservers()
        setClickEvents()
    }

    private fun setClickEvents() {
        binding.btnCreateBottomSheet.setOnClickListener {
            if (viewModel.countCategory.value == null) return@setOnClickListener
            if (viewModel.countCategory.value!! <= 5) {
                viewModel.setCategoryList(
                    binding.layoutCreateBottomSheet.getSelectedItems()
                )
                dismiss()
            } else makeToastMessage("최대 5개까지만 선택해주세요")
        }
    }

    private fun setObservers() {
        viewModel.countCategory.observe(viewLifecycleOwner) { countCategory ->
            binding.btnCreateBottomSheet.isEnabled = countCategory != 0
        }
    }

    private fun initViews() {
        initCategoryList()
    }

    private fun initCategoryList() {
        binding.layoutCreateBottomSheet.submitList(
            clickable = true,
            resources.getStringArray(R.array.ssutudy_category).toList(),
            plusCategoryCount = viewModel.plusCountCategoryOne,
            minusCategoryCount = viewModel.minusCountCategoryOne
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}