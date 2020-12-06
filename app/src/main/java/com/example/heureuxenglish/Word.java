package com.example.heureuxenglish;

import java.util.ArrayList;

public class Word {
    private String wordInEnglish;
    private String wordInVietnamese;
    private ArrayList<Question> questions;
    private String exampleInEnglish;
    private String exampleInVietnamese;

    public Word(){
    }

    public Word(String wordInEnglish, String wordInVietnamese, ArrayList<Question> questions) {
        this.wordInEnglish = wordInEnglish;
        this.wordInVietnamese = wordInVietnamese;
        this.questions = questions;
    }

    public String getWordInEnglish() {
        return wordInEnglish;
    }

    public void setWordInEnglish(String wordInEnglish) {
        this.wordInEnglish = wordInEnglish;
    }

    public String getWordInVietnamese() {
        return wordInVietnamese;
    }

    public void setWordInVietnamese(String wordInVietnamese) {
        this.wordInVietnamese = wordInVietnamese;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getExampleInEnglish() {
        return exampleInEnglish;
    }

    public void setExampleInEnglish(String exampleInEnglish) {
        this.exampleInEnglish = exampleInEnglish;
    }

    public String getExampleInVietnamese() {
        return exampleInVietnamese;
    }

    public void setExampleInVietnamese(String exampleInVietnamese) {
        this.exampleInVietnamese = exampleInVietnamese;
    }
}
