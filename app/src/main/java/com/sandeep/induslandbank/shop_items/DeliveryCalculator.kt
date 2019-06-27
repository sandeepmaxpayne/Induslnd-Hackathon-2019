package com.sandeep.induslandbank.shop_items

import android.annotation.TargetApi
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.sandeep.induslandbank.R
import kotlinx.android.synthetic.main.activity_delivery_calculator.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DeliveryCalculator : AppCompatActivity() {
    var x: String? = null
    var current: LocalTime? = null
    var timelapse = 0
    var date: LocalDate? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_calculator)
        supportActionBar?.title = "Expected Delivery"
         current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        displayTime.text = "Current Login time $current"
        predel.setOnClickListener {
            getTime()

        }

    }

    private fun getTime() {
        date = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        current = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        Log.d("time", "time:$date")
        val time = current.toString()
        val dd = date.toString()
        Log.d("time", "date:${dd[0]}${dd.length}")
        timelapse = 24 - "${time[0]}${time[1]}".toInt()
        Log.d("time", "time:$time ${time[0]}${time[1]}")
        Log.d("time", "diff:$timelapse")
        val d = dist.text.toString().toDouble()
        val qty = noqty.text.toString().toInt()
        if (d <= 1.0 && qty > 5 && timelapse > 2) {
            x = "Expected time of delivery is within 20 min"
        } else if ((d in 2.0..4.0) && qty > 5 && timelapse > 3) {
            x = "Expected time of delivery is 30 to 45 min"
        } else if ((d in 5.0..7.0) && qty > 5 && timelapse > 4) {
            x = "Expected delivery is by less than 2 hr"
        } else if ((d in 8.0..12.0) && qty > 5 && timelapse > 4) {
            x = "Expected delivery is by 3 hr"
        }else if((d in 13.0 .. 20.0) && qty > 5 && timelapse > 2){
            x="Expected delivery more than 5 hrs , Delivery Agent contact will be sent for your convenience"
        }else if((d in 21.0 .. 100.0) && qty > 5 && timelapse > 2){
            x="Expected arrival of your order within 2 to 3 days"
        }
        else if (d <= 1 && qty <= 5 && timelapse > 2) {
            x = "Extra charges will be included for product qty $qty"
        } else if (d > 1 && qty <= 5 && timelapse > 2) {
            x = "delivery can't be done for product less than 5 \n or better get it from the shop instead"
        } else if (timelapse <= 2 && d >=1 && qty>=1) {
            Log.d("time", "date:${dd[0]}${dd.length}")

            x = "Delivery Time is up for so Your Expected delivery will be scheduled Tomorrow"

        }
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage(x)
            .setCancelable(false)
            .setPositiveButton("done") { _, _ ->
                finish()
            }
            .setNegativeButton("cancel") { dialog, _ ->
                dialog.cancel()
            }
        val alert = dialogbuilder.create()
        alert.setTitle("Delivery Prediction")
        alert.setIcon(R.drawable.shipping)
        alert.show()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.goback, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.back -> {
                val next = Intent(this@DeliveryCalculator, Category::class.java)
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("adhar")
                val accNum = bundle?.getString("acc")
                next.putExtra("addar",adhar)
                next.putExtra("useracc", accNum)
                startActivity(next)
            }
        }
        return super.onOptionsItemSelected(item)
    }



}
