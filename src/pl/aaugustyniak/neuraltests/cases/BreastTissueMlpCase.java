package pl.aaugustyniak.neuraltests.cases;

import pl.aaugustyniak.neuraltests.MlpCase;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.norm.MaxNormalizer;

/**
 * Przypadek testowy
 *
 * @url http://archive.ics.uci.edu/ml/datasets/Breast+Tissue
 *
 * @author artur
 */
public class BreastTissueMlpCase extends MlpCase {

    /**
     * Test z podanym perceptronem eksperymentalnym
     *
     * @param ann
     */
    public BreastTissueMlpCase(NeuralNetwork ann) {
        this();
        this.setAnn(ann);

    }

    /**
     * Domyślny referencyjny perceptron MLP
     */
    public BreastTissueMlpCase() {
        super("./datasets/breast_tissue/bt_preproc.csv", ",", "Breast Tissue ", 100, 0.01, new MaxNormalizer(), 9, 6);
        this.setAnn(new MultiLayerPerceptron(9, 6));
        this.ann.setLabel("9:6[lin-sigm]");
    }
}
