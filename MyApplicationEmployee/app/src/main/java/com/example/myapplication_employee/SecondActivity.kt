
package com.example.myapplication_employee

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EmployeeAdapter
    private lateinit var dbHelper: DBHelper
    public var pos:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //dataSource = EmployeeDataSource(this)
        recyclerView = findViewById(R.id.recyclerView)
        // Set up RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        dbHelper = DBHelper(this)
        // Retrieve all employees from the database
        val employees = dbHelper.getAllEmployees()
        //dataSource.deleteAllData()
        // Create and set the adapter for the RecyclerView
         adapter = EmployeeAdapter(employees,
            object : EmployeeAdapter.OnEditClickListener {
                override fun onEditClick(employeeId: Long,position:Int) {
                    val intent = Intent(this@SecondActivity, MainActivity::class.java)
                    intent.putExtra("edit_employee_id", employeeId)
                    pos=position
                    startActivity(intent)
                }
            },
            object : EmployeeAdapter.OnDeleteClickListener {
                override fun onDeleteClick(employeeId: Long,position:Int) {
                    dbHelper.removeEmployee(employeeId)
                    adapter.updateDataRemoved(dbHelper.getAllEmployees(),position)
                }
            }
         )
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.updateEdit(dbHelper.getAllEmployees(),pos)
    }

}
