package com.android.ssutudy.util.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.android.ssutudy.R
import com.google.android.flexbox.FlexboxLayout

fun FlexboxLayout.submitList(itemList: List<String>) {
    itemList.forEach { text ->
        addTextview(text)
    }
}

fun FlexboxLayout.addTextview(textResource: String) {
    val textView =
        (LayoutInflater.from(context)
            .inflate(R.layout.view_category_of_interest, null) as TextView).apply {
            text = textResource
            setOnClickListener {
                it.isSelected = !it.isSelected
            }
        }

    val layoutParams = ViewGroup.MarginLayoutParams(
        ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT
    ).apply {
        rightMargin = context.dpToPx(12)
        bottomMargin = context.dpToPx(10)
    }

    addView(textView, layoutParams)
}