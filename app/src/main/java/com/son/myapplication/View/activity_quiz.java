package com.son.myapplication.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.son.myapplication.Model.Question;
import com.son.myapplication.Presenter.QuizPresenter;
import com.son.myapplication.R;

import java.util.HashMap;


public class activity_quiz extends AppCompatActivity implements IQuizView{
    private final static long INTERVAL = 1000;
    private final static long TIMEOUT = 15000;
    private MediaPlayer mediaPlayer;
    private Animation uptodown,downtoup;
    private ImageView txtQuestion;
    private TextView txtScore;
    private TextView btnHelp;
    private TextView txtCountDown;
    private RadioGroup rbGroup;
    private RadioButton rbA;
    private RadioButton rbB;
    private RadioButton rbC;
    private RadioButton rbD;
    private Button btnNext, btnBack;
   // private int highScore=0;
    private com.son.myapplication.Model.Question question;
    private QuizPresenter quizPresenter;
    CountDownTimer mCountDown;
    private int mode;
    int grade=0 ;
    private HashMap<String,Object> displayMap;
    private HashMap<String,Object>initMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        txtQuestion = findViewById(R.id.txtQuestion);
        txtScore = findViewById(R.id.txtViewScore);
        btnHelp = findViewById(R.id.txtHelp);
        rbGroup = findViewById(R.id.RG);
        rbA = findViewById(R.id.rb1);
        rbB = findViewById(R.id.rb2);
        rbC = findViewById(R.id.rb3);
        rbD = findViewById(R.id.rb4);
        btnNext = findViewById(R.id.btnNext);
        btnBack =findViewById(R.id.btnBack);
        displayMap=new HashMap<>();
        initMap=new HashMap<>();
        initDatabase();
        onClickBackBtn();
        setAnim();
        displayQuestion();
        this.mediaPlayer.pause();
        onClickNext(mode);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizPresenter.takeHelp();
            }
        });
    }

    private void initDatabase() {
        Intent callerintent = getIntent();
        final Bundle packagefromcaller = callerintent.getBundleExtra("Level");
        mode = packagefromcaller.getInt("mode");
        initMap.put("activity",this);
        initMap.put("mode",mode);
        quizPresenter=new QuizPresenter(this,initMap);
    }

    private void onClickBackBtn() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_quiz.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setAnim() {
        uptodown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downtoup=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        txtQuestion.setAnimation(uptodown);
        rbGroup.setAnimation(uptodown);
        btnNext.setAnimation(downtoup);
        btnBack.setAnimation(downtoup);
        int songId = this.getRawResIdByName("myfile");
        this.mediaPlayer=   MediaPlayer.create(this, songId);
        this.mediaPlayer.start();
    }

    private void displayQuestion() {
        quizPresenter.display();
    }

    private void onClickNext(final int mode) {
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Checkanswer();
                int idCheck = rbGroup.getCheckedRadioButtonId();
                quizPresenter.checkAns(idCheck);
                quizPresenter.afterTestPre();
//                afterTest(mode);
            }
        });
    }


    private int getRawResIdByName(String myfile) {
        String pkgName = this.getPackageName();
        int resID = this.getResources().getIdentifier(myfile, "raw", pkgName);
        return resID;
    }

    void saveHighScoreEasy(int highScore)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData1",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("H",highScore);
        //editor.putInt("Easy",1);
        editor.apply();

    }
    void saveHighScoreMedium(int highScore)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData2",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("H",highScore);
        //editor.putInt("Medium",2);
        editor.apply();

    }
    void saveHighScoreHard(int highScore)
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData3",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("H",highScore);
        //editor.putInt("Hard",3);
        editor.apply();

    }
    protected void onResume() {
        super.onResume();
        txtCountDown = findViewById(R.id.txtTime);
        mCountDown = new CountDownTimer(TIMEOUT, INTERVAL)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                //Display();
                txtCountDown.setText("0:"+millisUntilFinished/INTERVAL);
                if(millisUntilFinished<10000)
                {
                    //txtCountDown.setTextColor(Color.RED);
                    txtCountDown.setText("0:0"+millisUntilFinished/INTERVAL);
                }
            }

            @Override
            public void onFinish() {
                mCountDown.cancel();
                int idCheck = rbGroup.getCheckedRadioButtonId();
                quizPresenter.checkAns(idCheck);
                txtCountDown.setText("Hết giờ");
//                Display(grade);
                quizPresenter.display();
                mCountDown.start();
            }

        }
                .start();
    }

    @Override
    public void checkAnswer(HashMap<String,Object> map) {
        rbA.setEnabled(true);
        rbB.setEnabled(true);
        rbC.setEnabled(true);
        rbD.setEnabled(true);
        mCountDown.start();
        quizPresenter.display();
        //mode= (int) displayMap.get("mode");
//        Display(grade);
    }

    @Override
    public void displayQuiz(HashMap<String,Object>checkMap) {
       // questionList= (ArrayList<Question>) checkMap.get("questionsCk");
        Question question= (com.son.myapplication.Model.Question) checkMap.get("questionCk");
        rbA.setText(question.getA());
        rbB.setText(question.getB());
        rbC.setText(question.getC());
        rbD.setText(question.getD());
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(question.getImage(), 0, question.getImage().length);
        txtQuestion.setImageBitmap(bmHinhDaiDien);
        txtScore.setText("Score: " + checkMap.get("gradeCk"));
        mCountDown.start();
        rbGroup.clearCheck();

    }

    @Override
    public void afterTest(HashMap<String, Object> mapAfter) {
        int gradeAfter= (int) mapAfter.get("gradeAfter");
        int posAfter= (int) mapAfter.get("posAfter");
        int modeAfter=(int)mapAfter.get("modeAfter");
        int highScoreAfter= (int) mapAfter.get("highScore");
        Intent intent = new Intent(activity_quiz.this, activity_result.class);
        Bundle bundle = new Bundle();
        bundle.putInt("score", gradeAfter);
        bundle.putInt("position", posAfter);
        intent.putExtra("MyPackage", bundle);
        startActivity(intent);
        if (gradeAfter > highScoreAfter) {
            highScoreAfter = gradeAfter;
            if (modeAfter == 1) {
                saveHighScoreEasy(highScoreAfter);
            }
            if (modeAfter == 2) {
                saveHighScoreMedium(highScoreAfter);
            }
            if (modeAfter == 3) {
                saveHighScoreHard(highScoreAfter);
            }

        }
        finish();
    }

    @Override
    public void checkErrorHelper(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getScoreAfterHelp(int score) {
        txtScore.setText("Score: "+score);
    }

    @Override
    public void setHelp(int help) {
        if(help==0) {
            rbA.setEnabled(false);
        }
        if(help==1)
            rbB.setEnabled(false);
        if(help==2)
            rbC.setEnabled(false);
        if(help==3)
            rbD.setEnabled(false);
    }

}