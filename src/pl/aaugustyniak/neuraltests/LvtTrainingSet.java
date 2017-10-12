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
package pl.aaugustyniak.neuraltests;

import org.neuroph.core.learning.TrainingSet;

/**
 * Interface for TestCase. This is wrapper for TrainingSet objects divided with
 * LVT (learn-validation-test) scheme
 *
 * @author Artur Augustyniak
 */
public interface LvtTrainingSet {

    /**
     * Returns wrapped TrainingSet
     *
     * @return
     */
    public TrainingSet getCompleteSet();

    /**
     * Returns training subset
     *
     * @return
     */
    public TrainingSet getTrainingSet();

    /**
     * Returns validation subset
     *
     * @return
     */
    public TrainingSet getValidationSet();

    /**
     * Returns test subset
     *
     * @return
     */
    public TrainingSet getTestSet();

    /**
     * Force independent shuffle of all subsets
     */
    public void shuffleAllSubsets();
}
