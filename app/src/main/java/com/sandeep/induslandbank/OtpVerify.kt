package com.sandeep.induslandbank

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_otp_verify.*
import kotlinx.android.synthetic.main.activity_register.*
import java.util.concurrent.TimeUnit

class OtpVerify : AppCompatActivity() {
    lateinit var callBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    lateinit var mauth: FirebaseAuth
    var verificationId = ""
    var phno: String? = null
    var adhNum: String? = null
    var aacc:String?  =null
    var userage: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)
        mauth = FirebaseAuth.getInstance()
        val bundle: Bundle? = intent.extras

        phno = bundle?.getString("phmain")
        adhNum = bundle?.getString("adhr")
        aacc = bundle?.getString("acc")
        userage = bundle?.getString("agg")
        verifyBtn.setOnClickListener {
            view: View? -> progress.visibility = View.VISIBLE
            verify()
            Log.d("phone", "phno:+91$phno")
        }
        authenticateBtn.setOnClickListener {
            view: View -> progress.visibility = View.INVISIBLE
            authenticate()
        }
    }
    private fun verificationCallBacks(){
        callBack = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                progress.visibility = View.INVISIBLE
                signIn(credential)
                Log.d("cred","$credential")
               //To change body of created functions use File | Settings | File Templates.
            }
            override fun onVerificationFailed(p0: FirebaseException?) {
                //To change body of created functions use File | Settings | File Templates.
                Log.d("error","$p0")
            }
            override fun onCodeSent(verification: String?, p1: PhoneAuthProvider.ForceResendingToken?) {
                super.onCodeSent(verification, p1)
                verificationId = verification.toString()
                Log.d("code","code:$verification")
                Log.d("error","code:$verificationId")
                progress.visibility = View.INVISIBLE
            }
        }
    }

    private fun verify(){
        verificationCallBacks()

        val rand = (111111 .. 999999).random()
        randomval.text = "Random OTP: ${rand.toString()}"
        verificationId = rand.toString()

        val phoneno = phno
       // Log.d("phone", "phno:+91$phoneno")
        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91$phoneno!!", 60, TimeUnit.SECONDS, this, callBack)
    //Log.d("error",PhoneAuthProvider.PHONE_SIGN_IN_METHOD)
    }
    private fun signIn(credential: PhoneAuthCredential){
        mauth.signInWithCredential(credential)
            .addOnCompleteListener {
                Log.d("error","$mauth")
                if (it.isSuccessful){
                    Toast.makeText(this, "Successfully LoggedIN", Toast.LENGTH_SHORT).show()
                //    val intent = Intent(this, Account::class.java)
                   // intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                //    startActivity(intent)

                }else{
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener{
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                Log.d("error","${it.message}")
            }
    }
    private fun authenticate(){
        try {



            var authotp = otp.text.toString()
         //   verificationId = "123456"
            // otp not able to send
            val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, authotp)
            Log.d("cred","$credential")
           signIn(credential)


            // TO be implemented later due to not proper android physical device
            if (verificationId.equals(authotp)){
                val intent = Intent(this@OtpVerify, Account::class.java)
                Log.d("acc", "$aacc")
                intent.putExtra("acc", aacc)
                intent.putExtra("adhar", adhNum)
                intent.putExtra("agg", userage)
              //  intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK.or(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }else{
                Toast.makeText(this@OtpVerify, "Cannot Create credential without either verification proof", Toast.LENGTH_SHORT).show()
            }


        }catch (e: Exception){
            Log.d("error", "error cause:${e.message}")
           // Toast.makeText(this@OtpVerify, "Cannot Create credential without either verification proof", Toast.LENGTH_SHORT).show()
        }
    }
}
