package com.example.employedb
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Employe (
   @PrimaryKey
    var emp_id: Int = 0,
    var emp_name: String? = null,
    var emp_position: String? = null,
    var emp_salary: String? = null,
    var emp_description: String? = null,
    var emp_photo: ByteArray? = null
) : RealmObject ()

