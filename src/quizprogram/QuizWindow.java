/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 * Author: Kristen Belanger
 */
package quizprogram;

import java.awt.Dimension;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import org.mp3transform.Decoder;

/**
 *
 * @author dianebelanger
 */
public class QuizWindow extends BaseWindow {

    private SubjectWindow subjectWindow;
    private QuizResultsWindow quizResultsWindow;
    private String subjectName;
    private String quizName;
    private Question currentQuestion;
    private Decoder musicPlayer;
    private Thread musicThread;
    
    private boolean revertSizeWhenDone = false;
    private int incomingWidth;
    private int incomingHeight;
    
    private ImageIcon mapImage;
    private Recorder recorder;
    
    private int numQuestionsToAsk;
    private HashSet<Integer> questionsAsked = new HashSet<>();
    
    /**
     * Creates new form QuizWindow
     */
    public QuizWindow(SubjectWindow subjectWindow, String subjectName, String quizName) {
        super();
        initComponents();
        this.mapLabel.setText("");
        this.mapLabel.setLocation(0, 0);
        this.mapLabel.setSize(new Dimension(QuizWindow.WIDTH, QuizWindow.HEIGHT));

        this.subjectWindow = subjectWindow;
        this.subjectName = subjectName;
        this.quizName = quizName;
        Quiz quiz = dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName);
        
        // for geography and music quizzes get a random question, otherwise just get the first
        if (QuestionType.GEOGRAPHY.equals(quiz.getQuestionType()) || QuestionType.MUSIC.equals(quiz.getQuestionType())) {
            currentQuestion = getRandomQuestion(quiz);
            
            // clear out any saved answers
            quiz.clearAnswers();
        } else {
            currentQuestion = quiz.getQuestion(0); 
        }

        this.recorder = new Recorder();

