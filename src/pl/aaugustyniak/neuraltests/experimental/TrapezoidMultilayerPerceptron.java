/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.neuraltests.experimental;

import java.util.List;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

/**
 *
 * @author artur
 */
public class TrapezoidMultilayerPerceptron extends MultiLayerPerceptron {

    public TrapezoidMultilayerPerceptron(List<Integer> inLayers) {
        super(inLayers, TransferFunctionType.TRAPEZOID);
        this.setLabel("Trapezoid MLP");
    }
}
