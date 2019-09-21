package com.example.yassirfirstessay;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.muddzdev.styleabletoast.StyleableToast;
import com.skyfishjy.library.RippleBackground;

public class DragExperimentTouchListener implements View.OnTouchListener {
    float initX;
    private int mScreenWidthInPixel;
    private int mScreenWidthInDp;
    private RippleBackground rippleBack;


    public DragExperimentTouchListener(float initialX, float initialY, float refusePos, float acceptPos, float maxDelta,RippleBackground  ripple) {

        initX= initialX;

        lastX = initialX;
        lastY = initialY;
        this.acceptPos = acceptPos;
        this.maxDelta = maxDelta;
        this.refusePos = refusePos;
        this.rippleBack = ripple;


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


                //when image moves ==> stop animation
                rippleBack.stopRippleAnimation();


                float current = arg0.getX() + arg1.getX() - deltaX;

                arg0.setX(arg0.getX() + arg1.getX() - deltaX);
                arg0.setY(arg0.getY());

                //traitement de toute les cas de image moving

                System.out.println("checkmarkPos:  "+acceptPos+"currentPos:  "+current+"refusePos:  "+refusePos);


                if (arg0.getX()>=acceptPos)
                {
                    //call accepted
                    System.out.println("LastX = "+lastX+"AcceptPos"+acceptPos);
                    Intent intent = new Intent(arg0.getContext(), Main2Activity.class);
                    arg0.getContext().startActivity(intent);
                        //Toast.makeText(arg0.getContext(), "Call accepted ", Toast.LENGTH_SHORT).show();
                    new StyleableToast
                            .Builder(arg0.getContext())
                            .text("Call accepted")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.BLUE)
                            .show();
                    arg0.setX(acceptPos+20);


                }
                else
                {
                    if(arg0.getX()>initX-arg0.getWidth()/2){
                       // Animation animation = new TranslateAnimation(arg0.getX()-arg0.getWidth(), initX-arg0.getWidth()/2,0, 0);
                       // animation.setDuration(300);
                        //animation.setFillAfter(false);
                        //arg0.startAnimation(animation);

                    }


                }
                if(arg0.getX()<=refusePos)
                {
                    //call accepted
                    System.out.println("LastX = "+lastX+"AcceptPos"+acceptPos);
                    Intent intent = new Intent(arg0.getContext(), Main2Activity.class);
                    arg0.getContext().startActivity(intent);
                    arg0.setX(refusePos-80);

                    new StyleableToast
                            .Builder(arg0.getContext())
                            .text("Call refused")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();
                    //Toast.makeText(arg0.getContext(), "Call refused ", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    if(arg0.getX()<initX-arg0.getWidth()/2){
                        //in this bloc, we should return img to initial position


                    }

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


        }





        return false;
    }
}
