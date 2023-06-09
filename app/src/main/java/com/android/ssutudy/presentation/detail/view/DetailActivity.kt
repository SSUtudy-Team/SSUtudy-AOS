package com.android.ssutudy.presentation.detail.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import com.android.ssutudy.R
import com.android.ssutudy.presentation.base.BaseDataBindingActivity
import com.android.ssutudy.presentation.detail.viewmodel.DetailViewModel
import com.android.ssutudy.util.extensions.makeToastMessage
import com.android.ssutudy.util.extensions.submitList
import com.android.ssutudy.util.publics.PublicString.END
import com.android.ssutudy.util.publics.PublicString.RECRUITING
import com.android.ssutudy.util.publics.PublicString.RECRUITMENT_COMPLETE
import com.android.ssutudy.util.publics.PublicString.STUDY_ID

class DetailActivity :
    BaseDataBindingActivity<com.android.ssutudy.databinding.ActivityDetailBinding>(R.layout.activity_detail) {
    private val viewModel by viewModels<DetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initData()
        initViews()
        initObservers()
        setClickEvents()
    }

    private fun initObservers() {
        initGetResultObservers()
        initEndResultObservers()
        initDeleteResultObservers()
        initJoinResultObservers()
        initGoOutResultObservers()
    }

    private fun initGoOutResultObservers() {
        viewModel.goOutSuccessResponse.observe(this) {
            makeToastMessage(it.message)
            viewModel.getStudy()
        }
        viewModel.goOutErrorResponse.observe(this) {
            makeToastMessage(it)
        }
    }

    private fun initJoinResultObservers() {
        viewModel.joinSuccessResponse.observe(this) {
            makeToastMessage(it.message)
            viewModel.getStudy()
        }
        viewModel.joinErrorResponse.observe(this) {
            makeToastMessage(it)
        }
    }

    private fun initDeleteResultObservers() {
        viewModel.deleteSuccessResponse.observe(this) {
            makeToastMessage(it.message)
            if (!isFinishing) finish()
        }
        viewModel.deleteErrorResponse.observe(this) {
            makeToastMessage(it)
        }
    }

    private fun initEndResultObservers() {
        viewModel.endSuccessResponse.observe(this) {
            makeToastMessage(it.message)
            viewModel.getStudy()
        }
        viewModel.endErrorResponse.observe(this) {
            makeToastMessage(it)
        }
    }

    private fun initGetResultObservers() {
        viewModel.getSuccessResponse.observe(this) {
            viewModel.studyStateDrawable.value = when (it.data.studyStatus) {
                RECRUITING -> {
                    ResourcesCompat.getDrawable(
                        resources, R.drawable.bg_solid_sky_5ec2c4_radius_4, null
                    )
                }

                RECRUITMENT_COMPLETE -> {
                    ResourcesCompat.getDrawable(
                        resources, R.drawable.bg_solid_blue_009bcb_radius_4, null
                    )
                }

                else -> {
                    ResourcesCompat.getDrawable(
                        resources, R.drawable.bg_solid_gray_969696_radius_4, null
                    )

                }
            }
            binding.btnDetailJoin.isEnabled = it.data.studyStatus != END
            binding.layoutDetailStudyCategory.submitList(
                clickable = false, itemList = it.data.categoryList
            )
        }
        viewModel.getErrorResponse.observe(this) {
            makeToastMessage(it)
        }
    }

    private fun initData() {
        viewModel.studyId = intent.getStringExtra(STUDY_ID)
            ?: throw Exception("intent.getStringExtra(STUDY_ID) is null")
        viewModel.getStudy()
    }

    private fun setClickEvents() {
        setlMenuIvClickEvent()
        setBackIvClickEvent()
        setJoinBtnClickEvent()
        setGoOutTvClickEvent()
    }

    private fun setGoOutTvClickEvent() {
        binding.tvDetailGoOut.setOnClickListener {
            viewModel.goOutStudy()
        }
    }

    private fun setJoinBtnClickEvent() {
        binding.btnDetailJoin.setOnClickListener {
            viewModel.joinStudy()
        }
    }

    private fun setBackIvClickEvent() {
        binding.ivDetailBack.setOnClickListener {
            if (!isFinishing) finish()
        }
    }

    private fun setlMenuIvClickEvent() {
        binding.ivDetailMenu.setOnClickListener {
            val bottomSheetDialogFragment = DetailBottomSheetDialogFragment()
            bottomSheetDialogFragment.show(
                supportFragmentManager, bottomSheetDialogFragment.tag
            )
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