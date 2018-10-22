package com.example.truefalsequiz;

import java.util.List;

public class Quiz {

    private List <Question> questions;
    private int currentQuestion;
    private int score;

    public Quiz(int score, List<Question> questions, int currentQuestion) {
        this.score = score;
        this.questions = questions;
        this.currentQuestion = currentQuestion;
    }


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public void getNextQuestion() {
        if (isAnotherQuestion()) {
            currentQuestion++;
        }
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "questions=" + questions +
                ", currentQuestion=" + currentQuestion +
                ", score=" + score +
                '}';
    }

    public boolean isAnotherQuestion() {
        if(currentQuestion < questions.size() - 1 )
        {
            return true;
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
