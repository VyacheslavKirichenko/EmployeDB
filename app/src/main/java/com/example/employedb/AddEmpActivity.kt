package com.example.employedb

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_emp.*
import java.io.ByteArrayOutputStream

class AddEmpActivity : AppCompatActivity() {


    val realmBD = RealmObject
   // var notification = Notification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_emp)

        save_add_button.setOnClickListener {
            addContactToRecycler()
        }
    }



    private fun addContactToRecycler() {

        var name = add_name.text.toString()
        var position = add_position.text.toString()
        var salary = add_salary.text.toString()
        var description = add_description.text.toString()
        if(validateFields(name, position, salary, description)){
            add_name.setText("")
            add_position.setText("")
            add_salary.setText("")
            add_description.setText("")
            var id = RealmObject.getNewId().toInt()
            realmBD.createUser(Employe(id,name,position,salary,description,imgToByteArray(R.drawable.b4)))
           // var lastId = realmBD.getNewId()
          //  var notificationIntent = Intent(this, ContactDetails::class.java)
         //   notificationIntent.putExtra("id",id)
          //  notification.makeNotification(this,"Add contact","Successfully add",R.drawable.add_white_24dp,notificationIntent)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("listChanged", true)
            startActivity(intent)
        }
    }

    private fun validateFields(name: String, position: String, salary: String, description: String):Boolean {

        if (name.length <=2 ){
            showAlert("Field name must contain more then 2 letters")
        }
        else if(position.length<4){
            showAlert("Field position must contain more then 4 letters")
        }
        else if(salary.length <=2){
            showAlert("Field salary must contain  more 2 digits")
        }
        else{
            return true
        }
        return false
    }

    private fun showAlert(message:String) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Input all fields")
        builder.setMessage(message)
        builder.show()
    }

    fun imgToByteArray(imgId: Int) : ByteArray {
        val bitmap = BitmapFactory.decodeResource(this.resources, imgId)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)

        return stream.toByteArray()
    }



}