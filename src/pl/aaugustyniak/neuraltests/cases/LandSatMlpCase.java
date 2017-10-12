package pl.aaugustyniak.neuraltests.cases;

import pl.aaugustyniak.neuraltests.MlpCase;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.norm.MaxNormalizer;

/**
 * Przypadek testowy
 *
 * @url http://archive.ics.uci.edu/ml/datasets/Statlog+(Landsat+Satellite)
 *
 * @author artur
 */
public class LandSatMlpCase extends MlpCase {

    /**
     * Test z podanym perceptronem eksperymentalnym
     *
     * @param ann
     */
    public LandSatMlpCase(NeuralNetwork ann) {
        this();
        this.setAnn(ann);

    }

    /**
     * Domyślny referencyjny perceptron MLP
     */
    public LandSatMlpCase() {
        super("./datasets/landsat/landsat.csv", ",", "Landsat 36:20:7 [lin-sigm-sigm]", 100, 0.01, new MaxNormalizer(), 36, 7);
        this.setAnn(new MultiLayerPerceptron(36, 20, 7));
    }
}
