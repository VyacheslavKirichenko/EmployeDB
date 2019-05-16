package com.example.employedb

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.cell_item_layout.*

class DetailActivity : AppCompatActivity() {
    var flagClick = 0
    var id = 0
   //var realmDB = com.example.employedb.RealmObject
    var emp = Employe()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        id = intent.getIntExtra("id", 0)

//        if(intent.hasExtra("id")) {
//            id = intent.getIntExtra("id", 0)
//        }else{
//            db.initBase(this)
//        }
//
//        emp = db.readUserById(id)!!

        com.example.employedb.RealmObject.initBase(this)
            emp = RealmObject.readUserById(id)!!


        if (intent != null) {
             id = intent.getIntExtra("id", 0)
           fillFields(id)
        }

        fillFields(id)
         clickButtonsUpdate()
        clickDeleteButton()

    }


    private fun clickButtonsUpdate(){
        btn_update_detail.setOnClickListener {
            if( flagClick == 0 ){
            btn_update_detail.setImageResource(R.drawable.btnsave)
                updateInfo()
                flagClick = 1
            }else{
               btn_update_detail.setImageResource(R.drawable.btnedit)
                seaveContactInfo()
                flagClick = 0
                updateInfo()
            }
        }
    }

    fun clickDeleteButton() {
        btn_delete_detail.setOnClickListener {
            RealmObject.deleteUser(id)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("listChanged", true)
            startActivity(intent)
        }
    }


    private fun seaveContactInfo() {
        var name = text_detail_name.text.toString()
        var position = text_detail_position.text.toString()
        var salary = text_detail_salary.text.toString()
        var description = text_detail_description.text.toString()
        RealmObject.updateUser(Employe(id,name,position,salary,description,emp.emp_photo))

//        var notificationIntent = Intent(this, ContactDetails::class.java)
//        notificationIntent.putExtra("id",entry_id)
//        notification.makeNotification(this,"Update contact","Successfully update",R.drawable.edit_white_24dp,notificationIntent)

    }

    private fun updateInfo() {
        if (text_detail_description.isEnabled == false) {
            text_detail_name.isEnabled = true
            text_detail_position.isEnabled = true
            text_detail_description.isEnabled = true
            text_detail_salary.isEnabled = true
            //details_update.text = "Save"
        }else {
            text_detail_name.isEnabled = false
            text_detail_position.isEnabled = false
            text_detail_description.isEnabled = false
            text_detail_salary.isEnabled = false
        }
    }


    private  fun fillFields (id: Int){
        val employe2 = RealmObject.readUserById(id)
        text_detail_name.setText(employe2!!.emp_name)
        text_detail_position.setText(employe2.emp_position)
        text_detail_salary.setText(employe2.emp_salary)
        text_detail_description.setText(employe2.emp_description)
        image_foto_detail.setImageBitmap(convertToBitmap(employe2.emp_photo!!))
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun convertToBitmap(byteArray: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

}
