package quizprogram;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dianebelanger
 */
public class Subject {
    private HashMap<String, Quiz> quizzes = new HashMap(); 
    private String subjectName;
    private QuestionType type;
    
    public Subject(String subjectName, QuestionType type) {
        this.subjectName = subjectName;
        this.type = type;
    }
   
    
    public String getName() {
        return subjectName;
    }
    
    public HashMap<String, Quiz> getQuizzes() {
        return quizzes;
    }

    public void addQuiz(Quiz quiz) {
        if (null == quizzes) {
            quizzes = new HashMap<>(); 
        }
        quizzes.put(quiz.getName(), quiz);
    }
    
    public Quiz getQuizWithName(String name) {
        return quizzes.get(name);
    }
    
    public Quiz deleteQuizWithName(String name) {
        return quizzes.remove(name);
    }

    public float getAverage() {
        int numQuizzes = 0;
        float totalAverages = 0;
        for (Quiz quiz: quizzes.values()) {
            totalAverages += quiz.getAverageScore();
            numQuizzes++;
        }
        if (numQuizzes == 0) {
            return 0.0f;
        }
        return totalAverages / numQuizzes;
    }
    
    public QuestionType getType() {
        return type;
    }
    public void setType(QuestionType type) {
        this.type = type;
    }
}


