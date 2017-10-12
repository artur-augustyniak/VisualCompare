package pl.aaugustyniak.neuraltests.threadcontroll;

import java.util.Observable;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.neuroph.core.NeuralNetwork;
import pl.aaugustyniak.neuraltests.model.BenchmarkedBp;
import pl.aaugustyniak.neuraltests.rating.ReferencePerceptronRaiting;
import pl.aaugustyniak.neuraltests.model.TrainingEfficiencyOutcomeObserver;
import pl.aaugustyniak.neuraltests.MlpCase;
import pl.aaugustyniak.neuraltests.RaitingPOJO;
import pl.aaugustyniak.neuraltests.hibernate.HibernateUtil;
import pl.aaugustyniak.neuraltests.rating.ExperimentalPerceptronRaiting;
import pl.aaugustyniak.neuraltests.reporting.TrainingProcessReportFormatter;

/**
 * Wątek badający zachowanie danego perceptronu dla przypadku testowego
 *
 * @author artur
 */
public class RecognitionThread extends Observable implements Runnable {

    protected MlpCase tc;
    protected boolean saving = false;
    private RunType runType;

    /**
     * Typ przebigu REF - ucz domyślny perceptron referencyjny; EXP - ucz podany
     * perceptron eksperymentalny
     */
    public enum RunType {

        REF,
        EXP
    }

    /**
     * Konstruktor - badany jest domyślny perceptron dla danego przypadku
     * testowego
     *
     * @param tc przypadek testowy
     */
    public RecognitionThread(MlpCase tc) {
        this.tc = tc;
        this.runType = RunType.REF;

    }

    /**
     * Konstruktor - badany jest podany perceptron dla danego przypadku
     * testowego
     *
     * @param tc
     * @param n
     */
    public RecognitionThread(MlpCase tc, NeuralNetwork n) {
        this.tc = tc;
        this.tc.setAnn(n);
        this.runType = RunType.EXP;

    }

    /**
     * Start
     */
    @Override
    public void run() {
        TrainingEfficiencyOutcomeObserver o = this.proceedTest();
        if (this.saving) {
            this.saveResults(o);
        } else {
            System.out.println(TrainingProcessReportFormatter.makeTrainingStats(o));
        }
        setChanged();
        notifyObservers();
        clearChanged();
    }

    /**
     * Jeśli była ustawiona flaga zapisu zapisz rating
     *
     * @param o
     */
    private void saveResults(TrainingEfficiencyOutcomeObserver o) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(makeRatingEntity(o, this.tc.getTestName()));
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Rating odpowiedni dla typu przebiegu
     *
     * @param o
     * @param name
     * @return
     */
    protected RaitingPOJO makeRatingEntity(TrainingEfficiencyOutcomeObserver o, String name) {
        switch (this.runType) {
            case REF:
                return new ReferencePerceptronRaiting(o, this.tc.getTestName());
            case EXP:
                return new ExperimentalPerceptronRaiting(o, this.tc.getTestName());
        }
        throw new IllegalArgumentException("Unknown run type: " + this.runType);
    }

    /**
     * Przeprowadza test nadzorowaną metodą BP (czyste BP + obserwator)
     *
     * @return
     */
    protected TrainingEfficiencyOutcomeObserver proceedTest() {
        BenchmarkedBp bp = new BenchmarkedBp(tc.getDataCase(), tc.getNetwork());
        bp.setMaxIterations(this.tc.getMaxIterations());
        TrainingEfficiencyOutcomeObserver o = new TrainingEfficiencyOutcomeObserver(bp);
        bp.addObserver(o);
        bp.setMaxError(this.tc.getMaxTeachingMSE());
        bp.bmLearn();
        return o;
    }

    /**
     * Flaga trybu zapisu
     *
     * @return
     */
    public boolean isSaving() {
        return saving;
    }

    /**
     * Ustaw flagę trybu zapisu
     *
     * @param saving
     */
    public void setSaving(boolean saving) {
        this.saving = saving;
    }

    public RunType getRunType() {
        return runType;
    }

    public void setRunType(RunType runType) {
        this.runType = runType;
    }
}
