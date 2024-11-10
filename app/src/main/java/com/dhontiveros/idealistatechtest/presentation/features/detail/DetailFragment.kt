package com.dhontiveros.idealistatechtest.presentation.features.detail

import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.databinding.FragmentDetailBinding
import com.dhontiveros.idealistatechtest.presentation.base.BaseFragmentVM
import com.dhontiveros.idealistatechtest.presentation.features.detail.adapter.ImageCarouselAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragmentVM<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    private val args: DetailFragmentArgs by navArgs()

    override fun initViewComponents() {
        args.item?.also { item ->
            val imageAdapter = ImageCarouselAdapter(item.multimedia.images.map { it.url })
            binding.vpCarousel.apply {
                adapter = imageAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
            binding.item = item
        }
    }


}