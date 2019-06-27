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
import kotlinx.android.synthetic.main.activity_loan_account.*
import kotlinx.android.synthetic.main.loan_status.*
import kotlinx.android.synthetic.main.loan_status.loanaccname

class LoanAccount : AppCompatActivity() {
    var loanadhar: String? = null
    var loanaccno: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_account)
        loan_status.setOnClickListener {
            detail()
            setContentView(R.layout.loan_status)
            val ref = FirebaseDatabase.getInstance().getReference("/Loan")
            val user = object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()){
                        p0.children.forEach{
                            val data = it.getValue(LoanUser::class.java)
                            if (it.key == loanadhar){
                                loandesc.text = "Description: No new  description"
                                loanaccname.text = "Account Name: ${data?.lna}"
                                loanroi.text = "rate of ineterst: 18%"
                                loantype.text = "Loan Type: ${data?.ltype}"
                                loanoutstanding.text = "No Previous outstanding"
                                loanemithismonth.text = "Loan this month INR 5000"
                                loancustid.text = "Loan CustomerID: ${data?.cid}"
                                loancurrcode.text = "Loan Currency: ${data?.lcode}"
                                loanduration.text = "Duration: 5 month"
                                loanopendate.text = "loan open data: ${data?.laccop}"
                                accloan.text = "Accoutn Statuc: Active"
                                loanjhodername1.text = "Holder Name: ${data?.ljholder1}"
                                loanjholdername2.text = "Holder Name 2: ${data?.ljholder2}"
                                loanjholdername3.text = "Holder Name 3: ${data?.ljholder3}"
                                loanfreezestatcode.text = "Freeze Code: 15"
                                loanphno.text = "Phone Number: ${data?.lph}"
                                loantelno.text = "Telephone Number: ${data?.ltelno}"
                                loanfaxno.text = "Fax Number: ${data?.lfax}"
                                loanemaiaddr.text = "Address: "
                                loanadr1.text = "Address1: ${data?.ladd1}"
                                loanaddr2.text = "Address2: ${data?.lsdd2}"
                                loanaddr3.text = "Address3 :${data?.ladd3}"
                                loancity1.text = "City: ${data?.lcity}"
                                loanstateprov.text = "State: ${data?.lstate}"
                                loanpostalcode.text = "Postal Code: ${data?.lpostcode}"
                                loancountry.text ="Country: ${data?.lcountr}"
                            }
                        }
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }
            }
            ref.addValueEventListener(user)

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
                val intent = Intent(this@LoanAccount, Account::class.java)
                intent.putExtra("adhar",adhar)
                intent.putExtra("acc", accNum)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun detail(){
        loanaccno = loanaccNum.text.toString()
        loanadhar = loanadhaarNum.text.toString()

    }
}
