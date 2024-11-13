package com.dhontiveros.idealistatechtest.presentation.usecases

import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import com.dhontiveros.idealistatechtest.domain.usecases.base.BaseUseCase

abstract class RemoveFavProperty : BaseUseCase<Boolean, PropertyListItem>()