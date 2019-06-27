package com.sandeep.induslandbank.invest

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sandeep.induslandbank.Account
import com.sandeep.induslandbank.R
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.activity_fixed_deposit_interest.*
import kotlinx.android.synthetic.main.activity_register.*

class FixedDepositInterest : AppCompatActivity() {

    var p:Int = 0
    var rate:Double = 0.0
    var i:Double = 0.0
    var year:String? = null
    var adr: String? = null
    var acn: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixed_deposit_interest)

        supportActionBar?.title = "Fixed Deposit"
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.mediumPink))

        val bundle: Bundle? = intent.extras
        adr = bundle?.getString("ad")
        acn = bundle?.getString("acno")

        val adapter = ArrayAdapter.createFromResource(this, R.array.interest_year, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        yearOption.adapter = adapter
        yearOption.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                year = parent?.getItemIdAtPosition(position).toString()
                when(year){
                    "1" -> {

                            showRate.text = "Interest Rate offered is 8.5%"
                            p = fd.text.toString().toInt()
                            rate = 8.5
                            i = p*rate*1/100

                            if (p < 5000){
                                showLessDeposit()
                                showInt.text = ""
                            }else{
                                showInt.text = "Interest payable ₹$i"

                        }

                    }
                    "2" -> {
                       showRate.text = "Interest Rate offered is 8.60%"
                        p = fd.text.toString().toInt()
                        rate = 8.6
                        i = p*rate*2/100
                            if (p < 5000){
                                showLessDeposit()
                                showInt.text = ""
                            }else{
                                showInt.text = "Interest payable ₹$i"
                            }
                    }
                    "3" -> {
                       showRate.text = "Interest Rate offered is 8.75%"
                        p = fd.text.toString().toInt()
                        rate = 8.5
                        i = p*rate*3/100
                        if (p < 5000){
                            showLessDeposit()
                            year = null
                            showInt.text = ""
                        }else{
                            showInt.text = "Interest payable ₹$i"
                        }
                    }
                    "4" -> {
                        showRate.text = "Interest Rate offered is 8.8%"
                        p = fd.text.toString().toInt()
                        rate = 8.5
                        i = p*rate*4/100
                        if (p < 5000){
                            showLessDeposit()
                            year = null
                            showInt.text = ""
                        }else{
                            showInt.text = "Interest payable ₹$i"
                        }
                    }
                    ">=5" -> {
                        showRate.text = "Interest Rate offered is 9.0%"
                        p = fd.text.toString().toInt()
                        rate = 9.0
                        i = p*rate*5/100
                        if (p < 5000){
                            showLessDeposit()
                            year = null
                            showInt.text = ""
                        }else{
                            showInt.text = "Interest payable ₹$i"
                        }

                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        bank_contact.setOnClickListener {
            //Toast.makeText(this@FixedDepositInterest, "Details saved successfully, Bank will contact you shortly", Toast.LENGTH_SHORT).show()


            if (p > 5000){
               // dispAlert.text = "Details saved successfully, Bank will contact you Sortly "

                msg()

            }

        }
    }
    private fun showLessDeposit(){
        val dialogbuilder = AlertDialog.Builder(this)
        dialogbuilder.setMessage("Fixed deposit balance should be greater than ₹5000")
            .setCancelable(false)
            .setPositiveButton("done") { _, _ -> finish()
            }
            .setNegativeButton("cancel") { dialog, _ ->  dialog.cancel()
            }
        val alert = dialogbuilder.create()
        alert.setIcon(R.drawable.warnings)
        alert.setTitle("Deposit Balance is less than Minimum Balance")
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.home -> {
                val next = Intent(this@FixedDepositInterest, Account::class.java)
                next.putExtra("adhar", adr)
                next.putExtra("acc", acn)
                startActivity(next)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun msg(){
        val email = eid.text.toString()
        if (email.isEmpty()){
            dispAlert.text = "Please enter email to get notification about bank contact "
            return
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, adr!!) // adr is passord
            .addOnCompleteListener {
                if (!it.isSuccessful)
                {
                    return@addOnCompleteListener
                }else{
                    verifyEmail()
                  //  dispAlert.text = "Details saved successfully, Bank will contact you Sortly "

                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }

    }
    private fun verifyEmail(){
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener {
                if(it.isSuccessful){
                    val drawable = ContextCompat.getDrawable(baseContext, R.drawable.ribbon)?.apply {
                        setBounds(0,0,intrinsicWidth, intrinsicHeight)
                    }
                    dispAlert.setCompoundDrawables(drawable, null, null, null)
                    dispAlert.text = "Details saved successfully, Bank will contact you Sortly "
                }else{
                    dispAlert.text = "Sorry for inconvenience, Bank will technical issue occurred, Your record saved bank will send you notification shortly"
                }
            }
    }

}

