package com.test.gameoflife.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.test.gameoflife.R
import com.test.gameoflife.viewModels.CellViewModel

class CellRecyclerViewAdapter : RecyclerView.Adapter<BindableViewHolder>() {

    var cellItemViewModels: List<CellViewModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindableViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cell,
            parent,
            false
        )
        return BindableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableViewHolder, position: Int) {
        holder.bind(cellItemViewModels[position])
    }

    override fun getItemCount(): Int = cellItemViewModels.size

    fun updateItems(items: List<CellViewModel>?) {
        cellItemViewModels = items ?: emptyList()
        notifyDataSetChanged()
    }

}

class BindableViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cellViewModel: CellViewModel) {
        binding.setVariable(BR.cellViewModel, cellViewModel)
    }
}