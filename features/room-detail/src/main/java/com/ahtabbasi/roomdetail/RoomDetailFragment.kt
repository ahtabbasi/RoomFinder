package com.ahtabbasi.roomdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomdetail.databinding.FragmentRoomDetailBinding
import com.ahtabbasi.utils.loadImage

class RoomDetailFragment : Fragment() {

    private val args: RoomDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val binding = FragmentRoomDetailBinding.inflate(inflater, container, false)
        setupUI(binding)
        return binding.root
    }

    private fun setupUI(binding: FragmentRoomDetailBinding) {
        val room = args.roomDetail

        if (room == null) {
            findNavController().navigateUp()
            return
        }

        val spotsLeft = binding.root.context.getString(
            R.string.spots_remaining
        ).format(room.spots)

        binding.tvName.text = room.name
        binding.tvSpots.text = spotsLeft
        binding.ivRoomImage.loadImage(room.photo)
    }
}