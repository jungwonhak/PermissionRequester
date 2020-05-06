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

# Customize view
You can get dialog layout from `getDialogLayout(activity: Activity = this.activity!!)` method.<br>
And you can set Customize view use `setDialogLayout(layout: LinearLayout)` method.<br>
You should called  `setDialogLayout(layout: LinearLayout)` method before use `with(activity: Activty)`.<br><br>
[[Customize view example]](https://github.com/sungbin5304/PermissionRequester/blob/master/app/src/main/java/com/sungbin/permissionrequester/MainActivity.kt#L20)

# Warning
If you called `getDialogLayout(activity: Activity = this.activity!!)` method before use `with(activty: Activity)`, you should put parameter `activity` value.<br>
If you want set customize view, you should call `setDialogLayout(layout: LinearLayout)` method before use `with(activty: Activity)` method.

# View IDs.
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
