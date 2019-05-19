package com.example.employedb
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var flagClick = 0
    var id = 0
    var notification = EmpNotifications()
    var emp = Employe()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
       RealmObject.initBase(this)
        if (intent != null) {
             id = intent.getIntExtra("id", 0)
           fillFields(id)
            emp = RealmObject.readUserById(id)!!
        }
        clickButtonsUpdate()
        clickDeleteButton()
    }


    override fun onResume() {
        super.onResume()
       // RealmObject.initBase(this)
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

                var notificationIntent = Intent(this, DetailActivity::class.java)
                notificationIntent.putExtra("id",id)                                    ////
                notification.makeNotifications(this,R.drawable.editnotif,"Update employe",
                    "Employe  has been edited ",notificationIntent,3)

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("listChanged", true)
                startActivity(intent)

            }
        }
    }

    fun clickDeleteButton() {
        btn_delete_detail.setOnClickListener {
            val empDel = RealmObject.readUserById(id)!!.emp_name
            RealmObject.deleteEmploye(id)
            var notificationIntent = Intent(this, MainActivity::class.java)
            notification.makeNotifications(this,R.drawable.notifdell,"Delete employe",
                "Employe "+ empDel +" has been removed from database",notificationIntent,2)
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
        RealmObject.updateEmploye(Employe(id,name,position,salary,description,emp.emp_photo))
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
        var employe2 = RealmObject.readUserById(id)
        text_detail_name.setText(employe2!!.emp_name)
        text_detail_position.setText(employe2.emp_position)
        text_detail_salary.setText(employe2.emp_salary)
        text_detail_description.setText(employe2.emp_description)
        if (employe2.emp_photo == null){
        image_foto_detail.setImageResource(R.drawable.empty)
    } else
        {image_foto_detail.setImageBitmap(convertToBitmap(employe2.emp_photo!!))
        }
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
