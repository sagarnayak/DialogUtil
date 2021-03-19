@file:Suppress("unused")

package com.sagar.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout


@Suppress("SENSELESS_COMPARISON")
class DialogUtil(private var context: Context) : DialogContracts() {

    override fun showSuccessDialog(
        message: String,
        waitMills: Long,
        viewOnlyDialogCallBack: ViewOnlyDialogCallBack?
    ) {
        if (isCustomDialogInitialised() && customDialog.isShowing)
            customDialog.dismiss()
        customDialog = Dialog(context)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.dialog_success)

        customDialog.window
            ?.setLayout(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val textViewMessage = customDialog.findViewById<TextView>(R.id.text_view_heading)

        if (message.isNotEmpty())
            textViewMessage.text = message

        customDialog.setCancelable(true)

        customDialog.setOnCancelListener {
            customDialog.dismiss()
            viewOnlyDialogCallBack?.dialogFinished()
        }

        customDialog.show()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                customDialog.dismiss()
                viewOnlyDialogCallBack?.dialogFinished()
            },
            waitMills
        )
    }

    @SuppressLint("SetTextI18n")
    override fun showProgressDialog(): ((progress: Float) -> Unit) {
        if (isCustomDialogInitialised() && customDialog.isShowing)
            customDialog.dismiss()
        customDialog = Dialog(context)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.dialog_progress)

        customDialog.window
            ?.setLayout(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val textViewMessage = customDialog.findViewById<TextView>(R.id.text_view_heading)

        customDialog.setCancelable(false)

        customDialog.show()

        return {
            textViewMessage.text =
                "${context.getString(R.string.dialogUploadingDefaultString)} ${NumberUtil.roundToTwoDecimal(it)} %"
            if (it == 100f)
                textViewMessage.text = "${context.getString(R.string.dialogDefaultProcessingText)} ..."
        }
    }

    override fun hideDialog() {
        if (isCustomDialogInitialised() && customDialog.isShowing)
            customDialog.dismiss()
    }

    override fun showFailDialog(
        message: String,
        waitMills: Long,
        viewOnlyDialogCallBack: DialogContracts.ViewOnlyDialogCallBack?
    ) {
        if (isCustomDialogInitialised() && customDialog.isShowing)
            customDialog.dismiss()
        customDialog = Dialog(context)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.dialog_fail)

        customDialog.window
            ?.setLayout(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val textViewMessage = customDialog.findViewById<TextView>(R.id.text_view_heading)

        if (message.isNotEmpty())
            textViewMessage.text = message

        customDialog.setCancelable(true)

        customDialog.setOnCancelListener {
            customDialog.dismiss()
            viewOnlyDialogCallBack?.dialogFinished()
        }

        customDialog.show()

        Handler(Looper.getMainLooper()).postDelayed(
            {
                customDialog.dismiss()
                viewOnlyDialogCallBack?.dialogFinished()
            },
            waitMills
        )
    }

    override fun showMessage(
        message: String,
        cancellable: Boolean,
        buttonText: String,
        callBack: DialogContracts.CallBack?,
        image: Drawable?,
        heading: String?
    ) {
        if (isCustomDialogInitialised() && customDialog.isShowing)
            customDialog.dismiss()
        customDialog = Dialog(context)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.dialog_with_single_button)

        customDialog.window
            ?.setLayout(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val textViewHeading = customDialog.findViewById<TextView>(R.id.text_view_heading)
        val textViewMessage = customDialog.findViewById<TextView>(R.id.text_view_message)
        val buttonAction = customDialog.findViewById<TextView>(R.id.button_action)
        val imageView = customDialog.findViewById<AppCompatImageView>(R.id.appcompatImageView)

        heading?.let {
            if (it.isNotEmpty()) {
                textViewHeading.visibility = View.VISIBLE
                textViewHeading.text = heading
            }
        }
        textViewMessage.text = message
        if (buttonText.isNotEmpty())
            buttonAction.text = buttonText

        image?.let {
            imageView.visibility = View.VISIBLE
            imageView.setImageDrawable(
                it
            )
        }

        customDialog.setCancelable(cancellable)

        buttonAction.setOnClickListener {
            callBack?.buttonClicked()
            customDialog.dismiss()
        }
        customDialog.setOnCancelListener { callBack?.dialogCancelled() }

        customDialog.show()
    }

    override fun showMessage(
        message: String,
        cancellable: Boolean,
        buttonOneText: String,
        buttonTwoText: String,
        multiButtonCallBack: DialogContracts.MultiButtonCallBack?,
        image: Drawable?,
        heading: String?
    ) {
        if (isCustomDialogInitialised() && customDialog.isShowing)
            customDialog.dismiss()
        customDialog = Dialog(context)
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        customDialog.setContentView(R.layout.dialog_with_two_button)

        customDialog.window
            ?.setLayout(
                CoordinatorLayout.LayoutParams.MATCH_PARENT,
                CoordinatorLayout.LayoutParams.WRAP_CONTENT
            )
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val textViewHeading = customDialog.findViewById<TextView>(R.id.text_view_heading)
        val textViewMessage = customDialog.findViewById<TextView>(R.id.text_view_message)
        val buttonActionOne = customDialog.findViewById<TextView>(R.id.button_action_one)
        val buttonActionTwo = customDialog.findViewById<TextView>(R.id.button_action_two)
        val imageView = customDialog.findViewById<AppCompatImageView>(R.id.appcompatImageView)

        heading?.let {
            if (it.isNotEmpty()) {
                textViewHeading.visibility = View.VISIBLE
                textViewHeading.text = heading
            }
        }
        textViewMessage.text = message
        if (buttonOneText.isNotEmpty())
            buttonActionOne.text = buttonOneText
        if (buttonTwoText.isNotEmpty())
            buttonActionTwo.text = buttonTwoText

        image?.let {
            imageView.visibility = View.VISIBLE
            imageView.setImageDrawable(
                it
            )
        }

        customDialog.setCancelable(cancellable)

        buttonActionOne.setOnClickListener {
            multiButtonCallBack?.buttonOneClicked()
            customDialog.dismiss()
        }
        buttonActionTwo.setOnClickListener {
            multiButtonCallBack?.buttonTwoClicked()
            customDialog.dismiss()
        }
        customDialog.setOnCancelListener { multiButtonCallBack?.dialogCancelled() }

        customDialog.show()
    }
}
