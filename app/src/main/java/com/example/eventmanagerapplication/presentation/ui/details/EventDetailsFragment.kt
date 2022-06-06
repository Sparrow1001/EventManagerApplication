package com.example.eventmanagerapplication.presentation.ui.details

import android.content.ContentValues
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.eventmanagerapplication.databinding.FragmentEventDetailsBinding
import com.example.eventmanagerapplication.model.database.entity.MyEventDTO
import com.example.eventmanagerapplication.model.network.api.EventDetailsApiResponse
import com.example.eventmanagerapplication.presentation.MainActivity
import com.example.eventmanagerapplication.utils.Resource
import com.example.eventmanagerapplication.viewmodel.EventDetailViewModel


class EventDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEventDetailsBinding
    private lateinit var viewModel: EventDetailViewModel
    private val args: EventDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).detailViewModel

        viewModel.getEventDetails(args.event.event_id)

        binding.likeBt.setOnClickListener {
            viewModel.saveEvent(args.event)
        }

        binding.buyTicketBt.setOnClickListener {
            setupDialog()
            viewModel.buyTicket(MyEventDTO(
                null,
                args.event.start_date,
                args.event.imagesUrl,
                args.event.title,
                args.event.event_id
            ))
        }


        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.eventDetails.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let { eventsResponse ->

                        setData(eventsResponse)

                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(ContentValues.TAG, "An error occured: $message")
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading -> {
                }
            }
        })
    }

    private fun setData(eventsResponse: EventDetailsApiResponse) {
        binding.nameTextView.text = eventsResponse.title

        Glide.with(binding.imageView).load(eventsResponse.images[0].image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            }).into(binding.imageView)
        val description = eventsResponse.description.replace(Regex("[</p>]"), "")
        var descriptionBody = eventsResponse.body_text.replace(Regex(("[^\\w\\d,. ]")), "")

        val regex = Regex("[A-Za-z]")
        descriptionBody = regex.replace(descriptionBody, "")
        descriptionBody.replace(Regex(("([\\s]{2,10})")) ,"")
        descriptionBody.replace(Regex(("([.]{2,10})")) ,"")
        binding.descriptionTextView.text = description
        binding.descriptionBodyTextView.text = descriptionBody

        binding.ageTextView.text = eventsResponse.age_restriction
        if(eventsResponse.price.equals("")){
            binding.priceTextView.text = "Цена: Бесплатно"
        } else {
            binding.priceTextView.text = "Цена: ${eventsResponse.price}"
        }
        binding.tagsTextView.text = eventsResponse.tags.toString()
    }

    private fun setupDialog() {
        activity?.supportFragmentManager?.let { MyCustomDialog().show(it, "MyCustomFragment") }
    }


}