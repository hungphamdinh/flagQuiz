package com.son.myapplication.View;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.son.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    ImageView question;
    Animation uptodown,downtoup;
    Button highScore,quit;
    int highscore1=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question = findViewById(R.id.txtQuestion);
        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        highScore=(Button)findViewById(R.id.btnhighscore);
        quit=(Button)findViewById(R.id.btnQuit);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        buttonStartQuiz.setAnimation(downtoup);
        highScore.setAnimation(uptodown);
        quit.setAnimation(downtoup);
        final int songId = this.getRawResIdByName("myfile");
        this.mediaPlayer = MediaPlayer.create(this, songId);
        this.mediaPlayer.start();

        RelativeLayout r1 = (RelativeLayout) findViewById(R.id.main);
        AnimationDrawable anidr = (AnimationDrawable) r1.getBackground();
        anidr.setEnterFadeDuration(1000);
        anidr.setExitFadeDuration(4000);
        anidr.start();
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                highscore();
            }
        });
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private int getRawResIdByName(String myfile) {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(myfile, "raw", pkgName);
        return resID;
    }

    private void startQuiz() {
        this.mediaPlayer.pause();
        Intent intent = new Intent(MainActivity.this, activity_level.class);
        startActivity(intent);
    }
    private void highscore(){
        this.mediaPlayer.pause();
        Intent intent=new Intent(MainActivity.this,HighScoreActivity.class);
        startActivity(intent);
    }

}
