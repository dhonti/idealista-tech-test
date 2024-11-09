package com.dhontiveros.idealistatechtest.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.dhontiveros.idealistatechtest.BR

abstract class BaseFragmentVM<VB : ViewDataBinding, VM : BaseViewModel<*, *>>(@LayoutRes private val layoutResId: Int) :
    BaseFragment<VB>(layoutResId) {

    protected lateinit var viewModel: VM

    abstract fun getViewModelClass(): Class<VM>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doDataBinding()
    }

    private fun doDataBinding() {
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}