package com.sandeep.induslandbank

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.res.FontResourcesParserCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var adhaarNumber: String? = null
    var adrNum: String? = null
    var ph: String? = null
    var fetchPhone:String? = null
    var userage:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle:Bundle? = intent.extras
        userage = bundle?.getString("agg")
        SignUp.setOnClickListener {
            val intent = Intent(this@MainActivity, Register::class.java)
            startActivity(intent)
        }
//        Log.d("a","AdNo1:$adrNum")
//        Log.d("a","adhar Num$adhaarNumber")
        if(bundle != null){
            adrNum = bundle.getString("regAdhr")
            //var adNum = findViewById<EditText>(R.id.adhharNumber)
            adhharNumber.setText(adrNum)
        }

        login.setOnClickListener {
            adhaarNumber = adhharNumber.text.toString()
//            if(bundle != null){
//                adrNum = bundle!!.getString("regAdhr")
//            }
           //            ph = bundle!!.getString("phno")
           // Log.d("phone", "ph:$ph")

            if (adhaarNumber!!.length != 12){
                Log.d("a","AdNo:$adhaarNumber")
                Toast.makeText(this@MainActivity, "Enter valid Adhaar Number", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(adhaarNumber!!.length == 12) {
                    fetchSavingUser()
                    fetchLoanUser()
                    val ref = FirebaseDatabase.getInstance().getReference("/users")
                    val userList = object: ValueEventListener{
                        override fun onDataChange(p0: DataSnapshot)  {
                            if(p0.exists()){
                                p0.children.forEach{
                                    //  Log.d("ph", it.toString())
                                    val data = it.getValue(User::class.java)
                                    val accc = data?.accno
                                 //   Log.d("acc","acoutn:$accc")
                                        if (it.key == (adhaarNumber)) {
                                            // Log.d("msg", "Please register")
                                            ph = data?.mobile
                                            Log.d("ph", "phone:$ph")
                                            Log.d("ph", "ad:$adhaarNumber")
                                            val intent = Intent(this@MainActivity, OtpVerify::class.java)
                                            intent.putExtra("phmain", ph)
                                            intent.putExtra("acc",accc)
                                            intent.putExtra("agg",userage)
                                            intent.putExtra("adhr", adhaarNumber)
                                            startActivity(intent)

                                    }
                                }
                            }else if(!(p0.exists())){
                                Toast.makeText(
                                    this@MainActivity,
                                    "No record found! Please Register",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            }
                        override fun onCancelled(p0: DatabaseError) {
                        }
                    }
                    ref.addValueEventListener(userList)
                }
            }
//        val bundle:Bundle? = intent.extras
//        adhaarNumber = bundle!!.getString("adhaar")
    }

  private fun fetchSavingUser(){
       Log.d("ph","pp$fetchPhone")
      val ref = FirebaseDatabase.getInstance().getReference("/Saving")
      val userList = object: ValueEventListener{
          override fun onDataChange(p0: DataSnapshot)  {
              if(p0.exists()){
                  p0.children.forEach{
                      //  Log.d("ph", it.toString())
                      val data = it.getValue(SaveUser::class.java)
                      val accc = data?.savacc
                      if (it.key == (adhaarNumber)) {
                          // Log.d("msg", "Please register")
                          ph = data?.savph
                          Log.d("ph", "phone:$ph")
                          Log.d("ph", "ad:$adhaarNumber")
                          val intent = Intent(this@MainActivity, OtpVerify::class.java)
                          intent.putExtra("phmain", ph)
                          intent.putExtra("acc",accc)
                          intent.putExtra("adhr", adhaarNumber)
                          startActivity(intent)

                      }else {
                          Toast.makeText(
                              this@MainActivity,
                              "No record found! Please Register",
                              Toast.LENGTH_SHORT
                          ).show()
                          // Log.d("ph", "phone:${data?.mobile}")
                      }

                  }
              }
          }
          override fun onCancelled(p0: DatabaseError) {
          }
      }
      ref.addValueEventListener(userList)
   }
    private fun fetchLoanUser(){
        val ref = FirebaseDatabase.getInstance().getReference("/Loan")
        val userList = object: ValueEventListener{
            override fun onDataChange(p0: DataSnapshot)  {
                if(p0.exists()){
                    p0.children.forEach{
                        //  Log.d("ph", it.toString())
                        val data = it.getValue(LoanUser::class.java)
                        val accc = data?.cid
                        if (it.key == (adhaarNumber)) {
                            // Log.d("msg", "Please register")
                            ph = data?.lph
                            Log.d("ph", "phone:$ph")
                            Log.d("ph", "ad:$adhaarNumber")
                            val intent = Intent(this@MainActivity, OtpVerify::class.java)
                            intent.putExtra("phmain", ph)
                            intent.putExtra("acc",accc)
                            intent.putExtra("adhr", adhaarNumber)
                            startActivity(intent)

                        }else {
                            Toast.makeText(
                                this@MainActivity,
                                "No record found! Please Register",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Log.d("ph", "phone:${data?.mobile}")
                        }
                    }
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        }
        ref.addValueEventListener(userList)
    }

}
