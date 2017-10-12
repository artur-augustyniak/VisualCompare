package pl.aaugustyniak.neuraltests;

import java.util.Random;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.util.norm.Normalizer;
import pl.aaugustyniak.neuraltests.model.ParetoGuyonTrainingCase;
import pl.aaugustyniak.neuraltests.model.ParetoGuyonTrainingCase.DatasetTransformer;

/**
 * Wrapper przypadku badawczego, dataset wraz z perceptronem
 *
 * @author artur
 */
public class MlpCase implements Cloneable {

    protected NeuralNetwork ann;
    protected LvtTrainingSet tc;
    protected String dsFileName;
    protected String separator;
    protected String testName;
    protected Integer namIterations;
    protected Double maxTeachingMSE;
    protected final Normalizer normalizer;
    protected Integer inp;
    protected Integer out;

    /**
     * Konstruktor testu
     *
     * @param ann perceptron
     * @param testName nazwa przypadku
     * @param namIterations maksymalna ilość epok uczenia
     * @param maxTeachingMSE poziom MSE uczenia poniżej którego uczenie będzie
     * @param dsFileName nazwa pliku csv
     * @param separator separator w pliku
     * @param normalizer rodzaj normalizacji danych
     * @param inp ilośc zmiennych objaśniających
     * @param out ilość zmiennych objaśnianych
     */
    public MlpCase(String dsFileName, String separator, String testName, Integer namIterations, Double maxTeachingMSE, Normalizer normalizer, Integer inp, Integer out) {
        this.dsFileName = dsFileName;
        this.separator = separator;
        this.testName = testName;
        this.namIterations = namIterations;
        this.maxTeachingMSE = maxTeachingMSE;
        this.normalizer = normalizer;
        this.inp = inp;
        this.out = out;
        this.setUp();
    }

    /**
     * Tworzy dataset z odpowiednim podziałem oraz konkretyzuje strategię dla
     * sortowania i normailzacji podzbiorów LVT
     */
    private void setUp() {
        this.tc = new ParetoGuyonTrainingCase(dsFileName, inp, out, separator, new DatasetTransformer() {
            @Override
            public void transform(TrainingSet t) {
                t.shuffle();
                normalizer.normalize(t);
            }
        });
    }

    /**
     * Ustawia perceptron i randomizuje wagi
     *
     * @param ann
     */
    public MlpCase setAnn(NeuralNetwork ann) {
        this.ann = ann;
        this.ann.randomizeWeights(new Random());
        return this;
    }

    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public MlpCase clone() throws CloneNotSupportedException {
        return (MlpCase) super.clone();
    }

    public NeuralNetwork getNetwork() {
        return this.ann;
    }

    public LvtTrainingSet getDataCase() {
        return this.tc;
    }

    public String getTestName() {
        return this.testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    

    public Integer getMaxIterations() {
        return this.namIterations;
    }

    public Double getMaxTeachingMSE() {
        return this.maxTeachingMSE;
    }
}