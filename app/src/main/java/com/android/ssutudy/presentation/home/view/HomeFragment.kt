package com.android.ssutudy.presentation.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.data.remote.model.ResponseHomeDto
import com.android.ssutudy.databinding.FragmentHomeBinding
import com.android.ssutudy.presentation.base.BaseDataBindingFragment
import com.android.ssutudy.presentation.create.view.CreateActivity
import com.android.ssutudy.presentation.home.adapter.MyStudyAdapter
import com.android.ssutudy.presentation.home.adapter.RecommendStudyAdapter
import com.android.ssutudy.presentation.home.viewmodel.HomeViewModel
import com.android.ssutudy.util.extensions.makeToastMessage

class HomeFragment : BaseDataBindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()
    private var myStudyAdapter: MyStudyAdapter? = null
    private var recommendStudyAdapter: RecommendStudyAdapter? = null

    override fun onResume() {
        super.onResume()
        setData()
    }

    private fun setData() {
        viewModel.getHomeData()
    }

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapters()
        setClickEvents()
        initObservers()
    }

    private fun initObservers() {
        initSuccessResponseObserver()
        initErrorResponseObserver()
    }

    private fun initErrorResponseObserver() {
        viewModel.getHomeDataErrorResponse.observe(viewLifecycleOwner) {
            makeToastMessage(it)
        }
    }

    private fun initSuccessResponseObserver() {
        viewModel.getHomeDataSuccessResponse.observe(viewLifecycleOwner) {
            recommendStudyAdapter?.submitList(it.data.recommendStudy)
            val myStudyDummyList = listOf(
                ResponseHomeDto.Data.JoinStudy(
                    ""
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
            startActivity(Intent(requireContext(), CreateActivity::class.java))
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
    }

    private fun initRecommendStudyAdapter() {
        recommendStudyAdapter = RecommendStudyAdapter()
        binding.rvHomeRecommendation.adapter = recommendStudyAdapter
    }

    private fun initMyStudyAdapter() {
        myStudyAdapter =
            MyStudyAdapter { startActivity(Intent(requireContext(), CreateActivity::class.java)) }
        binding.rvHomeMySsutudy.adapter = myStudyAdapter
    }

    override fun onDestroyView() {
        myStudyAdapter = null
        recommendStudyAdapter = null
        super.onDestroyView()
    }
}