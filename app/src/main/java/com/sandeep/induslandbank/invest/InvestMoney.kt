package com.sandeep.induslandbank.invest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.sandeep.induslandbank.Account
import com.sandeep.induslandbank.R
import com.sandeep.induslandbank.shop_items.CompareItem
import kotlinx.android.synthetic.main.activity_invest_money.*

class InvestMoney : AppCompatActivity() {

    var adhar: String? = null
    var acc:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invest_money)

        supportActionBar?.title = "Banking Solutions"
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.green))

        val bundle: Bundle? = intent.extras

         adhar = bundle?.getString("addar")
        acc = bundle?.getString("useracc")
        val amt = bundle?.getString("am")
        disAmt.text = "INR $amt"
        accNo.setText(acc)

        invYes.setOnClickListener {
            val nextInvList = Intent(this@InvestMoney, InvestList::class.java)
            nextInvList.putExtra("adr", adhar)
            nextInvList.putExtra("acno", acc)
            startActivity(nextInvList)
        }
        invNo.setOnClickListener {
            val prev = Intent(this@InvestMoney, Account::class.java)
            prev.putExtra("adhar", adhar)
            prev.putExtra("acc", acc)
            startActivity(prev)
        }
    }


}
