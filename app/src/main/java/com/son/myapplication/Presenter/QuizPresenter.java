package com.son.myapplication.Presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.son.myapplication.Model.Question;
import com.son.myapplication.View.IQuizView;

import java.util.ArrayList;
import java.util.HashMap;

public class QuizPresenter implements IQuizListener{
    private IQuizView iQuizView;
    private Quiz mainInterator;
    public QuizPresenter(IQuizView iQuizView,HashMap<String,Object>initMap){
        this.iQuizView=iQuizView;
        mainInterator=new Quiz(this,initMap);
    }
    public void checkAns(int idCheck){
        mainInterator.checkAnswer(idCheck);
    }
    public void display(){
        mainInterator.display();
    }
    public void takeHelp(){
        mainInterator.takeHelper();
    }
    public void afterTestPre(){
        mainInterator.afterTest();
    }
    @Override
    public void checkAnswer(HashMap<String,Object> map) {
        iQuizView.checkAnswer(map);
    }

    @Override
    public void displayQuiz(HashMap<String,Object>checkMap) {
        iQuizView.displayQuiz(checkMap);
    }

    @Override
    public void afterTest(HashMap<String, Object> mapAfter) {
        iQuizView.afterTest(mapAfter);
    }

    @Override
    public void checkErrorHelper(String msg) {
        iQuizView.checkErrorHelper(msg);
    }

    @Override
    public void setScoreAfterHelp(int score) {
        iQuizView.getScoreAfterHelp(score);
    }

    @Override
    public void setHelp(int help) {
        iQuizView.setHelp(help);
    }
}
