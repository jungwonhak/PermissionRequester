package com.sungbin.permissionrequester

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sungbin.permissionrequester.library.PermissionRequester
import com.sungbin.permissionrequester.library.dto.PermissionType
import com.sungbin.permissionrequester.library.dto.Permission
import com.sungbin.permissionrequester.library.ui.RoundImageView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newLayout = PermissionRequester.getDialogLayout(this)
        newLayout.findViewById<RoundImageView>(R.id.iv_app_icon).set(dp2px(25))

        PermissionRequester
            .setDialogLayout(newLayout)
            .with(this)
            .setAppData(
                R.drawable.android,
                getString(R.string.app_name),
                "If you want use this app,\nplease accept use permissions"
            )
            .addRequiredPermission(
                Permission(
                    PermissionType.STORAGE,
                    "Storage (REQUIRED)",
                    "need for save app data")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.CALENDAR,
                    "Calendar",
                    "need for create new plan")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.LOCATION,
                    "Location",
                    "need for get user location")
            )
            .setDoneButtonText("Ok")
            .setOnDoneClickListener {
                Log.d("TAG", "${it.text} clicked.")
            }
            .create(1000)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Toast.makeText(
            applicationContext,
            "grant results : [${grantResults.joinToString { ", "  }}]",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun dp2px(dp: Int): Int {
        val scale: Float = applicationContext.resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }
}
