package com.sagar.dialogutil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.sagar.dialog.DialogUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler(Looper.getMainLooper())
            .postDelayed(
                {
                    showDialog()
                },
                4000
            )
    }

    private fun showDialog(){
        DialogUtil(this)
            .showMessage(
                "This is a test."
            )
    }
}