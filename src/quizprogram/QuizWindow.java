/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
        currentQuestion = dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName).getQuestion(0);
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
                questionLabel2.setText("Name this song.");
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
                }});  
                musicThread.start();
                break;
            case GEOGRAPHY:
                // display map
                questionLabel2.setText("Name the state:");

                try {
                    String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                            + "center=39.8282,-98.5795"
                            + "&zoom=3"
                            + "&size=" + WIDTH + "x" + HEIGHT
                            + "&maptype=roadmap"
                            + "&markers=color:blue%7Clabel:S%7C" + currentQuestion.getQuestion()
                            + "&style=feature:all|element:labels|visibility:off"
                            + "&key=AIzaSyAJjW_ZL-ZEkHpsIrZlVJgGPeniH-i_N1c";
                    String destinationFile = "image.jpg";
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

                mapImage = new ImageIcon((new ImageIcon("image.jpg")).getImage().getScaledInstance(
                        WIDTH, 
                        HEIGHT,
                        Image.SCALE_SMOOTH
                ));

                mapLabel.setIcon(mapImage);
                setMapLabelsVisibility(true);                
                setQuestionLabelsVisibility(true);                
                setAnswerLabelsVisibility(true);    
                revertSizeWhenDone = true;
                incomingHeight = HEIGHT;
                incomingWidth = WIDTH;
                HEIGHT += 250;
                moveAndResize();
                break;
        }
    }
    
    private void setMapLabelsVisibility(boolean isVisible) {
        mapLabel.setVisible(isVisible);
    }

    private void setQuestionLabelsVisibility(boolean isVisible) {
        questionLabel2.setVisible(isVisible);
        qLabel.setVisible(isVisible);
    }

    private void setAnswerLabelsVisibility(boolean isVisible) {
        answerTextField.setVisible(isVisible);
        aLabel.setVisible(isVisible);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        aLabel.setText("A: ");

        qLabel.setText("Q: ");

        mapLabel.setText("mapLabel");
        mapLabel.setToolTipText("");
        mapLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mapLabel.setDoubleBuffered(true);

        questionLabel2.setText("jLabel1");

        jLayeredPane1.setLayer(nextButton, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(answerTextField, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(aLabel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(qLabel, javax.swing.JLayeredPane.PALETTE_LAYER);
        jLayeredPane1.setLayer(mapLabel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(questionLabel2, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(162, 162, 162)
                        .addComponent(nextButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(mapLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(mapLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addGap(28, 28, 28)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(qLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(aLabel))
                .addGap(58, 58, 58)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1)
                .addGap(0, 0, Short.MAX_VALUE))
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
        currentQuestion = quiz.getQuestion(currentQuestion.getQuestionNumber());

        if (null == currentQuestion) {
            // Just finished the last question, go to the next window
            quizResultsWindow = new QuizResultsWindow(subjectWindow, subjectName, quizName);
            cleanupWindow();
            quizResultsWindow.refreshTable();
            quizResultsWindow.setVisible(true);
            this.setVisible(false);
        } else {
            if (null == quiz.getQuestion(currentQuestion.getQuestionNumber())) {
                // if this is the last question, change the next button to say done.
                nextButton.setText("Finish");
            }
            questionLabel2.setText("<html>" + currentQuestion.getQuestion() + "</html>");
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void cleanupWindow() {
        if (null != musicPlayer) {
            musicPlayer.stop();
        }
    
        // http://stackoverflow.com/questions/671049/how-do-you-kill-a-thread-in-java
        if (null != musicThread) {
            musicThread.interrupt();
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
    private javax.swing.JLabel qLabel;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel questionLabel2;
    // End of variables declaration//GEN-END:variables
}
