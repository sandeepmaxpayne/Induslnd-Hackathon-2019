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
import com.sandeep.induslandbank.MainActivity
import com.sandeep.induslandbank.R
import com.sandeep.induslandbank.SaveUser
import com.sandeep.induslandbank.SavingNom
import kotlinx.android.synthetic.main.activity_savings_form.*
import kotlinx.android.synthetic.main.activity_savings_form.accopendate
import kotlinx.android.synthetic.main.activity_savings_form.dob
import kotlinx.android.synthetic.main.activity_savings_form.join1
import kotlinx.android.synthetic.main.activity_savings_form.join2
import kotlinx.android.synthetic.main.activity_savings_form.join3
import kotlinx.android.synthetic.main.saving_status.*

class SavingsForm : AppCompatActivity() {

    //    var nomdob: String? = null
//    var nomna: String? = null
//    var nomrel: String? = null
//    var nomph: String? = null
    var name: String? = null
    var dob1: String? = null
    var accop: String? = null
    var j1: String? = null
    var j2: String? = null
    var j3: String? = null
    var savph: String? = null
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
    var userag:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savings_form)
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val accna = bundle.getString("na")
            val phone = bundle.getString("ph")
            userag = bundle.getString("ag")
            savaccno = bundle.getString("ac")
            sav_acc_no.setText(savaccno)
            accname.setText(accna)
            sph.setText(phone)

        }

        adhar = bundle?.getString("adhr")

        val adapter = ArrayAdapter.createFromResource(this, R.array.countrycode, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countcode.adapter = adapter
        countcode.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                code = parent?.getItemAtPosition(position).toString()

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


        // enterDetail()
        val nomadapter = ArrayAdapter.createFromResource(this, R.array.nomineestatus, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        nomineebool.adapter = nomadapter
        nomineebool.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val x = parent?.getItemAtPosition(position).toString()

                    if (x.equals("yes")) {

                            nomineeVisible()
                            val nomdob = nominnedob.text.toString()
                            val nomna = nomname.text.toString()
                            val nomrel = nomrelation.text.toString()
                            val nomph = nomineephone.text.toString()
                            if (nomdob.isEmpty() || nomna.isEmpty() || nomrel.isEmpty() || nomph.isEmpty()) {
                                Toast.makeText(this@SavingsForm, "Please enter the nominee field", Toast.LENGTH_SHORT)
                                    .show()
                            } else {

                                val ref = FirebaseDatabase.getInstance().getReference()
                                val fire: DatabaseReference = ref
                                val availableUser: List<SavingNom> =
                                    mutableListOf(SavingNom(nomna, nomdob, nomrel, nomph, ""))
                                availableUser.forEach {
                                    //                            val k = fire.child("Saving").push().key
//                            fire.child("Saving").child(k!!).setValue(it)
                                    fire.child("/Saving/$adhar").push().setValue(it)
//
                                }
                            }


                        } else {
                            nomineeInvisible()
                        }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        savReg.setOnClickListener {
            try {
               perRegister()
                Log.d("code", "email:$eadd")
                if (name!!.isEmpty() || dob1!!.isEmpty() || accop!!.isEmpty() || j1!!.isEmpty() || savph!!.isEmpty()
                    || eadd!!.isEmpty() || add1!!.isEmpty() || city!!.isEmpty() || state!!.isEmpty() ||
                    postcode!!.isEmpty() || countr!!.isEmpty()
                ) {
                 //   Log.d("code", "code:$code")
                    Toast.makeText(this, "Please enter the mandatory fields", Toast.LENGTH_SHORT).show()
                } else {
                    val ref = FirebaseDatabase.getInstance().getReference()
                    loadSavingData(ref)
                    val intent = Intent(this@SavingsForm, MainActivity::class.java)
                    intent.putExtra("regAdhr", adhar)
                    intent.putExtra("agg",userag)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)

                }
            }catch (e: Exception){
                Log.d("err", e.message)
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun perRegister()
    {
        name = accname.text.toString()
        dob1 = dob.text.toString()
        accop = accopendate.text.toString()
        j1 = join1.text.toString()
        j2 = join2.text.toString()
        j3 = join3.text.toString()
        savph = sph.text.toString()
        eadd = eadr.text.toString()
        telno = savtenno.text.toString()
        fax = savfax.text.toString()
        add1 = addr1.text.toString()
        add2 = addr2.text.toString()
        add3 = addr3.text.toString()
        city = savcity.text.toString()
        state = savstate.text.toString()
        postcode = savpostcode.text.toString()
        countr = coun.text.toString()
    }

    private fun nomineeVisible() {
        nominnedob.visibility = View.VISIBLE
        nomname.visibility = View.VISIBLE
        nomrelation.visibility = View.VISIBLE
        nomineephone.visibility = View.VISIBLE
    }

    private fun nomineeInvisible() {
        nominnedob.visibility = View.INVISIBLE
        nomname.visibility = View.INVISIBLE
        nomrelation.visibility = View.INVISIBLE
        nomineephone.visibility = View.INVISIBLE
    }

    private fun loadSavingData(firebasedata: DatabaseReference) {
        val availableUser: List<SaveUser> = mutableListOf(
            SaveUser(
                name!!,
                savaccno!!,
                dob1!!,
                accop!!,
                j1!!,
                j2!!,
                j3!!,
                savph!!,
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
                code!!,
                ""

            )
        )
        availableUser.forEach {
            var key = firebasedata.child("Saving").push().key
            key = adhar
            it.uuid = key!!
            firebasedata.child("Saving").child(key).setValue(it)

//            val intent = Intent(this@SavingsForm, MainActivity::class.java)
//            startActivity(intent)

        }

    }

}



