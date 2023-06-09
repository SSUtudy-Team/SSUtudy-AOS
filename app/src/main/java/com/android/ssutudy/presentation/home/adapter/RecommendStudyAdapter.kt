package com.android.ssutudy.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.ssutudy.R
import com.android.ssutudy.data.remote.model.Study
import com.android.ssutudy.databinding.ItemRecommendationSsutudyBinding
import com.android.ssutudy.util.DiffUtilCallback
import com.android.ssutudy.util.publics.PublicString.END
import com.android.ssutudy.util.publics.PublicString.RECRUITING
import com.android.ssutudy.util.publics.PublicString.RECRUITMENT_COMPLETE

class RecommendStudyAdapter(private val startDetailActivity: (String) -> Unit) :
    ListAdapter<Study, RecommendStudyAdapter.RecommendViewHolder>(DiffUtilCallback<Study>()) {

    class RecommendViewHolder(
        private val binding: ItemRecommendationSsutudyBinding,
        private val startDetailActivity: (String) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Study) {
            item.apply {
                with(binding) {
                    tvItemRecommendationMajor.text = department
                    tvItemRecommendationTitle.text = title
                    tvItemRecommendationState.text = studyStatus
                    tvItemRecommendationClass.text = className
                    tvItemRecommendationContent.text = content
                    tvItemRecommendationParticipants.text = binding.root.context.getString(
                        R.string.current_max_participants,
                        curUserCount.toString(),
                        userCount.toString()
                    )
                    root.setOnClickListener {
                        startDetailActivity.invoke(item.studyId.toString())
                    }
                    when (studyStatus) {
                        RECRUITING -> tvItemRecommendationState.setTextColor(
                            ResourcesCompat.getColor(
                                binding.root.resources, R.color.sky_5ec2c4, null
                            )
                        )

                        RECRUITMENT_COMPLETE -> tvItemRecommendationState.setTextColor(
                            ResourcesCompat.getColor(
                                binding.root.resources, R.color.blue_009bcb, null
                            )
                        )

                        END -> tvItemRecommendationState.setTextColor(
                            ResourcesCompat.getColor(
                                binding.root.resources, R.color.gray_969696, null
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val binding = ItemRecommendationSsutudyBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return RecommendViewHolder(binding, startDetailActivity)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}