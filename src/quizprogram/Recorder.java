/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * Author: Kristen Belanger
 */
package quizprogram;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
 
// http://www.codejava.net/coding/capture-and-record-sound-into-wav-file-with-java-sound-api
public class Recorder {
 
    // path of the wav file
    private File wavFile;
 
    // the line from which audio data is captured
    private TargetDataLine line;
 
    /**
     * Defines an audio format
     */
    private AudioFormat getAudioFormat() {
        float sampleRate = 16000;
        int sampleSizeInBits = 8;
        int channels = 2;
        boolean signed = true;
        boolean bigEndian = true;
        AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
                                             channels, signed, bigEndian);
        return format;
    }
 
    private Path getPathToFile(String outputFileName) {
        // http://stackoverflow.com/questions/3153337/get-current-working-directory-in-java
        return Paths.get(System.getProperty("user.dir") + "/recordings/" + outputFileName);
    }
    
    /**
     * Captures the sound and record into a WAV file
     */
    public void start(String outputFileName) {
        try {
            // http://stackoverflow.com/questions/320542/how-to-get-the-path-of-a-running-jar-file
            // http://stackoverflow.com/questions/17540942/how-to-get-the-path-of-running-java-program
            Path path = getPathToFile(outputFileName);
            wavFile = path.toFile();
            
            // http://stackoverflow.com/questions/2833853/create-whole-path-automatically-when-writing-to-a-new-file
            if (path.toFile().exists()) {
                Files.delete(path);
            }
            Files.createDirectories(Paths.get(wavFile.getParent()));
            Files.createFile(path);
            
            AudioFormat format = getAudioFormat();
            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
 
            // checks if system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Line not supported");
                System.exit(0);
            }
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();   // start capturing
 
            System.out.println("Start capturing...");
            AudioInputStream ais = new AudioInputStream(line);
            System.out.println("Start recording...");
 
            // start recording
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, wavFile);
 
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
 
    /**
     * Closes the target data line to finish capturing and recording
     */
    public void finish() {
        line.stop();
        line.close();
        System.out.println("Finished");
    }
 
    public void playWavFile(String file) {
        // http://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
        try {
            File yourFile = getPathToFile(file).toFile();
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
            System.out.println("Failed to play the file.");
        }
    }
    
}