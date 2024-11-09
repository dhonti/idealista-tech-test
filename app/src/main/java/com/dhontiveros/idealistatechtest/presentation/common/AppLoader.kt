package com.dhontiveros.idealistatechtest.presentation.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.dhontiveros.idealistatechtest.core.extensions.goneIfTextEmpty
import com.dhontiveros.idealistatechtest.core.extensions.lazyFast
import com.dhontiveros.idealistatechtest.databinding.LoaderAppBinding

class AppLoader(context: Context) : Dialog(context) {

    private val binding: LoaderAppBinding by lazyFast {
        LoaderAppBinding.inflate(LayoutInflater.from(context), null, false)
    }

    init {
        this.setCancelable(false)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window?.decorView?.setBackgroundResource(android.R.color.transparent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    fun show(msg: String? = null) {
        binding.tvTextLoading.text = msg
        binding.tvTextLoading.goneIfTextEmpty()
        super.show()
    }

}