package com.sungbin.permissionrequester.library.dto

class Permission {
    var type: Int? = null
    var name: String?  = null
    var description: String? = null

    constructor(){}
    constructor(type: Int?, name: String?, description: String?) {
        this.type = type
        this.name = name
        this.description = description
    }
}