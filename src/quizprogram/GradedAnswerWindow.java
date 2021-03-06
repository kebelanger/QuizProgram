/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * Author: Kristen Belanger
 *
 */
package quizprogram;

public class GradedAnswerWindow extends BaseWindow {

    private QuizResultsWindow quizResultsWindow;
    private Question question;
    private Answer answer;

    /**
     * Creates new form GradedAnswerWindow
     */
    public GradedAnswerWindow(QuizResultsWindow quizResultsWindow, Question question, Answer answer) {
        super();
        initComponents();
        
        this.quizResultsWindow = quizResultsWindow;
        this.question = question;
        this.answer = answer;
        resetLabels();
        enableDisableButtons();
        setVisible(false);
    }

    public void resetLabels() {
        questionNumberLabel.setText(question.getQuestionNumber().toString());
        questionStrLabel.setText(question.getQuestion());
        correctAnswerStrLabel.setText(question.getAnswer());
        yourAnswerStrLabel.setText(answer.getAnswer());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        questionNumberLabel = new javax.swing.JLabel();
        questionLabel = new javax.swing.JLabel();
        yourAnswerLabel = new javax.swing.JLabel();
        correctAnswerLabel = new javax.swing.JLabel();
        questionStrLabel = new javax.swing.JLabel();
        yourAnswerStrLabel = new javax.swing.JLabel();
        correctAnswerStrLabel = new javax.swing.JLabel();
        doneButton = new javax.swing.JButton();
        nextQuestionButton = new javax.swing.JButton();
        previousQuestionButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        questionNumberLabel.setText("jLabel1");

        questionLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        questionLabel.setText("Question:");

        yourAnswerLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        yourAnswerLabel.setText("Your Answer:");

        correctAnswerLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        correctAnswerLabel.setText("Correct Answer:");

        questionStrLabel.setText("jLabel4");

        yourAnswerStrLabel.setText("jLabel4");

        correctAnswerStrLabel.setText("jLabel4");

        doneButton.setBackground(new java.awt.Color(51, 51, 255));
        doneButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        doneButton.setForeground(new java.awt.Color(255, 255, 51));
        doneButton.setText("Done");
        doneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doneButtonActionPerformed(evt);
            }
        });

        nextQuestionButton.setBackground(new java.awt.Color(51, 51, 255));
        nextQuestionButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        nextQuestionButton.setForeground(new java.awt.Color(255, 255, 51));
        nextQuestionButton.setText("Next Question");
        nextQuestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextQuestionButtonActionPerformed(evt);
            }
        });

        previousQuestionButton.setBackground(new java.awt.Color(51, 51, 255));
        previousQuestionButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        previousQuestionButton.setForeground(new java.awt.Color(255, 255, 153));
        previousQuestionButton.setText("Previous Question");
        previousQuestionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousQuestionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(questionNumberLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(questionLabel)
                            .addComponent(correctAnswerLabel)
                            .addComponent(yourAnswerLabel))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(yourAnswerStrLabel)
                            .addComponent(correctAnswerStrLabel)
                            .addComponent(questionStrLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(previousQuestionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextQuestionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(doneButton)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(questionNumberLabel)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(questionLabel)
                    .addComponent(questionStrLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yourAnswerLabel)
                    .addComponent(yourAnswerStrLabel))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(correctAnswerLabel)
                    .addComponent(correctAnswerStrLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doneButton)
                    .addComponent(nextQuestionButton)
                    .addComponent(previousQuestionButton))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doneButtonActionPerformed
        // TODO add your handling code here:
        quizResultsWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_doneButtonActionPerformed

    private void nextQuestionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextQuestionButtonActionPerformed
        // TODO add your handling code here:
        Quiz quiz = quizResultsWindow.getQuiz();
        question = quiz.getQuestion(question.getQuestionNumber());
        answer = quiz.getAnswer(question.getQuestionNumber());
        resetLabels();
        enableDisableButtons();
    }//GEN-LAST:event_nextQuestionButtonActionPerformed

    private void previousQuestionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousQuestionButtonActionPerformed
        // TODO add your handling code here:
        Quiz quiz = quizResultsWindow.getQuiz();
        question = quiz.getQuestion(question.getQuestionNumber() - 2);
        answer = quiz.getAnswer(question.getQuestionNumber());
        resetLabels();
        enableDisableButtons();
    }//GEN-LAST:event_previousQuestionButtonActionPerformed

    public void enableDisableButtons() {
        Quiz quiz = quizResultsWindow.getQuiz();

        if (null == quiz.getQuestion(question.getQuestionNumber())) {
            // if this is the last question, disable the next button
            nextQuestionButton.setEnabled(false);
        } else {
            nextQuestionButton.setEnabled(true);
        }
        if (null == quiz.getQuestion(question.getQuestionNumber() - 2)) {
            // if this is the first question, disable the previous button
            previousQuestionButton.setEnabled(false);
        } else {
            previousQuestionButton.setEnabled(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel correctAnswerLabel;
    private javax.swing.JLabel correctAnswerStrLabel;
    private javax.swing.JButton doneButton;
    private javax.swing.JButton nextQuestionButton;
    private javax.swing.JButton previousQuestionButton;
    private javax.swing.JLabel questionLabel;
    private javax.swing.JLabel questionNumberLabel;
    private javax.swing.JLabel questionStrLabel;
    private javax.swing.JLabel yourAnswerLabel;
    private javax.swing.JLabel yourAnswerStrLabel;
    // End of variables declaration//GEN-END:variables
}
