package com.sandeep.induslandbank.passwordless_login

import android.accounts.Account
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log



import com.google.android.gms.vision.face.Face
import android.content.Intent

internal class FaceGraphic(overlay: GraphicOverlay) : GraphicOverlay.Graphic(overlay) {
    private val mFacePositionPaint: Paint
    private val mIdPaint: Paint
    private val mBoxPaint: Paint
    var colorName: String? = null
    @Volatile private var mFace: Face? = null
     var mFaceId: Int = 0
    private val mFaceHappiness: Float = 0.toFloat()




    init {

        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.size
        val selectedColor = COLOR_CHOICES[mCurrentColorIndex]
        if (selectedColor == Color.BLUE){
            colorName = "BLUE"
            mFaceId = mFaceId
        }else if (selectedColor == Color.CYAN){
            colorName = "CYAN"
        }else if (selectedColor == Color.GREEN){
            colorName = "GREEN"
        }else if (selectedColor == Color.MAGENTA){
            colorName = "MAGENTA"
        }else if (selectedColor == Color.RED){
            colorName = "RED"
        }else if (selectedColor == Color.WHITE){
            colorName = "WHITE"
        }else if (selectedColor == Color.YELLOW){
            colorName = "YELLOW"
        }

        mFacePositionPaint = Paint()
        mFacePositionPaint.color = selectedColor
        mIdPaint = Paint()
        mIdPaint.color = selectedColor
        mIdPaint.textSize = ID_TEXT_SIZE
        mBoxPaint = Paint()
        mBoxPaint.color = selectedColor
        mBoxPaint.style = Paint.Style.STROKE
        mBoxPaint.strokeWidth = BOX_STROKE_WIDTH
    }

    fun setId(id: Int) {
        mFaceId = id
    }

    fun updateFace(face: Face) {
        mFace = face
        postInvalidate()
        logFaceData(mFace, object : FaceTrackingListener {
            override fun onFaceLeftMove() {

            }

            override fun onFaceRightMove() {

            }

            override fun onFaceUpMove() {

            }

            override fun onFaceDownMove() {

            }

            override fun onGoodSmile() {

            }

            override fun onEyeCloseError() {

            }

            override fun onMouthOpenError() {

            }

            override fun onMultipleFaceError() {

            }
        })
    }

    override fun draw(canvas: Canvas) {
        val face = mFace ?: return
        val x = translateX(face.position.x + face.width / 2)
        val y = translateY(face.position.y + face.height / 2)
        Log.e("Y", "" + y)
        Log.d("FaceId", "faceId:$mFaceId")
        Log.d("FaceId", "color:$colorName")


        canvas.drawCircle(x, y, FACE_POSITION_RADIUS, mFacePositionPaint)
        canvas.drawText("id: $mFaceId", x + ID_X_OFFSET, y + ID_Y_OFFSET, mIdPaint)
        /* canvas.drawText("happiness: " + String.format("%.2f", face.getIsSmilingProbability()), x - ID_X_OFFSET, y - ID_Y_OFFSET, mIdPaint);
        canvas.drawText("right eye: " + String.format("%.2f", face.getIsRightEyeOpenProbability()), x + ID_X_OFFSET * 2, y + ID_Y_OFFSET * 2, mIdPaint);
       */ canvas.drawText("left eye: " + String.format("%.2f", face.isLeftEyeOpenProbability), x - ID_X_OFFSET * 2, y - ID_Y_OFFSET * 2, mIdPaint)
        val xOffset = scaleX(face.width / 2.0f)
        val yOffset = scaleY(face.height / 2.0f)
        val left = x - xOffset
        val top = y - yOffset
        val right = x + xOffset
        val bottom = y + yOffset
        val EulerY = mFace!!.eulerY
        val EulerZ = mFace!!.eulerZ
        canvas.drawText("Euler Y: " + String.format("%.2f", EulerY), x - ID_X_OFFSET, y - ID_Y_OFFSET, mIdPaint)
        canvas.drawText("Euler Z: " + String.format("%.2f", EulerZ), x + ID_X_OFFSET * 2, y + ID_Y_OFFSET * 2, mIdPaint)
        Log.e("Right", "" + right)
        Log.e("Left", "" + left)
        Log.e("Top", "" + top)
        Log.e("Bottom", "" + bottom)
        canvas.drawRect(left, top, right, bottom, mBoxPaint)
        if (colorName == "GREEN" && mFaceId > 0.9){
            canvas.drawText("Face detected clearly\nLogin Success", x , y, mIdPaint)

        }
    }

    private fun logFaceData(mFaces: Face?, listener: FaceTrackingListener) {
        mFaces!!.isSmilingProbability
        val leftEyeOpenProbability: Float = mFaces.isLeftEyeOpenProbability
        val rightEyeOpenProbability: Float = mFaces.isRightEyeOpenProbability
        val eulerY: Float = mFaces.eulerY
        val eulerZ: Float = mFaces.eulerZ

        /* Log.e( "Tuts+ Face Detection", "Smiling: " + smilingProbability );
            Log.e( "Tuts+ Face Detection", "Left eye open: " + leftEyeOpenProbability );
            Log.e( "Tuts+ Face Detection", "Right eye open: " + rightEyeOpenProbability );
            Log.e( "Tuts+ Face Detection", "Euler Y: " + eulerY );*/
        Log.e("Tuts+ Face Detection", "Euler Z: " + eulerZ)

    }

    companion object {
        private const val FACE_POSITION_RADIUS = 10.0f
        private const val ID_TEXT_SIZE = 40.0f
        private const val ID_Y_OFFSET = 50.0f
        private const val ID_X_OFFSET = -50.0f
        private const val BOX_STROKE_WIDTH = 5.0f
        private val COLOR_CHOICES = intArrayOf(Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.RED, Color.WHITE, Color.YELLOW)
        private var mCurrentColorIndex = 0

        private lateinit var instance: FaceGraphic


    }



}

