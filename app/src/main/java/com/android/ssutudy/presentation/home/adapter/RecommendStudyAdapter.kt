package com.android.ssutudy.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.ssutudy.R
import com.android.ssutudy.data.remote.model.ResponseHomeDto.Data.RecommendStudy
import com.android.ssutudy.databinding.ItemRecommendationSsutudyBinding
import com.android.ssutudy.util.DiffUtilCallback

class RecommendStudyAdapter :
    ListAdapter<RecommendStudy, RecommendStudyAdapter.RecommendViewHolder>(DiffUtilCallback<RecommendStudy>()) {

    class RecommendViewHolder(private val binding: ItemRecommendationSsutudyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: RecommendStudy) {
            item.apply {
                with(binding) {
                    tvItemRecommendationMajor.text = department
                    tvItemRecommendationTitle.text = title
                    tvItemRecommendationState.text = studyStatus
                    tvItemRecommendationClass.text = className
                    tvItemRecommendationContent.text = content
                    tvItemRecommendationParticipants.text =
                        binding.root.context.getString(
                            R.string.current_max_participants,
                            curUserCount.toString(),
                            userCount.toString()
                        )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemRecommendationSsutudyBinding.inflate(LayoutInflater.from(parent.context))
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}