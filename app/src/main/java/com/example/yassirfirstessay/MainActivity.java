package com.example.yassirfirstessay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity
{


    private TextView src, srcChemin, dest, destChemin, costMessage, costValue, time;
    private ImageButton destCircle, srcCircle;
    private ImageView fleche, accept, refuse;
    private View view1, view2;
    private boolean stayShaked;
    private RippleBackground rippleBack;
    private int xDelta;
    private int yDelta;
    private ViewGroup mainLayout;
    private ImageView img;
    private GestureDetector mGestureDetector;
    private int initialX,initialY,maxDelta,refusePos,acceptPos;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        src = findViewById(R.id.src_place);
        dest = findViewById(R.id.dest_place);
        srcChemin = findViewById(R.id.chemin_src);
        destChemin = findViewById(R.id.chemin_dest);
        srcCircle = findViewById(R.id.src_circle);
        destCircle = findViewById(R.id.dest_circle);
        fleche = findViewById(R.id.fleche);
        costMessage = findViewById(R.id.cost_message);
        costValue = findViewById(R.id.cost_value);
        time = findViewById(R.id.time);
        img = findViewById(R.id.user_img);
        accept = findViewById(R.id.accept);
        refuse = findViewById(R.id.refuse);

        //ConstrainLayout isntatiation
        mainLayout = (ConstraintLayout) findViewById(R.id.root);

        srcChemin.bringToFront();
        destChemin.bringToFront();
        srcCircle.bringToFront();
        destCircle.bringToFront();
        src.bringToFront();
        dest.bringToFront();
        fleche.bringToFront();
        time.bringToFront();


        costMessage.bringToFront();
        costValue.bringToFront();
        stayShaked = true;


        Drawable imgBack = img.getBackground();


        //rippleBack = (RippleBackground)img.getBackground();

        //rippleBack.setActivated(true);
        img.bringToFront();
        //Translation animation

        //Animation animation = new TranslateAnimation(0, 100,0, 0);
        //animation.setDuration(1000);
        //animation.setFillAfter(true);
        //costValue.startAnimation(animation);
        //costValue.setVisibility(View.INVISIBLE);


// Create an object of the Android_Gesture_Detector  Class
        //Android_Gesture_Detector  android_gesture_detector  =  new Android_Gesture_Detector();
// Create a GestureDetector
        // mGestureDetector = new GestureDetector(this, android_gesture_detector);

        // img.setOnTouchListener(onTouchListener());
// in onCreate of the containing view

        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // do something about the view's x and y. also
                // don't forget to remove this OnGlobalLayoutListener using removeOnGlobalLayoutListener in here.

                initialY = (int) img.getY();
                initialX = (int) img.getX()+img.getWidth()/2;
                final int[] location =  new int[2];
                //accept.getLocationOnScreen(location);


                acceptPos = (int) accept.getX()-40;
                refusePos = (int) refuse.getX()+refuse.getWidth();
                maxDelta = (int) acceptPos - initialX;
                DragExperimentTouchListener dragObject = new DragExperimentTouchListener(initialX, initialY, refusePos, acceptPos, maxDelta);
                img.setOnTouchListener(dragObject);


                float lastXImage = dragObject.lastX;
                float deltaX = dragObject.deltaX;


            }

    });







        onShakeView(img);
    }




















    public void onShakeView(View v) {
        Animation shake;
        int shaketrue;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        shake.setRepeatCount(Animation.INFINITE);

            v.startAnimation(shake); // starts animation




    }



}
