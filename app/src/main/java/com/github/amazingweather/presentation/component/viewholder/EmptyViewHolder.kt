package com.github.amazingweather.presentation.component.viewholder

import android.view.View
import com.github.amazingweather.presentation.base.BaseViewHolder
import com.github.amazingweather.presentation.component.view.EmptyUiView

class EmptyViewHolder(view: View) : BaseViewHolder<EmptyUiView>(view) {
    override fun bindView(position: Int, item: EmptyUiView) {
    }

    override fun bindViewPayloads(position: Int, item: EmptyUiView, diffSet: Set<String>) {
    }
}