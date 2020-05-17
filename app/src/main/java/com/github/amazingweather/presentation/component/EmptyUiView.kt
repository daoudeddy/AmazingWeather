package com.github.amazingweather.presentation.component

import com.github.amazingweather.presentation.base.BaseUiView

object EmptyUiView : BaseUiView() {

    override var itemViewType: Int = ViewHolderFactory.EMPTY

    override fun getIdentifier(): String = "EmptyUiModel"

    override fun areContentsTheSame(other: BaseUiView): Boolean = true

}