package com.example.jack.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class QuizActivity extends Activity {

    private Button mButtonTrue;
    private Button mButtonFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mButtonTrue = (Button)findViewById(R.id.button_true);
        mButtonTrue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // public static Toast makeText(Context context, int resID, int duration)
                Toast.makeText(QuizActivity.this
                              ,R.string.toast_correct
                              ,Toast.LENGTH_SHORT).show();
            }
        });

        mButtonFalse = (Button)findViewById(R.id.button_false);
        mButtonFalse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // public static Toast makeText(Context context, int resID, int duration)
                Toast.makeText(QuizActivity.this
                        ,R.string.toast_incorrect
                        ,Toast.LENGTH_SHORT).show();
            }
        });
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
}
