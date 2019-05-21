package com.sandeep.induslandbank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.sandeep.induslandbank.Register
import com.sandeep.induslandbank.form_register.LoanForm
import com.sandeep.induslandbank.form_register.SavingsForm
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.saving_status.*

class Register : AppCompatActivity() {
    var adhaarNum: String? = null
    var name: String? = null
    var aage: String? = null
    var accountNum: String? = null
    var phno: String? = null
    var x: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val adapter = ArrayAdapter.createFromResource(this, R.array.acctype, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinneraccount.adapter = adapter
        spinneraccount.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
              //  btn.setOnClickListener {
                    Log.d("spin", "spinner:${parent?.getItemIdAtPosition(position).toString()}")
                    x = parent?.getItemAtPosition(position).toString()
                    when(x){
                        "Savings Account" -> {
                            Register.text = "Fill Savings Account Form"
                            Register.setOnClickListener {
                                val name = username.text.toString()
                                val aage = age.text.toString()
                                val adhar = adhaar.text.toString()
                                val ph = phoneNum.text.toString()
                                val regacc = accNo.text.toString()
                                if (adhar.length != 12){
                                    Toast.makeText(this@Register, "Please enter valid adhaar number", Toast.LENGTH_SHORT).show()
                                }else {
                                    val intent = Intent(this@Register, SavingsForm::class.java)
                                    intent.putExtra("na", name)
                                    intent.putExtra("adhr", adhar)
                                    intent.putExtra("ph", ph)
                                    intent.putExtra("ac",regacc)
                                    intent.putExtra("ag",aage)
                                    //  Log.d("Reg","Phone:$phno")
                                    startActivity(intent)
                                }
                            }
                        }
                        "Current Account" -> {
                            Register.text = "Register"
                            Register.setOnClickListener{
                                performRegister()
                                if (name!!.isEmpty() || (aage!!.isEmpty()) ){
                                    Toast.makeText(this@Register, "Please enter the field correctly", Toast.LENGTH_SHORT).show()

                                }
                                else if (adhaarNum!!.length != 12){
                                    Toast.makeText(this@Register,"Please Enter valid Adhaar Number", Toast.LENGTH_SHORT).show()

                                }
                                else if (accountNum!!.isBlank() || accountNum!!.length < 8){
                                    Toast.makeText(this@Register, "enter valid account number", Toast.LENGTH_SHORT).show()

                                }
                                else if (phno!!.length < 10){
                                    Toast.makeText(this@Register ,"Please enter 10 digit Phone Number", Toast.LENGTH_SHORT).show()
                                }else {
                                    //database()
                                    val ref = FirebaseDatabase.getInstance().getReference()
                                    loadDatabase(ref)
                                    val intent = Intent(this@Register, MainActivity::class.java)
                                    intent.putExtra("regAdhr", adhaarNum)
                                    intent.putExtra("aacno",accountNum)
                                    intent.putExtra("phno",phno)
                                    startActivity(intent)
                                }
                            }
                        }
                        "Loan Account" -> {
                            Register.text = "Fill Loan Account form"
                            val adhar = adhaar.text.toString()
                            val name = username.text.toString()
                            val ph = phoneNum.text.toString()
                            val regacc = accNo.text.toString()
                            Register.setOnClickListener {
                                val intent = Intent(this@Register, LoanForm::class.java)
                                intent.putExtra("adhr",adhar)
                                intent.putExtra("na",name)
                                intent.putExtra("p",ph)
                                intent.putExtra("ac",regacc)
                                startActivity(intent)
                            }
                        }
                    }

              //  }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    private fun performRegister(){
         name = username.text.toString()
         aage = age.text.toString()
        accountNum = accNo.text.toString()
        adhaarNum = adhaar.text.toString()
         phno = phoneNum.text.toString()

    }
   /* private fun database(){
        val ref = FirebaseDatabase.getInstance().getReference("/users/$adhaarNum/$name/$aage/$phno/$accountNum")
        val user = User(adhaarNum!!, name!!, aage!!, phno!!, accountNum!!)
        ref.setValue(user)
            .addOnCompleteListener {
                Log.d("reg", "Succefully registered")
            }
            .addOnFailureListener{
                Log.d("err",it.message)
            }


    }*/
    private fun loadDatabase(firebasedata: DatabaseReference){
            val availableUser: List<User> = mutableListOf(
                User(adhaarNum!!, name!!, aage!!, phno!!, accountNum!!,""))
            availableUser.forEach {
                var key = firebasedata.child("users").push().key
                key = adhaarNum
                it.uuid = key!!
                firebasedata.child("users").child(key).setValue(it)
            }
    }

}
