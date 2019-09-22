package com.example.yassirfirstessay;



import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.muddzdev.styleabletoast.StyleableToast;
import com.skyfishjy.library.RippleBackground;

public class DragExperimentTouchListener implements View.OnTouchListener {
    float initX;
    private int mScreenWidthInPixel;
    private int mScreenWidthInDp;
    private float density;
    private RippleBackground rippleBack;
    private Context MainActivityContext;
    private boolean acceptedCall=false;
    private boolean refusedCall=false;
    //int screenWisdthInDp;


    public DragExperimentTouchListener(float initialX, float initialY, float refusePos, float acceptPos, float maxDelta,RippleBackground  ripple,Context context,float density) {

        initX= initialX ;

        lastX = initialX ;
        lastY = initialY ;
        this.acceptPos = acceptPos ;
        this.maxDelta = maxDelta ;
        this.refusePos = refusePos ;
        this.rippleBack = ripple;
        this.MainActivityContext =context;
        this.density = density;



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
    public boolean onTouch( View arg0, final MotionEvent arg1) {

        int action = arg1.getAction();
        if (action == MotionEvent.ACTION_DOWN && !isDragging) {
            isDragging = true;
            deltaX = arg1.getX() ;
            return true;
        } else if (isDragging) {
            if (action == MotionEvent.ACTION_MOVE) {

            System.out.println("initial x : "+arg0.getX() + "Screen width in dp : "+mScreenWidthInDp);



                float current = (arg0.getX() + arg1.getX() - deltaX) ;

                arg0.setX((arg0.getX() + arg1.getX() - deltaX) );
                arg0.setY(arg0.getY() );

                //traitement de toute les cas de image moving

                System.out.println("checkmarkPos:  "+acceptPos+"currentPos:  "+current+"refusePos:  "+refusePos);


                if(Math.round(arg0.getX() )!=Math.round(initX ))
                {
                    //when image moves ==> stop animation
                    rippleBack.stopRippleAnimation();
                }
                else
                {
                    rippleBack.startRippleAnimation();
                }


                if (Math.round(arg0.getX())>=Math.round(acceptPos+50) )
                {



                    MainActivity.img.setVisibility(View.INVISIBLE);
                    rippleBack.stopRippleAnimation();
                    rippleBack.setVisibility(View.INVISIBLE);

                    System.out.println("LastX = "+lastX+"AcceptPos"+acceptPos);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.example.android"));

                    arg0.getContext().startActivity(intent);

                    acceptedCall=true;




                        //Toast.makeText(arg0.getContext(), "Call accepted ", Toast.LENGTH_SHORT).show();
                    new StyleableToast
                            .Builder(arg0.getContext())

                            .text("Call accepted")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.BLUE)
                            .show();

                    rippleBack.stopRippleAnimation();

                    arg0.setX(acceptPos-50);


                }

                if(Math.round(arg0.getX())<=Math.round(refusePos-80))
                {
                    //call refused

                    refusedCall=true;
                    System.out.println("LastX = "+lastX+"AcceptPos"+acceptPos);
                    //Destroy the Activity
                    System.exit(0);



                    arg0.setX(refusePos-80);

                    new StyleableToast
                            .Builder(arg0.getContext())
                            .text("Call refused")
                            .textColor(Color.WHITE)
                            .backgroundColor(Color.RED)
                            .show();


                    //Toast.makeText(arg0.getContext(), "Call refused ", Toast.LENGTH_SHORT).show();

                }



                return true;
            } else if (action == MotionEvent.ACTION_UP) {
                isDragging = false;
                lastX = arg1.getX();
                lastY = arg1.getY();

                rippleBack.startRippleAnimation();
                if(Math.round(arg0.getX())<Math.round(acceptPos+50))
                {
                    arg0.setX(initX-arg0.getWidth()/2);
                }


                return true;
            } else if (action == MotionEvent.ACTION_CANCEL) {
                arg0.setX(lastX);
                arg0.setY(lastY);
                isDragging = false;
                MainActivity.img.setVisibility(View.INVISIBLE);
                return true;
            }


        }





        return false;
    }
}
