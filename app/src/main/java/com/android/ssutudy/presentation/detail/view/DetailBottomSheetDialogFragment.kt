package com.android.ssutudy.presentation.detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.android.ssutudy.databinding.FragmentDetailBottomSheetBinding
import com.android.ssutudy.presentation.detail.viewmodel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private var _binding: FragmentDetailBottomSheetBinding? = null
    private val binding: FragmentDetailBottomSheetBinding
        get() = requireNotNull(_binding)
    private val viewModel by activityViewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDetailBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickEvents()
    }

    private fun setClickEvents() {
        setEndRecruitmentClickEvent()
        setEndStudyClickEvent()
    }

    private fun setEndStudyClickEvent() {

    }

    private fun setEndRecruitmentClickEvent() {

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}