package com.sandeep.induslandbank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_saving_account.*
import kotlinx.android.synthetic.main.saving_status.*
import kotlinx.android.synthetic.main.saving_status.dob
import kotlinx.android.synthetic.main.saving_status.join1
import kotlinx.android.synthetic.main.saving_status.join2
import kotlinx.android.synthetic.main.saving_status.join3

class SavingAccount : AppCompatActivity() {

    var savadhaarNum: String? = null
    var savingNum: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saving_account)

        savstat.setOnClickListener {
            det()
            setContentView(R.layout.saving_status)
//            stat.text = "hello"
//            desc.text = "no"
            val ref = FirebaseDatabase.getInstance().getReference("/Saving")
            val userList = object: ValueEventListener {
                override fun onDataChange(p0: DataSnapshot)  {
                    if(p0.exists()){
                        p0.children.forEach{
                            //  Log.d("ph", it.toString())
                            val data = it.getValue(SaveUser::class.java)
                            val accc = data?.savacc
                            if (it.key == (savadhaarNum)) {
                                // Log.d("msg", "Please register")
                                stat.text = "status: Savings Account"
                                desc.text = "Description: No description"
                                dob.text = "Date of Birth: ${data?.dob}"
                                add2.text = "address2: ${data?.sdd2}"
                                join3.text = "join holder 3: ${data?.jholder3}"
                                add1.text = "address 1: ${data?.add1}"
                                join1.text = "join holder 1: ${data?.jholder1}"
                                join2.text = "join holder 2: ${data?.jholder2}"
                                emaddr.text = "Email Address: ${data?.email}"
                                solid.text = "Solid: None"
                                city.text = "City: ${data?.city}"
                                country.text = "Country: ${data?.countr}"
                                freezestatcode.text = "freeze code: 20"
                                bal.text = "Available Balance: "
                                telno.text = "telephone number: ${data?.telno}"
                                phoneno.text = "Phone Number: ${data?.savph}"
                                currcode.text = "Currency Code: ${data?.code}"
                                drawingpower.text = ""
                                faxno.text = "fax no: ${data?.fax}"
                                schemecode.text = "Schema Code: 12"
                                accstatus.text = "Account Statuc: Active"
                                custId.text = "Customer Id: ${savadhaarNum}"
                                statProv.text = "State: ${data?.state}"
                                accName.text = "Account Name: ${data?.na}"
                                postalCode.text = "Postal Code: ${data?.postcode}"
                                nomineename.text = "Nominee: not updated"
                                nomineedob.text = "not updated"
                                nominee.text = "nominee name: not updated"
                                nomineerelation.text = "not updated"
                            }
                            // Log.d("ph", "phone:${data?.mobile}")

                        }
                    }
                }
                override fun onCancelled(p0: DatabaseError) {
                }
            }
            ref.addValueEventListener(userList)

        }
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
                val intent = Intent(this@SavingAccount, Account::class.java)
                intent.putExtra("adhar",adhar)
                intent.putExtra("acc", accNum)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun det(){
        savadhaarNum = adhaarNum.text.toString()
        savingNum = savAccNum.text.toString()
    }
}
