package com.example.employedb

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

class GenerateEmployeData  (val context: Context)
{
   // var  imgArr = ArrayList<Integer>()
    var result = ArrayList<Employe>()
   private val addDescriptionSomeText = "William H. Seward (May 16, 1801 – October 10, 1872) was United States Secretary of State from 1861 to 1869. " +
            "A determined opponent of the spread of slavery in the years leading up to the Civil War, he was a dominant figure in the " +
            "Republican Party in its formative years. While Governor of New York, he signed laws that advanced the rights of black residents." +
            " He was elected as a U.S. Senator in 1849, serving two six-year terms."

        fun generateEmployeeData(): ArrayList<Employe> {
            var emp = Employe()
            emp.emp_id = 0
            emp.emp_name = "Patric Kopitov"
            emp.emp_position = "President"
            emp.emp_salary = "USD 10000"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b1)
                result.add(emp)

            emp = Employe()
            emp.emp_id = 1
            emp.emp_name = "Arnold Greeder"
            emp.emp_position = "Developer"
            emp.emp_salary = "USD 1800"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b2)
            result.add(emp)

            emp = Employe()
            emp.emp_id = 2
            emp.emp_name = "Oksana Kobylko"
            emp.emp_position = "Manager"
            emp.emp_salary = "USD 3000"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b3)
            result.add(emp)

            emp = Employe()
            emp.emp_id = 3
            emp.emp_name = "Petro Mogila"
            emp.emp_position = "Developer"
            emp.emp_salary = "USD 3100"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b4)
            result.add(emp)

            emp = Employe()
            emp.emp_id = 4
            emp.emp_name = "Sergey Gavrilov"
            emp.emp_position = "Driver"
            emp.emp_salary = "USD 1200"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b5)
            result.add(emp)

            emp = Employe()
            emp.emp_id = 5
            emp.emp_name = "Masha Malina"
            emp.emp_position = "Singer"
            emp.emp_salary = "USD 2000"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b6)
            result.add(emp)

            emp = Employe()
            emp.emp_id = 6
            emp.emp_name =
                "Petya Gdanov"
            emp.emp_position = "Teacher"
            emp.emp_salary = "USD 1000"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b7)
            result.add(emp)

            emp = Employe()
            emp.emp_id = 7
            emp.emp_name = "Natasha Ponkova"
            emp.emp_position = "Model"
            emp.emp_salary = "USD 3000"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b8)
            result.add(emp)

            emp = Employe()
            emp.emp_id = 8
            emp.emp_name = "Yana Curkchi"
            emp.emp_position = "Freelancer"
            emp.emp_salary = "USD 2000"
            emp.emp_description = addDescriptionSomeText
            emp.emp_photo = imgToByteArray(R.drawable.b9)
            result.add(emp)

            return result
        }

    fun imgToByteArray(imgId: Int) : ByteArray {
        val bitmap = BitmapFactory.decodeResource(context.resources, imgId)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)

        return stream.toByteArray()
    }

}