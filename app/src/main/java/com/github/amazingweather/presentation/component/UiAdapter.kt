package com.github.amazingweather.presentation.component

import android.view.ViewGroup
import androidx.recyclerview.widget.*
import com.github.amazingweather.presentation.base.BaseUiView
import com.github.amazingweather.presentation.base.BaseViewHolder

class UiAdapter : RecyclerView.Adapter<BaseViewHolder<BaseUiView>>() {

    companion object {
        const val SPAN_COUNT = 6
    }

    private val currentList: List<BaseUiView> get() = mDiffer.currentList

    private val mDiffer by lazy {
        val listUpdateCallBack = BaseAdapterListUpdateCallback()
        val diffCallBack = BaseAdapterDiffCallback()
        val config: AsyncDifferConfig<BaseUiView> = AsyncDifferConfig.Builder(diffCallBack).build()
        AsyncListDiffer(listUpdateCallBack, config)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseUiView> = ViewHolderFactory.getViewHolder(parent, viewType)

    override fun getItemViewType(position: Int): Int =
        getItem(position)?.itemViewType?:ViewHolderFactory.EMPTY

    override fun getItemCount(): Int = currentList.size

    @Suppress("unchecked_cast")
    override fun onBindViewHolder(
        holder: BaseViewHolder<BaseUiView>,
        position: Int,
        payloads: MutableList<Any>
    ) {

        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }

        val diffSet: Set<String>? = payloads.firstOrNull() as? Set<String>?
        diffSet?.let { diff ->
            holder.bindClicksAnd(position) {
                bindViewPayloads(position, it, diff)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<BaseUiView>, position: Int) =
        holder.bindClicksAnd(position) {
            bindView(position, it)
        }

    private inline fun BaseViewHolder<BaseUiView>.bindClicksAnd(
        position: Int,
        func: BaseViewHolder<BaseUiView>.(BaseUiView) -> Unit) {

        getItem(position)?.let {
            itemView.setOnClickListener { _ ->
                it.onItemClicked(position)
            }
            func(it)
        }
    }

    fun submitList(list: List<BaseUiView>) {
        mDiffer.submitList(list)
    }

    inner class BaseAdapterDiffCallback : DiffUtil.ItemCallback<BaseUiView>() {

        override fun areItemsTheSame(oldItem: BaseUiView, newItem: BaseUiView) =
            oldItem.getIdentifier() == newItem.getIdentifier() && oldItem.spanSize == newItem.spanSize

        override fun areContentsTheSame(
            oldItem: BaseUiView,
            newItem: BaseUiView
        ) =
            oldItem.areContentsTheSame(newItem)

        override fun getChangePayload(
            oldItem: BaseUiView,
            newItem: BaseUiView
        ) =
            oldItem.getChangePayload(newItem)
    }

    inner class BaseAdapterListUpdateCallback : ListUpdateCallback {

        override fun onChanged(position: Int, count: Int, payload: Any?) {
            notifyItemRangeChanged(position, count, payload)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            notifyItemMoved(fromPosition, toPosition)
        }

        override fun onInserted(position: Int, count: Int) {
            notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            notifyItemRangeRemoved(position, count)
        }
    }

    fun getItem(position: Int): BaseUiView? = currentList.getOrNull(position)

}