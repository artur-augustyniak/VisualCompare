package pl.aaugustyniak.neuraltests.cases;

import pl.aaugustyniak.neuraltests.MlpCase;
import java.util.Random;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.norm.MaxMinNormalizer;
import org.neuroph.util.norm.MaxNormalizer;
import pl.aaugustyniak.neuraltests.LvtTrainingSet;
import pl.aaugustyniak.neuraltests.model.ParetoGuyonTrainingCase;

/**
 * Przypadek testowy
 *
 * @url http://archive.ics.uci.edu/ml/datasets/Steel+Plates+Faults
 *
 * @author artur
 */
public class SteelPlatesFaultsMlpCase extends MlpCase {

    /**
     * Test z podanym perceptronem eksperymentalnym
     *
     * @param ann
     */
    public SteelPlatesFaultsMlpCase(NeuralNetwork ann) {
        this();
        this.setAnn(ann);

    }

    /**
     * Domyślny referencyjny perceptron MLP
     */
    public SteelPlatesFaultsMlpCase() {
        super("./datasets/steel_plates/steel_plates.csv", ",", "Steel Plates Faults 27:7 [lin-sigm]", 300, 0.01, new MaxNormalizer(), 27, 7);
        this.setAnn(new MultiLayerPerceptron(27, 7));
    }
}
