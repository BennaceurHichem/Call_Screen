package com.example.yassirfirstessay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ScaleGestureDetectorCompat;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity  {


        private  TextView src,srcChemin,dest,destChemin,costMessage,costValue,time ;
        private ImageButton destCircle,srcCircle;
        private ImageView fleche;
        private View view1,view2;
        private boolean stayShaked;
        private RippleBackground rippleBack;
        private int xDelta;
        private int yDelta;
        private ViewGroup mainLayout;
        private ImageView img;
        private GestureDetector mGestureDetector;


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
        img= findViewById(R.id.user_img);

        //ConstrainLayout isntatiation
        mainLayout =  (ConstraintLayout) findViewById(R.id.root);

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
        img.setOnTouchListener(new DragExperimentTouchListener(img.getX(), img.getY()));
          onShakeView(img);
          //img.getBackgroundTintMode("repeat");













    }


    public void onShakeView(View v) {
        Animation shake;
        int shaketrue;
        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        shake.setRepeatCount(Animation.INFINITE);

            v.startAnimation(shake); // starts animation




    }


    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                      ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams)
                            view.getLayoutParams();

                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;

                    case MotionEvent.ACTION_UP:
                        Toast.makeText(MainActivity.this, "thanks for new location!", Toast.LENGTH_SHORT)
                         .show();
                    break;

                    case MotionEvent.ACTION_MOVE:
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x- xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = -50;
                        layoutParams.bottomMargin = -50;
                        view.setLayoutParams(layoutParams);
                        break;





                }
                mainLayout.invalidate();
                return true;
            }
        };
    }
}
