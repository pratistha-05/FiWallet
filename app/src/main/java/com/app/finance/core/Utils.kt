package com.nursyah.finance.core

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.text.isDigitsOnly
import com.nursyah.finance.R
import com.nursyah.finance.db.model.Data
import com.app.finance.presentation.MainActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object Utils {
  private const val SHARED_PREFERENCES = "sharedPreferences"
  const val SHARED_CHART_INCOME = "chartIncome"
  const val SHARED_CHART_SPENDING = "chartSpending"
  private val calendar = Calendar.getInstance()

  /**
   * Returns 2022-12 to December 2022
   */
  fun convertDateString(ctx:Context, s:String): String{
    val listMonth = listOf(
      R.string.january, R.string.february, R.string.march,R.string.april, R.string.may, R.string.june,
      R.string.july, R.string.august, R.string.september, R.string.october, R.string.november, R.string.december)

    val year = s.split("-")[0]
    val month = ctx.getString(listMonth[s.split("-")[1].toInt() - 1])

    return "$month $year"
  }
  fun getThisYear():String {
    return calendar.get(Calendar.YEAR).toString()
  }
  fun getThisMonth():String {
    return "%02d".format(calendar.get(Calendar.MONTH)+1)
  }

  fun saveSharedString(ctx: Context, value: String, name: String){
   ctx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE).edit()
     .apply {
       putString(name, value)
     }.apply()
  }

  fun getSharedString(ctx: Context, name: String, default: String = ""):String {
    return ctx.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
      .getString(name, default) ?: ""
  }

  @SuppressLint("SimpleDateFormat")
  fun getDateToday(pattern: String): String {
    return SimpleDateFormat(pattern).format(Calendar.getInstance().time)
  }

  // category: Spending | Income
  fun totalData(data: List<Data>, category: String):Long {
    var res:Long = 0
    data.filter { it.category == category }.forEach {
      res += it.value
    }
    return res
  }

  fun totalDataString(data: List<Data>, category: String):String {
    if(data.isEmpty()) return "0"

    var res:Long = 0
    data.filter { it.category == category }.forEach {
      res += it.value
    }
    return convertText(res.toString())
  }

  private fun totalIncomeSpend(data: List<Data>):Long{
    val spending = totalData(data, "Spending")
    val income = totalData(data, "Income")
    return income-spending
  }

  fun totalBalance(data: List<Data>):Long{
    val spending = totalData(data, "balanceSpending")
    val income = totalData(data, "balanceIncome")
    return totalIncomeSpend(data) + (income-spending)
  }

  fun convertToLong(text: String):Long {
    return try {
      if(text.isDigitsOnly()) text.toLong() else 0
    } catch (_:Exception){ 0 }
  }

  // convert date from 2022-12-10 to 10-12-2022
  fun convertDate(date: String):String{
    return date.split("-").reversed()
      .joinToString(postfix = "", prefix = "", separator = "-")
  }

  fun convertText(text: String):String {
    if(text == "") return "0"
    val prefix = if(text.contains("-")) "-" else ""
    try {
      text.toLong()
    }catch (_:Exception){ return "0" }

    var res = ""
    text.filterNot { it == '-' }.reversed().forEachIndexed { index, c ->
      if(index % 3 == 0)res += ","
      res += c
    }
    res = prefix + res.reversed().dropLast(1)

    return res
  }


  @SuppressLint("UnspecifiedImmutableFlag")
  fun notification(context:Context, description: String, file: File? = null){
    val channelID = "notification"
    val title = "finance"
    val priority = NotificationCompat.PRIORITY_DEFAULT

    val intent = Intent(context, MainActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    var pendingIntent: PendingIntent =
      PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    if(file != null){
      val newIntent = Intent(Intent.ACTION_VIEW).apply {
        setDataAndType(
          Uri.parse(file.path),
          "*/*")
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
      }
      pendingIntent = PendingIntent
        .getActivity(context, 0, newIntent, PendingIntent.FLAG_IMMUTABLE)
    }

    val builder = NotificationCompat.Builder(context, channelID)
      .setSmallIcon(R.drawable.finance)
      .setContentTitle(title)
      .setContentText(description)
      .setStyle(
        NotificationCompat.BigTextStyle()
          .bigText(description)
      )
      .setPriority(priority)
      .setContentIntent(pendingIntent)
      .setAutoCancel(true)

    val notificationManager: NotificationManager =
      context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val importance = NotificationManager.IMPORTANCE_DEFAULT
      notificationManager.createNotificationChannel(
        NotificationChannel(channelID, title, importance)
      )
    }

    notificationManager.notify(0, builder.build())
  }
  private var toast:Toast? = null
  fun showToast(context:Context, text: String){
    toast?.cancel()
    toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    toast?.show()
  }
}