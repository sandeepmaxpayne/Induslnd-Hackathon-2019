package com.sandeep.induslandbank.cash

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.*
import com.sandeep.induslandbank.R
import com.sandeep.induslandbank.UserAcc
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_otp_verify.*
import kotlinx.android.synthetic.main.activity_withdraw.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask


class Withdraw : AppCompatActivity() {
     var aadh: String? = null
    var accno: String? = null
    var amount: String? = null
    var userBal: String? = null
    var res: String? = null
    var currentamt: Int = 0
    var curcode: String? = null
    var uid: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_withdraw)
        Log.d("adh", "aa:$aadh")
        val bundle: Bundle? = intent.extras
        aadh = bundle?.getString("addar")
        uid = bundle?.getString("id")
        accno = bundle?.getString("useracc")
        Log.d("id","uuuis:$uid")
        userBal = bundle!!.getString("amt")
        currentamt = userBal!!.toInt()
        with_curr_bal.text = "Available Balance: $currentamt"
        aadharnum.setText(aadh)
        acc_no.setText(accno)
        //Log.d("with", "bal:$currentamt")
        val adapter = ArrayAdapter.createFromResource(this, R.array.currency_code, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spincode.adapter = adapter
        spincode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                curcode = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        btnwithdraw.setOnClickListener {
            aadh = aadharnum.text.toString()
             accno = acc_no.text.toString()
             amount = amt.text.toString()
             val dnarr = debnarr.text.toString()
            val crednarr = crnarr.text.toString()
            Log.d("res","AAA:$aadh")
            if (aadh?.length != 12) {
                displaywrongID()
                return@setOnClickListener
                //Toast.makeText(this, "Enter 12 digit aadhar number correctly", Toast.LENGTH_SHORT).show()
            } else if (accno!!.isEmpty().or(accno!!.length < 8).or(accno != uid)) {
                wrongAccountdisplay()
                return@setOnClickListener
               // Toast.makeText(this, "enter valid account number", Toast.LENGTH_SHORT).show()
            } else if (amount!!.isEmpty()) {
                Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show()
            }
            else{

                Log.d("adh", "aa:$aadh")
                if (currentamt < amount!!.toInt()){
                   // Toast.makeText(this@Withdraw, "You do not have sufficient fund!",Toast.LENGTH_SHORT).show()
                    displayNotSucc()
                    return@setOnClickListener
                }else {
                    currentamt -= amount!!.toInt()
                }

                val ref = FirebaseDatabase.getInstance().getReference()
                loadWithdrawDatabase(ref)
                displaySucc()
            }
        }

    }

    private fun loadWithdrawDatabase(firedata: DatabaseReference){

        val depWithdrawList: List<UserAcc> = mutableListOf(UserAcc(aadh!!, accno!!, currentamt.toString(), ""))
        depWithdrawList.forEach {
            var key = firedata.child("Amount/$aadh/").push().key
            key = accno
            it.auid = key!!
            firedata.child("Amount/$aadh/").child(key).setValue(it)

        }

    }
    private fun displaySucc(){
        val diagbuild = AlertDialog.Builder(this)
        diagbuild.setMessage("$curcode $amount Withdrawn Successfully")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener{
                _, _ ->  finish()
            })
        val alert = diagbuild.create()
        alert.setTitle("Transaction")
        alert.show()
    }
    private fun displayNotSucc(){
        val dialogMess = AlertDialog.Builder(this)
        dialogMess.setMessage("Insufficient Fund Available")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener{
                dialog, which -> finish()
            })
            .setNegativeButton("cancel", DialogInterface.OnClickListener {
                dialog, which -> dialog.cancel()
            })
        val alert = dialogMess.create()
        alert.setTitle("Transaction")
        alert.show()
    }
    private fun displaywrongID(){
        val dialogMess = AlertDialog.Builder(this)
        dialogMess.setMessage("Wrong Aadhar Number Detected")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener{
                    dialog, which -> finish()
            })
            .setNegativeButton("cancel", DialogInterface.OnClickListener {
                    dialog, which -> dialog.cancel()
            })
        val alert = dialogMess.create()
        alert.setTitle("Transaction")
        alert.show()
    }
    private fun wrongAccountdisplay(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Account Number Invalid")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener{
                    dialog, id -> finish()
            })
            .setNegativeButton("cancel", DialogInterface.OnClickListener{
                    dialog, which ->  dialog.cancel()
            })
        val alert = dialogbuilder.create()
        alert.setTitle("Transaction")
        alert.show()
    }

}




