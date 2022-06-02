package com.example.eventmanagerapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagerapplication.databinding.ItemEventBinding
import com.example.eventmanagerapplication.databinding.ItemFavouriteBinding
import com.example.eventmanagerapplication.model.database.entity.EventDTO

class FavouriteAdapter: RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    inner class FavouriteViewHolder(val binding: ItemFavouriteBinding): RecyclerView.ViewHolder(binding.root)

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFavouriteBinding.inflate(inflater, parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val event = differ.currentList[position]
        with(holder.binding){
            titleTv.text = event.title
            dateTv.text = event.start_date

            holder.itemView.setOnClickListener {
                onItemClickListener?.let { it1 -> it1(event) }
            }
        }
    }

    private var onItemClickListener: ((EventDTO) -> Unit)? = null

    fun setOnItemClickListener(listener:(EventDTO) -> Unit){
        onItemClickListener = listener
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}