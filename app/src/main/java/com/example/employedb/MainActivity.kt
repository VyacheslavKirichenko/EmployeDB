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

    lateinit var defaultEmployeList: GenerateEmployeData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        com.example.employedb.RealmObject.initBase(this)

        if(com.example.employedb.RealmObject.isEmpty()) {
            defaultEmployeList = GenerateEmployeData(this)
            com.example.employedb.RealmObject.initData(defaultEmployeList.generateEmployeeData())
        }
        var q  = com.example.employedb.RealmObject.readAllContacts()

        clickAddEmployeBtn()
        setRecyclerAdapter()
    }

    override fun onRestart() {
        super.onRestart()
    setRecyclerAdapter()
    }

    fun setRecyclerAdapter() {
        var adapter: EmplyeAdapter?
        adapter = EmplyeAdapter(this, com.example.employedb.RealmObject.readAllContacts())

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_emp.setLayoutManager(layoutManager)

        recycler_emp.adapter = adapter
    }
    private fun clickAddEmployeBtn() {

        add_button.setOnClickListener {

            val intent = Intent(this, AddEmpActivity::class.java)
            startActivity(intent)
        }
    }

}
