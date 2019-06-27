package com.sandeep.induslandbank.recharge_mobile

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
import kotlinx.android.synthetic.main.activity_recharge.*


class Recharge : AppCompatActivity() {

    var recadhaar: String? = null
    var raccno: String? = null
    var phaccno: String? = null
    var rnarr: String? = null
    var cnrr: String? = null
    var pan: String? = null
    var rechtype: String? = null
    var y: String? = null
    var curramt: Int = 0
    var rechid: String? = null
    var ramt: String? = null
//    var xx  = ""
//    var yy = ""
//    var msg = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recharge)
        val bundle: Bundle? = intent.extras
        recadhaar = bundle?.getString("addar")
        raccno = bundle?.getString("useracc")
        rechid = bundle?.getString("id")
        y = bundle?.getString("amt")
        curramt = y!!.toInt()
        balView.text = "Available Balance: $curramt"
        payaadhar.setText(recadhaar)
        recaccno.setText(raccno)
        val adapter = ArrayAdapter.createFromResource(this, R.array.recharge_type, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dthrech.adapter = adapter
        dthrech.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                rechtype = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        btn_rech.setOnClickListener {
            recadhaar = payaadhar.text.toString()
            raccno = recaccno.text.toString()
            ramt = rechamt.text.toString()
            phaccno = dthnum.text.toString()
            rnarr = rdrrnarr.text.toString()
            cnrr = rcrnarr.text.toString()
            pan = recpan.text.toString()


//            for(i in 0 until pan!!.length/2){
//                xx += pan!![i].toString()
//            }
//            for(i in pan!!.length/2 until pan!!.length-1){
//                yy += pan!![i].toString()
//            }
//            Log.d("xx", "${xx} y:$yy")

            if (recadhaar!!.length != 12) {
                displaywrongID()
              //  Toast.makeText(this@Recharge, "Please enter valid aadhar number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (raccno!!.isEmpty().or(raccno != rechid)) {
                wrongAccNo()
               // Toast.makeText(this@Recharge, "Please enter account number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (phaccno!!.isEmpty().or(phaccno!!.length < 10)) {
                Toast.makeText(
                    this@Recharge,
                    "Please enter valid DTH or mobile number not less than 10 digit",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            } else if (rnarr!!.isEmpty().or(cnrr!!.isEmpty())) {
                Toast.makeText(this@Recharge, "Please enter credit and debit narration", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (pan!!.isEmpty().or(pan!!.length != 10)) {
                Toast.makeText(this@Recharge, "Please enter Pan number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                if (curramt < ramt!!.toInt()) {
                    failure()
                    return@setOnClickListener
                } else {
                    curramt -= ramt!!.toInt()
                }
                val ref = FirebaseDatabase.getInstance().getReference()
                loadWithdrawDatabase(ref)
                displaySucc()
            }




        }


    }

    private fun loadWithdrawDatabase(firedata: DatabaseReference) {

        val depWithdrawList: List<UserAcc> = mutableListOf(UserAcc(recadhaar!!, raccno!!, curramt.toString(), ""))
        depWithdrawList.forEach {
            var key = firedata.child("Amount/$recadhaar/").push().key
            key = raccno
            it.auid = key!!
            firedata.child("Amount/$recadhaar/").child(key).setValue(it)

        }

    }

    private fun displaySucc() {
        val diagbuild = AlertDialog.Builder(this)
        diagbuild.setMessage("Recharge of ₹$ramt for your $rechtype with $phaccno done successfully")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener { _, _ ->
                finish()
            })
        val alert = diagbuild.create()
        alert.setTitle("RECHARGE SUCCESS")
        alert.setIcon(R.drawable.check_mark)
        alert.show()
    }
    private fun failure(){
        val dialogMess = AlertDialog.Builder(this)
        dialogMess.setMessage("Less Balance Available for Recharge")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener{
                    _,_ -> finish()
            })
            .setNegativeButton("cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogMess.create()
        alert.setTitle("RECHARGE FAILURE")
        alert.setIcon(R.drawable.cross)
        alert.show()
    }
    private fun displaywrongID(){
        val dialogMess = AlertDialog.Builder(this)
        dialogMess.setMessage("Wrong Aadhar Number Detected")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener{
                    _, _ -> finish()
            })
            .setNegativeButton("cancel", DialogInterface.OnClickListener {
                    dialog, _ -> dialog.cancel()
            })
        val alert = dialogMess.create()
        alert.setTitle("ERROR ID")
        alert.setIcon(R.drawable.warnings)
        alert.show()
    }
    private fun wrongAccNo(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Account Number Entered is invalid or not linked to aadhar number")
            .setCancelable(false)
            .setPositiveButton("done", DialogInterface.OnClickListener{
                    dialog, id -> finish()
            })
            .setNegativeButton("cancel", DialogInterface.OnClickListener{
                    dialog, which ->  dialog.cancel()
            })
        val alert = dialogbuilder.create()
        alert.setTitle("Account Error !")
        alert.setIcon(R.drawable.error)
        alert.show()
    }

}
