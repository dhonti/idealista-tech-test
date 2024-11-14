package com.dhontiveros.idealistatechtest.presentation.features.detail

import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.databinding.FragmentDetailBinding
import com.dhontiveros.idealistatechtest.presentation.base.BaseFragmentVM
import com.dhontiveros.idealistatechtest.presentation.features.HomeActivity
import com.dhontiveros.idealistatechtest.presentation.features.detail.adapter.ImageCarouselAdapter
import com.dhontiveros.idealistatechtest.presentation.utils.getTitleItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment :
    BaseFragmentVM<FragmentDetailBinding, DetailViewModel>(R.layout.fragment_detail) {

    private var isExpanded = false

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    private val args: DetailFragmentArgs by navArgs()

    override fun initViewComponents() {
        initAuxComponents()
        initMainViewItem()
    }

    private fun initMainViewItem() {
        args.item?.also { item ->
            (activity as? HomeActivity)?.setToolbarAttrs( activity?.getTitleItem(item).orEmpty() )
            val imageAdapter = ImageCarouselAdapter(item.multimedia.images.map { it.url })
            binding.vpCarousel.apply {
                adapter = imageAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
            }
            binding.item = item
        }
    }

    private fun initAuxComponents() {
        binding.btToggleText.setOnClickListener {
            isExpanded = !isExpanded
            if (isExpanded) {
                binding.tvDescription.maxLines = Int.MAX_VALUE
                binding.btToggleText.text = getString(R.string.button_show_less)
            } else {
                binding.tvDescription.maxLines = MAX_LINES_DESCRIPTION
                binding.btToggleText.text = getString(R.string.button_show_more)
            }

        }
    }

    companion object {
        private const val MAX_LINES_DESCRIPTION = 5
    }
}