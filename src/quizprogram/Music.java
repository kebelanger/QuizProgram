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
        super("Music", QuestionType.MUSIC);
        
        // add disney Quiz
        Quiz myDisneyQuiz = new Quiz();
        myDisneyQuiz.setName("Disney Quiz");
        
        myDisneyQuiz.addQuestion(new Question(1, "TheLionKing-CircleOfLife.mp3", "The Lion King", QuestionType.MUSIC));
        myDisneyQuiz.addQuestion(new Question(2, "A_Whole_New_World.mp3", "Alladin", QuestionType.MUSIC));
        myDisneyQuiz.addQuestion(new Question(3, "Be_Prepared.mp3", "The Lion King", QuestionType.MUSIC));
        myDisneyQuiz.addQuestion(new Question(4, "Colors_of_the_wind.mp3", "Pocahontas", QuestionType.MUSIC));
        myDisneyQuiz.addQuestion(new Question(5, "Be_Our_Guest.mp3", "Beauty and the Beast", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(6, "Gaston.mp3", "Beauty and the Beast", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(7, "Hakuna_Matata.mp3", "The Lion King", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(8, "I_just_can't_wait_to_be_king.mp3", "The Lion King", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(9, "I'll_Make_A_Man.mp3", "Mulan", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(10, "Let_It_Go.mp3", "Frozen", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(11, "How_far_I'll_go.mp3", "Moana", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(12, "Part_Of_Your_World.mp3", "The Little Mirmaid", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(13, "Prince_Ali.mp3", "Alladin", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(14, "The_Bare_Necessities.mp3", "The Jungle Book", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(15, "Under_the_Sea.mp3", "The Little Mirmaid", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(16, "When_You_Wish.mp3", "Cinderella", QuestionType.MUSIC));
//        myDisneyQuiz.addQuestion(new Question(17, "You_got_a_friend.mp3", "Toy Story", QuestionType.MUSIC));
  
        addQuiz(myDisneyQuiz);
    }
}
