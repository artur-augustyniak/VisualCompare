package pl.aaugustyniak.neuraltests.factories;

import java.util.ArrayList;
import java.util.List;
import org.neuroph.core.Layer;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;
import pl.aaugustyniak.neuraltests.MlpCase;
import pl.aaugustyniak.neuraltests.experimental.TrapezoidMultilayerPerceptron;

/**
 *
 * @author artur
 */
public class MlpCaseFactory {

    /**
     * Tworzy zbiór testów zaimplementowanych architektur z
     * pl.aaugustyniak.neuraltests.experimental/* dla podanego testu
     * referencyjnego
     *
     *
     * @param tc przypadek referencyjny
     * @return
     */
    public static List<MlpCase> makeExperimentalCasesCol(MlpCase tc) throws CloneNotSupportedException {
        List<MlpCase> e = new ArrayList<MlpCase>();
        List<Integer> inLayers = new ArrayList();
        for (Layer l : tc.getNetwork().getLayers()) {
            inLayers.add(l.getNeuronsCount());
        }
        inLayers.set(0, inLayers.get(0) - 1);
        e.add(tc.clone().setAnn(new TrapezoidMultilayerPerceptron(inLayers)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.GAUSSIAN)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.LINEAR)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.LOG)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.RAMP)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.SGN)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.SIN)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.STEP)));
        e.add(tc.clone().setAnn(new MultiLayerPerceptron(inLayers, TransferFunctionType.TANH)));
        
        return e;
    }
}

