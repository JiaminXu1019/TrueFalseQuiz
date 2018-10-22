package com.example.truefalsequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private Button playAgain;
    private TextView finalScore;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        wireWidgets();

        Intent receiveIntent = getIntent();
        String score = receiveIntent.getStringExtra(TrueFalseQuiz.EXTRA_MESSAGE);
        finalScore.setText(getString(R.string.score_finalScore) + " " + score);

        playAgain.setText(R.string.score_PlayAgain);
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    private void wireWidgets() {
        playAgain = findViewById(R.id.button_score_playagain);
        finalScore = findViewById(R.id.textView_score_finalscore);
    }
}
