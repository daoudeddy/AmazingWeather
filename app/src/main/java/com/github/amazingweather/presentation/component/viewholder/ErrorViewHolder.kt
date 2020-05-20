package com.github.amazingweather.presentation.component.viewholder

import android.view.View
import androidx.annotation.StringRes
import com.github.amazingweather.presentation.base.BaseViewHolder
import com.github.amazingweather.presentation.component.view.ErrorUiView
import kotlinx.android.synthetic.main.error_item_layout.view.*

class ErrorViewHolder(itemView: View) : BaseViewHolder<ErrorUiView>(itemView) {

    override fun bindView(position: Int, item: ErrorUiView) {
        item.let {
            bindImage(it.drawableRes)
            bindTitle(it.titleRes)
            bindSubtitle(it.subtitleRes)
        }
    }

    override fun bindViewPayloads(position: Int, item: ErrorUiView, diffSet: Set<String>) {
        diffSet.forEach {
            when (it) {
                ErrorUiView.IMAGE_RES_CHANGED -> bindImage(item.drawableRes)
                ErrorUiView.TITLE_CHANGED -> bindTitle(item.titleRes)
                ErrorUiView.SUBTITLE_CHANGED -> bindSubtitle(item.subtitleRes)
            }
        }
    }

    private fun bindSubtitle(@StringRes subtitleRes: Int) {
        itemView.subtitleTv.text = itemView.context.getString(subtitleRes)
    }

    private fun bindTitle(@StringRes titleRes: Int) {
        itemView.titleTv.text = itemView.context.getString(titleRes)
    }

    private fun bindImage(drawableRes: Int) {
        itemView.imageIv.setImageResource(drawableRes)
    }

}