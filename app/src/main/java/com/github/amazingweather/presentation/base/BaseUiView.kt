package com.github.amazingweather.presentation.base

import com.github.amazingweather.presentation.component.UiAdapter.Companion.SPAN_COUNT
import com.github.amazingweather.presentation.component.ViewHolderFactory

/** Any view to be rendered must extend this class so the Ui adapter knows how to render */

abstract class BaseUiView {

    open var spanSize: Int = SPAN_COUNT

    open var onItemClicked: (itemPosition: Int) -> Unit = {}

    open var itemViewType: Int = ViewHolderFactory.EMPTY

    abstract fun getIdentifier(): String

    abstract fun areContentsTheSame(other: BaseUiView): Boolean

    open fun getChangePayload(other: BaseUiView): Set<String> = emptySet()

    open var doAction: (action: () -> Unit) -> Unit = {}
}