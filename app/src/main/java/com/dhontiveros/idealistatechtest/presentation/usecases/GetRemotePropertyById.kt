package com.dhontiveros.idealistatechtest.presentation.usecases

import com.dhontiveros.idealistatechtest.domain.models.PropertyDetail
import com.dhontiveros.idealistatechtest.domain.usecases.base.BaseUseCase

abstract class GetRemotePropertyById : BaseUseCase<PropertyDetail, Int?>()