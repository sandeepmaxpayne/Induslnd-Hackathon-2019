package com.sandeep.induslandbank.passwordless_login


import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

import com.google.android.gms.vision.CameraSource

import java.util.HashSet


 class GraphicOverlay(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mLock = Any()
    private var mPreviewWidth: Int = 0
    private var mWidthScaleFactor = 1.0f
    private var mPreviewHeight: Int = 0
    private var mHeightScaleFactor = 1.0f
    private var mFacing = CameraSource.CAMERA_FACING_FRONT
    private val mGraphics = HashSet<Graphic>()


    abstract class Graphic(private val mOverlay: GraphicOverlay) {


        abstract fun draw(canvas: Canvas)


        fun scaleX(horizontal: Float): Float {
            return horizontal * mOverlay.mWidthScaleFactor
        }

        fun scaleY(vertical: Float): Float {
            return vertical * mOverlay.mHeightScaleFactor
        }


        fun translateX(x: Float): Float {
            return if (mOverlay.mFacing == CameraSource.CAMERA_FACING_FRONT) {
                mOverlay.width - scaleX(x)
            } else {
                scaleX(x)
            }
        }


        fun translateY(y: Float): Float {
            return scaleY(y)
        }

        fun postInvalidate() {
            mOverlay.postInvalidate()
        }




    }


    fun clear() {
        synchronized(mLock) {
            mGraphics.clear()
        }
        postInvalidate()
    }


    fun add(graphic: Graphic) {
        synchronized(mLock) {
            mGraphics.add(graphic)
        }
        postInvalidate()
    }


    fun remove(graphic: Graphic) {
        synchronized(mLock) {
            mGraphics.remove(graphic)
        }
        postInvalidate()
    }


    fun setCameraInfo(previewWidth: Int, previewHeight: Int, facing: Int) {
        synchronized(mLock) {
            mPreviewWidth = previewWidth
            mPreviewHeight = previewHeight
            mFacing = facing
        }
        postInvalidate()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        synchronized(mLock) {
            if (mPreviewWidth != 0 && mPreviewHeight != 0) {
                mWidthScaleFactor = width.toFloat() / mPreviewWidth.toFloat()
                mHeightScaleFactor = height.toFloat() / mPreviewHeight.toFloat()
            }

            for (graphic in mGraphics) {
                graphic.draw(canvas)
            }
        }
    }
}
