package com.ahtabbasi.roomlist.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.R
import com.ahtabbasi.roomlist.databinding.ItemRoomBinding
import com.ahtabbasi.roomlist.presentation.navigation.RoomListNavigator
import com.ahtabbasi.roomlist.presentation.view.RoomListViewModel
import com.ahtabbasi.utils.loadImage

internal class RoomAdapter(
    private val viewModel: RoomListViewModel,
    private val navigator: RoomListNavigator,
) : ListAdapter<Room, RoomAdapter.ViewHolder>(RoomItemDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            viewModel,
            navigator,
            ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position))
    }

    class ViewHolder(
        private val viewModel1: RoomListViewModel,
        private val navigator: RoomListNavigator,
        private val binding: ItemRoomBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindItem(room: Room) {

            val spotsLeft = binding.root.context.getString(
                R.string.spots_remaining
            ).format(room.spots)

            binding.tvName.text = room.name
            binding.tvSpots.text = spotsLeft
            binding.bBook.isEnabled = !room.isBooked && room.spots > 0
            if (room.isBooked) {
                binding.bBook.text = binding.root.context.getString(R.string.already_booked)
            } else {
                binding.bBook.text = binding.root.context.getString(R.string.book)
            }
            binding.bBook.setOnClickListener {
                viewModel1.bookRoom(room)
            }
            binding.ivRoomImage.loadImage(room.photo)
            binding.roomRowItem.setOnClickListener {
                navigator.navigateToRoomDetail(binding.root, room)
            }
        }
    }
}