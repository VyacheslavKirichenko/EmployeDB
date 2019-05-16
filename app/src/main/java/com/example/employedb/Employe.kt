package com.example.employedb
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Employe (
   @PrimaryKey
    var emp_id: Int = 0,
    var emp_name: String? = null,
    var emp_position: String? = null,
    var emp_salary: String? = null
    //var emp_photo: ByteArray? = null
) : RealmObject ()







//open class User (@PrimaryKey var id: Long = -1,
//                 var name: String? = null,
//                 var company: String? = null,
//                 var phone: String? = null,
//                 var about: String? = null,
//                 var image: ByteArray? = null) : RealmObject()