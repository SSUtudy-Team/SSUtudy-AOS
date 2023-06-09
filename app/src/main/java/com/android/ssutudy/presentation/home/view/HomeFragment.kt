package com.android.ssutudy.presentation.home.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.data.remote.model.ResponseHomeDto
import com.android.ssutudy.databinding.FragmentHomeBinding
import com.android.ssutudy.presentation.base.BaseDataBindingFragment
import com.android.ssutudy.presentation.create.view.CreateActivity
import com.android.ssutudy.presentation.detail.view.DetailActivity
import com.android.ssutudy.presentation.home.adapter.MyStudyAdapter
import com.android.ssutudy.presentation.home.adapter.RecommendStudyAdapter
import com.android.ssutudy.presentation.home.viewmodel.HomeViewModel
import com.android.ssutudy.util.extensions.makeToastMessage
import com.android.ssutudy.util.publics.PublicString.STUDY_ID

class HomeFragment : BaseDataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private var myStudyAdapter: MyStudyAdapter? = null
    private var recommendStudyAdapter: RecommendStudyAdapter? = null
    private lateinit var createActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var detailActivityResultLauncher: ActivityResultLauncher<Intent>

    private val startDetailActivity: (String) -> Unit = { studyId ->
        val intent = Intent(requireContext(), DetailActivity::class.java).apply {
            putExtra(STUDY_ID, studyId)
        }
        detailActivityResultLauncher.launch(intent)
    }

    private val startCreateActivity: () -> Unit = {
        createActivityResultLauncher.launch(
            Intent(
                requireActivity(), CreateActivity::class.java
            )
        )
    }

    private fun setData() {
        viewModel.getHomeData()
    }

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        initLaunchers()
        initAdapters()
        setClickEvents()
        initObservers()
    }

    private fun initLaunchers() {
        createActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.getHomeData()
            }
        }

        detailActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.getHomeData()
            }
        }
    }

    private fun initObservers() {
        initHomeResponseObserver()
        initHomeTimeResponseObserver()

    }

    private fun initHomeTimeResponseObserver() {
        initHomeTimeSuccessResponseObserver()
        initHomeTimeErrorResponseObserver()
    }

    private fun initHomeTimeErrorResponseObserver() {
        viewModel.getHomeTimeDataErrorResponse.observe(viewLifecycleOwner) {
            makeToastMessage(it)
        }
    }

    private fun initHomeTimeSuccessResponseObserver() {
        viewModel.getHomeTimeDataSuccessResponse.observe(viewLifecycleOwner) {
            recommendStudyAdapter?.submitList(it.data)
        }
    }

    private fun initHomeResponseObserver() {
        initHomeSuccessResponseObserver()
        initHomeErrorResponseObserver()
    }

    private fun initHomeErrorResponseObserver() {
        viewModel.getHomeDataErrorResponse.observe(viewLifecycleOwner) {
            makeToastMessage(it)
        }
    }

    private fun initHomeSuccessResponseObserver() {
        viewModel.getHomeDataSuccessResponse.observe(viewLifecycleOwner) {
            recommendStudyAdapter?.submitList(it.data.recommendStudy)
            val myStudyDummyList = listOf(
                ResponseHomeDto.Data.JoinStudy(
                    "", -1
                )
            )
            val myStudyList = myStudyDummyList + it.data.joinStudy
            myStudyAdapter?.submitList(myStudyList)
        }
    }

    private fun setClickEvents() {
        setCreateBtnClickEvent()
    }

    private fun setCreateBtnClickEvent() {
        binding.btnHomeCreate.setOnClickListener {
            createActivityResultLauncher.launch(
                Intent(
                    requireActivity(), CreateActivity::class.java
                )
            )
        }
    }

    private fun initAdapters() {
        initMyStudyAdapter()
        initRecommendStudyAdapter()
        initRecommendationSpinnerAdapter()
    }

    private fun initRecommendationSpinnerAdapter() {
        binding.spinnerHomeRecommendation.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_ssutudy_recommendation,
            R.layout.item_sign_up_second_spinner_major
        )

        binding.spinnerHomeRecommendation.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == RECOMMENDATION_ORDER) viewModel.getHomeData()
                else viewModel.getHomeTimeData()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun initRecommendStudyAdapter() {
        recommendStudyAdapter = RecommendStudyAdapter(startDetailActivity)
        binding.rvHomeRecommendation.adapter = recommendStudyAdapter
    }

    private fun initMyStudyAdapter() {
        myStudyAdapter = MyStudyAdapter(startCreateActivity, startDetailActivity)
        binding.rvHomeMySsutudy.adapter = myStudyAdapter
    }

    override fun onDestroyView() {
        myStudyAdapter = null
        recommendStudyAdapter = null
        super.onDestroyView()
    }

    companion object {
        const val RECOMMENDATION_ORDER = 0
        const val NEWEST = 1
    }
}