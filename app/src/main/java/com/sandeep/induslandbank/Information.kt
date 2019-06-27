package com.sandeep.induslandbank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class Information : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {


        when(item?.itemId){
            R.id.home -> {
                val bundle:Bundle? = intent.extras
                val adhar = bundle?.getString("addar")
                val accNum = bundle?.getString("useracc")
                Log.d("info", "Adhr:$adhar $accNum")
                val intent = Intent(this@Information, Account::class.java)
                intent.putExtra("adhar",adhar)
                intent.putExtra("acc", accNum)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
