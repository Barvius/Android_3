package com.example.barvius.lb3.GestureDetector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.SeekBar;

import com.example.barvius.lb3.DrawView;
import com.example.barvius.lb3.Entity.Circle;
import com.example.barvius.lb3.Entity.Figure;
import com.example.barvius.lb3.Entity.Square;

public class MainGestureDetector extends GestureDetector.SimpleOnGestureListener {
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        int j = 0;
        if(DrawView.getInstance().getState() == DrawView.State.DRAW) {
            for (int i = 0 ;i < DrawView.getInstance().getFigures().size(); i++){
                if (DrawView.getInstance().getFigures().get(i).onArea(e.getX(), e.getY())) {

//                    DrawView.getInstance().setFocusFigure(i);
                    DrawView.getInstance().getFigures().get(i).changeColor();
                    DrawView.getInstance().getFigures().set(i, DrawView.getInstance().getFigures().get(i).next(DrawView.getInstance().getFigures().get(i).getX(),DrawView.getInstance().getFigures().get(i).getY()));

                    //DrawView.getInstance().e();
                    //i.changeColor();
                    //i = new Circle(i.getCoordinates().get('x'),i.getCoordinates().get('y'));
                    //i = i.next(i.getCoordinates().get('x'),i.getCoordinates().get('y'));
                    break;
                }
            }
//            for (Figure i : DrawView.getInstance().getFigures()) {
//                if (i.onArea(e.getX(), e.getY())) {
//
////                    DrawView.getInstance().setFocusFigure(i);
//                    i.changeColor();
//                    i = new Square(i.getX(),i.getY());
//
//                    //DrawView.getInstance().e();
//                    //i.changeColor();
//                    //i = new Circle(i.getCoordinates().get('x'),i.getCoordinates().get('y'));
//                    //i = i.next(i.getCoordinates().get('x'),i.getCoordinates().get('y'));
//                    break;
//                }
//            }
        }
        return super.onDoubleTap(e);
    }

    @Override
    public void onLongPress(MotionEvent e) {
        String[] colors = {"Альфа-канал", "Масштаб"};
        AlertDialog.Builder builder = new AlertDialog.Builder(DrawView.getInstance().getContext());
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0){
                    alphaDialog();
                }
                if(which==1){
                    DrawView.getInstance().setState(DrawView.State.SCALE_CHANGE);
                }
            }
        });
        for (Figure i : DrawView.getInstance().getFigures()) {
            if(i.onArea(e.getX(),e.getY())){
                DrawView.getInstance().setFocusFigure(i);
                builder.show();
                break;
            }
        }
        super.onLongPress(e);
    }

    public void alphaDialog() {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(DrawView.getInstance().getContext());
        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        final SeekBar seek = new SeekBar(DrawView.getInstance().getContext());
        seek.setMax( 255 );
        seek.setProgress(DrawView.getInstance().getFocusFigure().getAlpha());
        final AlertDialog handle = popDialog.create();
        handle.setTitle("Прозрачность");
        handle.setView(seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                DrawView.getInstance().getFocusFigure().setAlpha(progress);
                DrawView.getInstance().invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        handle.show();
    }
}
