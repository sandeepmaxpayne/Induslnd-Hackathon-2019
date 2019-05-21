package com.sandeep.induslandbank

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class User(
    val uid: String,
    val userName: String,
    val age: String,
    val mobile: String,
    val accno: String,
    var uuid: String
) {
    constructor() : this("", "", "", "", "", "")
}

class SaveUser(
    val na: String,
    val savacc: String,
    val dob: String,
    val accop: String,
    val jholder1: String,
    val jholder2: String,
    val jholder3: String,
    val savph: String,
    val telno: String,
    val fax: String,
    val email: String,
    val add1: String,
    val sdd2: String,
    val add3: String,
    val city: String,
    val state: String,
    val postcode: String,
    val countr: String,
    val code: String,
    var uuid: String
) {
    constructor() : this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "","","")

}

class SavingNom(val nomname:String, val nomDob:String, val nomRel:String, val nomPh:String, var uid:String){
    constructor(): this("", "", "", "", "")
}

class UserAcc(val aaadhar:String, val accountNo: String, val amt:String, var auid:String){
    constructor(): this("", "", "", "")
}

class LoanUser(
    val lna: String,
    val cid: String,
    val laccop: String,
    val ljholder1: String,
    val ljholder2: String,
    val ljholder3: String,
    val lph: String,
    val ltelno: String,
    val lfax: String,
    val lemail: String,
    val ladd1: String,
    val lsdd2: String,
    val ladd3: String,
    val lcity: String,
    val lstate: String,
    val lpostcode: String,
    val lcountr: String,
    val ltype: String,
    val lcode: String,
    var luuid: String
){
    constructor(): this("", "", "", "", "","","","","","","","","","","","","","","","")
}
