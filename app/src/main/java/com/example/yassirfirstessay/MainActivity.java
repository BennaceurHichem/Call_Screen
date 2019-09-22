package com.example.yassirfirstessay;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.MediaRouteButton;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
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
    private ImageView right,left;
    public static CircularImageView img;
    private GestureDetector mGestureDetector;
    private int initialX,initialY,maxDelta,refusePos,acceptPos,rightPos,leftPos;
    private static RippleBackground rippleBackground;
    private int mScreenWidthInPixel;
    private int mScreenWidthInDp;
    private float mDensity,mLengthOfSlider;
    private Context mcontext;





    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
        right = findViewById(R.id.right_btn);
        left = findViewById(R.id.left_btn);

        rippleBackground = findViewById(R.id.content);


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
        img.bringToFront();

        mcontext = getApplicationContext();

        img.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // do something about the view's x and y. also
                // don't forget to remove this OnGlobalLayoutListener using removeOnGlobalLayoutListener in here.

                initialY = (int) img.getY();

                initialX = (int) img.getX()+img.getWidth()/2;

                final int[] location =  new int[2];
                //accept.getLocationOnScreen(location);
                rightPos = (int)left.getX();
                leftPos = (int) left.getX();
                acceptPos = (int) (rightPos+accept.getX())/2;
                refusePos = (int) (refuse.getX()+leftPos)/2;
                maxDelta =  acceptPos - initialX;

                mDensity = getResources().getDisplayMetrics().density;






                DragExperimentTouchListener dragObject = new DragExperimentTouchListener(initialX, initialY, refusePos, acceptPos, maxDelta,rippleBackground,mcontext,mDensity);
                img.setOnTouchListener(dragObject);


                float lastXImage = dragObject.lastX;
                float deltaX = dragObject.deltaX;


            }

    });


            //Rippling and shaking image
                    onShakeRippleView(img,rippleBackground);








    }




















    public void onShakeRippleView(View v,RippleBackground rippleBackground) {
        Animation shake;
        int shaketrue;

        shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);

        shake.setRepeatCount(Animation.INFINITE);

            v.startAnimation(shake); // starts animation
        rippleBackground.startRippleAnimation();




    }



}
