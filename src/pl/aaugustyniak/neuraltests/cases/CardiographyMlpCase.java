package pl.aaugustyniak.neuraltests.cases;

import pl.aaugustyniak.neuraltests.MlpCase;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.norm.MaxNormalizer;

/**
 * Przypadek testowy
 *
 * @url http://archive.ics.uci.edu/ml/datasets/Cardiotocography
 *
 * @author artur
 */
public class CardiographyMlpCase extends MlpCase {

    /**
     * Test z podanym perceptronem eksperymentalnym
     *
     * @param ann
     */
    public CardiographyMlpCase(NeuralNetwork ann) {
        this();
        this.setAnn(ann);

    }

    /**
     * Domyślny referencyjny perceptron MLP
     */
    public CardiographyMlpCase() {
        super("./datasets/cardiography/cardiography.csv", ",", "Cardiography 36:12:3 [lin-sigm-sigm]", 100, 0.01, new MaxNormalizer(), 36, 3);
        this.setAnn(new MultiLayerPerceptron(36, 12, 3));
    }
}
