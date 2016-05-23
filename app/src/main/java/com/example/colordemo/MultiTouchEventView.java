package com.example.colordemo;

import android.content.Context;
import android.graphics.Canvas;

import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
* This class is use to get touch coordinate and draw circle according
 * to points and selected colors.
* created on 20 may by nikhil
* */

public class MultiTouchEventView extends View {
    private Paint paint = new Paint();
    public static List<DataInformation> pointsList = new ArrayList<DataInformation>();
    private int pointSize = 25;

    public MultiTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);}

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);

        //drawing circle according to x,y axis
        for(DataInformation dataInformation : pointsList){
            paint.setColor(dataInformation.color);
            canvas.drawCircle(dataInformation.x, dataInformation.y, pointSize , paint);
        }
        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                DataInformation pointData = new DataInformation();
                pointData.x = (int)event.getX();     //get x- coordinate
                pointData.y = (int)event.getY();     //get y- coordinate
                pointData.color = MainActivity.color;
                //add point in list only if radiobutton is selected
                if (MainActivity.radioColorGroup.getCheckedRadioButtonId() == -1)
                {   // no radio buttons are checked
                    Toast.makeText(getContext(),"Please select color to draw circle",Toast.LENGTH_SHORT).show();
                } else {
                    // one of the radio buttons is checked
                    pointsList.add(pointData);
                }

                invalidate();
                 {
                break;
            }
        }
        invalidate();
        return true;

    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        super.setOnLongClickListener(l);

        pointSize++;

    }
}