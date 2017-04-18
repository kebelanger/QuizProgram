/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizprogram;

/**
 * 
 * @author dianebelanger
 */
public class Question {
    private Integer questionNumber;
    private String question;
    private String answer;
    private QuestionType type;
    
    public Question(int questionNumber, String question, String answer, QuestionType type) {
        this.questionNumber = questionNumber;
        this.question = question;
        this.answer = answer;
        this.type = type;
    }
    
    public String getQuestion() {
        return question;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public QuestionType getQuestionType() {
        return type;
    }

}

