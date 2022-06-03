package com.example.eventmanagerapplication.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventmanagerapplication.databinding.ItemMyEventBinding
import com.example.eventmanagerapplication.model.database.entity.MyEventDTO

class MyEventsAdapter: RecyclerView.Adapter<MyEventsAdapter.MyEventsViewHolder>() {

    inner class MyEventsViewHolder(val binding: ItemMyEventBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<MyEventDTO>(){
        override fun areItemsTheSame(
            oldItem: MyEventDTO,
            newItem: MyEventDTO
        ): Boolean {
            return oldItem.event_id == newItem.event_id
        }

        override fun areContentsTheSame(
            oldItem: MyEventDTO,
            newItem: MyEventDTO
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEventsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMyEventBinding.inflate(inflater, parent, false)
        return MyEventsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyEventsViewHolder, position: Int) {
        val event = differ.currentList[position]

        with(holder.binding){

            nameTv.text = event.title
            dateTv.text = event.start_date

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}