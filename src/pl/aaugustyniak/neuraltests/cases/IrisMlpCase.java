package pl.aaugustyniak.neuraltests.cases;

import pl.aaugustyniak.neuraltests.MlpCase;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.norm.MaxNormalizer;

/**
 * Przypadek testowy
 *
 * @url http://archive.ics.uci.edu/ml/datasets/Iris
 *
 * @author artur
 */
public class IrisMlpCase extends MlpCase {

    /**
     * Test z podanym perceptronem eksperymentalnym
     *
     * @param ann
     */
    public IrisMlpCase(NeuralNetwork ann) {
        this();
        this.setAnn(ann);

    }

    /**
     * Domyślny referencyjny perceptron MLP
     */
    public IrisMlpCase() {
        super("./datasets/iris/iris_preprocessed.csv", ",", "Iris 4:5:3 [lin-sigm-lin]", 100, 0.01, new MaxNormalizer(), 4, 3);
        this.setAnn(new MultiLayerPerceptron(4, 5, 3));
    }
}
