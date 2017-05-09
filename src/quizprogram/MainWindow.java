/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 * Author: Kristen Belanger
 */
package quizprogram;

import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kristen Belanger
 */
public class MainWindow extends BaseWindow {
    private DefaultTableModel myTableModel;
    private SubjectWindow subjectWindow;
    private SubjectBuilder subjectBuilderWindow;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        super();
        initComponents();
        
       // http://stackoverflow.com/questions/11606852/change-background-color-of-jtable
        jScrollPane1.getViewport().setBackground(Color.white);
       // http://stackoverflow.com/questions/4408644/how-can-i-change-the-font-of-a-jtables-header
        subjectsTable.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
        //http://stackoverflow.com/questions/7778958/how-can-i-change-jtables-header-background-color
//        subjectsTable.getTableHeader().setOpaque(false);
//        subjectsTable.getTableHeader().setBackground(Color.BLUE);
//        subjectsTable.getTableHeader().setForeground(new Color(255,255,51));
        
        
        
        // data handler is responsible for loading the saved data and carrying it around
        dataHandler = new DataHandler();
        dataHandler.load("data.txt");
        subjectBuilderWindow = new SubjectBuilder(this);
        subjectBuilderWindow.setVisible(false);

        myTableModel = (DefaultTableModel) subjectsTable.getModel();
            
        Subject defaultMusic = new Music();
        Subject existingMusic = dataHandler.getSubjectWithName("Music");
        if (existingMusic == null) {
            dataHandler.addSubject(defaultMusic);
        } else if (existingMusic != null ) {
            
            for( String name : existingMusic.getQuizzes().keySet()) {
                Quiz existingQuiz = existingMusic.getQuizWithName(name);
                Quiz defaultQuiz = defaultMusic.getQuizWithName(name);
                
                // check to make sure every question in the default quiz existing in 
                // the existing quiz
                for( Question defaultQuestion : defaultQuiz.getQuestions()) {
                    Question existingQuestion = existingQuiz.getQuestion(defaultQuestion.getQuestionNumber() - 1);
                    if (null == existingQuestion) {
                        existingQuiz.addQuestion(defaultQuestion);
                    }
                }
            }
        }
      
        if (dataHandler.getSubjectWithName("Geography") == null) {
            Geography defaultGeography = new Geography();
            dataHandler.addSubject(defaultGeography);
        }

        refreshTable();
        setVisible(false);
        
    }
    
    public void refreshTable() {
        // Remove all rows in table model before refreshing the quizzes
        // http://stackoverflow.com/questions/10413977/javaremoving-all-the-rows-of-defaulttablemodel
        myTableModel.setRowCount(0);

        if (null != dataHandler.getSubjects()) {
            for (Subject subject: dataHandler.getSubjects().values()) {
                myTableModel.addRow(new Object[]{subject.getName(), SubjectWindow.toPercent(subject.getAverage())});
            }
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

        goButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        subjectsTable = new javax.swing.JTable();
        addSubjectButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 153));

        goButton.setBackground(new java.awt.Color(51, 51, 255));
        goButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        goButton.setForeground(new java.awt.Color(255, 255, 51));
        goButton.setText("Go");
        goButton.setToolTipText("");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        subjectsTable.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        subjectsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Subject", "Average Score"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(subjectsTable);
        if (subjectsTable.getColumnModel().getColumnCount() > 0) {
            subjectsTable.getColumnModel().getColumn(0).setResizable(false);
            subjectsTable.getColumnModel().getColumn(1).setResizable(false);
        }

        addSubjectButton.setBackground(new java.awt.Color(51, 51, 255));
        addSubjectButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        addSubjectButton.setForeground(new java.awt.Color(255, 255, 51));
        addSubjectButton.setText("Add Subject");
        addSubjectButton.setToolTipText("");
        addSubjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSubjectButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(51, 51, 255));
        deleteButton.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 51));
        deleteButton.setText("Delete Subject");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setText("Quiz Pop");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addSubjectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(goButton)
                    .addComponent(addSubjectButton)
                    .addComponent(deleteButton))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
        // get the data for the entire selected row
        // http://stackoverflow.com/questions/29345792/java-jtable-getting-the-data-of-the-selected-row
        int column = 0;
        int row = subjectsTable.getSelectedRow();
        if (row != -1) {
            String subjectName = subjectsTable.getModel().getValueAt(row, column).toString();
            QuestionType selectedSubjectType = dataHandler.getSubjectWithName(subjectName).getType();
            subjectWindow = new SubjectWindow(this, subjectName, selectedSubjectType);
            subjectWindow.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_goButtonActionPerformed

    private void addSubjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSubjectButtonActionPerformed
        // TODO add your handling code here:
        subjectBuilderWindow.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_addSubjectButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // http://stackoverflow.com/questions/29345792/java-jtable-getting-the-data-of-the-selected-row
        int column = 0;
        int row = subjectsTable.getSelectedRow();
        if (row != -1) {
            String value = subjectsTable.getModel().getValueAt(row, column).toString();
            dataHandler.deleteSubjectWithName(value);
            refreshTable();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {     
                new MainWindow().setVisible(true);
            }
        });
        
        // http://stackoverflow.com/questions/9317461/get-the-application-closing-event
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                dataHandler.save("data.txt");
            }
        });
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSubjectButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton goButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable subjectsTable;
    // End of variables declaration//GEN-END:variables
}
