package com.sandeep.induslandbank

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.sandeep.induslandbank.cash.Deposit
import com.sandeep.induslandbank.cash.Withdraw
import com.sandeep.induslandbank.invest.InvestList
import com.sandeep.induslandbank.invest.InvestMoney
import com.sandeep.induslandbank.recharge_mobile.Recharge
import com.sandeep.induslandbank.shop_items.Category
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.saving_status.*
import kotlin.math.log
import kotlin.system.exitProcess

class Account : AppCompatActivity() {

    private var adhaarnum: String? = null
    var amount: String? = null
    var uid: String? = null
    var acount: String? = null
    var userage:String? = null
    var curr: String = "INR"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

       // loadDepositDatabase(ref)
        fetchUserBalance()

        val bundle: Bundle? = intent.extras
        adhaarnum = bundle?.getString("adhar")
        userage = bundle?.getString("agg")

        withdraw.setOnClickListener {

            fetchUserBalance()
            val intent = Intent(this@Account, Withdraw::class.java)
            intent.putExtra("amt", amount)
            intent.putExtra("id", uid)
            intent.putExtra("addar", adhaarnum)
            intent.putExtra("useracc", acount)
            startActivity(intent)
        }
        deposit.setOnClickListener {
            fetchUserBalance()
            val intent = Intent(this@Account, Deposit::class.java)
            intent.putExtra("amt", amount)
            intent.putExtra("id", uid)
            intent.putExtra("addar", adhaarnum)
            intent.putExtra("useracc", acount)
            startActivity(intent)
            Log.d("res", "depaccount$acount")

        }
        rch.setOnClickListener {
            fetchUserBalance()
            val intent = Intent(this@Account, Recharge::class.java)
            intent.putExtra("amt", amount)
            intent.putExtra("id", uid)
            intent.putExtra("useracc", acount)
            intent.putExtra("addar", adhaarnum)
            startActivity(intent)
        }
        appinfo.setOnClickListener {
            fetchUserBalance()
            Log.d("info", "amt:$amount, $uid, $acount, $adhaarnum")
            val intent = Intent(this@Account, Information::class.java)
            intent.putExtra("useracc", acount)
            intent.putExtra("addar", adhaarnum)
            startActivity(intent)

        }
        shopping.setOnClickListener {
            fetchUserBalance()
            val intent = Intent(this@Account, Category::class.java)
            intent.putExtra("useracc", acount)
            intent.putExtra("addar", adhaarnum)
            startActivity(intent)
        }
        investMny.setOnClickListener {
            val intent = Intent(this@Account, InvestMoney::class.java)
            intent.putExtra("useracc", acount)
            intent.putExtra("addar", adhaarnum)
            intent.putExtra("am", amount)
            startActivity(intent)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.stat -> {
                getLoanData()
                getSavingsData()
                val ref = FirebaseDatabase.getInstance().getReference("/users")
                val menuListener = object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {
                        if (p0.exists()) {
                            p0.children.forEach {
                                Log.d("message", it.toString())
                                val data = it.getValue(User::class.java)
                                Log.d("message", "user:${data?.userName}")
                                Log.d("message", "age:${data?.age}")
                                if (it.key.equals(adhaarnum)) {
                                    // Log.d("message", "${data?.userName} with id:${data?.uid}")
                                    val intent = Intent(this@Account, StatusInfo::class.java)
                                    intent.putExtra("name", data?.userName)
                                    intent.putExtra("age", data?.age)
                                    intent.putExtra("adr",adhaarnum)
                                    intent.putExtra("amt", amount)
                                    intent.putExtra("AccNum", data?.accno)
                                    intent.putExtra("mob", data?.mobile)
                                    startActivity(intent)
                                }
                            }
                        }else{
                            Toast.makeText(this@Account, "No status foung for current user", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        //To change body of created functions use File | Settings | File Templates.
                    }
                }
                ref.addValueEventListener(menuListener)
            }
            R.id.savingacc -> {
                val intent = Intent(this@Account, SavingAccount::class.java)
                intent.putExtra("adr",adhaarnum)
                intent.putExtra("amt", amount)
                intent.putExtra("AccNum", acount)
                startActivity(intent)
            }
            R.id.loanacc -> {
                val intent = Intent(this@Account, LoanAccount::class.java)
                intent.putExtra("adr",adhaarnum)
                intent.putExtra("amt", amount)
                intent.putExtra("AccNum", acount)
                startActivity(intent)
            }
            R.id.signout -> {
                val intent = Intent(this@Account, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            R.id.feed -> {
                feedback()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun fetchUserBalance() {
        val bundle: Bundle? = intent.extras
        acount = bundle?.getString("acc")
        adhaarnum = bundle?.getString("adhar")
      //  amount = bundle?.getString("amtuser")
      //  acount = bundle?.getString("acc")
        Log.d("aac","account$acount")
        Log.d("aac","adhaar$adhaarnum")
        val ref = FirebaseDatabase.getInstance().getReference("/Amount/$adhaarnum")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    p0.children.forEach {
                        Log.d("bal", it.toString())
                        val data = it.getValue(UserAcc::class.java)
                        Log.d("usr", "user:${data?.aaadhar}")
                        Log.d("usr", "sccc$acount")
                        if (it.key.equals(acount)) {
                            // Log.d("message", "${data?.userName} with id:${data?.uid}")
                            amount = data?.amt
                            uid = it.key

                        }
                    }
                }
                Log.d("res", "id$uid")
                amount = amount
                uid = uid
                accBal.text = "Available Balance: $curr $amount"
                Log.d("acc", "amt:$amount")
                val loadref = FirebaseDatabase.getInstance().getReference()
                if(amount == null){
                    loadDepositDatabase(loadref)
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                //To change body of created functions use File | Settings | File Templates.
            }
        }
        ref.addValueEventListener(menuListener)

    }

    private fun loadDepositDatabase(firebasedata: DatabaseReference) {
        val bundle: Bundle? = intent.extras
        acount = bundle?.getString("acc")
        adhaarnum = bundle?.getString("adhar")
//        Log.d("acc", "accccc:$acount")
       Log.d("acc", "adhaar:$adhaarnum")
        Log.d("acc", "acount:$acount")
        // Log.d("res","ad:$ad")
        val depositList: List<UserAcc> = mutableListOf(UserAcc(adhaarnum!!, acount!!, "1000", ""))
        depositList.forEach {
            var key = firebasedata.child("Amount/$adhaarnum").push().key
            key = acount
            it.auid = key!!
            firebasedata.child("Amount/$adhaarnum").child(key).setValue(it)
            Log.d("aiod", "$key")
            //firebasedata.child(key).setValue(it)
        }
    }
    private fun getLoanData(){
        val ref = FirebaseDatabase.getInstance().getReference("/Loan")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    p0.children.forEach {
                        Log.d("message", it.toString())
                        val data = it.getValue(LoanUser::class.java)
                        Log.d("message", "user:${data?.lna}")
                        if (it.key.equals(adhaarnum)) {
                            // Log.d("message", "${data?.userName} with id:${data?.uid}")
                            val intent = Intent(this@Account, StatusInfo::class.java)
                            intent.putExtra("name", data?.lna)
                           // intent.putExtra("age", data?.age)
                            intent.putExtra("AccNum", data?.cid)
                            intent.putExtra("adr",adhaarnum)
                            intent.putExtra("mob", data?.lph)
                            intent.putExtra("amt", amount)
                            startActivity(intent)
                        }
                    }
                }else{
                    Toast.makeText(this@Account, "No status foung for current user", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                //To change body of created functions use File | Settings | File Templates.
            }
        }
        ref.addValueEventListener(menuListener)
    }
    private fun getSavingsData(){
        val ref = FirebaseDatabase.getInstance().getReference("/Saving")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    p0.children.forEach {
                        Log.d("message", it.toString())
                        val data = it.getValue(SaveUser::class.java)
//                        Log.d("message", "user:${data?.na}")
//                        Log.d("message", "age:${data?.")
                        if (it.key.equals(adhaarnum)) {
                            // Log.d("message", "${data?.userName} with id:${data?.uid}")
                            val intent = Intent(this@Account, StatusInfo::class.java)
                            intent.putExtra("name", data?.na)
                           // intent.putExtra("age", data?.)
                            intent.putExtra("AccNum", data?.savacc)
                            intent.putExtra("amt", amount)
                            intent.putExtra("adr",adhaarnum)
                            intent.putExtra("mob", data?.savph)
                            intent.putExtra("age",userage)

                            startActivity(intent)
                        }
                    }
                }else{
                    Toast.makeText(this@Account, "No status foung for current user", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                //To change body of created functions use File | Settings | File Templates.
            }
        }
        ref.addValueEventListener(menuListener)
    }

    private fun feedback(){
        val userfeedback = arrayOf("Excellent Service", "Good Service", "better Service", "Should be more better", "liked the service", "bad service")
        val selectList = ArrayList<Int>()
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Feedback")
        builder.setMultiChoiceItems(userfeedback, null){
                dialog, which, isChecked ->  if (isChecked){
            selectList.add(which)
        }else  if (selectList.contains(which)){
            selectList.remove(Integer.valueOf(which))
        }
        }
        builder.setPositiveButton("Submit"){
                dialogInterface, i -> val selectItem = ArrayList<String>()
            for (j in selectItem.indices){
                selectItem.add(userfeedback[selectList[j]])
            }
           // Toast.makeText(this@Account, "Thanks for Feedback", Toast.LENGTH_SHORT).show()
            thanksAlert()
        }
        builder.show()
    }
    private fun thanksAlert(){
        val diagbuild = AlertDialog.Builder(this)
        diagbuild.setMessage("Thanks for your feedback")
            .setCancelable(true)
            .setPositiveButton("done", DialogInterface.OnClickListener { dialog, _ ->
                dialog.dismiss()
            })
        val alert = diagbuild.create()
        alert.setTitle("Your Valuable Feedback Submitted")
        alert.show()
    }
}

