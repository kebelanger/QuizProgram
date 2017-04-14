/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizprogram;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dianebelanger
 */
public class QuizResultsWindow extends BaseWindow {

    private DefaultTableModel myTableModel;

    private SubjectWindow subjectWindow;
    private GradedAnswerWindow gradedAnswerWindow;
    private String subjectName;
    private String quizName;

    private QuizGrader quizGrader;

    /**
     * Creates new form QuizResultsWindow
     */
    public QuizResultsWindow(SubjectWindow subjectWindow, String subjectName, String quizName) {
        super();
        initComponents();
        this.subjectWindow = subjectWindow;
        this.subjectName = subjectName;
        this.quizName = quizName;

        quizNameLabel.setText(quizName);
        backToSubjectButton.setText("Back to " + subjectName);
        quizGrader = new QuizGrader();
        myTableModel = (DefaultTableModel) questionsTable.getModel();
        refreshTable();
        setVisible(false);
    }
    
    public void refreshTable() {
        // for every quiz add a row to the table
        Quiz quiz = dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName);
        if (null != quiz) {
            // Remove all rows in table model before refreshing the quizzes
            // http://stackoverflow.com/questions/10413977/javaremoving-all-the-rows-of-defaulttablemodel
            myTableModel.setRowCount(0);
            
            for (Question question: quiz.getQuestions()) {
                Integer questionNumber = question.getQuestionNumber();
                Answer actualAnswer = quiz.getAnswer(questionNumber);
                Boolean isCorrect = quizGrader.grade(actualAnswer.getAnswer(), question.getAnswer());
                actualAnswer.setIsCorrect(isCorrect);
                myTableModel.addRow(new Object[]{ question.getQuestionNumber(), question.getQuestion(), isCorrect });
            }
            dataHandler.getSubjectWithName(subjectName).addQuiz(quiz);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        questionsTable = new javax.swing.JTable();
        backToSubjectButton = new javax.swing.JButton();
        quizNameLabel = new javax.swing.JLabel();
        questionDetailsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        questionsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Question", "Correct"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        questionsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(questionsTable);
        if (questionsTable.getColumnModel().getColumnCount() > 0) {
            questionsTable.getColumnModel().getColumn(0).setResizable(false);
            questionsTable.getColumnModel().getColumn(1).setResizable(false);
            questionsTable.getColumnModel().getColumn(2).setResizable(false);
        }

        backToSubjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToSubjectButtonActionPerformed(evt);
            }
        });

        questionDetailsButton.setText("View question details");
        questionDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                questionDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(questionDetailsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backToSubjectButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quizNameLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(quizNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(backToSubjectButton))
                    .addComponent(questionDetailsButton)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backToSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToSubjectButtonActionPerformed

        Quiz quiz = dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName);
        int numCorrect = 0;
        int numQuestions = 0;
        // Reference for getting all values in a map
        // http://stackoverflow.com/questions/16246821/how-to-get-values-keys-from-hashmap
        for (Answer answer: quiz.getAnswers().values()) {
            if (answer.getIsCorrect()) {
                numCorrect++;
            }
            numQuestions++;
        }
        
        // need to cast to float, was always equalling 0
        // http://stackoverflow.com/questions/10455677/division-in-java-always-results-in-zero-0
        float score = ((float) numCorrect) / numQuestions;
        
        int timesTaken = quiz.getTimesTaken();
        float currentAverage = quiz.getAverageScore();
        float newAverage = ((currentAverage * timesTaken ) + score) / (timesTaken + 1);
        
        quiz.setLastScore(score);
        quiz.setTimesTaken(quiz.getTimesTaken() + 1);
        quiz.setAverageScore(newAverage);
       
        // adding this quiz without changing the quiz name will overwrite the existing quiz so it will now have all tha scores. 
        dataHandler.getSubjectWithName(subjectName).addQuiz(quiz);
        subjectWindow.refreshTable();
        subjectWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backToSubjectButtonActionPerformed

    private void questionDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_questionDetailsButtonActionPerformed
        // TODO add your handling code here:
        // http://stackoverflow.com/questions/29345792/java-jtable-getting-the-data-of-the-selected-row
        int column = 0;
        int row = questionsTable.getSelectedRow();
        if (row != -1) {
            String value = questionsTable.getModel().getValueAt(row, column).toString();
            Integer questionNumber = Integer.valueOf(value);
            Quiz quiz = dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName);
            gradedAnswerWindow = new GradedAnswerWindow(this, quiz.getQuestion(questionNumber - 1), quiz.getAnswer(questionNumber));
            gradedAnswerWindow.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_questionDetailsButtonActionPerformed

    public Quiz getQuiz() {
        return dataHandler.getSubjectWithName(subjectName).getQuizWithName(quizName);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToSubjectButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton questionDetailsButton;
    private javax.swing.JTable questionsTable;
    private javax.swing.JLabel quizNameLabel;
    // End of variables declaration//GEN-END:variables
}
