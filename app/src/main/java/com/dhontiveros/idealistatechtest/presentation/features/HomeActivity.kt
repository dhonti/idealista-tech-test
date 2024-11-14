package com.dhontiveros.idealistatechtest.presentation.features

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.databinding.ActivityHomeBinding
import com.dhontiveros.idealistatechtest.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment).navController
    }

    override fun initViewComponents(savedInstanceState: Bundle?) {
        val toolbar: androidx.appcompat.widget.Toolbar = binding.toolbar.toolbar
        setSupportActionBar(toolbar)
        binding.toolbar.btBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    fun setToolbarAttrs(
        titleContent: String = "",
        showFav: Boolean = false,
        onFavButtonAction: (() -> Unit)? = null
    ) {
        binding.apply {
            titleValue = titleContent
            showBackButton = canGoBack()
            showFavButton = showFav
        }
        onFavButtonAction?.let { action ->
            binding.toolbar.btFavButton.setOnClickListener {
                action()
            }
        }
    }

    fun showHideBackButton(){
        binding.showBackButton = canGoBack()
    }

    private fun canGoBack(): Boolean = navController.previousBackStackEntry != null

}