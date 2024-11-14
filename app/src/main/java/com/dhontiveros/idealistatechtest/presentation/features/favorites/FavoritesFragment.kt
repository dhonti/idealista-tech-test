package com.dhontiveros.idealistatechtest.presentation.features.favorites

import androidx.navigation.fragment.findNavController
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.core.extensions.getData
import com.dhontiveros.idealistatechtest.core.extensions.hideLoader
import com.dhontiveros.idealistatechtest.core.extensions.observeEffect
import com.dhontiveros.idealistatechtest.core.extensions.observeErrorEffect
import com.dhontiveros.idealistatechtest.core.extensions.observeState
import com.dhontiveros.idealistatechtest.core.extensions.showLoader
import com.dhontiveros.idealistatechtest.core.extensions.showNotification
import com.dhontiveros.idealistatechtest.databinding.FragmentFavoritesBinding
import com.dhontiveros.idealistatechtest.presentation.base.BaseFragmentVM
import com.dhontiveros.idealistatechtest.presentation.features.list.adapter.ListItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.toImmutableList

@AndroidEntryPoint
class FavoritesFragment :
    BaseFragmentVM<FragmentFavoritesBinding, FavoritesViewModel>(R.layout.fragment_favorites) {

    private val adapter: ListItemsAdapter by lazy {
        ListItemsAdapter(
            onChange = { currentList ->
                binding.showList = currentList.isNotEmpty()
                viewModel.updateStateWithNewList(currentList.toImmutableList())
            },
            onClick = { item ->
                viewModel.getPropertyById(item.propertyCode.toInt())
            },
            onFavUpdate = { item, indexPos ->
                viewModel.removeFav(item, indexPos)
            }
        )
    }

    override fun getViewModelClass(): Class<FavoritesViewModel> = FavoritesViewModel::class.java

    override fun initViewComponents() {
        initAdapterList()
        initObservers()
    }

    private fun initAdapterList() {
        binding.rvList.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            adapter.submitList(null)
            viewModel.getFavoritesList()
        }
    }

    private fun initObservers() {
        observeState<FavoritesContract.State>(viewModel) { state ->
            when (state.listFavState) {
                is FavoritesContract.FavoritesState.Idle -> {
                    binding.showList = false
                    hideLoader()
                }

                is FavoritesContract.FavoritesState.Loading -> {
                    showLoader()
                }

                is FavoritesContract.FavoritesState.Done -> {
                    hideLoader()
                    state.currentList?.let {
                        adapter.submitList(it)
                        binding.swipeRefresh.isRefreshing = false
                        binding.showList = it.isNotEmpty()
                    }
                }
            }
        }
        observeEffect<FavoritesContract.Effect>(viewModel = viewModel) {
            when (it) {
                is FavoritesContract.Effect.GoDetail -> {
                    val action = FavoritesFragmentDirections.actionFavoritesToDetail()
                    action.item = it.item
                    findNavController().navigate(action)
                }

                is FavoritesContract.Effect.UpdateFav -> {
                    adapter.removeItem(it.indexPos)
                }
            }
        }
        observeErrorEffect(viewModel = viewModel) {
            binding.swipeRefresh.isRefreshing = false
            showNotification(
                it.appException?.getData(
                    requireContext(),
                    mainAction = it.mainAction,
                    secondaryAction = it.secondaryAction
                )
            )
        }
    }

}