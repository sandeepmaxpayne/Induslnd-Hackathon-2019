package com.sandeep.induslandbank.shop_items

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.sandeep.induslandbank.R


class Kart : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kart)
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.yellow))
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.goback, menu)
        return super.onCreateOptionsMenu(menu)


    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){

            R.id.back -> {
                val next = Intent(this@Kart, Category::class.java)
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("addh")
                val accNum = bundle?.getString("accno")
                next.putExtra("addar",adhar)
                next.putExtra("useracc", accNum)
                startActivity(next)

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
