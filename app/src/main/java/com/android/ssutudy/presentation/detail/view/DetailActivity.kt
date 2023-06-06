package com.android.ssutudy.presentation.detail.view

import android.os.Bundle
import androidx.activity.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivityDetailBinding
import com.android.ssutudy.presentation.base.BaseDataBindingActivity
import com.android.ssutudy.presentation.detail.viewmodel.DetailViewModel
import com.android.ssutudy.util.extensions.submitList

class DetailActivity : BaseDataBindingActivity<ActivityDetailBinding>(R.layout.activity_detail) {
    private val viewModel by viewModels<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        setClickEvents()
    }

    private fun setClickEvents() {
        binding.ivDetailMenu.setOnClickListener {
            val bottomSheetDialogFragment = DetailBottomSheetDialogFragment()
            bottomSheetDialogFragment.show(supportFragmentManager, bottomSheetDialogFragment.tag)
        }
    }

    private fun initViews() {
        initCategory()
    }

    private fun initCategory() {
        binding.layoutDetailStudyCategory.submitList(
            clickable = false,
            itemList = resources.getStringArray(R.array.ssutudy_category_test).toList()
        )
    }

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }
}