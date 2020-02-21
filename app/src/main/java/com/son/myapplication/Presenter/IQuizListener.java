package com.son.myapplication.Presenter;

import com.son.myapplication.Model.Question;

import java.util.ArrayList;
import java.util.HashMap;

public interface IQuizListener {
    void checkAnswer(HashMap<String,Object> map);
    void displayQuiz(HashMap<String,Object>checkMap);
    void afterTest(HashMap<String,Object>mapAfter);
    void checkErrorHelper(String msg);
    void setScoreAfterHelp(int score);
    void setHelp(int help);
}
