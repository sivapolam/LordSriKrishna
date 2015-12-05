package com.sivapolam.jesusthelord.data;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by pnaganjane001 on 17/11/15.
 */
public class QuizModel implements Serializable{

    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String answer;
    private String fullAnswer;

    public QuizModel(JSONObject object) {
        this.question = object.optString(JsonMap.QUESTION);
        this.option1 = object.optString(JsonMap.OPTION1);
        this.option2 = object.optString(JsonMap.OPTION2);
        this.option3 = object.optString(JsonMap.OPTION3);
        this.answer = object.optString(JsonMap.ANSWER);
        this.fullAnswer = object.optString(JsonMap.ANSWER_FULL);
    }

    public String getQuestion() {
        return question;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getAnswer() {
        return answer;
    }

    public String getFullAnswer() {
        return fullAnswer;
    }

    interface JsonMap{
        public static final String QUESTION = "question";
        public static final String OPTION1 = "option1";
        public static final String OPTION2 = "option2";
        public static final String OPTION3 = "option3";
        public static final String ANSWER = "answer";
        public static final String ANSWER_FULL = "full_answer";
    }

}
