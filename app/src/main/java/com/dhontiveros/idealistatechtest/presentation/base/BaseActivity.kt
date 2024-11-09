package com.dhontiveros.idealistatechtest.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dhontiveros.idealistatechtest.presentation.common.AppLoader

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    AppCompatActivity() {

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    private var _loader: AppLoader? = null

    fun showLoader(msg: String? = null) = _loader?.show(msg)
    fun hideLoader() = _loader?.hide()

    abstract fun initViewComponents(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _loader = AppLoader(this)

        _binding = DataBindingUtil.setContentView(this, layoutResId) as VB
        binding.lifecycleOwner = this

        initViewComponents(savedInstanceState)
    }

}