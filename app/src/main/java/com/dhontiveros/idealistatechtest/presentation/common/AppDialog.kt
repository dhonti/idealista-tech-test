package com.dhontiveros.idealistatechtest.presentation.common

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import com.dhontiveros.idealistatechtest.R
import com.dhontiveros.idealistatechtest.core.extensions.goneIfTextEmpty
import com.dhontiveros.idealistatechtest.core.extensions.lazyFast
import com.dhontiveros.idealistatechtest.databinding.DialogAppBinding
import com.dhontiveros.idealistatechtest.domain.exceptions.NotificationData

class AppDialog(context: Context, private val notificationData: NotificationData?) {

    private val binding: DialogAppBinding by lazyFast {
        DialogAppBinding.inflate(LayoutInflater.from(context), null, false)
    }

    private val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        .setView(binding.root)

    private val title: TextView by lazyFast {
        binding.tvTitle
    }

    private val message: TextView by lazyFast {
        binding.tvMessage
    }

    private val positiveButton: Button by lazyFast {
        binding.btPositive
    }

    private val negativeButton: Button by lazyFast {
        binding.btNegative
    }

    private var dialog: AlertDialog? = null

//    init {
//        with(notificationData) {
//            title.text = this?.title
//            message.text = this?.message
//            positiveButton.text = this?.positiveLabel
//            negativeButton.text = this?.negativeLabel
//
//            positiveButton.setClickListenerToDialogButton(this?.positiveAction)
//            negativeButton.setClickListenerToDialogButton(this?.negativeAction)
//        }
//    }

    // Positive button is always visible
    fun create(): AlertDialog {
        dialog = builder
            .setCancelable(false)
            .create()

        with(notificationData) {
            title.text = this?.title
            title.goneIfTextEmpty()

            message.text = this?.message
            message.goneIfTextEmpty()

            negativeButton.text = this?.negativeLabel
            negativeButton.goneIfTextEmpty()

            if (this?.positiveLabel != null) {
                positiveButton.text = this.positiveLabel
            } else positiveButton.text = dialog?.context?.getDefaultPositiveLabel()

            positiveButton.setClickListenerToDialogButton(this?.positiveAction)
            negativeButton.setClickListenerToDialogButton(this?.negativeAction)
        }

        return dialog!!
    }


    private fun Button.setClickListenerToDialogButton(func: (() -> Unit)?) {
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
    }
}

fun Context.getDefaultPositiveLabel(): String = getString(R.string.accept)
