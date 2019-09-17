package com.example.yassirfirstessay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {


       private  TextView src,srcChemin,dest,destChemin,costMessage,costValue,time ;
        private ImageButton destCircle,srcCircle;
        private ImageView fleche;
        private View view1,view2;
        RoundedImageView riv;

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

        //Translation animation

        Animation animation = new TranslateAnimation(0, 100,0, 0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        costValue.startAnimation(animation);
        costValue.setVisibility(View.INVISIBLE);







    }
}
