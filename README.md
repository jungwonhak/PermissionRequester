![image](https://raw.githubusercontent.com/sungbin5304/PermissionRequester/master/permission%20requester.png)

-----

# Download [![](https://jitpack.io/v/sungbin5304/PermissionRequester.svg)](https://jitpack.io/#sungbin5304/PermissionRequester)
```gradle
repositories {
  mavenCentral()
  google()
  maven { 
    url 'https://jitpack.io' 
  }
}

dependencies {
  implementation 'com.github.sungbin5304:PermissionRequester:{version}'
} 
```

# Usage
```kotlin
PermissionRequester
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
    .create(1000)
```

# Listener
```kotlin
.setOnDoneClickListener {
    Log.d("TAG", "${it.text} clicked.")
}
```
`it` will return `TextView`. (DoneButton Layout)

# Override
You can override `onRequestPermissionsResult` this.<br><br>
[[Override example]](https://github.com/sungbin5304/PermissionRequester/blob/master/app/src/main/java/com/sungbin/permissionrequester/MainActivity.kt#L56)

# Customize view
1. Get original view layout from `get~~~Layout(activity: Activity = this.activity!!)` method.
2. Set Customize view use `set~~~Layout(layout: LinearLayout)` method.
3. You should called  `set~~~Layout(layout: LinearLayout)` method before use `with(activity: Activty)`.

[[Customize view example]](https://github.com/sungbin5304/PermissionRequester/blob/master/app/src/main/java/com/sungbin/permissionrequester/MainActivity.kt#L20)

# Warning
If you called `get~~~Layout(activity: Activity = this.activity!!)` method before use `with(activty: Activity)`, you should put parameter `activity` value.

# Permission
You can create new permission from `Permission(type: Int, name: String, description: String)` class.
## PermissionType List
```
PermissionType.CALENDAR
PermissionType.CAMERA
PermissionType.CONTACTS
PermissionType.LOCATION
PermissionType.MICROPHONE
PermissionType.PHONE
PermissionType.SENSORS
PermissionType.SMS
PermissionType.STORAGE
```

# View IDs for customzing view.
You can see View IDs from [this](https://github.com/sungbin5304/PermissionRequester/tree/master/library/src/main/res/layout) page.

# All methods
```kotlin
with(activity: Activity)

addRequiredPermission(item: Permission)
addChoosePermission(item: Permission)

setAppData(icon: Int, name: String, description: String)
setDoneButtonText(string: String)

setDialogLayout(layout: LinearLayout)
setRequiredPermissionLayout(layout: LinearLayout)
setChoosePermissionLayout(layout: LinearLayout)

getDialogLayout(activity: Activity = this.activity!!)
getRequiredPermissionLayout(activity: Activity = this.activity!!)
getChoosePermissionLayout(activity: Activity = this.activity!!)

create(code: Int)
get(code: Int): AlertDialog
```
`code` parameter in `create(code: Int)` and `get(code: Int)` methods means is permission [`RequestCode`](https://developer.android.com/training/permissions/requesting#make-the-request).
