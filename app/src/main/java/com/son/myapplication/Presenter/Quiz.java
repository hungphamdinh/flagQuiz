package com.son.myapplication.Presenter;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.Toast;

import com.son.myapplication.Model.Question;
import com.son.myapplication.Model.QuizContract;
import com.son.myapplication.R;
import com.son.myapplication.View.activity_quiz;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {
    //private ArrayList<Question>questions=new ArrayList<>();
    private Question question;
    private int pos=0;
    private int highScore=0;
    private int grade=0;
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private SQLiteDatabase database;
    private int mode;
    private IQuizListener iQuizListener;
    private ArrayList<Question>questions;
    public Quiz(IQuizListener iQuizListener, HashMap<String,Object>initMap){
        this.iQuizListener=iQuizListener;
        database = CDatabase.initDatabase((Activity) initMap.get("activity"), DATABASE_NAME);
        mode= (int) initMap.get("mode");
        questions=getQuizQuestion(database);
    }
    public void checkAnswer(int idCheck)
    {
        switch (idCheck) {
            case R.id.rb1: {
                if (question.getA().compareTo(question.getAnswerNr()) == 0) {
                    grade = grade + 1;
                }
                pos++;
                break;
            }
            case R.id.rb2: {

                if (question.getB().compareTo(question.getAnswerNr()) == 0) {
                    grade = grade + 1;
                }
                pos++;
                break;
            }
            case R.id.rb3: {
                if (question.getC().compareTo(question.getAnswerNr()) == 0) {
                    grade = grade + 1;
                }
                pos++;
                break;
            }
            case R.id.rb4: {
                if (question.getD().compareTo(question.getAnswerNr()) == 0) {
                    grade = grade + 1;
                }
                pos++;
                break;
            }
            default: {
                pos++;
            }

        }
        HashMap<String,Object>displayMap=new HashMap<>();
        displayMap.put("pos",pos);
        displayMap.put("question",question);
        displayMap.put("grade",grade);
        //displayMap.put("mode",mode);
        //displayMap.put("database",database);
        iQuizListener.checkAnswer(displayMap);

    }

    public void display() {
        try {
            question = questions.get(pos);
            //mCountDown.start();
            HashMap<String,Object>checkMap=new HashMap<>();
            checkMap.put("gradeCk",grade);
            checkMap.put("posCk",pos);
            checkMap.put("modeCk",mode);
            checkMap.put("questionCk",question);
            checkMap.put("questionsCk",questions);
            //checkMap.put("databaseCk",database);
            iQuizListener.displayQuiz(checkMap);
        }
        catch (Exception e){
            Log.e("Error size", e.toString());
        }

    }


    private ArrayList<Question> getQuizQuestion(SQLiteDatabase database) {
        ArrayList<Question> questionList = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME + " WHERE mode= " + mode + " ORDER BY Random() ", null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                //question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setImage(c.getBlob(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setA(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_A)));
                question.setB(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_B)));
                question.setC(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_C)));
                question.setD(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_D)));
                question.setAnswerNr(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                question.setMode(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_MODE)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
    public void afterTest() {
        try {
//            ArrayList<Question> questions= (ArrayList<Question>) map.get("questions");
            if (pos >= questions.size()) {
                questions.clear();
                HashMap<String,Object>afterTestMap=new HashMap<>();
                afterTestMap.put("gradeAfter",grade);
                afterTestMap.put("posAfter",pos);
                afterTestMap.put("modeAfter",mode);
                afterTestMap.put("highScore",0);
                iQuizListener.afterTest(afterTestMap);
            }
        }
        catch (Exception e){
            Log.e("Error next btn",e.toString());
        }
    }
    public void takeHelper()
    {
        if(grade<5) {
            iQuizListener.checkErrorHelper("Your score will be more than 5");
        }
        else {
            checkHelper();


            grade = grade - 5;
        }
        iQuizListener.setScoreAfterHelp(grade);
    }
    public void checkHelper() {
        if(question.getA().compareTo(question.getAnswerNr()) != 0)
            iQuizListener.setHelp(0);
        if(question.getB().compareTo(question.getAnswerNr()) != 0)
            iQuizListener.setHelp(1);
        if(question.getC().compareTo(question.getAnswerNr()) != 0)
            iQuizListener.setHelp(2);
        if(question.getD().compareTo(question.getAnswerNr()) != 0)
            iQuizListener.setHelp(3);

    }


}
