/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.neuraltests.experimental;

import java.util.List;
import org.neuroph.core.Connection;
import org.neuroph.core.input.InputFunction;

/**
 *
 * @author artur
 */
public class RidellaSum extends InputFunction {

    private static final long serialVersionUID = 134522L;

    @Override
    public double getOutput(List<Connection> inputConnections) {
        double sum = 0.0;
        double quadricComp = 0.0;
        double quadricWeight = 0.8;

        for (int i = 1; i < inputConnections.size(); i++) {
            sum += Math.pow(inputConnections.get(i).getInput() - (-1.0 * (inputConnections.get(i).getWeight().getValue() / (2 * quadricWeight))), 2);
            quadricComp += Math.pow(inputConnections.get(i).getInput(), 2) / (4 * quadricWeight);
        }
        return quadricWeight * (sum - (1.0 / quadricWeight) * (quadricComp - inputConnections.get(0).getWeight().getValue()));

    }
//    public double getOutput(List<Connection> inputConnections) {
//        double sum = inputConnections.get(0).getWeight().getValue();
//        double quadricComp = 0.0;
//        double quadricWeight = 0.01;
//        for (int i = 0; i < inputConnections.size(); i++) {
//            sum += inputConnections.get(i).getWeightedInput();
//            quadricComp += Math.pow(inputConnections.get(i).getInput(), 2);
//        }
//        return sum + (quadricWeight * quadricComp);
//    }
}
