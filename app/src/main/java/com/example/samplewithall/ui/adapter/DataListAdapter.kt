package com.example.samplewithall.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.samplewithall.R
import com.example.samplewithall.models.Row
import com.log4k.d
import kotlinx.android.synthetic.main.recycle_list_view.view.*


class DataListAdapter(
    list: List<Row>,
    private val interaction: Interaction? = null
) : RecyclerView.Adapter<DataListAdapter.ViewHolder>() {

    private val mutableDataList = mutableListOf<Row>()

    init {
        mutableDataList.addAll(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycle_list_view, parent, false)
        return ViewHolder(
            view,
            interaction
        )
    }

    override fun getItemCount() = mutableDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = mutableDataList[position])
    }

    fun swap(list: List<Row>) {
        val diffCallback = DiffCallback(this.mutableDataList, list)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.mutableDataList.clear()
        this.mutableDataList.addAll(list)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Row) {
            itemView.description.text = item.description
            itemView.title_value.text = item.title

            d("Inside View Holder bind : ${item.imageHref}")
            val imageURL = item.imageHref
            imageURL?.let {
                val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
                Glide.with(itemView.image_href.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(itemView.image_href)
                }
            //Handle item click
            itemView.setOnClickListener { interaction?.onItemSelected(adapterPosition, item) }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Row)
    }
}