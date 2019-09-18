package com.example.yassirfirstessay;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class DragExperimentTouchListener implements View.OnTouchListener {
    float initX;


    public DragExperimentTouchListener(float initialX, float initialY, float refusePos, float acceptPos, float maxDelta) {

        initX= initialX;

        lastX = initialX;
        lastY = initialY;
        this.acceptPos = acceptPos;
        this.maxDelta = maxDelta;
        this.refusePos = refusePos;


    }

    boolean isDragging = false;
    float lastX;
    float lastY;
    float deltaX;
    float refusePos;
    final float acceptPos;
    final float maxDelta;
    Context context;

    @Override
    public boolean onTouch(View arg0, MotionEvent arg1) {

        int action = arg1.getAction();
        if (action == MotionEvent.ACTION_DOWN && !isDragging) {
            isDragging = true;
            deltaX = arg1.getX();
            return true;
        } else if (isDragging) {
            if (action == MotionEvent.ACTION_MOVE) {

                float current = arg0.getX() + arg1.getX() - deltaX;

                arg0.setX(arg0.getX() + arg1.getX() - deltaX);
                arg0.setY(arg0.getY());

                //traitement de toute les cas de image moving

                System.out.println("checkmarkPos:  "+acceptPos+"currentPos:  "+current+"refusePos:  "+refusePos);


                if (arg0.getX()>=acceptPos)
                {
                    // accept the call case
                    System.out.println("LastX = "+lastX+"AcceptPos"+acceptPos);
                    Intent intent = new Intent(arg0.getContext(), Main2Activity.class);
                    arg0.getContext().startActivity(intent);
                    Toast.makeText(arg0.getContext(), "Call accepted ", Toast.LENGTH_SHORT).show();


                }
                if(arg0.getX()<=refusePos)
                {
                    System.out.println("LastX = "+lastX+"AcceptPos"+acceptPos);
                    Intent intent = new Intent(arg0.getContext(), Main2Activity.class);
                    arg0.getContext().startActivity(intent);


                    Toast.makeText(arg0.getContext(), "Call refused ", Toast.LENGTH_SHORT).show();

                }
                return true;
            } else if (action == MotionEvent.ACTION_UP) {
                isDragging = false;
                lastX = arg1.getX();
                lastY = arg1.getY();
                return true;
            } else if (action == MotionEvent.ACTION_CANCEL) {
                arg0.setX(lastX);
                arg0.setY(lastY);
                isDragging = false;
                return true;
            }

            if (lastX==acceptPos)
            {
                System.out.println("LastX = "+lastX+"AcceptPos"+acceptPos);
                //Intent intent = new Intent(context, TouchActivity.class);
                //context.startActivity(intent);
                Toast.makeText(context, "intent ..", Toast.LENGTH_SHORT).show();

            }
        }





        return false;
    }
}
