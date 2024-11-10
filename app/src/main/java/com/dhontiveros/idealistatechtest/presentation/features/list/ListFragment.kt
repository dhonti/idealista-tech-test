package com.dhontiveros.idealistatechtest.presentation.features.list

import androidx.navigation.fragment.findNavController
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.core.extensions.getData
import com.dhontiveros.idealistatechtest.core.extensions.hideLoader
import com.dhontiveros.idealistatechtest.core.extensions.observeEffect
import com.dhontiveros.idealistatechtest.core.extensions.observeErrorEffect
import com.dhontiveros.idealistatechtest.core.extensions.observeState
import com.dhontiveros.idealistatechtest.core.extensions.showLoader
import com.dhontiveros.idealistatechtest.core.extensions.showNotification
import com.dhontiveros.idealistatechtest.databinding.FragmentListBinding
import com.dhontiveros.idealistatechtest.presentation.base.BaseFragmentVM
import com.dhontiveros.idealistatechtest.presentation.features.list.adapter.ListItemsAdapter
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.toImmutableList

@AndroidEntryPoint
class ListFragment : BaseFragmentVM<FragmentListBinding, ListViewModel>(R.layout.fragment_list) {

    private val adapter: ListItemsAdapter by lazy {
        ListItemsAdapter(
            onChange = { currentList ->
                binding.showList = currentList.isNotEmpty()
                viewModel.updateStateWithNewList(currentList.toImmutableList())
            },
            onClick = { item ->
                viewModel.getPropertyById(item.propertyCode.toInt())
            })
    }

    override fun getViewModelClass(): Class<ListViewModel> = ListViewModel::class.java

    override fun initViewComponents() {
        initAdapterList()
        initObservers()
    }

    private fun initAdapterList() {
        binding.rvList.adapter = adapter
        binding.swipeRefresh.setOnRefreshListener {
            adapter.submitList(null)
            viewModel.getUsersList()
        }
    }

    private fun initObservers() {
        observeState<ListContract.State>(viewModel) {
            when (it.listPropertiesState) {
                is ListContract.ListsState.Idle -> {
                    binding.showList = false
                    hideLoader()
                }

                is ListContract.ListsState.Loading -> {
                    showLoader()
                }

                is ListContract.ListsState.Done -> {
                    hideLoader()
                    adapter.submitList(it.currentList)
                    binding.swipeRefresh.isRefreshing = false
                    binding.showList = it.currentList!!.isNotEmpty()
                }
            }
        }
        observeEffect<ListContract.Effect>(viewModel = viewModel) {
            when (it) {
                is ListContract.Effect.GoDetail -> {
                    val value = ListFragmentDirections.actionListToDetail()
                    value.arguments

                    val action = ListFragmentDirections.actionListToDetail()
//                    action.item = it.item
                    findNavController().navigate(action)
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