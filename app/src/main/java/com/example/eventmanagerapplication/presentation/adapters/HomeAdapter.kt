package com.example.eventmanagerapplication.presentation.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.eventmanagerapplication.R
import com.example.eventmanagerapplication.databinding.FragmentHomeBinding
import com.example.eventmanagerapplication.databinding.ItemEventBinding
import com.example.eventmanagerapplication.model.database.entity.EventDTO
import com.example.eventmanagerapplication.model.network.api.EventApiResponse

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(val binding: ItemEventBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<EventDTO>(){
        override fun areItemsTheSame(
            oldItem: EventDTO,
            newItem: EventDTO
        ): Boolean {
            return oldItem.event_id == newItem.event_id
        }

        override fun areContentsTheSame(
            oldItem: EventDTO,
            newItem: EventDTO
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemEventBinding.inflate(inflater, parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val event = differ.currentList[position]
        with(holder.binding) {
            nameTv.text = event.title
            dateTv.text = event.start_date
            priceTv.text = "Бесплатно"

//            Glide.with(imageIv).load(event.imagesUrl?.get(0))
//                .listener(object : RequestListener<Drawable>{
//                    override fun onLoadFailed(
//                        e: GlideException?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        return false
//                    }
//
//                    override fun onResourceReady(
//                        resource: Drawable?,
//                        model: Any?,
//                        target: Target<Drawable>?,
//                        dataSource: DataSource?,
//                        isFirstResource: Boolean
//                    ): Boolean {
//                        return false
//                    }
//
//                }).into(imageIv)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}