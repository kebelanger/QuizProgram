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
public class Answer {
    private String answer;
    private Boolean isCorrect;
    
    public Answer(String answer) {
        this.answer = answer;
    }
    
    public String getAnswer() {
        return answer;
    }
    
    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    public Boolean getIsCorrect() {
        return isCorrect;
    }

}
