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
public class QuizWindow extends BaseWindow {

    private SubjectWindow subjectWindow;
    private QuizResultsWindow quizResultsWindow;
    private String subjectName;
    private String quizName;
    private Question currentQuestion;
    /**
     * Creates new form QuizWindow
     */
    public QuizWindow(SubjectWindow subjectWindow, String subjectName, String quizName) {
        super();
        initComponents();
        this.subjectWindow = subjectWindow;
        this.subjectName = subjectName;
        this.quizName = quizName;
        currentQuestion = dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName).getQuestion(0);
        String questionStr = currentQuestion.getQuestion();
        questionLabel2.setText("<html>" + questionStr + "</html>");
        setVisible(false);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        qLabel = new javax.swing.JLabel();
        answerTextField = new javax.swing.JTextField();
        aLabel = new javax.swing.JLabel();
        questionLabel = new javax.swing.JLabel();
        nextButton = new javax.swing.JButton();
        questionLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        qLabel.setText("Q: ");

        aLabel.setText("A: ");

        nextButton.setText("Next");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        questionLabel2.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(qLabel)
                    .addComponent(aLabel))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(questionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(questionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGap(213, 213, 213)
                .addComponent(nextButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(qLabel)
                    .addComponent(questionLabel)
                    .addComponent(questionLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(aLabel)
                    .addComponent(answerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(nextButton)
                .addContainerGap(112, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aLabel;
    private javax.swing.JTextField answerTextField;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel qLabel;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel questionLabel2;
    // End of variables declaration//GEN-END:variables
}
