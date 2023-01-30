package uz.umarov.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import uz.umarov.todo.databinding.ActivityToDoListArgsBinding

class ToDoListArgs : AppCompatActivity() {
    private lateinit var binding: ActivityToDoListArgsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListArgsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedPrefarance.init(this)
        val name = intent.getStringExtra("name")
        println(name)
        val planArray = MySharedPrefarance.planList
        var plan1 = MyData("", "", null, "", "", "")
        var index = -1
        for (plan in planArray) {
            if (plan.name == name) {
                plan1 = plan
                index = planArray.indexOf(plan)
                binding.txtNamePlan.text = plan.name
                binding.txtCreateData.text = plan.createData
                binding.txtDedlineData.text = plan.dedline
                binding.txtDegree.text = plan.degree?.name
                binding.imgPlan.setImageResource(plan.degree!!.img)
                when (plan.level) {
                    "Open" -> binding.radOpen.isChecked = true
                    "Development" -> binding.radDev.isChecked = true
                    "Uploading" -> binding.radUploading.isChecked = true
                    "Reject" -> binding.radReject.isChecked = true
                    "Close" -> binding.radClosed.isChecked = true
                }
                break
            }
        }
        binding.btnOk.setOnClickListener {
            var rad = ""
            if (binding.radOpen.isChecked) rad = "Open"
            if (binding.radClosed.isChecked) rad = "Close"
            if (binding.radDev.isChecked) rad = "Development"
            if (binding.radReject.isChecked) rad = "Reject"
            if (binding.radUploading.isChecked) rad = "Uploading"

            plan1.level = rad
            planArray[index] = plan1
            MySharedPrefarance.planList = planArray
            Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}