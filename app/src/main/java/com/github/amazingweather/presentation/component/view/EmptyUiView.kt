package com.github.amazingweather.presentation.component.view

import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.component.viewholder.ViewHolderFactory

object EmptyUiView : BaseUiView() {

    override var itemViewType: Int =
        ViewHolderFactory.EMPTY

    override fun getIdentifier(): String = "EmptyUiModel"

    override fun areContentsTheSame(other: BaseUiView): Boolean = true

}