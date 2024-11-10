package com.dhontiveros.idealistatechtest.data.local.datasource

import com.dhontiveros.idealistatechtest.core.common.Resource
import com.dhontiveros.idealistatechtest.core.extensions.callOpDB
import com.dhontiveros.idealistatechtest.core.extensions.callQueryDB
import com.dhontiveros.idealistatechtest.data.local.dao.PropertyDao
import com.dhontiveros.idealistatechtest.data.local.entity.PropertyLocalModel
import com.dhontiveros.idealistatechtest.data.local.entity.toDomain
import com.dhontiveros.idealistatechtest.domain.models.PropertyListItem
import javax.inject.Inject

class PropertyLocalDsImpl @Inject constructor(
    private val propertyDao: PropertyDao,
) : PropertyLocalDs {

    override suspend fun getAll(): Resource<List<PropertyListItem>> = callQueryDB(
        dbOperation = { propertyDao.getAll() },
        transform = { it.map { it.toDomain()} }
    )

    override suspend fun insert(user: PropertyLocalModel): Resource<Boolean> =
        callOpDB { propertyDao.insert(user = user) }

    override suspend fun remove(user: PropertyLocalModel): Resource<Boolean> =
        callOpDB { propertyDao.remove(user = user) }

}