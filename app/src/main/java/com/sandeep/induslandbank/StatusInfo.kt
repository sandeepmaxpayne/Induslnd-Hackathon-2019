package com.sandeep.induslandbank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_status_info.*

class StatusInfo : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_info)
        val bundle:Bundle? = intent.extras
        val name = bundle?.getString("name")
        Log.d("name", "$name")
        showData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val bundle:Bundle? = intent.extras
        val adhar = bundle?.getString("adr")
        val accNum = bundle?.getString("AccNum")
        when(item?.itemId){
            R.id.home -> {
                val intent = Intent(this@StatusInfo, Account::class.java)
                intent.putExtra("adhar",adhar)
                intent.putExtra("acc", accNum)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
//    private fun fetchUser(){
//        val uid = FirebaseAuth.getInstance().uid
//        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")
//        ref.addListenerForSingleValueEvent(object: ValueEventListener{
//            override fun onDataChange(p0: DataSnapshot) {
//                 currentUser = p0.getValue(User::class.java)
//                Log.d("current", "currentUser:$currentUser")
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//
//            }
 //       })

    fun showData(){
        val bundle:Bundle? = intent.extras
        val name = bundle?.getString("name")
        val age = bundle?.getString("age")
        val amount = bundle?.getString("amt")
        val accNum = bundle?.getString("AccNum")
        val mob = bundle?.getString("mob")
        namest.text = "NAME: $name"
        agest.text = "AGE: $age"
        accnost.text = "ACCOUNT NUMBER: $accNum"
        mobilest.text = "MOBILE: $mob"
        Log.d("name", "$name")
        Log.d("name", "$age")
        stat.text = "CURRENT STATUS"
        acctypest.text = "TYPE: CURRENT"
        descst.text = "DESCRIPTION: Current account with balance $amount"
        flagst.text = "STATUS: ACTIVE"
    }

}


