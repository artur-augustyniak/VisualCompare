package pl.aaugustyniak.neuraltests;

import java.util.List;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.hibernate.SessionFactory;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;
import org.neuroph.util.TransferFunctionType;
import pl.aaugustyniak.neuraltests.cases.BreastTissueMlpCase;
import pl.aaugustyniak.neuraltests.cases.CardiographyMlpCase;
import pl.aaugustyniak.neuraltests.cases.IrisMlpCase;
import pl.aaugustyniak.neuraltests.cases.LandSatMlpCase;
import pl.aaugustyniak.neuraltests.cases.ParkinsonsMlpCase;
import pl.aaugustyniak.neuraltests.cases.RobotNavigationMlpCase;
import pl.aaugustyniak.neuraltests.cases.SteelPlatesFaultsMlpCase;
import pl.aaugustyniak.neuraltests.factories.MlpCaseFactory;
import pl.aaugustyniak.neuraltests.hibernate.HibernateUtil;
import pl.aaugustyniak.neuraltests.threadcontroll.RecognitionThread;
import pl.aaugustyniak.neuraltests.threadcontroll.RecognitionThread.RunType;
import pl.aaugustyniak.neuraltests.view.NeuralTestsGui;

/**
 *
 *
 * @author Artur Augustyniak
 */
public class NeuralTests {

    public NeuralTests() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                NeuralTestsGui frame = new NeuralTestsGui();
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        NeuralTests app;
        app = new NeuralTests();




//        List<MlpCase> expCases = (List<MlpCase>) MlpCaseFactory.makeExperimentalCasesCol(new BreastTissueMlpCase());
//        for (MlpCase mlpCase : expCases) {
//            mlpCase.setAnn(mlpCase.getNetwork());
//            mlpCase.setTestName(mlpCase.getTestName() + " - " + mlpCase.getNetwork().getLabel());
//            RecognitionThread bt = new RecognitionThread(mlpCase);
//            bt.setSaving(true);
//            bt.setRunType(RunType.EXP);
//            bt.run();
//        }


//        //BatchTests.generateReferenceData();
//        
//        //SessionFactory buildSessionFactory = HibernateUtil.getSessionFactory();
//        ThreadPoolExecutor ex;
//        
//        ex = new ThreadPoolExecutor(7, 7, 30000, TimeUnit.DAYS, new DelayQueue());
//        RecognitionThread bt = new RecognitionThread(new BreastTissueMlpCase(),);
//        ex.execute(bt);
//        ex.shutdown();
    }
}