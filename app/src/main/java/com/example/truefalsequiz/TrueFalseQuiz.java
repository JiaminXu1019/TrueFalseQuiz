package com.example.truefalsequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TrueFalseQuiz extends AppCompatActivity implements View.OnClickListener {

    private TextView questionNumber;
    private TextView question;
    private Button buttonTrue;
    private Button buttonFalse;
    private TextView displayScore;
    private Quiz newQuiz;


    public static final String TAG = "TrueFalseQuizActivity";

    public static final String EXTRA_MESSAGE = "bob";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false_quiz);

        wireWidgets();
        setListeners();

        InputStream JSONFileInputStream = getResources().openRawResource(R.raw.questions); // getting XML
        String sJSON = readTextFile(JSONFileInputStream);

        // create a gson object
        Gson gson = new Gson();
// read json file into an array of questions
        Question[] questions = gson.fromJson(sJSON, Question[].class);
// convert array to a list using the Arrays utility class
        List<Question> questionList = Arrays.asList(questions);
        Collections.shuffle(questionList);
// verify that it read everything properly

        newQuiz = new Quiz(0, questionList, 0);
        question.setText(newQuiz.getQuestions().get(0).getQuestion() + "");
        questionNumber.setText((1 + newQuiz.getCurrentQuestion()) + ".");
        displayScore.setText(getString(R.string.quiz_initialQuizScore) + "  " + newQuiz.getScore() + "");

    }
    private void wireWidgets() {
        questionNumber = findViewById(R.id.textView_quiz_questionNumber);
        question = findViewById(R.id.textView_quiz_question);
        buttonTrue = findViewById(R.id.button_quiz_true);
        buttonFalse = findViewById(R.id.button_quiz_false);
        displayScore = findViewById(R.id.textView_quiz_score);

    }

    private void setListeners() {
        buttonTrue.setOnClickListener(this);
        buttonFalse.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_quiz_false:
                if(newQuiz.getQuestions().get(newQuiz.getCurrentQuestion()).getAnswer() == false){
                    Toast.makeText(this, R.string.quiz_correct, Toast.LENGTH_SHORT).show();
                    newQuiz.setScore(newQuiz.getScore() + 1);
                }
                else
                {
                    Toast.makeText(this, R.string.quiz_incorrect, Toast.LENGTH_SHORT).show();
                }
                if(newQuiz.isAnotherQuestion()) {
                    newQuiz.getNextQuestion();
                    questionNumber.setText((1 + newQuiz.getCurrentQuestion()) + ".");
                    question.setText(newQuiz.getQuestions().get(newQuiz.getCurrentQuestion()).getQuestion());
                    displayScore.setText("Score:  " + newQuiz.getScore());
                }
                else
                {
                    sendToEnd();
                    recreate();
                }
                    break;
                    case R.id.button_quiz_true:
                        if(newQuiz.getQuestions().get((newQuiz.getCurrentQuestion())).getAnswer() == true){
                            Toast.makeText(this, getString(R.string.quiz_correct), Toast.LENGTH_SHORT).show();
                            newQuiz.setScore(newQuiz.getScore() + 1);
                        }
                        else
                        {
                            Toast.makeText(this, getString(R.string.quiz_incorrect), Toast.LENGTH_SHORT).show();
                        }
                        if(newQuiz.isAnotherQuestion()) {
                            newQuiz.getNextQuestion();
                            questionNumber.setText((1 + newQuiz.getCurrentQuestion()) + ".");
                            question.setText(newQuiz.getQuestions().get(newQuiz.getCurrentQuestion()).getQuestion());
                            displayScore.setText("Score:  " + newQuiz.getScore());
                        }
                        else
                        {
                            sendToEnd();
                            recreate();
                        }
                break;
        }
    }

    private void sendToEnd() {

        Intent intentFinish = new Intent(this, ScoreActivity.class);
        String score = newQuiz.getScore() + "" ;
        intentFinish.putExtra(EXTRA_MESSAGE, score);
        startActivity(intentFinish);

    }


    public String readTextFile(InputStream inputStream) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        byte buf[] = new byte[1024];
        int len;
        try {
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {

        }
        return outputStream.toString();
    }
}
