package com.example.animsample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //variables
   // protected ImageView imageView;

     protected  ImageView ivlogo, ivslogan, ivfooter;
     protected TextView ivapp_name;
     Animation animationdown, animationright, animationleft;
     int SPLASH_DISPLAY_TIME = 3000;
    Boolean mShouldFinish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // hooks
        //imageView = findViewById(R.id.load_image);
        ivlogo = findViewById(R.id.ivlogo);
        ivapp_name = findViewById(R.id.ivapp_name);
        ivslogan = findViewById(R.id.ivslogan);
        ivfooter = findViewById(R.id.ivfooter);

        animationdown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_down);
        animationright = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_right);
        animationleft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_left);


        // ---- set anim
        ivlogo.setAnimation(animationdown);
        ivapp_name.setAnimation(animationright);
        ivfooter.setAnimation(animationright);
        ivslogan.setAnimation(animationleft);
        // finished anim





        // -------- Animation drawable start ------
//        AnimationDrawable animationDrawable  = (AnimationDrawable) imageView.getDrawable();
//        animationDrawable.start();

        // -------- Animation drawable End ------

        // -------- HANDLER start ------
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                Pair [] pairs = new Pair[2];
                pairs[0] =  new Pair<View,String>(ivlogo,"transition_logo");
                pairs[1] = new Pair<View,String>(ivapp_name,"transition_appName");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent,options.toBundle());
                    mShouldFinish = true;

                }
                //                startActivity(intent);
               // finish();
            }
        },SPLASH_DISPLAY_TIME );




    }
    // not working backpress solve later

    @Override
    protected void onStop() {
        super.onStop();
        if(mShouldFinish==true)
            finish();
    }
}
