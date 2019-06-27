package com.sandeep.induslandbank.invest

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.sandeep.induslandbank.R
import kotlinx.android.synthetic.main.activity_invest_list.*

class InvestList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest_list)
        supportActionBar?.title = "Banking Products"
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.pink))
        val  bundle: Bundle? = intent.extras
        val aadr = bundle?.getString("adr")
        val acno1 = bundle?.getString("acno")
        fxd.setOnClickListener {
            val next = Intent(this@InvestList, FixedDepositInterest::class.java)
            next.putExtra("ad", aadr)
            next.putExtra("acno", acno1)
            startActivity(next)
        }
        mtf.setOnClickListener {
            mutual_bankNotification()
        }
        tax.setOnClickListener {
            tax_bankNotification()
        }
    }
    private fun mutual_bankNotification(){
        val notify = AlertDialog.Builder(this)
        notify.setMessage("Bank has got the details about Your interest in Mutual Fund, Bank will contact you shortly.")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener { _, _ ->
                finish()
            })
        val alert = notify.create()
        alert.setTitle("Mutual Funds Services")
        alert.setIcon(R.drawable.m_fund)
        alert.show()
    }
    private fun tax_bankNotification(){
        val notify = AlertDialog.Builder(this)
        notify.setMessage("For more information related to taxes, Bank will contact you shortly.")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener { _, _ ->
                finish()
            })
        val alert = notify.create()
        alert.setTitle("Tax Services")
        alert.setIcon(R.drawable.tax)
        alert.show()
    }
}
