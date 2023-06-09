package com.android.ssutudy.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.ssutudy.data.remote.model.ResponseHomeDto.Data.JoinStudy
import com.android.ssutudy.databinding.ItemMySsutudyContentBinding
import com.android.ssutudy.databinding.ItemMySsutudyHeaderBinding
import com.android.ssutudy.util.DiffUtilCallback

class MyStudyAdapter(private val startCreateActivity: () -> Unit) :
    ListAdapter<JoinStudy, RecyclerView.ViewHolder>(DiffUtilCallback<JoinStudy>()) {
    override fun getItemViewType(position: Int): Int {
        return if (position == HEADER) HEADER
        else CONTENT
    }

    class MyStudyHeaderViewHolder(
        private val binding: ItemMySsutudyHeaderBinding,
        private val startCreateActivity: () -> Unit,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind() {
            binding.root.setOnClickListener {
                startCreateActivity.invoke()
            }
        }
    }

    class MyStudyContentViewHolder(private val binding: ItemMySsutudyContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: JoinStudy) {
            binding.tvItemMyStudy.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER) {
            val binding = ItemMySsutudyHeaderBinding.inflate(LayoutInflater.from(parent.context))
            MyStudyHeaderViewHolder(binding, startCreateActivity)
        } else {
            val binding = ItemMySsutudyContentBinding.inflate(LayoutInflater.from(parent.context))
            MyStudyContentViewHolder(binding)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == HEADER) (holder as? MyStudyHeaderViewHolder)?.onBind()
            ?: Exception("holder can't cast by MyStudyContentViewHolder")
        else (holder as? MyStudyContentViewHolder)?.onBind(currentList[position])
            ?: Exception("holder can't cast by MyStudyContentViewHolder")
    }

    companion object {
        const val HEADER = 0
        const val CONTENT = 1
    }
}