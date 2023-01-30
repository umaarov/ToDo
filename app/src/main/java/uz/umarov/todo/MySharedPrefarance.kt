package uz.umarov.todo

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPrefarance {
    private const val NAME = "CACHE"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var planList: ArrayList<MyData>
        get() = stringToArray(preferences.getString("key", "[]")!!)
        set(value) = preferences.edit {
            it.putString("key", arrayToString(value))
        }

    fun arrayToString(arrayList: ArrayList<MyData>): String {
        return Gson().toJson(arrayList)
    }

    fun stringToArray(gsonString: String): ArrayList<MyData> {
        val typeToken = object : TypeToken<ArrayList<MyData>>() {}.type
        return Gson().fromJson(gsonString, typeToken)
    }
}