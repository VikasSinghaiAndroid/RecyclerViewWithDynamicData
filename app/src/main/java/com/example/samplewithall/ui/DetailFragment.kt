package com.example.samplewithall.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.example.samplewithall.R
import com.log4k.d
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_details.*

class DetailFragment : DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            val title = safeArgs.titleValue
            val rowItems = safeArgs.rowItems

            d("Arguments  title : $title  and row items Title : ${rowItems?.title}  Row Item description : ${rowItems?.description}  Row Image URL : ${rowItems?.imageHref}")

            main_title.text = title.toString()
            subTitle_value.text = rowItems?.title
            description_value.text = rowItems?.description

            val imageURL = rowItems?.imageHref
            if (imageURL.isNullOrEmpty() || imageURL.isNullOrBlank()) {
                profile.setImageResource(R.drawable.ic_broken_image)
            } else {
                val imgUri = imageURL.toUri().buildUpon().scheme("http").build()
                Picasso.get()
                    .load(imgUri)
                    .error(R.drawable.ic_broken_image)
                    .placeholder(R.drawable.loading_animation)
                    .into(profile, object : Callback {
                        override fun onSuccess() {
                            d("Picasso image loading success")
                        }

                        override fun onError(e: Exception?) {
                            d("Picasso image loading error : ${e?.message}")
                        }
                    })
            }
        }
    }

}