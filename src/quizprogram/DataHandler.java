/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizprogram;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dianebelanger
 */
public class DataHandler {
    
    private HashMap<String, Subject> subjects = new HashMap<>();
        
    public Subject getSubjectWithName(String name) {
        return subjects.get(name);
    }
    
    public void deleteSubjectWithName(String name) {
        subjects.remove(name);
    }
    
    public void addSubject(Subject subject) {
        subjects.put(subject.getName(), subject);
    }
    
    public HashMap<String, Subject> getSubjects() {
        return subjects;
    }
   
    // adding the dependency for gson
    // where to put the jar
    // https://www.google.com/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=netbeans%20add%20dependency
    // already had the jar on my laptop from software engineering
    public void save(String fileName) {
        // create the file to save data to, if it doesnt exist.
        // http://stackoverflow.com/questions/9620683/java-fileoutputstream-create-file-if-not-exists
        try {
            File outputFile = new File(fileName);
            outputFile.createNewFile(); // if file already exists will do nothing 
            
            // write to file
            // http://stackoverflow.com/questions/1053467/how-do-i-save-a-string-to-a-text-file-using-java
            PrintWriter out = new PrintWriter(outputFile);
            Gson gson = new Gson();
            String output = gson.toJson(subjects);
            out.println(output);
            out.close();
        } catch(IOException ioe) {
            System.out.println("Save Failed");  
        }
    }
    
    // read data from file, if file doest not exist move on
    // http://stackoverflow.com/questions/5464631/java-read-a-file-if-it-doesnt-exist-create-it
    public void load(String fileName) {
        BufferedReader r = null;
        try {
            File file = new File(fileName);

            if (file.isFile()) {
                String data  = new String(Files.readAllBytes(Paths.get(fileName)));
                Gson gson = new Gson();
                
                // reference for special list deserializing
                // http://stackoverflow.com/questions/5554217/google-gson-deserialize-listclass-object-generic-type
                Type map = new TypeToken<HashMap<String, Subject>>(){}.getType();
                subjects = gson.fromJson(data, map);
                if (null == subjects) {
                    subjects = new HashMap<>();
                }
            }
        } catch (IOException ioe) { 
            System.out.println("Read Failed");  
        }
    }
}
