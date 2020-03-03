package com.example.samplewithall.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.samplewithall.R
import com.example.samplewithall.models.Row
import com.log4k.d
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
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

            val imageURL = item.imageHref
            if (imageURL.isNullOrEmpty() || imageURL.isNullOrBlank()) {
                itemView.image_href.setImageResource(R.drawable.ic_broken_image)
            } else {
                val imgUri = imageURL.toUri().buildUpon().scheme("http").build()
                Picasso.get()
                    .load(imgUri)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
                    .into(itemView.image_href, object : Callback {
                        override fun onSuccess() {
                            d("Picasso Success")
                        }

                        override fun onError(e: Exception?) {
                            d("Picasso Error : ${e?.message}")
                        }
                    })
            }

            //Handle item click
            itemView.setOnClickListener { interaction?.onItemSelected(adapterPosition, item) }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Row)
    }
}