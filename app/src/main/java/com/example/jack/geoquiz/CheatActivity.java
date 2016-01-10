package com.example.jack.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class CheatActivity extends Activity {

    public static final String EXTRA_IS_ANSWER_TRUE =
            "com.example.jack.geoquiz.is_answer_true";
    public static final String EXTRA_IS_ANSWER_SHOWN =
            "com.example.jack.geoquiz.is_answer_shown";

    private boolean mIsAnswerTrue;

    private TextView mTextViewAnswer;
    private Button mButtonShowAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        setAnswerResultShown(false);
        mIsAnswerTrue = getIntent().getBooleanExtra(EXTRA_IS_ANSWER_TRUE, false);

        mTextViewAnswer = (TextView)findViewById(R.id.textview_answer);

        mButtonShowAnswer = (Button)findViewById(R.id.button_show_answer);
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsAnswerTrue) {
                    mTextViewAnswer.setText(R.string.button_true);
                }
                else {
                    mTextViewAnswer.setText(R.string.button_false);
                }
                setAnswerResultShown(true);
            }
        });
    }

    private void setAnswerResultShown(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_IS_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

}
