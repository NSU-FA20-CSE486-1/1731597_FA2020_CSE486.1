package com.abirhossain.cse486.fall2020.quizz2;

public class fetchData {
    String id,english_word,bengali_word;

    public fetchData(String id, String english_word, String bengali_word) {
        this.id = id;
        this.english_word = english_word;
        this.bengali_word = bengali_word;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnglish_word() {
        return english_word;
    }

    public void setEnglish_word(String english_word) {
        this.english_word = english_word;
    }

    public String getBengali_word() {
        return bengali_word;
    }

    public void setBengali_word(String bengali_word) {
        this.bengali_word = bengali_word;
    }
}
