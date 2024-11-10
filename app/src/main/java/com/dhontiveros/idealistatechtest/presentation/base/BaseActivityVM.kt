package com.dhontiveros.idealistatechtest.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.dhontiveros.idealistatechtest.BR

abstract class BaseActivityVM<VB : ViewDataBinding, VM : BaseViewModel<*, *>>(@LayoutRes private val layoutResId: Int) :
    BaseActivity<VB>(layoutResId) {

    protected lateinit var viewModel: VM

    abstract fun getViewModel(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[getViewModel()]
        doDataBinding()
    }

    private fun doDataBinding() {
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

}