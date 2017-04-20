/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * Author: Kristen Belanger
 *
 */
package quizprogram;

/**
 *
 * @author dianebelanger
 */
public class Geography extends Subject {
    
    public Geography() {
        super("Geography", QuestionType.GEOGRAPHY);
        
        Quiz myUsaQuiz = new Quiz(QuestionType.GEOGRAPHY);
        myUsaQuiz.setName("USA Quiz");
        
        myUsaQuiz.addQuestion(new Question(1, "31.9686,-99.9018", "Texas", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(2, "36.7783,-119.4179", "California", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(3, "42.4072,-71.3824", "Massachusetts", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(4, "43.1939,-71.5724", "New Hampshire", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(5, "41.6032,-73.0877", "Connecticut", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(6, "45.2538,-69.4455", "Maine", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(7, "44.5588,-72.5778", "Vermont", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(8, "43.2994,-74.2179", "New York", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(9, "39.0458,-76.6413", "Maryland", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(10, "38.9108,-75.5277", "Delaware", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(11, "40.0583,-74.4057", "New Jersey", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(12, "41.2033,-77.1945", "Pennsylvania", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(13, "37.4316,-78.6569", "Virginia", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(14, "35.7596,-79.0193", "North Carolina", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(15, "33.8361,-81.1637", "South Carolina", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(16, "38.5976,-80.4549", "West Virginia", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(17, "32.3182,-86.9023", "Alabama", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(18, "32.1656,-82.9001", "Georgia", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(19, "27.6648,-81.5158", "Florida", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(20, "32.3547,-89.3985", "Mississippi", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(21, "40.4173,-82.9071", "Ohio", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(22, "37.8393,-84.2700", "Kentucky", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(23, "35.5175,-86.5804", "Tennessee", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(24, "44.3148,-85.6024", "Michigan", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(25, "40.6331,-89.3985", "Illinois", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(26, "40.2672,-86.1349", "Indiana", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(27, "43.7844,-88.7879", "Wisconsin", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(28, "46.7296,-94.6859", "Minnesota", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(29, "41.8780,-93.0977", "Iowa", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(30, "35.2010,-91.8318", "Arkansas", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(31, "35.0078,-97.0929", "Oklahoma", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(32, "39.0119,-98.4842", "Kansas", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(33, "41.4925,-99.9018", "Nebraska", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(34, "47.5515,-101.0020", "North Dakota", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(35, "43.9695,-99.9018", "South Dakota", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(36, "43.0760,-107.2903", "Wyoming", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(37, "39.5501,-105.7821", "Colorado", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(38, "46.8797,-110.3626", "Montana", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(39, "34.5199,-105.8701", "New Mexico", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(40, "44.0682,-114.7420", "Idaho", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(41, "39.3210,-111.0937", "Utah", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(42, "34.0489,-111.0937", "Arizona", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(43, "38.8026,-116.4194", "Nevada", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(44, "47.7511,-120.7401", "Washington", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(45, "43.8041,-120.5542", "Oregon", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(46, "37.9643,-91.8318", "Missouri", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(47, "30.9843,-91.9623", "Louisiana", QuestionType.GEOGRAPHY));
        myUsaQuiz.addQuestion(new Question(48, "41.5801,-71.4774", "Rhode Island", QuestionType.GEOGRAPHY));

        addQuiz(myUsaQuiz);
    }
}

