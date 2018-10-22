package com.example.truefalsequiz;

public class Question {

    public Question(String question, boolean A) {
        this.question = question;
        this.A = A;
    }

    private String question;
    private boolean A;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean getAnswer() {
        return A;
    }

    public void setAnswer(boolean A) {
        this.A = A;
    }
    public boolean checkAnswer(boolean answer, String question)
    {
        if(answer == this.A);
        {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answer=" + A +
                '}';
    }
}


