/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.neuraltests.view;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.hibernate.SessionFactory;
import pl.aaugustyniak.neuraltests.hibernate.HibernateUtil;
import pl.aaugustyniak.neuraltests.cases.BreastTissueMlpCase;
import pl.aaugustyniak.neuraltests.cases.CardiographyMlpCase;
import pl.aaugustyniak.neuraltests.cases.IrisMlpCase;
import pl.aaugustyniak.neuraltests.cases.LandSatMlpCase;
import pl.aaugustyniak.neuraltests.cases.ParkinsonsMlpCase;
import pl.aaugustyniak.neuraltests.cases.RobotNavigationMlpCase;
import pl.aaugustyniak.neuraltests.cases.SteelPlatesFaultsMlpCase;
import pl.aaugustyniak.neuraltests.threadcontroll.GuiRecognitionThread;
import pl.aaugustyniak.neuraltests.threadcontroll.RecognitionThread;

/**
 *
 * @author artur
 */
public class NeuralTestsGui extends javax.swing.JFrame implements Observer {
    //private final SessionFactory buildSessionFactory;

    /**
     * Creates new form NeuralTestsGui
     */
    public NeuralTestsGui() {
        //this.buildSessionFactory = HibernateUtil.getSessionFactory();
        initComponents();
        initAssets();
    }

    /*
     * Refresh view on observables state change
     */
    @Override
    public void update(Observable o, Object o1) {
        this.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        topMenuBar = new javax.swing.JMenuBar();
        testCaseMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        perceptronMenu = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MLP Compare");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("mainFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 800));

        referenceNetworksTabbedPane.setBackground(new java.awt.Color(255, 255, 255));

        testCaseMenu.setText("Test Case");

        jMenuItem1.setText("New");
        testCaseMenu.add(jMenuItem1);

        jMenuItem2.setText("Open");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        testCaseMenu.add(jMenuItem2);

        jMenuItem3.setText("Save");
        testCaseMenu.add(jMenuItem3);

        jMenuItem4.setText("Save As");
        testCaseMenu.add(jMenuItem4);

        topMenuBar.add(testCaseMenu);

        perceptronMenu.setText("Perceptron");

        jMenuItem5.setText("New");
        perceptronMenu.add(jMenuItem5);

        jMenuItem6.setText("Open");
        perceptronMenu.add(jMenuItem6);

        jMenuItem7.setText("Save");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        perceptronMenu.add(jMenuItem7);

        jMenuItem8.setText("Save As");
        perceptronMenu.add(jMenuItem8);

        topMenuBar.add(perceptronMenu);

        setJMenuBar(topMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(referenceNetworksTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(referenceNetworksTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenu perceptronMenu;
    private final javax.swing.JTabbedPane referenceNetworksTabbedPane = new javax.swing.JTabbedPane();
    private javax.swing.JMenu testCaseMenu;
    private javax.swing.JMenuBar topMenuBar;
    // End of variables declaration//GEN-END:variables

    private void initAssets() {
        try {
            reRunCurrentPane.setIcon(new ImageIcon(getClass().getResource("/pl/aaugustyniak/neuraltests/view/assets/run.png")));
        } catch (Exception e) {
            Logger logger = Logger.getLogger("NameOfYourLogger");
            logger.log(Level.SEVERE, e.toString());
        }
    }
}