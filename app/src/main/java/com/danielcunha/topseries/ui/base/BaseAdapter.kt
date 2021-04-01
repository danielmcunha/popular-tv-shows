package com.danielcunha.topseries.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.KoinComponent

abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>(), KoinComponent {

    var onItemClick: ((T) -> Unit)? = null

    var viewModels = mutableListOf<T>()
        protected set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun clear() {
        viewModels.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )

        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        val model = viewModels[position]
        onItemClick?.let { clickEvent ->
            handleItemClick(holder, model, clickEvent)
        }
        holder.bindModel(model)
    }

    open fun handleItemClick(holder: BaseViewHolder<T>, model: T, clickEvent: (T) -> Unit) {
        holder.itemView.setOnClickListener {
            clickEvent.invoke(model)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    override fun getItemCount(): Int {
        return viewModels.count()
    }

    protected abstract fun getLayoutIdForPosition(position: Int): Int

    fun addItem(item: T) {
        viewModels.add(item)
        notifyItemChanged(viewModels.size - 1)
    }

    fun addItems(items: List<T>) {
        viewModels.addAll(items)

        notifyDataSetChanged()
    }

}