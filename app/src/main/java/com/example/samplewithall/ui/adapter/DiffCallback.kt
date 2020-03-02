package com.example.samplewithall.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.samplewithall.models.Row


class DiffCallback(
    private val oldList: List<Row>,
    private val newList: List<Row>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
                && oldList[oldItemPosition].imageHref == newList[newItemPosition].imageHref
                && oldList[oldItemPosition].description == newList[newItemPosition].description
    }
}