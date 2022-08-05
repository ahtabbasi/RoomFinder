package com.ahtabbasi.roomlist.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.R
import com.ahtabbasi.roomlist.databinding.FragmentRoomListBinding
import com.ahtabbasi.roomlist.presentation.adapter.RoomAdapter
import com.ahtabbasi.roomlist.presentation.navigation.RoomListNavigator
import com.ahtabbasi.utils.gone
import com.ahtabbasi.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RoomListFragment : Fragment() {

    @Inject
    internal lateinit var navigator: RoomListNavigator

    private val viewModel: RoomListViewModel by viewModels()
    private lateinit var binding: FragmentRoomListBinding
    private lateinit var roomAdapter: RoomAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentRoomListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RoomAdapter(viewModel, navigator).also {
            roomAdapter = it
            binding.rvRooms.adapter = it
        }
        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    onViewStateChanged(it)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collect { isLoading ->
                    if (isLoading) showLoading()
                    else hideLoading()
                }
            }
        }
    }

    private fun onViewStateChanged(viewState: RoomListViewState) {
        when (viewState) {
            is RoomListViewState.ShowRooms -> {
                showList(viewState.rooms)
            }
            is RoomListViewState.Error -> {
                showError(viewState.error.getErrorText(requireContext()))
            }
        }
    }

    private fun setupListeners() {
        binding.bRetry.setOnClickListener {
            viewModel.getRoomsList()
        }
    }

    private fun showError(message: String) {
        binding.bRetry.visible()
        binding.tvMessage.text = message
        binding.tvMessage.visible()
        binding.progressBar.gone()
        binding.rvRooms.gone()
    }

    private fun showLoading() {
        binding.bRetry.gone()
        binding.tvMessage.gone()
        binding.progressBar.visible()
    }

    private fun hideLoading() {
        binding.progressBar.gone()
    }

    private fun showList(rooms: List<Room>) {
        binding.bRetry.gone()
        binding.tvMessage.gone()
        binding.progressBar.gone()
        roomAdapter.submitList(rooms)
        binding.rvRooms.visible()
    }

}