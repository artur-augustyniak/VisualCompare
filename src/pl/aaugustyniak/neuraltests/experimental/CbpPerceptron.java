/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.neuraltests.experimental;

import org.neuroph.nnet.Neuroph;

import java.util.List;
import java.util.Vector;

import org.neuroph.core.Layer;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.input.WeightedSum;
import org.neuroph.core.transfer.Linear;
import org.neuroph.nnet.comp.BiasNeuron;
import org.neuroph.nnet.comp.InputNeuron;
import org.neuroph.nnet.flat.FlatNetworkPlugin;
import org.neuroph.nnet.learning.MomentumBackpropagation;
import org.neuroph.util.ConnectionFactory;
import org.neuroph.util.LayerFactory;
import org.neuroph.util.NeuralNetworkFactory;
import org.neuroph.util.NeuralNetworkType;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;
import org.neuroph.util.random.NguyenWidrowRandomizer;

/**
 *
 * @author artur
 */
public class CbpPerceptron extends NeuralNetwork {

    /**
     * The class fingerprint that is set to indicate serialization compatibility
     * with a previous version of the class.
     */
    private static final long serialVersionUID = 2456L;

//    /**
//     * Creates new MultiLayerPerceptron with specified number of neurons in
//     * layers
//     *
//     * @param neuronsInLayers collection of neuron number in layers
//     */
//    public CbpPerceptron(List<Integer> neuronsInLayers) {
//        // init neuron settings
//        NeuronProperties neuronProperties = new NeuronProperties();
//        neuronProperties.setProperty("useBias", true);
//        neuronProperties.setProperty("transferFunction", TransferFunctionType.SIGMOID);
//
//        this.createNetwork(neuronsInLayers, neuronProperties);
//    }

    public CbpPerceptron(int... neuronsInLayers) {
        // init neuron settings
        NeuronProperties neuronProperties = new NeuronProperties();
        neuronProperties.setProperty("useBias", true);
        neuronProperties.setProperty("transferFunction",
                TransferFunctionType.SIGMOID);
        neuronProperties.setProperty("inputFunction", RidellaSum.class);

        Vector<Integer> neuronsInLayersVector = new Vector<Integer>();
        for (int i = 0; i < neuronsInLayers.length; i++) {
            neuronsInLayersVector.add(new Integer(neuronsInLayers[i]));
        }

        this.createNetwork(neuronsInLayersVector, neuronProperties);
    }

//    public CbpPerceptron(TransferFunctionType transferFunctionType, int... neuronsInLayers) {
//        // init neuron settings
//        NeuronProperties neuronProperties = new NeuronProperties();
//        neuronProperties.setProperty("useBias", true);
//        neuronProperties.setProperty("transferFunction", transferFunctionType);
//        neuronProperties.setProperty("inputFunction", WeightedSum.class);
//
//
//        Vector<Integer> neuronsInLayersVector = new Vector<Integer>();
//        for (int i = 0; i < neuronsInLayers.length; i++) {
//            neuronsInLayersVector.add(new Integer(neuronsInLayers[i]));
//        }
//
//        this.createNetwork(neuronsInLayersVector, neuronProperties);
//    }

//    public CbpPerceptron(List<Integer> neuronsInLayers, TransferFunctionType transferFunctionType) {
//        // init neuron settings
//        NeuronProperties neuronProperties = new NeuronProperties();
//        neuronProperties.setProperty("useBias", true);
//        neuronProperties.setProperty("transferFunction", transferFunctionType);
//
//        this.createNetwork(neuronsInLayers, neuronProperties);
//    }
//
//    /**
//     * Creates new MultiLayerPerceptron net with specified number neurons in
//     * getLayersIterator
//     *
//     * @param neuronsInLayers collection of neuron numbers in layers
//     * @param neuronProperties neuron properties
//     */
//    public CbpPerceptron(List<Integer> neuronsInLayers, NeuronProperties neuronProperties) {
//        this.createNetwork(neuronsInLayers, neuronProperties);
//    }

    /**
     * Creates MultiLayerPerceptron Network architecture - fully connected feed
     * forward with specified number of neurons in each layer
     *
     * @param neuronsInLayers collection of neuron numbers in getLayersIterator
     * @param neuronProperties neuron properties
     */
    private void createNetwork(List<Integer> neuronsInLayers, NeuronProperties neuronProperties) {

        // set network type
        this.setNetworkType(NeuralNetworkType.MULTI_LAYER_PERCEPTRON);

        // create input layer
        NeuronProperties inputNeuronProperties = new NeuronProperties(InputNeuron.class, Linear.class);
        Layer layer = LayerFactory.createLayer(neuronsInLayers.get(0), inputNeuronProperties);

        boolean useBias = true; // use bias neurons by default
        if (neuronProperties.hasProperty("useBias")) {
            useBias = (Boolean) neuronProperties.getProperty("useBias");
        }

        if (useBias) {
            layer.addNeuron(new BiasNeuron());
        }

        this.addLayer(layer);

        // create layers
        Layer prevLayer = layer;

        //for(Integer neuronsNum : neuronsInLayers)
        for (int layerIdx = 1; layerIdx < neuronsInLayers.size(); layerIdx++) {
            Integer neuronsNum = neuronsInLayers.get(layerIdx);
            // createLayer layer
            layer = LayerFactory.createLayer(neuronsNum, neuronProperties);

            if (useBias && (layerIdx < (neuronsInLayers.size() - 1))) {
                layer.addNeuron(new BiasNeuron());
            }

            // add created layer to network
            this.addLayer(layer);
            // createLayer full connectivity between previous and this layer
            if (prevLayer != null) {
                ConnectionFactory.fullConnect(prevLayer, layer);
            }

            prevLayer = layer;
        }

        // set input and output cells for network
        NeuralNetworkFactory.setDefaultIO(this);

        // set learnng rule
        //this.setLearningRule(new BackPropagation(this));
        this.setLearningRule(new MomentumBackpropagation());
        // this.setLearningRule(new DynamicBackPropagation());

        this.randomizeWeights(new NguyenWidrowRandomizer(-0.7, 0.7));

        // flatten the network, if desired
        if (Neuroph.getInstance().shouldFlattenNetworks()) {
            FlatNetworkPlugin.flattenNeuralNetworkNetwork(this);
        }

    }

    public void connectInputsToOutputs() {
        // connect first and last layer
        ConnectionFactory.fullConnect(getLayers().get(0), getLayers().get(getLayers().size() - 1), false);
    }
}
