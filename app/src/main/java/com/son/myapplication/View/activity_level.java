package com.son.myapplication.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.son.myapplication.R;

public class activity_level extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    TextView Easy,Hard,Medium;
    Animation uptodown,downtoup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        Easy=(TextView) findViewById(R.id.txtEasy);
        Medium=(TextView) findViewById(R.id.txtMedium);
        Hard=(TextView)findViewById(R.id.txtHard);
        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        Easy.setAnimation(uptodown);
        Medium.setAnimation(uptodown);
        Hard.setAnimation(downtoup);
        int songId = this.getRawResIdByName("guitar");
        this.mediaPlayer=   MediaPlayer.create(this, songId);
        this.mediaPlayer.start();
        Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity_level.this,activity_quiz.class);
                Bundle bundle=new Bundle();
                bundle.putInt("mode",1);
                intent.putExtra("Level",bundle);
                startActivity(intent);
                finish();

            }
        });
        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity_level.this,activity_quiz.class);
                Bundle bundle=new Bundle();
                bundle.putInt("mode",2);
                intent.putExtra("Level",bundle);
                startActivity(intent);
                finish();
            }
        });
        Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity_level.this,activity_quiz.class);
                Bundle bundle=new Bundle();
                bundle.putInt("mode",3);
                intent.putExtra("Level",bundle);
                startActivity(intent);
                finish();
            }
        });

    }

    private int getRawResIdByName(String guitar) {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(guitar, "raw", pkgName);
        return resID;
    }
}

