package com.example.fininfocomtask.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.bson.types.ObjectId

open class UserRealm(

    @PrimaryKey
    var id: String = ObjectId().toHexString(),
    var name: String = "",
    var age: Int = 0,
    var city: String = ""

) : RealmObject()