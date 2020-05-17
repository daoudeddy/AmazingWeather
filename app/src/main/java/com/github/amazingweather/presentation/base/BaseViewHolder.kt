package com.github.amazingweather.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<in T : BaseUiView>(
    itemView: View
) : LayoutContainer, RecyclerView.ViewHolder(itemView) {

    override val containerView: View? = itemView

    abstract fun bindView(position: Int, item: T)

    abstract fun bindViewPayloads(position: Int, item: T, diffSet: Set<String>)
}