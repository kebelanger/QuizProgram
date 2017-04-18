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
public class Geography extends Subject {
    
    public Geography() {
        super("Geography");
        
        // add disney Quiz
        Quiz myUsaQuiz = new Quiz();
        myUsaQuiz.setName("USA Quiz");
        
        myUsaQuiz.addQuestion(new Question(1, "31.9686,-99.9018", "Texas", QuestionType.GEOGRAPHY));
//        myDisneyQuiz.addQuestion(new Question(2, "YOUTUBE_URL_2", "Toy Story - You've got a Friend in Me"));
        
        addQuiz(myUsaQuiz);
    }
}

