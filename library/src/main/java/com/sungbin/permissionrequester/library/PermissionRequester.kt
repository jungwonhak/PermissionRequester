package com.sungbin.permissionrequester.library

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sungbin.permissionrequester.library.dto.Permission


@SuppressLint("InflateParams")
object PermissionRequester {

    private const val PERMISSION_REQUEST_CODE = 1000

    private var activity: Activity? = null
    private var requiredPermissionItems: ArrayList<Permission> = ArrayList()
    private var choosePermissionItems: ArrayList<Permission> = ArrayList()
    private var selectedPermissionItems: ArrayList<Permission> = ArrayList()
    private var dialogLayout: RelativeLayout? = null

    fun with(activity: Activity): PermissionRequester {
        this.activity = activity
        requiredPermissionItems.clear()
        choosePermissionItems.clear()
        selectedPermissionItems.clear()
        dialogLayout = getDialogLayout()
        return this
    }

    fun addRequiredPermission(item: Permission): PermissionRequester{
        if(!requiredPermissionItems.contains(item))
            requiredPermissionItems.add(item)
        return this
    }

    fun addChoosePermission(item: Permission): PermissionRequester{
        if(!choosePermissionItems.contains(item))
            choosePermissionItems.add(item)
        return this
    }

    fun setAppData(icon: Int, name: String, description: String): PermissionRequester{
        dialogLayout!!.apply {
            findViewById<ImageView>(R.id.iv_app_icon).setImageResource(icon)
            findViewById<TextView>(R.id.tv_app_name).text = name
            findViewById<TextView>(R.id.tv_app_description).text = description
        }
        return this
    }

    fun create(){
        for(element in requiredPermissionItems){
            if(checkPermissionStatue(getPermissionList(element)) == PackageManager.PERMISSION_GRANTED) continue
            val layout = getRequiredPermissionLayout()
            layout.apply {
                findViewById<ImageView>(R.id.iv_permission_icon).setImageResource(
                    getPermissionIcon(element.type!!)
                )
                findViewById<TextView>(R.id.tv_permission_name).text = element.name!!
                findViewById<TextView>(R.id.tv_permission_description).text = element.description!!
            }
            dialogLayout!!.findViewById<LinearLayout>(R.id.ll_permissions_scroll).addView(layout)
        }
        for(element in choosePermissionItems){
            if(checkPermissionStatue(getPermissionList(element)) == PackageManager.PERMISSION_GRANTED) continue
            val layout = getChoosePermissionLayout()
            layout.apply {
                findViewById<ImageView>(R.id.iv_permission_icon).setImageResource(
                    getPermissionIcon(element.type!!)
                )
                findViewById<TextView>(R.id.tv_permission_name).text = element.name!!
                findViewById<TextView>(R.id.tv_permission_description).text =
                    element.description!!
                findViewById<CheckBox>(R.id.cb_choose)
                    .setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            selectedPermissionItems.add(element)
                        } else {
                            selectedPermissionItems.remove(element)
                        }
                    }
            }
            dialogLayout!!.findViewById<LinearLayout>(R.id.ll_permissions_scroll).addView(layout)
        }
        val dialog = AlertDialog.Builder(activity)
        dialog.setView(dialogLayout!!)
        val alert = dialog.create()
        dialogLayout!!.findViewById<TextView>(R.id.tv_done).setOnClickListener {
            val permissions = ArrayList<String>()
            for(permission in selectedPermissionItems){
                for(element in getPermissionList(permission)){
                    permissions.add(element)
                }
            }
            for(permission in requiredPermissionItems){
                for(element in getPermissionList(permission)){
                    permissions.add(element)
                }
            }
            ActivityCompat.requestPermissions(
                activity!!,
                permissions.toTypedArray(),
                PERMISSION_REQUEST_CODE)
            alert.cancel()
        }
        alert.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        alert.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        alert.show()
    }

    private fun getPermissionList(permission: Permission): Array<String>{
        return when(permission.type!!){
            0 -> arrayOf(Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR)
            1 -> arrayOf(Manifest.permission.CAMERA)
            2 -> arrayOf(Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS, Manifest.permission.GET_ACCOUNTS)
            3 -> arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            4 -> arrayOf(Manifest.permission.RECORD_AUDIO)
            5 -> arrayOf(Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE, Manifest.permission.USE_SIP)
            6 -> arrayOf(Manifest.permission.BODY_SENSORS)
            7 -> arrayOf(Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH, Manifest.permission.RECEIVE_MMS)
            else -> arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    private fun checkPermissionStatue(permissions: Array<String>): Int{
        var statue = PackageManager.PERMISSION_GRANTED
        for(permission in permissions){
            if(ContextCompat.checkSelfPermission(
                activity!!,
                permission
            ) == PackageManager.PERMISSION_DENIED) statue = PackageManager.PERMISSION_DENIED
        }
        return statue
    }

    private fun getPermissionIcon(type: Int): Int {
        return when(type){
            0 -> R.drawable.ic_perm_contact_calendar_black_24dp
            1 -> R.drawable.ic_camera_alt_black_24dp
            2 -> R.drawable.ic_contact_phone_black_24dp
            3 -> R.drawable.ic_location_on_black_24dp
            4 -> R.drawable.ic_mic_black_24dp
            5 -> R.drawable.ic_local_phone_black_24dp
            6 -> R.drawable.ic_memory_black_24dp
            7 -> R.drawable.ic_sms_black_24dp
            else -> R.drawable.ic_folder_black_24dp
        }
    }

    private fun getDialogLayout(): RelativeLayout {
        return LayoutInflater.from(activity!!)
            .inflate(R.layout.dialog_layout, null, false).findViewById(R.id.cl_main)
    }

    private fun getRequiredPermissionLayout(): LinearLayout {
        return LayoutInflater.from(activity!!)
            .inflate(R.layout.required_permission_layout, null, false).findViewById(R.id.ll_main)
    }

    private fun getChoosePermissionLayout(): LinearLayout {
        return LayoutInflater.from(activity!!)
            .inflate(R.layout.choose_permission_layout, null, false).findViewById(R.id.ll_main)
    }
}