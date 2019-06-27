package com.sandeep.induslandbank.passwordless_login

import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.Tracker
import com.google.android.gms.vision.face.Face


class GraphicFaceTracker internal constructor(private val mOverlay: GraphicOverlay) : Tracker<Face>() {
    private val mFaceGraphic: FaceGraphic = FaceGraphic(mOverlay)

    override fun onNewItem(faceId: Int, item: Face?) {
        mFaceGraphic.setId(faceId)
    }

    override fun onUpdate(p0: Detector.Detections<Face>?, face: Face?) {
        mOverlay.add(mFaceGraphic)
        face?.let { mFaceGraphic.updateFace(it) }
        //        logFaceData(face);
    }




    override fun onMissing(p0: Detector.Detections<Face>?) {
        mOverlay.remove(mFaceGraphic)
    }

    override fun onDone() {
        mOverlay.remove(mFaceGraphic)
    }
}