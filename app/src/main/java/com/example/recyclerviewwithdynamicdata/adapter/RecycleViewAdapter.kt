/**
 * This class loads the data into recycle view
 */
package com.example.recyclerviewwithdynamicdata.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewwithdynamicdata.R
import com.example.recyclerviewwithdynamicdata.network.models.Row

/**
 * Takes the dataList and loads into recycle view
 *
 * @property dataList
 */
class RecycleViewAdapter(private val dataList: List<Row>) :
    RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_list_view, parent, false)
        return ViewHolder(
            v
        )
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(dataList[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return dataList.size
    }

    //the class is holding the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(data: Row) {
            val textDescription = itemView.findViewById(R.id.description) as TextView
            val textTitle = itemView.findViewById(R.id.title) as TextView
            val imageView = itemView.findViewById(R.id.image_href) as ImageView
            var imageURL = data.imageHref
            textDescription.text = data.description
            textTitle.text = data.title
            imageURL?.let {
                val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
                Glide.with(imageView.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(imageView)
            }
        }
    }
}