package com.github.amazingweather.presentation.component

import android.view.View
import com.github.amazingweather.presentation.base.BaseViewHolder

class EmptyViewHolder(view: View) : BaseViewHolder<EmptyUiView>(view) {
    override fun bindView(position: Int, item: EmptyUiView) {
    }

    override fun bindViewPayloads(position: Int, item: EmptyUiView, diffSet: Set<String>) {
    }
}