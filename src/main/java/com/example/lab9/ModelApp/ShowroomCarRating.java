package com.example.lab9.ModelApp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowroomCarRating {
   private double score;
   private String review;


   // @JsonProperty definiuje nazwę która będzie zmapowana na zmienną podczas serializacji
    public ShowroomCarRating(@JsonProperty("score") double score, @JsonProperty("review") String review) {
        this.score = score;
        this.review = review;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
