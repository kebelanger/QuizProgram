/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizprogram;

import java.util.HashMap;

/**
 *
 * @author dianebelanger
 */
public enum QuestionType {
    BASIC("BASIC"),
    MUSIC("MUSIC"),
    GEOGRAPHY("GEOGRAPHY"),
    SPELLING("SPELLING");
    
    private String name;
    private static HashMap<String, QuestionType> stringToTypeMap = new HashMap<>();
    
    static {
        
        for (QuestionType type: QuestionType.values()) {
            stringToTypeMap.put(type.name, type);
        }
    }
    
    QuestionType(String name) {
        this.name = name;
    }
    
    public static QuestionType fromString(String name) {
        return stringToTypeMap.get(name);
    }
}
