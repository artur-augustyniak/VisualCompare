package pl.aaugustyniak.neuraltests.cases;

import pl.aaugustyniak.neuraltests.MlpCase;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.norm.MaxNormalizer;

/**
 * Przypadek testowy
 *
 * @url
 * http://archive.ics.uci.edu/ml/datasets/Wall-Following+Robot+Navigation+Data
 *
 * @author artur
 */
public class RobotNavigationMlpCase extends MlpCase {

    /**
     * Test z podanym perceptronem eksperymentalnym
     *
     * @param ann
     */
    public RobotNavigationMlpCase(NeuralNetwork ann) {
        this();
        this.setAnn(ann);

    }

    /**
     * Domyślny referencyjny perceptron MLP
     */
    public RobotNavigationMlpCase() {
        super("./datasets/robot_nav/robot_nav.csv", ",", "Robot Navigation 24:10:4 [lin-sigm-sigm]", 100, 0.01, new MaxNormalizer(), 24, 4);
        this.setAnn(new MultiLayerPerceptron(24, 10, 4));
    }
}
