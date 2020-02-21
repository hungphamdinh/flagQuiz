package com.son.myapplication.View;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.son.myapplication.R;

public class HighScoreActivity extends Activity {
    private MediaPlayer mediaPlayer;
    TextView HScoreEasy,HScoreMedium,HScoreHard;
    int highScore=0;
    Animation uptodown,downtoup;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        HScoreEasy=(TextView)findViewById(R.id.TxtHighScoreEasy);
        HScoreMedium=(TextView)findViewById(R.id.TxtHighScoreMedium);
        HScoreHard=(TextView)findViewById(R.id.TxtHighScoreHard);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        HScoreEasy.setAnimation(uptodown);
        HScoreMedium.setAnimation(uptodown);
        HScoreHard.setAnimation(downtoup);
        int songId = this.getRawResIdByName("truesong");
        this.mediaPlayer = MediaPlayer.create(this, songId);
        this.mediaPlayer.start();
        back=(Button)findViewById(R.id.btnBackHS);
        back.setAnimation(downtoup);
        //this.mediaPlayer.start();
        loadHighScoreEasy();
        loadHighScoreMedium();
        loadHighScoreHard();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private int getRawResIdByName(String truesong) {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(truesong, "raw", pkgName);
        return resID;
    }

    /*void loadHighScore()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if(sharedPreferences!=null)
            highScore=sharedPreferences.getInt("H",0);
        HScore.setText("HighScore: "+highScore);

    }*/
    void loadHighScoreEasy()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData1", Context.MODE_PRIVATE);
        if(sharedPreferences!=null)
            highScore=sharedPreferences.getInt("H",0);
        HScoreEasy.setText("Easy: "+highScore);

    }
    void loadHighScoreMedium()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData2", Context.MODE_PRIVATE);
        if(sharedPreferences!=null)
            highScore=sharedPreferences.getInt("H",0);
        HScoreMedium.setText("Medium: "+highScore);
    }
    void loadHighScoreHard()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData3", Context.MODE_PRIVATE);
        if(sharedPreferences!=null)
            highScore=sharedPreferences.getInt("H",0);
        HScoreHard.setText("Hard: "+highScore);

    }
}
