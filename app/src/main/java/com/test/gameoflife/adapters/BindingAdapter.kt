package com.test.gameoflife.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.test.gameoflife.viewModels.CellViewModel

@BindingAdapter("recyclerViewData")
fun bindItemViewModels(recyclerView: RecyclerView, cellViewModel: List<CellViewModel>?) {
    val adapter = getOrCreateAdapter(recyclerView)
    adapter.updateItems(cellViewModel)
}

private fun getOrCreateAdapter(recyclerView: RecyclerView): CellRecyclerViewAdapter {
    return if (recyclerView.adapter != null && recyclerView.adapter is CellRecyclerViewAdapter) {
        recyclerView.adapter as CellRecyclerViewAdapter
    } else {
        val bindableRecyclerAdapter = CellRecyclerViewAdapter()
        recyclerView.adapter = bindableRecyclerAdapter
        bindableRecyclerAdapter
    }
}