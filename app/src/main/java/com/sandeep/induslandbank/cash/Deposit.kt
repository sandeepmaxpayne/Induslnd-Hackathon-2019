package com.sandeep.induslandbank.cash

import android.arch.lifecycle.ViewModelStore
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.Type
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sandeep.induslandbank.R
import com.sandeep.induslandbank.UserAcc
import kotlinx.android.synthetic.main.activity_deposit.*
import kotlinx.android.synthetic.main.activity_saving_account.*
import kotlinx.android.synthetic.main.activity_withdraw.*
import kotlinx.android.synthetic.main.saving_status.*
import java.lang.StringBuilder

class Deposit : AppCompatActivity() {

    var ad: String? = null
    var accno: String? = null
    var depamt: String? = null
    var depdebarr: String? = null
    var depcbarr: String? = null
    var currcode: String? = null
    var x = 0
    var y: String? = null
    var uid: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)
        val bundle: Bundle? = intent.extras
        ad = bundle?.getString("addar")
        accno = bundle?.getString("useracc")
        uid = bundle?.getString("id")
        y = bundle?.getString("amt")
        x = y!!.toInt()
       // Log.d("res", "y$y")
        dep_curr_bal.text = "Available Balance: $x"
        depositadhaar.setText(ad)
        deposit_acc_no.setText(accno)
        val adapter = ArrayAdapter.createFromResource(this, R.array.currency_code, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        depspincode.adapter = adapter
        depspincode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              currcode = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        btndeposit.setOnClickListener {
            ad = depositadhaar.text.toString()
            accno = deposit_acc_no.text.toString()
            depamt = deposit_amt.text.toString()

            //avbal = dep_curr_bal.text.toString()
            if (ad!!.length != 12){
                wrongIDdisplay()
                Toast.makeText(this@Deposit, "Please enter valid aahsar number", Toast.LENGTH_SHORT).show()
            }
            else if(accno!!.isEmpty().or(accno!!.length < 8).or(accno != uid)){
                wrongAccDisp()
                return@setOnClickListener
               // Toast.makeText(this@Deposit, "Please enter amount greater than 8 digits", Toast.LENGTH_SHORT).show()
            }
            else if(depamt!!.isEmpty()){
             Toast.makeText(this@Deposit, "Please enter amount", Toast.LENGTH_SHORT).show()
            }
//            else if (ad!!.length == 12 && (uid != ad)){
//
//                return@setOnClickListener
//            }
            else{
                 x  += depamt!!.toInt()
                Log.d("res","dep:$x")
                dep_curr_bal.text = "Available balance: $x"

                val ref = FirebaseDatabase.getInstance().getReference()
                loadDepositDatabase(ref)
                display()


            }
        }
    }
    private fun loadDepositDatabase(firebasedata: DatabaseReference){

        Log.d("res","ad:$ad")
        val depositList: List<UserAcc> = mutableListOf(UserAcc(ad!!, accno!!, x.toString(), ""))
        depositList.forEach {
            var key = firebasedata.child("Amount/$ad/").push().key
            key = accno
            it.auid = key!!
            Log.d("key","key:${it.auid}")
            firebasedata.child("Amount/$ad/").child(key).setValue(it)
            Log.d("aiod", "$key")
            //firebasedata.child(key).setValue(it)
        }


    }

  private fun display(){
      val dialogbuilder = AlertDialog.Builder(this)
      dialogbuilder.setMessage("Success $currcode $depamt deposited")
          .setCancelable(false)
          .setPositiveButton("done", DialogInterface.OnClickListener{
              dialog, id -> finish()
          })
      val alert = dialogbuilder.create()
      alert.setTitle("Transaction")
      alert.show()
  }
    private fun wrongIDdisplay(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Wrong Aadhar Number Detected")
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
    private fun wrongAccDisp(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Wrong Account Number Detected")
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
