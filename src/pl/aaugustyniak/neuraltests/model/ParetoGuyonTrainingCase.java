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
import org.neuroph.core.learning.TrainingSet;

/**
 * Class implementing mixed thumb-rule like division of test data set whole set
 * division by Pareto 80/20, then bigger trainig set is divided by Isabelle
 * Guyon remarks for validation.
 *
 * @author Artur Augustyniak
 */
public class ParetoGuyonTrainingCase implements LvtTrainingSet {

    private TrainingSet completeSet;
    private TrainingSet trainingSet;
    private TrainingSet validationSet;
    private TrainingSet testSet;
    private DatasetTransformer shuffleTranform;

    public interface DatasetTransformer {

        /**
         * Shuffles given TraininSet with derrived implementation
         *
         * @param t TrainingSet prepared for randomization
         */
        public void transform(TrainingSet t);
    }

    /**
     * Constructor
     *
     * @param filename file containig dataset
     * @param inNum number of attributes
     * @param outNumnumber of classes
     * @param separator field separator in given data file
     * @param sf Object providing in learning datasets shuffling strategy
     */
    public ParetoGuyonTrainingCase(String filename, int inNum, int outNum, String separator, DatasetTransformer sf) {
        this.completeSet = TrainingSet.createFromFile(filename, inNum, outNum, separator);

        this.shuffleTranform = sf;
        this.shuffleTranform.transform(this.completeSet);
        this.completeSet.setLabel("Complete dataset.");

        TrainingSet[] tSubs;
        /**
         * Pareto rule
         */
        tSubs = this.completeSet.createTrainingAndTestSubsets(80, 20);
        this.testSet = tSubs[1];
        this.testSet.setLabel("Testing subset");
        
        /**
         * //TODO Isabelle Guyon. A scaling law for the validation-set
         * training-setsize ratio. AT&T Bell Laboratories, 1997.
         *
         */
        tSubs = tSubs[0].createTrainingAndTestSubsets(80, 20);
        this.trainingSet = tSubs[0];
        
        this.trainingSet.setLabel(
                "Training subset");
        this.validationSet = tSubs[1];

        this.validationSet.setLabel(
                "Validation Subset");


    }

    @Override
    public TrainingSet getCompleteSet() {
        return completeSet;
    }

    @Override
    public TrainingSet getTrainingSet() {
        return trainingSet;
    }

    @Override
    public TrainingSet getValidationSet() {
        return validationSet;
    }

    @Override
    public TrainingSet getTestSet() {
        return testSet;
    }

    @Override
    public void shuffleAllSubsets() {
        this.completeSet.shuffle();
    }
}
