package com.example.myapplication_employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var textemployeename : TextView
    private lateinit var textemployeeid : TextView
    private lateinit var textAge : TextView
    private lateinit var textEmail: TextView
    private lateinit var textAdress : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textemployeename=findViewById(R.id.tv_employeename)
        textemployeeid=findViewById(R.id.tv_employeeid)
        textAge=findViewById(R.id.tv_Age)
        textEmail=findViewById(R.id.tv_Email)
        textAdress=findViewById(R.id.tv_Adress)
        val employeename=intent.getStringExtra("Employeename")
        val employeeid=intent.getStringExtra("Employeeid")
        val Age=intent.getStringExtra("Age")
        val Email=intent.getStringExtra("Email")
        val Adress=intent.getStringExtra("Adress")
        textemployeename.text="Employeename: "+employeename
        textemployeeid.text="Employeeid: "+employeeid
        textAge.text="Age: "+Age
        textEmail.text="Email: "+Email
        textAdress.text="Adress: "+Adress


    }
}