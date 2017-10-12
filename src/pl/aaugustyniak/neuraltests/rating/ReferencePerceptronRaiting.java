/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.neuraltests.rating;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.internal.util.SerializationHelper;
import org.neuroph.core.NeuralNetwork;
import pl.aaugustyniak.neuraltests.RaitingPOJO;
import pl.aaugustyniak.neuraltests.model.TrainingEfficiencyOutcomeObserver;

/**
 *
 * @author artur
 */
@Entity
@Table(name = "ref_peceptron_rating")
public class ReferencePerceptronRaiting implements RaitingPOJO {

    private Long id;
    private String name;
    private Integer iterations;
    private Double goalTrainingMse;
    private Double trainingMseResult;
    private List<Double> trainingMseReadouts;
    private Double validationMseResult;
    private List<Double> validationMseReadouts;
    private Double efficiencyFactorResult;
    private List<Double> efficiencyFactorReadouts;
    private Double totalPerceptronRate;
    private Double testTrf;
    //private byte[] perceptron;
    private byte[] confusionMatrix;

    public ReferencePerceptronRaiting() {
    }

    public ReferencePerceptronRaiting(TrainingEfficiencyOutcomeObserver o, String name) {
        this.name = name;
        this.iterations = o.getObservable().getMaxIterations();
        this.goalTrainingMse = o.getObservable().getMaxError();
        this.trainingMseResult = o.getTrnError().get(o.getTrnError().size() - 1);
        this.validationMseResult = o.getValiError().get(o.getValiError().size() - 1);
        this.efficiencyFactorResult = o.getEfficiencyFactor().get(o.getEfficiencyFactor().size() - 1);
        this.totalPerceptronRate = o.getTestRatingMetric();
        this.testTrf = o.getTestClassificationAccuracy();
        //this.perceptron = SerializationHelper.serialize(o.getObservable().getNeuralNetwork());
        this.confusionMatrix = SerializationHelper.serialize(o.getCofusionMatrix());
        this.trainingMseReadouts = o.getTrnError();
        this.validationMseReadouts = o.getValiError();
        this.efficiencyFactorReadouts = o.getEfficiencyFactor();
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Type(type = "text")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public Double getGoalTrainingMse() {
        return goalTrainingMse;
    }

    public void setGoalTrainingMse(Double goalTrainingMse) {
        this.goalTrainingMse = goalTrainingMse;
    }

    public Double getTrainingMseResult() {
        return trainingMseResult;
    }

    public void setTrainingMseResult(Double trainingMseResult) {
        this.trainingMseResult = trainingMseResult;
    }

    public Double getValidationMseResult() {
        return validationMseResult;
    }

    public void setValidationMseResult(Double validationMseResult) {
        this.validationMseResult = validationMseResult;
    }

    public Double getEfficiencyFactorResult() {
        return efficiencyFactorResult;
    }

    public void setEfficiencyFactorResult(Double efficiencyFactorResult) {
        this.efficiencyFactorResult = efficiencyFactorResult;
    }

    public Double getTotalPerceptronRate() {
        return totalPerceptronRate;
    }

    public void setTotalPerceptronRate(Double totalPerceptronRate) {
        this.totalPerceptronRate = totalPerceptronRate;
    }

    public Double getTestTrf() {
        return testTrf;
    }

    public void setTestTrf(Double testTrf) {
        this.testTrf = testTrf;
    }

//    @Column(name = "perceptron", nullable = false, length = 65535)
//    public byte[] getPerceptron() {
//        return perceptron;
//    }
//
//    public void setPerceptron(byte[] perceptron) {
//        this.perceptron = perceptron;
//    }
    @Column(name = "confusion_matrix", nullable = false, length = 65535)
    public byte[] getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(byte[] confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    @ElementCollection
    @CollectionTable(name = "reference_training_mse_readouts", joinColumns =
            @JoinColumn(name = "ref_trn_readout_id"))
    @Column(name = "ref_trn_readout")
    public List<Double> getTrainingMseReadouts() {
        return trainingMseReadouts;
    }

    public void setTrainingMseReadouts(List<Double> trainingMseReadouts) {
        this.trainingMseReadouts = trainingMseReadouts;
    }

    @ElementCollection
    @CollectionTable(name = "reference_validation_mse_readouts", joinColumns =
            @JoinColumn(name = "ref_vali_readout_id"))
    @Column(name = "ref_vali_readout")
    public List<Double> getValidationMseReadouts() {
        return validationMseReadouts;
    }

    public void setValidationMseReadouts(List<Double> validationMseReadouts) {
        this.validationMseReadouts = validationMseReadouts;
    }

    @ElementCollection
    @CollectionTable(name = "reference_efficiency_factor_readouts", joinColumns =
            @JoinColumn(name = "ref_eff_readout_id"))
    @Column(name = "ref_eff_readout")
    public List<Double> getEfficiencyFactorReadouts() {
        return efficiencyFactorReadouts;
    }

    public void setEfficiencyFactorReadouts(List<Double> efficiencyFactorReadouts) {
        this.efficiencyFactorReadouts = efficiencyFactorReadouts;
    }
}
