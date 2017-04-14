/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizprogram;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dianebelanger
 */
public class Quiz {
    private String name;
    private ArrayList<Question> questions = new ArrayList(); 
    
    // cant use int
    // http://stackoverflow.com/questions/1780385/java-hashmapstring-int-not-working
    private HashMap<Integer, Answer> answers = new HashMap<>();
    
    private float lastScore = 0;
    private float averageScore = 0;
    private int timesTaken = 0;
    
    public Quiz() {
        
    }

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public float getLastScore() {
        return lastScore;
    }
    
    public void setLastScore(float lastScore) {  
        this.lastScore = lastScore;
    }

    public float getAverageScore() {
        return averageScore;
    }
    
    public void setAverageScore(float averageScore) {  
        this.averageScore = averageScore;
    }
    
    public int getTimesTaken() {
        return timesTaken;
    }
    
    public void setTimesTaken(int timesTaken) {  
        this.timesTaken = timesTaken;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {  
        this.name = name;
    }

    public Question getQuestion(int index) {
        if (index < questions.size() && index >= 0) {
            return questions.get(index);
        } else {
            return null;
        }
    }
        
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public Answer getAnswer(Integer index) {
        return answers.get(index);
    }
    
    public HashMap<Integer, Answer> getAnswers() {
        return answers;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
    
    public void keepAnswer(Integer questionNumber, Answer answer) {
        answers.put(questionNumber, answer);
    }
}