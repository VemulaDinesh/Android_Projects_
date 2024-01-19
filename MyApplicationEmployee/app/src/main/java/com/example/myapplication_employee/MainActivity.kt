package com.example.myapplication_employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var createemployee:Button
    private lateinit var editEmployeename:EditText
    private lateinit var editEmployeeid:EditText
    private lateinit var editAge:EditText
    private lateinit var editEmail:EditText
    private lateinit var editAdress:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createemployee=findViewById(R.id.btn_create)
        editEmployeename=findViewById(R.id.et_employeename)
        editEmployeeid=findViewById(R.id.et_employeeid)
        editAge=findViewById(R.id.et_Age)
        editEmail=findViewById(R.id.et_Email)
        editAdress=findViewById(R.id.et_Adress)
        createemployee.setOnClickListener{
            startActivity(Intent(this,SecondActivity::class.java)
                .putExtra("Employeename",editEmployeename.text.toString())
                .putExtra("Employeeid",editEmployeeid.text.toString())
                .putExtra("Age",editAge.text.toString())
                .putExtra("Email",editEmail.text.toString())
                .putExtra("Adress",editAdress.text.toString())

            )
        }

    }
}