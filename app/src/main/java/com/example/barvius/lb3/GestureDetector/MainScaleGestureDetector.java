package com.example.barvius.lb3.GestureDetector;

import android.util.Log;
import android.view.ScaleGestureDetector;

import com.example.barvius.lb3.DrawView;

public class MainScaleGestureDetector extends android.view.ScaleGestureDetector.SimpleOnScaleGestureListener {
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        Log.i("scale", Float.toString(scaleGestureDetector.getScaleFactor()));
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if(DrawView.getInstance().getState() == DrawView.State.SCALE_CHANGE){
            DrawView.getInstance().setScaleFocusFigure(detector.getScaleFactor());
            DrawView.getInstance().invalidate();
        }
        return super.onScale(detector);
    }
}

