package com.android.ssutudy.presentation.create.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivityCreateBinding
import com.android.ssutudy.presentation.base.BaseDataBindingActivity
import com.android.ssutudy.presentation.create.viewmodel.CreateViewModel
import com.android.ssutudy.util.extensions.makeToastMessage
import com.android.ssutudy.util.extensions.submitList

class CreateActivity : BaseDataBindingActivity<ActivityCreateBinding>(R.layout.activity_create) {
    private val viewModel by viewModels<CreateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        setClickEvents()
        setObservers()
    }

    private fun setObservers() {
        observeCategoryList()
        setCreateStudySuccessResponseObserver()
        setCreateStudyErrorResponseObserver()
    }

    private fun setCreateStudyErrorResponseObserver() {
        viewModel.createStudyErrorResponse.observe(this) {
            makeToastMessage(it)
        }
    }

    private fun setCreateStudySuccessResponseObserver() {
        viewModel.createStudySuccessResponse.observe(this) {
            makeToastMessage(it.message)
            setResult(RESULT_OK)
            if (!isFinishing) finish()
        }
    }

    private fun observeCategoryList() {
        viewModel.categoryList.observe(this) { categoryList ->
            binding.layoutCreateCategoryList.submitList(
                false, categoryList, backgroundResource = R.layout.view_category_of_interest
            )
        }
    }

    private fun setClickEvents() {
        setCloseBtnClickEvent()
        setCategoryClickEvent()
        setCompleteTvClickEvent()
    }

    private fun setCompleteTvClickEvent() {
        with(binding) {
            tvCreateComplete.setOnClickListener {
                viewModel.createStudy(
                    spinnerCreateCollege.selectedItem.toString(),
                    spinnerCreateMajor.selectedItem.toString(),
                    spinnerCreateSubject.selectedItem.toString(),
                    spinnerCreateParticipants.selectedItemPosition.plus(3)
                )
            }
        }
    }

    private fun setCategoryClickEvent() {
        binding.btnCreateCategoryInput.setOnClickListener {
            showBottomSheetDialog()
        }
        binding.layoutCreateCategoryList.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = CreateBottomSheetDialogFragment()
        bottomSheetDialog.show(supportFragmentManager, bottomSheetDialog.tag)
    }

    private fun setCloseBtnClickEvent() {
        binding.ivCreateClose.setOnClickListener {
            if (!isFinishing) finish()
            setResult(RESULT_CANCELED)
        }
    }

    private fun initViews() {
        initCollegeSpinner()
        initMajorSpinner()
        initSubjectSpinner()
        initParticipantsSpinner()
    }

    private fun initParticipantsSpinner() {
        binding.spinnerCreateParticipants.adapter = ArrayAdapter.createFromResource(
            this, R.array.spinner_create_participants, R.layout.item_sign_up_second_spinner_major
        )
    }

    private fun initSubjectSpinner() {
        binding.spinnerCreateSubject.adapter = ArrayAdapter.createFromResource(
            this, R.array.subject_list, R.layout.item_sign_up_second_spinner_major
        )
    }

    private fun initMajorSpinner() {
        binding.spinnerCreateMajor.adapter = ArrayAdapter.createFromResource(
            this, R.array.spinner_major, R.layout.item_sign_up_second_spinner_major
        )
    }

    private fun initCollegeSpinner() {
        binding.spinnerCreateCollege.adapter = ArrayAdapter.createFromResource(
            applicationContext,
            R.array.spinner_create_college,
            R.layout.item_sign_up_second_spinner_major
        )
    }

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }
}