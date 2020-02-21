package com.son.myapplication.Model;

public class Question {
    //private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private String answerNr;
    private byte[] image;
    public int getMode() {
        return mode;
    }

    public void setMode(int level) {
        this.mode = mode;
    }

    private int mode;
    public Question() {
    }

    public Question(String a, String b, String c, String d, String answerNr,int mode,byte[]image) {
        //  this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answerNr = answerNr;
        this.mode=mode;
        this.image=image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] anh) {
        this.image = anh;
    }

    /*  public String getQuestion() {
          return question;
      }

      public void setQuestion(String question) {
          this.question = question;
      }
  */
    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }
    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
    public String getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(String answerNr) {
        this.answerNr = answerNr;
    }
}
