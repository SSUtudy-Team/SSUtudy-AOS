package com.android.ssutudy.presentation.create.view

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivityCreateBinding
import com.android.ssutudy.presentation.base.BaseDataBindingActivity
import com.android.ssutudy.presentation.create.viewmodel.CreateViewModel

class CreateActivity : BaseDataBindingActivity<ActivityCreateBinding>(R.layout.activity_create) {
    private val viewModel by viewModels<CreateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        setClickEvents()
    }

    private fun setClickEvents() {
        setCloseBtnClickEvent()

    }

    private fun setCloseBtnClickEvent() {
        binding.ivCreateClose.setOnClickListener {
            if (!isFinishing) finish()
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
            this,
            R.array.spinner_create_participants,
            R.layout.item_sign_up_second_spinner_major
        )
    }

    private fun initSubjectSpinner() {
        binding.spinnerCreateSubject.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_create_subject,
            R.layout.item_sign_up_second_spinner_major
        )
    }

    private fun initMajorSpinner() {
        binding.spinnerCreateMajor.adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_create_major,
            R.layout.item_sign_up_second_spinner_major
        )
    }

    private fun initCollegeSpinner() {
        binding.spinnerCreateCollege.adapter = ArrayAdapter.createFromResource(
            applicationContext,
            R.array.spinner_create_college,
            R.layout.item_sign_up_second_spinner_grade
        )
    }

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }
}