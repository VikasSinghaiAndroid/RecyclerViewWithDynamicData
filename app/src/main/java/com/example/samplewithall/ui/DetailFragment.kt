package com.example.samplewithall.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.samplewithall.R
import com.log4k.d
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

            d("Arguments  title : $title  and row items : ${rowItems.toString()}")

            main_title.text = title.toString()
            subTitle_value.text = rowItems?.title
            description_value.text = rowItems?.description

            val imageURL = rowItems?.imageHref
            imageURL?.let {
                val imgUri = imageURL.toUri().buildUpon().scheme("https").build()
                Glide.with(profile.context)
                    .load(imgUri)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(profile)
            }
        }
    }

}