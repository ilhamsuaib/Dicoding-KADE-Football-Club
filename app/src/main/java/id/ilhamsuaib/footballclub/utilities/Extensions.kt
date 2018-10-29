package id.ilhamsuaib.footballclub.utilities

import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */


fun Spinner.addOnItemSelectedListener(onItemSelected: (position: Int) -> Unit) {
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            onItemSelected(position)
        }
    }
}

fun Any?.toJson(): JsonElement = Gson().toJsonTree(this)

fun ImageView.loadImage(url: String?) {
    logD(s = "image url : $url")
    Picasso.get().load(url).into(this)
}

fun String.parseDate(pattern: String): String {
    logD(s = "old date $this")
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    val date = sdf.parse(this)
    logD(s = "new date $date")
    val newSdf = SimpleDateFormat("E, dd MMM yyyy", Locale.getDefault())
    return newSdf.format(date)
}

fun String.getTimeMillis(): Long {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    val date = sdf.parse(this)
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.timeInMillis
}

fun logD(tag: String = "logD", s: String?) {
    Log.d(tag, s)
}