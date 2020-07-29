package com.example.citizenquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button falseButton;
    private Button trueButton;
    private ImageButton nextButton;
    private ImageButton backButton;
    private TextView questionTextView;

    private int currentQuestionIndex = 0;

    private Question [] questionBank = new Question []  {
            new Question(R.string.question_declaration, true),
            new Question(R.string.born_question, false),
            new Question(R.string.voluntarily_question, true),
            new Question(R.string.foreign_question, true),
            new Question(R.string.immigration_question, false),
            new Question(R.string.aliens_question, false),
            new Question(R.string.illegally_question, false),
            new Question(R.string.knowingly_question, true)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        questionTextView = findViewById(R.id.answer_text_view);

        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.back_button);


        falseButton.setOnClickListener(this); //Register the button to listen to the clicks
        trueButton.setOnClickListener(this);

        nextButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                break;
            case R.id.true_button:
                checkAnswer(true);
                break;
            case R.id.next_button:
                currentQuestionIndex++;
                if(currentQuestionIndex == questionBank.length)
                    currentQuestionIndex = 0;
                updateQuestion();
                trueButton.setBackgroundColor(Color.WHITE);
                falseButton.setBackgroundColor(Color.WHITE);
                break;
            case R.id.back_button:
                currentQuestionIndex--;
                if(currentQuestionIndex == -1)
                    currentQuestionIndex = questionBank.length - 1;
                updateQuestion();
                trueButton.setBackgroundColor(Color.WHITE);
                falseButton.setBackgroundColor(Color.WHITE);
                break;
        }
    }

    private void updateQuestion() {
        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerId());
    }

    private void checkAnswer(boolean userAnswer) {
        boolean correctAnswer = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if(correctAnswer == userAnswer) {
            messageId = R.string.correct_answer;
            trueButton.setBackgroundColor(Color.GREEN);
            falseButton.setBackgroundColor(Color.WHITE);
        }
        else {
            messageId = R.string.wrong_answer;
            falseButton.setBackgroundColor(Color.RED);
            trueButton.setBackgroundColor(Color.WHITE);
        }

        Toast.makeText(MainActivity.this,  messageId, Toast.LENGTH_SHORT).show();
    }
}