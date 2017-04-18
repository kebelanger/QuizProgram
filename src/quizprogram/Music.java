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
public class Music extends Subject {
    
    public Music() {
        super("Music");
        
        // add disney Quiz
        Quiz myDisneyQuiz = new Quiz();
        myDisneyQuiz.setName("Disney Quiz");
        
        myDisneyQuiz.addQuestion(new Question(1, "TheLionKing-CircleOfLife.mp3", "Circle of Life", QuestionType.MUSIC));
        
        addQuiz(myDisneyQuiz);
    }
}
