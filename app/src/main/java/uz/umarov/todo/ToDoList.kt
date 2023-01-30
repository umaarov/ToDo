package uz.umarov.todo


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import uz.umarov.todo.databinding.ActivityToDoListBinding

class ToDoList : AppCompatActivity() {
    private lateinit var map: HashMap<String, ArrayList<String>>
    private lateinit var binding: ActivityToDoListBinding
    private lateinit var titleList: ArrayList<String>
    private lateinit var openArray: ArrayList<String>
    private lateinit var developmentArray: ArrayList<String>
    private lateinit var uploadingArray: ArrayList<String>
    private lateinit var rejectArray: ArrayList<String>
    private lateinit var closedArray: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToDoListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.expandedMenu.setOnChildClickListener { _, _, groupPosition, childPosition, _ ->
            val intent = Intent(this, ToDoListArgs::class.java)
            intent.putExtra("name", map[titleList[groupPosition]]?.get(childPosition))
            startActivity(intent)
            true
        }
    }


    private fun cacheToArray() {
        map = HashMap()
        titleList = ArrayList()
        titleList.add("Open")
        titleList.add("Development")
        titleList.add("Uploading")
        titleList.add("Reject")
        titleList.add("Close")

        openArray = ArrayList()
        developmentArray = ArrayList()
        uploadingArray = ArrayList()
        rejectArray = ArrayList()
        closedArray = ArrayList()

        var planArray = ArrayList<MyData>()
        planArray = MySharedPrefarance.planList
        println(planArray)
        var nameArray = ArrayList<MyData>()
        for (todoPlan in planArray) {
            if (todoPlan.level == "Open") {
                openArray.add(todoPlan.name)
            }
            if (todoPlan.level == "Development") {
                developmentArray.add(todoPlan.name)
            }
            if (todoPlan.level == "Uploading") {
                uploadingArray.add(todoPlan.name)
            }
            if (todoPlan.level == "Reject") {
                rejectArray.add(todoPlan.name)
            }
            if (todoPlan.level == "Close") {
                closedArray.add(todoPlan.name)
            }
        }

        map[titleList[0]] = openArray
        map[titleList[1]] = developmentArray
        map[titleList[2]] = uploadingArray
        map[titleList[3]] = rejectArray
        map[titleList[4]] = closedArray

    }


    override fun onStart() {
        super.onStart()
        MySharedPrefarance.init(this)
        cacheToArray()
        Log.i("axborot", "onStart metodi")
        val spinnerAdapter = ExpandableAdapter(titleList, map)
        binding.expandedMenu.setAdapter(spinnerAdapter)

    }
}