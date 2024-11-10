package com.dhontiveros.idealistatechtest.presentation.features.detail

import androidx.navigation.fragment.navArgs
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.databinding.FragmentDetailBinding
import com.dhontiveros.idealistatechtest.presentation.base.BaseFragmentVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragmentVM<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    private val args: DetailFragmentArgs by navArgs()

    override fun initViewComponents() {
        binding.item = args.item
    }


}