package com.ahtabbasi.roomlist.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ahtabbasi.domain.models.Room

internal object RoomItemDiffUtil : DiffUtil.ItemCallback<Room>() {

    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        // preferably ask the api developers to provide a last updated field to optimize this
        return oldItem.hashCode() == newItem.hashCode()
    }
}