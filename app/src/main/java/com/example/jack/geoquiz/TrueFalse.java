package com.example.jack.geoquiz;

/**
 * Created by Jack on 1/5/2016.
 * Model
 */
public class TrueFalse {
    private int mQuestion;

    private boolean mQuestionTrue;

    public TrueFalse(int question, boolean questionTrue) {
        mQuestion = question;
        mQuestionTrue = questionTrue;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int question) {
        mQuestion = question;
    }

    public boolean isQuestionTrue() {
        return mQuestionTrue;
    }

    public void setQuestionTrue(boolean questionTrue) {
        mQuestionTrue = questionTrue;
    }




}
