package uz.umarov.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.umarov.todo.databinding.ActivityAddBinding

class Add : AppCompatActivity() {
    private lateinit var userArray: ArrayList<UserData>
    private lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()

        MySharedPrefarance.init(this)

        val spinnerAdapter = SpinnerAdapter(userArray)
        binding.spinerAddToDo.adapter = spinnerAdapter

        binding.btnSaveAddToDo.setOnClickListener {
            val toDoName = binding.edtToDoName.text.toString().trim()
            val toDoDescription = binding.edtToDoDescription.text.toString().trim()
            val toDoCreateData = binding.edtToDoCreateData.text.toString().trim()
            val toDoDedline = binding.edtDedline.text.toString().trim()

            val degree = userArray[binding.spinerAddToDo.selectedItemPosition]

            if (toDoName != "" && toDoCreateData != "" && toDoDedline != "" && toDoDescription != "") {
                val myList = MySharedPrefarance.planList
                myList.add(
                    MyData(
                        toDoName,
                        toDoDescription,
                        degree,
                        toDoCreateData,
                        toDoDedline,
                        "Open"
                    )
                )
                println(myList)
                MySharedPrefarance.planList = myList
                Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error because editText is empty", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun loadData() {
        userArray = ArrayList()
        userArray.add(UserData(-1, "To do degree"))
        userArray.add(UserData(R.drawable.flag_red, "Urgent"))
        userArray.add(UserData(R.drawable.flag_high, "High"))
        userArray.add(UserData(R.drawable.flag_normal, "Normal"))
        userArray.add(UserData(R.drawable.flag_low, "Low"))
    }
}