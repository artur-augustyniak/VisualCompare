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
package pl.aaugustyniak.neuraltests.reporting;

import com.google.common.base.Strings;
import com.google.common.primitives.Doubles;
import java.awt.Color;
import org.math.plot.Plot2DPanel;
import pl.aaugustyniak.neuraltests.model.TrainingEfficiencyOutcomeObserver;

/**
 * Helper class for statisitcs formatting
 *
 * @author Artur Augustyniak
 */
public class TrainingProcessReportFormatter {

    /**
     * Prepare text stats and confiusion matrix
     *
     * @param o BP process observer containing stats
     * @return formatted string
     */
    public static String makeTrainingStats(TrainingEfficiencyOutcomeObserver o) {
        String summary = "";
        if (o.getObservable().isStopped()) {
            summary += "Goal was to stop with: " + o.getObservable().getMaxError() + " training MSE error\n"; 
            summary+= "in: " + o.getObservable().getMaxIterations() + " max iterations\n";
            summary += "Goal reached or forced to stop at: \n";
            summary+= o.getEpoch().get(o.getEpoch().size() - 1).intValue() + " iteration (Epoch)\n";
            summary += "Training MSE result: " + o.getTrnError().get(o.getTrnError().size() - 1) + "\n";
            summary += "Validation MSE result: " + o.getValiError().get(o.getValiError().size() - 1) + "\n";
            summary += "Efficiency factor result: " + o.getEfficiencyFactor().get(o.getEfficiencyFactor().size() - 1) + "\n";

            summary += "**\n";
            summary += "Total perceptron rate: " + o.getTestRatingMetric() + "\n";
            summary += "**\n";
            summary += "TRF in real test (with acceptance ramp):\n";
            summary += o.getTestClassificationAccuracy() + "\n";
            summary += "Cofiusion Matrix (without acceptance ramp)\n";
            int matEdge = o.getObservable().getTc().getCompleteSet().getOutputSize();
            int i, j;
            summary += Strings.repeat("   -   ", matEdge) + "\n";
            for (i = 0; i < matEdge; i++) {
                for (j = 0; j < matEdge; j++) {
                    summary += " | " + o.getCofusionMatrix()[i][j] + " | ";
                }
                summary += "\n";
                summary += Strings.repeat("   -   ", matEdge) + "\n";
            }

        } else {
            summary = "Training in progress...\n";
        }
        return summary;
    }

    /**
     * Make Teach and Validation MSE plot
     *
     * @param o BP process observer containing stats
     * @return Plot object subclass of JPanel
     */
    public static Plot2DPanel generateMsePlot(TrainingEfficiencyOutcomeObserver o) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.setAxisLabel(0, "Epoch");
        plot.setAxisLabel(1, "MSE");
        plot.setLegendOrientation("SOUTH");
        plot.addLinePlot("Training MSE", Color.GREEN, Doubles.toArray(o.getEpoch()), Doubles.toArray(o.getTrnError()));
        plot.addLinePlot("Validation MSE", Color.RED, Doubles.toArray(o.getEpoch()), Doubles.toArray(o.getValiError()));
        return plot;

    }

    /**
     * Efficiency factor plot
     *
     * @param o BP process observer containing stats
     * @return Plot object subclass of JPanel
     */
    public static Plot2DPanel generateEfficiencyFactorPlot(TrainingEfficiencyOutcomeObserver o) {
        Plot2DPanel plot = new Plot2DPanel();
        plot.setAxisLabel(0, "Epoch");
        plot.setAxisLabel(1, "Efficiency factor - Bigger is better");
        plot.setLegendOrientation("SOUTH");
        plot.addLinePlot("Efficiency Factor", Color.MAGENTA, Doubles.toArray(o.getEpoch()), Doubles.toArray(o.getEfficiencyFactor()));
        return plot;

    }
}
