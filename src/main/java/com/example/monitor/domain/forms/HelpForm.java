package com.example.monitor.domain.forms;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Administrator on 2016/10/20.
 */
public class HelpForm {

    @NotBlank
    @Length(max = 1000)
    private String question;
    @Length(max = 10000)
    private String answer;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
