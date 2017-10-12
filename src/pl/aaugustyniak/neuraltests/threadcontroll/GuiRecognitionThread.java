/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.neuraltests.threadcontroll;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pl.aaugustyniak.neuraltests.rating.ReferencePerceptronRaiting;
import pl.aaugustyniak.neuraltests.model.TrainingEfficiencyOutcomeObserver;
import pl.aaugustyniak.neuraltests.MlpCase;
import pl.aaugustyniak.neuraltests.reporting.TrainingProcessReportFormatter;

/**
 *
 * @author artur
 */
public class GuiRecognitionThread extends RecognitionThread {

    private final JTabbedPane mainPane;
    /**
     *
     * @param pane
     * @param tc
     * @param sf
     */
   
    public GuiRecognitionThread(JTabbedPane pane, final MlpCase tc, final SessionFactory sf) {
        super(tc, sf);
        this.mainPane = pane;
    }

    @Override
    public void run() {
        JPanel loading = this.loadingPanel();

        synchronized (this.mainPane) {
            this.mainPane.addTab("Training...", loading);
            mainPane.setSelectedComponent(loading);
        }
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        TrainingEfficiencyOutcomeObserver o = this.proceedTest();
        session.save(new ReferencePerceptronRaiting(o, this.tc.getTestName()));
        session.getTransaction().commit();
        session.close();



        JSplitPane split = resultsTab(o, this.tc.getTestName());
        setChanged();
        notifyObservers();

        synchronized (this.mainPane) {
            mainPane.remove(loading);
            mainPane.add(split);
            mainPane.setSelectedComponent(split);
        }

        clearChanged();
    }

    /**
     * Prepare results tab
     */
    private JSplitPane resultsTab(TrainingEfficiencyOutcomeObserver o, String name) {
        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        split.setTopComponent(TrainingProcessReportFormatter.generateMsePlot(o));
        split.setBottomComponent(TrainingProcessReportFormatter.generateEfficiencyFactorPlot(o));
        split.setDividerLocation(400);
        JTextArea stats = new JTextArea(TrainingProcessReportFormatter.makeTrainingStats(o));
        JSplitPane splitTot = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitTot.setLeftComponent(stats);
        splitTot.setRightComponent(split);
        //splitTot.setDividerLocation(700);
        splitTot.setName(name);
        return splitTot;
    }

    private JPanel loadingPanel() {
        JPanel panel = new JPanel();
        try {
            ImageIcon imageIcon = new javax.swing.ImageIcon(getClass().getResource("/pl/aaugustyniak/neuraltests/view/assets/spinner.gif"));
            JLabel iconLabel = new JLabel();
            iconLabel.setIcon(imageIcon);
            imageIcon.setImageObserver(iconLabel);
            panel.add(iconLabel);
        } catch (Exception e) {
            Logger logger = Logger.getLogger("Logger");
            logger.log(Level.SEVERE, e.toString());
        }
        JLabel label = new JLabel("Training...");
        panel.add(label);
        return panel;
    }
}