        setupWindow();
        setVisible(false);
    }
    
    private void setupWindow() {
        switch(currentQuestion.getQuestionType()) {
            case BASIC:
                String questionStr = currentQuestion.getQuestion();
                questionLabel2.setText("<html>" + questionStr + "</html>");
                break;
            case MUSIC:
                questionLabel2.setText("Name the movie!");
                // threading: http://stackoverflow.com/questions/23184559/mp3transform-playing-mp3s-on-a-separate-thread
                musicThread = new Thread(new Runnable() {
                    public void run()
                    {
                        try {
                            // http://stackoverflow.com/questions/6045384/playing-mp3-and-wav-in-java
                            // https://code.google.com/archive/p/mp3transform/
                            musicPlayer = new Decoder();                            
                            File file = new File("/Users/dianebelanger/NetBeansProjects/QuizProgram/songs/" + currentQuestion.getQuestion());
                            FileInputStream in = new FileInputStream(file);
                            BufferedInputStream bin = new BufferedInputStream(in, 128 * 1024);
                            System.out.println("Before Play");

                            musicPlayer.play(file.getName(), bin);
                            System.out.println("Done Playing");
                            in.close();

                            musicPlayer.stop();
                        } catch(IOException ioe) {
                            ioe.printStackTrace();
                            System.out.println("Failed to play the file.");
                        }
                    }
                });  
                musicThread.start();
                
                numQuestionsToAsk = 5;
                
                break;
            case SPELLING:
                questionLabel2.setText("Spell the word!");
                
                break;
            case GEOGRAPHY:
                // display map
                questionLabel2.setText("What state is this?");

                String destinationFile = "image.jpg";
                // https://developers.google.com/maps/documentation/static-maps
                try {
                    double scale = 1.2;
                    Double scaledWidth = WIDTH * scale;
                    Double scaledHeight = HEIGHT * scale;
                    String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                            + "center=39.8282,-95.5795"
                            + "&zoom=3"
                            + "&scale=2"
                            + "&size=350x238"
                            + "&maptype=roadmap"
                            + "&markers=size:small%7Ccolor:red%7C" + currentQuestion.getQuestion()
                            + "&style=feature:all|element:labels|visibility:off"
                            + "&style=feature:all|element:all|weight:1"
                            + "&key=AIzaSyAJjW_ZL-ZEkHpsIrZlVJgGPeniH-i_N1c";
                    URL url = new URL(imageUrl);
                    InputStream is = url.openStream();
                    OutputStream os = new FileOutputStream(destinationFile);

                    byte[] b = new byte[2048];
                    int length;

                    while ((length = is.read(b)) != -1) {
                        os.write(b, 0, length);
                    }

                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.exit(1);
                }

                mapImage = new ImageIcon((new ImageIcon(destinationFile)).getImage().getScaledInstance(
                        WIDTH, 
                        HEIGHT,
                        Image.SCALE_SMOOTH
                ));

                mapLabel.setIcon(mapImage);
                revertSizeWhenDone = true;
                incomingHeight = HEIGHT;
                incomingWidth = WIDTH;
                HEIGHT += 250;
                moveAndResize();
                
                numQuestionsToAsk = 5;
                break;
        }
        showCorrectElements();
    }
    
    private void showCorrectElements() {
        switch(currentQuestion.getQuestionType()) {
            case BASIC:
                nextButton.setVisible(true);
                answerTextField.setVisible(true);
                aLabel.setVisible(true);
                qLabel.setVisible(true);
                questionLabel2.setVisible(true);
                mapLabel.setVisible(false);
                playButton.setVisible(false);
                break;
            case GEOGRAPHY:
                nextButton.setVisible(true);
                answerTextField.setVisible(true);
                aLabel.setVisible(true);
                qLabel.setVisible(true);
                questionLabel2.setVisible(true);
                mapLabel.setVisible(true);
                playButton.setVisible(false);
                break;
            case SPELLING:
                nextButton.setVisible(true);
                answerTextField.setVisible(true);
                aLabel.setVisible(true);
                qLabel.setVisible(true);
                questionLabel2.setVisible(true);
                mapLabel.setVisible(false);
                playButton.setVisible(true);
                break;
            case MUSIC:
                nextButton.setVisible(true);
                answerTextField.setVisible(true);
                aLabel.setVisible(true);
                qLabel.setVisible(true);
                questionLabel2.setVisible(true);
                mapLabel.setVisible(false);
                playButton.setVisible(false);
                break;
        }
    }
    
    @Override
    public void customOnResize() {
        if (null != mapImage) {
            mapImage = new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(
                WIDTH, 
                HEIGHT - 250,
                Image.SCALE_SMOOTH
            ));

            mapLabel.setIcon(mapImage);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        questionLabel = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        nextButton = new javax.swing.JButton();
        answerTextField = new javax.swing.JTextField();
        aLabel = new javax.swing.JLabel();
        qLabel = new javax.swing.JLabel();
        mapLabel = new javax.swing.JLabel();
        questionLabel2 = new javax.swing.JLabel();
        playButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nextButton.setBackground(new java.awt.Color(51, 51, 255));
        nextButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        nextButton.setForeground(new java.awt.Color(255, 255, 51));
        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        aLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        aLabel.setText("Answer:");

        qLabel.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        qLabel.setText("Question:");

        mapLabel.setText("mapLabel");
        mapLabel.setToolTipText("");
        mapLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mapLabel.setDoubleBuffered(true);

        questionLabel2.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        questionLabel2.setText("jLabel1");

        playButton.setBackground(new java.awt.Color(51, 51, 255));
        playButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        playButton.setForeground(new java.awt.Color(255, 255, 51));
        playButton.setText("Play");
        playButton.setToolTipText("");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(nextButton, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(answerTextField, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(aLabel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(qLabel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(mapLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(questionLabel2, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(playButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(aLabel)
                                    .addComponent(qLabel))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(questionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(playButton))))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(nextButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(mapLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(mapLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playButton)
                .addGap(20, 20, 20)
                .addComponent(nextButton)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(867, 867, 867))
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1)
                .addGap(12, 12, 12)
                .addComponent(questionLabel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        Quiz quiz = dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName);
        quiz.keepAnswer(currentQuestion.getQuestionNumber(), new Answer(answerTextField.getText()));
        answerTextField.setText("");
        // questions start with number 1 so using the current questions number will retrieve the next question from the list
        questionsAsked.add(currentQuestion.getQuestionNumber());
        currentQuestion = getNextQuestion(quiz);

        if (null == currentQuestion) {
            // Just finished the last question, go to the next window
            quizResultsWindow = new QuizResultsWindow(subjectWindow, subjectName, quizName);
            cleanupWindow();
            
            quizResultsWindow.refreshTable();
            this.setVisible(false);
            quizResultsWindow.setVisible(true);
        } else {
            if (null == quiz.getQuestion(currentQuestion.getQuestionNumber())) {
                // if this is the last question, change the next button to say Finish.
                nextButton.setText("Finish");
            }

            cleanupWindow();
            setupWindow();
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        recorder.playWavFile(currentQuestion.getQuestion());
    }//GEN-LAST:event_playButtonActionPerformed


    private Question getNextQuestion(Quiz quiz) {
        Question nextQuestion = null;
        switch (currentQuestion.getQuestionType()) {
            case BASIC:
            case SPELLING:
                nextQuestion = quiz.getQuestion(currentQuestion.getQuestionNumber());
                break;
            case MUSIC:
            case GEOGRAPHY:
                if (questionsAsked.size() < numQuestionsToAsk) {
                    nextQuestion = getRandomQuestion(quiz);
                }
                break;
        }
        
        return nextQuestion;
    }
    
    private Question getRandomQuestion(Quiz quiz) {
        Question question = null;
        // http://stackoverflow.com/questions/8236125/get-random-integer-in-range-x-y
        Random random = new Random();
        while (question == null) {
            int questionIndex = random.nextInt(quiz.getQuestions().size());
            Question temp = quiz.getQuestion(questionIndex);
            int questionNumber = temp.getQuestionNumber();
            
            // https://www.tutorialspoint.com/java/util/hashset_contains.htm
            if (!questionsAsked.contains(questionNumber)) {
                question = temp;
            }
        }
        return question;
    }
    
    private void cleanupWindow() {
        if (null != musicPlayer) {
            musicPlayer.stop();
        }
    
        // http://stackoverflow.com/questions/671049/how-do-you-kill-a-thread-in-java
        if (null != musicThread) {
            musicThread.stop();
        }
        
        if (revertSizeWhenDone) {
            WIDTH = incomingWidth;
            HEIGHT = incomingHeight;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aLabel;
    private javax.swing.JTextField answerTextField;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel mapLabel;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton playButton;
    private javax.swing.JLabel qLabel;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel questionLabel2;
    // End of variables declaration//GEN-END:variables
}
