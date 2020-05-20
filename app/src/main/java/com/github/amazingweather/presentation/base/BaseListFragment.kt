package com.github.amazingweather.presentation.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.amazingweather.R
import com.github.amazingweather.core.ext.observe
import com.github.amazingweather.presentation.component.MarginItemDecoration
import com.github.amazingweather.presentation.component.UiAdapter
import com.github.amazingweather.presentation.model.Event
import kotlinx.android.synthetic.main.list_fragment.*
import javax.inject.Inject
import kotlin.reflect.KClass


/**
Ui adapter will be responsible to translate UiView models into views
UiAdapter custom built on top of RecyclerView.Adapter along with DiffUtil
 */

abstract class BaseListFragment<State : Any, ViewModelClass : BaseViewModel<State, *, *>>(
    private val clazz: KClass<ViewModelClass>
) : BaseFragment() {


    open val supportSwipeToRefresh: Boolean = true

    val mAdapter: UiAdapter = UiAdapter()

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val mViewModel: ViewModelClass by lazy {
        viewModelFactory.create(clazz.java)
    }

    open fun onStateChanged(baseRefreshableListFragmentState: BaseState<State>) {}

    open fun getViewModelDynamicParams(): Array<out Any> = emptyArray()

    open suspend fun onNewCommand(it: BaseEvent) {}

    override val layoutId: Int = R.layout.list_fragment

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(mViewModel.getLiveDataState(), ::onNewState)
        observe(mViewModel.getCommandsLiveData(), ::onNewCommand)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSwipeToRefresh()
    }

    private fun initSwipeToRefresh() {
        swipeRefreshLayout?.apply {
            isEnabled = supportSwipeToRefresh
            setOnRefreshListener { mViewModel.fetchData(true) }
        }
    }

    private fun initRecyclerView() {
        recyclerView?.apply {
            layoutManager = GridLayoutManager(context, UiAdapter.SPAN_COUNT).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int =
                        mAdapter.getItem(position)?.spanSize ?: UiAdapter.SPAN_COUNT
                }
            }
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.margin).toInt()
                )
            )

            val swipeToDismissTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
                    ItemTouchHelper.LEFT
                ) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(
                        viewHolder: RecyclerView.ViewHolder,
                        direction: Int
                    ) {
                        // callback for swipe to dismiss, removing item from data and adapter
                        mViewModel.onItemRemoved(viewHolder.adapterPosition)
                    }
                })

            swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)
            adapter = mAdapter
        }
    }

    // Handle base state rendering
    private fun onNewState(baseRefreshableListFragmentState: BaseState<State>) {
        lifecycleScope.launchWhenStarted {
            baseRefreshableListFragmentState.let {
                swipeRefreshLayout?.isRefreshing = it.refreshing
                recyclerView.post {
                    mAdapter.submitList(it.list)
                }
                onStateChanged(it)
            }
        }
    }

    private fun onNewCommand(singleEvent: Event<BaseEvent>?) {
        lifecycleScope.launchWhenStarted {
            singleEvent?.getContentIfNotHandled()?.let {
                onNewCommand(it)
            }
        }
    }
}

