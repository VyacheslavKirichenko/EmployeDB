package com.example.employedb


import android.content.Context
import io.realm.Realm
import java.lang.Exception

 object RealmObject {

    lateinit var realm : Realm

    fun initBase(context: Context) {
        Realm.init(context)
        realm = Realm.getDefaultInstance()
    }

    fun createUser(emp: Employe) : Boolean {
        try {
            realm.executeTransaction {
                it.copyToRealm(emp)
            }
            return true
        }
        catch (ex: Exception){
            println(ex)
            return false
        }
    }

    fun readUserById(id: Int) : Employe? {
        var emp = Employe()
        try {
            realm.executeTransaction{
                emp = it.where(Employe :: class.java).equalTo("emp_id", id).findFirst()!!
            }
        }
        catch (ex: Exception){
            println(ex)
        }
        return emp
    }

    fun readAllContacts() : ArrayList<Employe> {
        var empList = ArrayList<Employe>()
        try{
            realm.executeTransaction{
                val result = it.where(Employe::class.java).findAll()
                result.forEach{ user ->
                   empList.add(user)
                }
            }
        }
        catch (ex:Exception) {
            println(ex)
        }
        return empList
    }


    fun updateUser(emp: Employe) : Boolean {
        try{
            realm.executeTransaction {
                it.copyToRealmOrUpdate(emp)
            }
            return true
        }
        catch (e: Exception) {
            println(e)
            return false
        }
    }

    fun deleteUser(id: Int) : Boolean {
        try{
            realm.executeTransaction{
                it.where(Employe :: class.java).equalTo("emp_id", id).findFirst()!!.deleteFromRealm()
            }
            return true
        }
        catch (ex: Exception){
            println(ex)
            return false
        }
    }

    fun getNewId() : Int {
        var id = 0
        try {
            realm.executeTransaction {
                id = it.where(Employe::class.java).max("emp_id")!!.toInt() + 1
            }
        }
        catch (ex: Exception) {
            println(ex)
        }
        return id
    }

    fun initData(defaulUserList: ArrayList<Employe>): Boolean {
        try {
            for(user in defaulUserList) {
                createUser(user)
            }
        } catch (ex: Exception) {
            print(ex)
            return false
        }
        return true
    }

    fun isEmpty() : Boolean {
        return realm.isEmpty
    }
}