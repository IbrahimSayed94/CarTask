package com.ibrahim.carstask.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import com.ibrahim.carstask.R
import java.lang.Exception

object ProgressLoading {
    private var progress: Dialog? = null

    private fun init(context: Context) {
        progress = Dialog(context)
        progress?.setContentView(R.layout.progress_layout)
        progress?.setCancelable(false)
        progress?.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progress?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

    } // init

    fun show(context: Context) {
        init(context)

        if (!(`context` as Activity).isFinishing) {
            //show dialog
            try {
                progress?.show()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    } // show

    fun isVisible(): Boolean {
        try {
            if (progress!!.isShowing)
                return true
            else
                return false
        } catch (e: Exception) {
            return false
        }
    } // fun of isVisible

    fun dismiss() {
        progress?.dismiss()
    } // dissmiss
} // class of ProgressLoading