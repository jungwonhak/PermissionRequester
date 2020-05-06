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

# All methods
