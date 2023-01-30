package uz.umarov.todo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import uz.umarov.todo.databinding.ItemViewBinding

class SpinnerAdapter(var list: List<UserData>) : BaseAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val binding = ItemViewBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        binding.txtSpinnerItem.text = list[position].name
        if (list[position].img != -1)
            binding.image.setImageResource(list[position].img)

        return binding.root
    }


}