package uz.umarov.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.umarov.todo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.cardAddToDo.setOnClickListener {
            startActivity(Intent(this,Add::class.java))
        }

        binding.cardToDoList.setOnClickListener {
            startActivity(Intent(this, ToDoList::class.java))
        }
    }
}