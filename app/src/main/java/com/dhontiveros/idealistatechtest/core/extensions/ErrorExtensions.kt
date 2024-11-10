package com.dhontiveros.idealistatechtest.core.extensions

import android.content.Context
import com.dhontiveros.idealistatechtest.R

import com.dhontiveros.idealistatechtest.domain.exceptions.AppException
import com.dhontiveros.idealistatechtest.domain.exceptions.NotificationData

fun AppException.getData(
    context: Context,
    mainAction: (() -> Unit)?,
    secondaryAction: (() -> Unit)? = null
): NotificationData {
    val notificationData = NotificationData(
        positiveLabel = context.getString(R.string.accept),
        positiveAction = mainAction,
        negativeAction = secondaryAction
    )
    when (this) {
        is AppException.BadRequestException -> {
            notificationData.message = context.getString(R.string.exception_bad_request)
        }
        is AppException.ConnectionException -> {
            notificationData.message = context.getString(R.string.exception_connection)
        }
        is AppException.ServerErrorException -> {
            notificationData.message = context.getString(R.string.exception_server_error)
        }
        is AppException.GeneralException -> {
            notificationData.message = context.getString(R.string.exception_general)
        }
        is AppException.RequestException -> {
            notificationData.message = context.getString(R.string.exception_request)
        }
        is AppException.LocalDBException -> {
            notificationData.message = "TODO: OCURRIÓ UN ERROR EN BASE DE DATOS!!!"
        }
        is AppException.EmptyException -> {
            notificationData.message = ""
        }
    }
    return notificationData
}