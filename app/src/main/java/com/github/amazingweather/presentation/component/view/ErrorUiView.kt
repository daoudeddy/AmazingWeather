package com.github.amazingweather.presentation.component.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.viewholder.ViewHolderFactory

data class ErrorUiView constructor(
    @DrawableRes val drawableRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val subtitleRes: Int
) : BaseUiView() {

    companion object {
        const val IMAGE_RES_CHANGED = "IMAGE_RES_CHANGED"
        const val TITLE_CHANGED = "TITLE_CHANGED"
        const val SUBTITLE_CHANGED = "SUBTITLE_CHANGED"
    }

    override var itemViewType: Int =
        ViewHolderFactory.ERROR_ITEM

    override fun getIdentifier() = "ErrorView"

    override fun areContentsTheSame(other: BaseUiView): Boolean =
        other is ErrorUiView && drawableRes == other.drawableRes
                && titleRes == other.titleRes && subtitleRes == other.subtitleRes

}