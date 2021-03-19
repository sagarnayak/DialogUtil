package com.sagar.dialog

import android.app.Dialog
import android.graphics.drawable.Drawable

abstract class DialogContracts() {

    protected lateinit var customDialog: Dialog

    fun isCustomDialogInitialised() = this::customDialog.isInitialized

    interface CallBack {
        fun dialogCancelled() {}

        fun buttonClicked() {}
    }

    interface MultiButtonCallBack {
        fun dialogCancelled() {}

        fun buttonOneClicked() {}

        fun buttonTwoClicked() {}
    }

    interface ViewOnlyDialogCallBack {
        fun dialogFinished()
    }

    abstract fun showMessage(
        message: String,
        cancellable: Boolean = true,
        buttonText: String = "",
        callBack: CallBack? = null,
        image: Drawable? = null,
        heading: String? = null
    )

    abstract fun showMessage(
        message: String,
        cancellable: Boolean = true,
        buttonOneText: String = "",
        buttonTwoText: String = "",
        multiButtonCallBack: MultiButtonCallBack? = null,
        image: Drawable? = null,
        heading: String? = null
    )

    abstract fun showSuccessDialog(
        message: String = "",
        waitMills: Long = 3000L,
        viewOnlyDialogCallBack: ViewOnlyDialogCallBack? = null
    )

    abstract fun showProgressDialog(): ((progress: Float) -> Unit)

    abstract fun showFailDialog(
        message: String = "",
        waitMills: Long = 3000L,
        viewOnlyDialogCallBack: ViewOnlyDialogCallBack? = null
    )

    abstract fun hideDialog()
}
