package com.danielcunha.topseries.ui.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.danielcunha.topseries.BR

class BaseViewHolder<in T>(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindModel(model: T) {
        binding.setVariable(BR.viewHolder, model)
        binding.executePendingBindings()
    }
}