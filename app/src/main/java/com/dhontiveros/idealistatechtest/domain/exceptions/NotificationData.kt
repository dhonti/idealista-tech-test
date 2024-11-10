package com.dhontiveros.idealistatechtest.domain.exceptions

data class NotificationData(
    var title: String? = null,
    var message: String? = null,
    var positiveLabel: String? = null,
    var negativeLabel: String? = null,
    var positiveAction: (()->Unit)? = null,
    var negativeAction: (()->Unit)? = null
)
