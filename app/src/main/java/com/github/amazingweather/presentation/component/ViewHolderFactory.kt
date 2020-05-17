package com.github.amazingweather.presentation.component

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.amazingweather.R
import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.base.BaseViewHolder

/** This class returns the right view holder for the right type */

object ViewHolderFactory {

    const val EMPTY = 0
    const val ERROR = 1

    inline fun <reified I : BaseUiView, reified T : BaseViewHolder<I>> getViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): T {
        val layoutRest = getLayoutResFromViewType(viewType)
        val view = LayoutInflater.from(parent.context).inflate(layoutRest, null)

        return when (viewType) {

            else -> EmptyViewHolder(view)
        } as T
    }

    fun getLayoutResFromViewType(viewType: Int) = when (viewType) {
        else -> R.layout.empty_layout
    }
}