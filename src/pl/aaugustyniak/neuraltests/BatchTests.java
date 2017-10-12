/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.aaugustyniak.neuraltests;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.hibernate.SessionFactory;
import pl.aaugustyniak.neuraltests.hibernate.HibernateUtil;
import pl.aaugustyniak.neuraltests.cases.BreastTissueMlpCase;
import pl.aaugustyniak.neuraltests.cases.CardiographyMlpCase;
import pl.aaugustyniak.neuraltests.cases.IrisMlpCase;
import pl.aaugustyniak.neuraltests.cases.LandSatMlpCase;
import pl.aaugustyniak.neuraltests.cases.ParkinsonsMlpCase;
import pl.aaugustyniak.neuraltests.cases.RobotNavigationMlpCase;
import pl.aaugustyniak.neuraltests.cases.SteelPlatesFaultsMlpCase;
import pl.aaugustyniak.neuraltests.threadcontroll.RecognitionThread;

/**
 *
 * @author artur
 */
public class BatchTests {
    
    public static void generateReferenceData() {
//        for (int i = 0; i < 2; i++) {
//            SessionFactory buildSessionFactory = HibernateUtil.getSessionFactory();
//            ThreadPoolExecutor ex;
//            ex = new ThreadPoolExecutor(7, 7, 30000, TimeUnit.DAYS, new DelayQueue());
//           RecognitionThread bt = new RecognitionThread(new BreastTissueMlpCase(), buildSessionFactory);
//           RecognitionThread ir = new RecognitionThread(new IrisMlpCase(), buildSessionFactory);
//           RecognitionThread cr = new RecognitionThread(new CardiographyMlpCase(), buildSessionFactory);
//           RecognitionThread ls = new RecognitionThread(new LandSatMlpCase(), buildSessionFactory);
//           RecognitionThread pr = new RecognitionThread(new ParkinsonsMlpCase(), buildSessionFactory);
//           RecognitionThread rn = new RecognitionThread(new RobotNavigationMlpCase(), buildSessionFactory);
//           RecognitionThread sp = new RecognitionThread(new SteelPlatesFaultsMlpCase(), buildSessionFactory);
//
//           ex.execute(bt);
//           ex.execute(ir);
//           ex.execute(cr);
//           ex.execute(ls);
//           ex.execute(pr);
//           ex.execute(rn);
//           ex.execute(sp);
//           ex.shutdown();
//            while (!ex.isTerminated()) {
//            }
//        }
    }
    
//    public static void generateExperimentData() {
//        for (int i = 0; i < 2; i++) {
//            SessionFactory buildSessionFactory = HibernateUtil.getSessionFactory();
//            ThreadPoolExecutor ex;
//            ex = new ThreadPoolExecutor(7, 7, 30000, TimeUnit.DAYS, new DelayQueue());
//           RecognitionThread bt = new RecognitionThread(new BreastTissueMlpCase(), buildSessionFactory);
//           RecognitionThread ir = new RecognitionThread(new IrisMlpCase(), buildSessionFactory);
//           RecognitionThread cr = new RecognitionThread(new CardiographyMlpCase(), buildSessionFactory);
//           RecognitionThread ls = new RecognitionThread(new LandSatMlpCase(), buildSessionFactory);
//           RecognitionThread pr = new RecognitionThread(new ParkinsonsMlpCase(), buildSessionFactory);
//           RecognitionThread rn = new RecognitionThread(new RobotNavigationMlpCase(), buildSessionFactory);
//           RecognitionThread sp = new RecognitionThread(new SteelPlatesFaultsMlpCase(), buildSessionFactory);
//
//           ex.execute(bt);
//           ex.execute(ir);
//           ex.execute(cr);
//           ex.execute(ls);
//           ex.execute(pr);
//           ex.execute(rn);
//           ex.execute(sp);
//           ex.shutdown();
//            while (!ex.isTerminated()) {
//            }
//        }
//    }
    
}
