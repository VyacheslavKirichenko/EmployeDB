package com.example.employedb

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.cell_item_layout.*

class DetailActivity : AppCompatActivity() {

    var index: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        if (intent != null) {

            var id = intent.getStringExtra("id")
            var employe2 = MainActivity.result[(id).toInt()]
            if (employe2 != null) {
                index = (id).toInt()
                text_detail_name.text = employe2.emp_name
                text_detail_position.text = employe2.emp_position
                text_detail_salary.text = employe2.emp_salary
               // image_fotoDetail_laout.setImageResource(employe2.emp_photo!!)
            }
        }
//        image_fotoDetail_laout.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(v: View?) {
//             //   goToFhoto(index)
//            }
//        })

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var id = item?.itemId

        if (id == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
//    fun goToFhoto(id: Int) {
//        val intent = Intent(this, FotoScreenClass::class.java)
//        intent.putExtra("id", id)
//        this.startActivity(intent)
//    }
}
