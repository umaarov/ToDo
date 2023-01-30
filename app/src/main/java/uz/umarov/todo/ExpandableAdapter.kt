package uz.umarov.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import uz.umarov.todo.databinding.ChildGroupBinding
import uz.umarov.todo.databinding.ItemGroupBinding

class ExpandableAdapter(var titleList: List<String>, var map: HashMap<String, ArrayList<String>>) :
    BaseExpandableListAdapter() {
    override fun getGroup(groupPosition: Int): String = titleList[groupPosition]

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    override fun hasStableIds(): Boolean = true

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding = ItemGroupBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        binding.txtName.text = titleList[groupPosition]
        return binding.root
    }

    override fun getChildrenCount(groupPosition: Int): Int = map[titleList[groupPosition]]!!.size

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return map[titleList[groupPosition]]!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val binding = ChildGroupBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        val list = map[titleList[groupPosition]]
        val childItem = list?.get(childPosition)
        binding.txtChild.text = childItem

        return binding.root
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

    override fun getGroupCount(): Int = titleList.size
}
