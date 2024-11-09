package com.dhontiveros.idealistatechtest.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {

    // protected lateinit var bindingObject: Binding
    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    abstract fun initViewComponents()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewComponents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}