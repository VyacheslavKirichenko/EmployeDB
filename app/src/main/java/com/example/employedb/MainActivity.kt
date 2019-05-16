package com.example.employedb

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import io.realm.RealmObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    companion object {
//        lateinit var result: ArrayList<Employe>
//    }

    lateinit var defaultEmployeList: GenerateEmployeData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        com.example.employedb.RealmObject.initBase(this)

        if(com.example.employedb.RealmObject.isEmpty()) {
            defaultEmployeList = GenerateEmployeData(this)
            com.example.employedb.RealmObject.initData(defaultEmployeList.generateEmployeeData())
        }

       // result = com.example.employedb.RealmObject.readAllContacts()
      //  com.example.employedb.RealmObject.initBase(this)


       // var empList: ArrayList<Employe>
        //empList = defaultEmployeList.generateEmployeeData()
        //result = empList

        setRecyclerAdapter()
    }


    fun setRecyclerAdapter() {
        var adapter: EmplyeAdapter?
        adapter = EmplyeAdapter(this, com.example.employedb.RealmObject.readAllContacts())

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_emp.setLayoutManager(layoutManager)

        recycler_emp.adapter = adapter

    }
}
