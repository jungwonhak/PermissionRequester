package com.sungbin.permissionrequester

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sungbin.permissionrequester.library.PermissionRequester
import com.sungbin.permissionrequester.library.dto.PermissionType
import com.sungbin.permissionrequester.library.dto.Permission

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PermissionRequester
            .with(this)
            .setAppData(
                R.drawable.ic_launcher_background,
                getString(R.string.app_name),
                "앱을 사용하려면 다음과 같은 권한들이 필요합니다."
            )
            .addRequiredPermission(
                Permission(
                    PermissionType.STORAGE,
                    "저장공간",
                    "앱 데이터를 저장하기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.CALENDAR,
                    "캘린더",
                    "일정을 관리하기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.CAMERA,
                    "카메라",
                    "사진을 찍기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.CONTACTS,
                    "연락처",
                    "연락처를 관리하기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.LOCATION,
                    "위치",
                    "현재 위치를 조회하기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.MICROPHONE,
                    "마이크",
                    "음성녹음을 하기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.PHONE,
                    "전화",
                    "전화를 걸기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.SENSORS,
                    "센서",
                    "각종 센서를 사용하기 위해 필요합니다.")
            )
            .addChoosePermission(
                Permission(
                    PermissionType.SMS,
                    "문자",
                    "문자를 보내기 위해 필요합니다.")
            )
            .create()
    }
}
