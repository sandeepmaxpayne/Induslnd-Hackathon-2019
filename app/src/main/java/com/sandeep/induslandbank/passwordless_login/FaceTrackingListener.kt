package com.sandeep.induslandbank.passwordless_login

interface FaceTrackingListener {
    fun onFaceLeftMove()
    fun onFaceRightMove()
    fun onFaceUpMove()
    fun onFaceDownMove()
    fun onGoodSmile()
    fun onEyeCloseError()
    fun onMouthOpenError()
    fun onMultipleFaceError()

}