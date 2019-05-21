package com.sandeep.induslandbank.form_register

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sandeep.induslandbank.LoanUser
import com.sandeep.induslandbank.MainActivity
import com.sandeep.induslandbank.R
import com.sandeep.induslandbank.SaveUser
import kotlinx.android.synthetic.main.activity_loan_form.*
import kotlinx.android.synthetic.main.activity_savings_form.*
import kotlinx.android.synthetic.main.saving_status.*

class LoanForm : AppCompatActivity() {

    var dur:String? = null
    var lna:String? = null
    var dob1: String? = null
    var accop: String? = null
    var ltype: String? = null
    var j1: String? = null
    var j2: String? = null
    var j3: String? = null
    var lph: String? = null
    var telno: String? = null
    var fax: String? = null
    var eadd: String? = null
    var add1: String? = null
    var add2: String? = null
    var add3: String? = null
    var city: String? = null
    var state: String? = null
    var postcode: String? = null
    var countr: String? = null
    var adhar: String? = null
    var savaccno: String? = null
    var code: String? = null
    var type:String? = null
    var custid:String? = null
    var freestat:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_form)
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val accna = bundle.getString("na")
             lph = bundle.getString("p")
            savaccno = bundle.getString("ac")
            cust_id.setText(savaccno)
            loanaccname.setText(accna)
            loanph.setText(lph)
        }

        adhar = bundle?.getString("adhr")

        val adapter = ArrayAdapter.createFromResource(this, R.array.countrycode, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        curr_code.adapter = adapter
        curr_code.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                code = parent?.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        val adapter1 = ArrayAdapter.createFromResource(this, R.array.loantypes, android.R.layout.simple_spinner_item)
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        l_type.adapter = adapter1
        l_type.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                type = parent?.getItemAtPosition(position).toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }



        loanReg.setOnClickListener {
            try {
                performLoanRegister()
                Log.d("code", "email:$eadd")
                if (lna!!.isEmpty() || accop!!.isEmpty() || j1!!.isEmpty() || lph!!.isEmpty()
                    || eadd!!.isEmpty() || add1!!.isEmpty() || city!!.isEmpty() || state!!.isEmpty() ||
                    postcode!!.isEmpty() || countr!!.isEmpty()
                ) {
                    //   Log.d("code", "code:$code")
                    Toast.makeText(this, "Please enter the mandatory fields", Toast.LENGTH_SHORT).show()
                } else {
                    val ref = FirebaseDatabase.getInstance().getReference()
                    loadLoanData(ref)
                    val intent = Intent(this@LoanForm, MainActivity::class.java)
                    intent.putExtra("regAdhr", adhar)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)

                }
            }catch (e: Exception){
                Log.d("err", e.message)
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun performLoanRegister(){
        lna = loanaccname.text.toString()
        accop = loandateopen.text.toString()
        custid = cust_id.text.toString()
        j1 = loanjoin1.text.toString()
        j2 = loanjoin2.text.toString()
        j3 = loanjoin3.text.toString()
        dur = duration.text.toString()
        freestat = statcode.text.toString()
        lph = loanph.text.toString()
        eadd = loaneadr.text.toString()
        telno = loantenno.text.toString()
        fax = loanfax.text.toString()
        add1 = laddr1.text.toString()
        add2 = laddr2.text.toString()
        add3 = laddr3.text.toString()
        city = loancity.text.toString()
        state = loanstate.text.toString()
        postcode = loanpostcode.text.toString()
        countr = loancoun.text.toString()
    }
    private fun loadLoanData(firebasedata: DatabaseReference) {
        val availableUser: List<LoanUser> = mutableListOf(
            LoanUser(
                lna!!,
                custid!!,
                accop!!,
                j1!!,
                j2!!,
                j3!!,
                lph!!,
                telno!!,
                fax!!,
                eadd!!,
                add1!!,
                add2!!,
                add3!!,
                city!!,
                state!!,
                postcode!!,
                countr!!,
                type!!,
                code!!,
                ""

            )
        )
        availableUser.forEach {
            var key = firebasedata.child("Loan").push().key
            key = adhar
            it.luuid = key!!
            firebasedata.child("Loan").child(key).setValue(it)

//            val intent = Intent(this@SavingsForm, MainActivity::class.java)
//            startActivity(intent)

        }

    }

}
