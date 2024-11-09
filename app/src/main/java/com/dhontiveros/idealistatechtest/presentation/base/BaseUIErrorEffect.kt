package com.dhontiveros.idealistatechtest.presentation.base

import com.dhontiveros.idealistatechtest.domain.exceptions.AppException

data class BaseUIErrorEffect(
    val appException: AppException?,
    val mainAction: (() -> Unit)? = null,
    val secondaryAction: (() -> Unit)? = null
)