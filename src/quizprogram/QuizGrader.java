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
public class QuizGrader {
    
    public Boolean grade(String actualAnswer, String correctAnswer) {
        return correctAnswer.toLowerCase().equals(actualAnswer.toLowerCase());
    }
}
