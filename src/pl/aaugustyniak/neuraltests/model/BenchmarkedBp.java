/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pl.aaugustyniak.neuraltests.model;

import pl.aaugustyniak.neuraltests.LvtTrainingSet;
import org.neuroph.core.NeuralNetwork;
import org.neuroph.nnet.learning.BackPropagation;

/**
 * Class providing classic NEUROPH Backporop trainer with dataset divided with
 * LVT (learn-validation-test) scheme
 *
 * @author Artur Augustyniak
 */
public class BenchmarkedBp extends BackPropagation {

    private LvtTrainingSet tc;

    private BenchmarkedBp() {
    }

    /**
     * Constructor
     *
     * @param tc LVT TrainigCase
     * @param neuralNet Perceptropn to train
     */
    public BenchmarkedBp(LvtTrainingSet tc, NeuralNetwork neuralNet) {
        super();
        this.tc = tc;
        this.setNeuralNetwork(neuralNet);
    }

    /**
     * Benchmarked Learn. Side effect is T Efficiency Observer with process
     * statistics
     */
    public void bmLearn() {
        this.learn(tc.getTrainingSet());
    }

    public LvtTrainingSet getTc() {
        return tc;
    }

    public Integer getMaxIterations() {
        return this.maxIterations;
    }

    public Double getTotalSquaredErrorSum() {

        return this.totalSquaredErrorSum;
    }

    @Override
    protected void beforeEpochStart() {
        super.beforeEpochStart();
        this.tc.getTrainingSet().shuffle();
    }
}
