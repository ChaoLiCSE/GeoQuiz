package com.example.jack.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {

    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonCheat;

    private ImageButton mButtonNext;
    private ImageButton mButtonPrev;


    private TextView mTextViewQuestion;

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
        new TrueFalse(R.string.question_oceans, true),
        new TrueFalse(R.string.question_mideast, false),
        new TrueFalse(R.string.question_africa, false),
        new TrueFalse(R.string.question_americas, true),
        new TrueFalse(R.string.question_asia, true),
    };

    private int mCurrentIndex = 0;

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";


    private boolean mIsCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mTextViewQuestion = (TextView)findViewById(R.id.textview_question);
        mTextViewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mButtonTrue = (Button)findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mButtonFalse = (Button)findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mButtonNext = (ImageButton)findViewById(R.id.button_next);
        mButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mButtonPrev = (ImageButton)findViewById(R.id.button_prev);
        mButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIsCheater = false;
                prevQuestion();
            }
        });

        mButtonCheat = (Button)findViewById(R.id.button_cheat);
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(QuizActivity.this, CheatActivity.class);
                boolean isAnswerTrue = mQuestionBank[mCurrentIndex].isQuestionTrue();
                i.putExtra(CheatActivity.EXTRA_IS_ANSWER_TRUE, isAnswerTrue);
                startActivityForResult(i, 0);
            }
        });
        mButtonCheat = (Button)findViewById(R.id.button_cheat);


        if(savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_IS_ANSWER_SHOWN, false);
    }


    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mTextViewQuestion.setText(question);
    }

    private void prevQuestion() {
        mCurrentIndex = mCurrentIndex == 0 ? mQuestionBank.length - 1 : mCurrentIndex - 1 ;
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mTextViewQuestion.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean ansIsTrue = mQuestionBank[mCurrentIndex].isQuestionTrue();

        int msgResId;

        if(mIsCheater) {
            msgResId = R.string.toast_judgment;
        }
        else {
            if (userPressedTrue == ansIsTrue) {
                msgResId = R.string.toast_correct;
            } else {
                msgResId = R.string.toast_incorrect;
            }
        }
        Toast.makeText(this, msgResId, Toast.LENGTH_SHORT).show();

    }

}
