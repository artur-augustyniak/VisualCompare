package pl.aaugustyniak.neuraltests.cases;

import pl.aaugustyniak.neuraltests.MlpCase;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.norm.MaxMinNormalizer;

/**
 * Przypadek testowy
 *
 * @url http://archive.ics.uci.edu/ml/datasets/Parkinsons
 *
 * @author artur
 */
public class ParkinsonsMlpCase extends MlpCase {

    /**
     * Test z podanym perceptronem eksperymentalnym
     *
     * @param ann
     */
    public ParkinsonsMlpCase(NeuralNetwork ann) {
        this();
        this.setAnn(ann);

    }

    /**
     * Domyślny referencyjny perceptron MLP
     */
    public ParkinsonsMlpCase() {
        super("./datasets/parkinsons/parkinsons.csv", ",", "Parkinsons 22:8:2 [lin-sigm-sigm]", 100, 0.01, new MaxMinNormalizer(), 22, 2);
        this.setAnn(new MultiLayerPerceptron(22, 8, 2));
    }
}
