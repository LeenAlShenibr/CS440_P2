/*
* CS440 Programming Assignment 2
* Author: Zhiqiang Ren
* Edited by: Veena Dali and Leen AlShenibr
* Date: 2/25/15
*/

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Arrays;

public class NeuralNetLearner {
  /**
  * @param args
  * @throws FileNotFoundException
  */
  public static void main(String[] args) throws FileNotFoundException {

    int trainingRounds = 300;
    int hidLayers;
    double learnRate;//Learning rate paramater

    //==========================================================================
    //======== Test Data
    //==========================================================================

    System.out.println("==========================================");
    System.out.println("======== Testing Neural Net 1");
    System.out.println("==========================================");


    //Three layers
    int[] layers = { 6, 3, 1 };
    NeuralNet net = new NeuralNet(layers);
    net.connectTest();

    double[][] inputvs = { { 1, 1, 0, 0, 0, 0 }, { 1, 0, 1, 0, 0, 0 },
    { 1, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 1, 0 },
    { 1, 0, 0, 0, 0, 1 }, { 0, 1, 1, 0, 0, 0 },
    { 0, 1, 0, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0 },
    { 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 1, 0, 0 },
    { 0, 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0, 1 },
    { 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 1, 0, 1 },
    { 0, 0, 0, 0, 1, 1 } };

    double[][] outputvs = { { 0 }, { 0 }, { 1 }, { 1 }, { 1 }, { 0 },
    { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 1 }, { 0 }, { 0 }, { 0 } };

    for (int n = 0; n < trainingRounds; ++n) {
      net.train(inputvs, outputvs, 1);
    }
    System.out.println("Error is :" +   net.error(inputvs, outputvs));
    net.errorrate(inputvs, outputvs);
    System.out.println("\n");

    System.out.println("==========================================");
    System.out.println("======== Testing Neural Net 2");
    System.out.println("==========================================");

    //Two layers
    int[] layers2 = { 2, 2 };
    NeuralNet net2 = new NeuralNet(layers2);
    net2.connectAll();

    double[][] inputvs2 = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 0 } };
    double[][] outputvs2 = { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 0, 1 } };

    for (int n = 0; n < trainingRounds; ++n) {
      net2.train(inputvs2, outputvs2, 1);
    }
    System.out.println("Error is :" + net2.errorrate(inputvs2, outputvs2));
    net2.errorrate(inputvs2, outputvs2);
    System.out.println("\n");


    //==========================================================================
    //======== General Info
    //==========================================================================

    System.out.println("==========================================");
    System.out.println("======== General Parameters");
    System.out.println("==========================================");

    System.out.println("# of Training Rounds: " + trainingRounds);
    System.out.println("\n");


    //==========================================================================
    //======== Neural Net for Credit Card Data
    //==========================================================================

    System.out.println("==========================================");
    System.out.println("======== Credit Card Neural Net");
    System.out.println("==========================================");

    hidLayers = 15;
    learnRate = 3;

    //Training
    DataProcessor data = new DataProcessor("crx.data.training", 0);
    int[] layers3 = { 15, hidLayers, 1 };
    NeuralNet net3 = new NeuralNet(layers3);
    net3.connectAll();

    double[][] inputvs3 = data.m_inputvs;
    double[][] outputvs3 = data.m_outputvs;

    System.out.println("==== Paramaters");
    System.out.println("Learning Rate: " + learnRate);
    System.out.println("# of Hidden Layers: " + hidLayers);

    for (int n = 0; n < trainingRounds; ++n) {
      net3.train(inputvs3, outputvs3, learnRate);
    }
    System.out.println("==== Training");
    System.out.println("Error is :" + net3.error(inputvs3, outputvs3));
    net3.errorrate(inputvs3, outputvs3);
    System.out.println("\n");

    // Testing
    data = new DataProcessor("crx.data.testing", 0);
    // net3.connectAll();

    inputvs3 = data.m_inputvs;
    outputvs3 = data.m_outputvs;

    System.out.println("==== Testing");
    System.out.println("Error is :" + net3.error(inputvs3, outputvs3));
    net3.errorrate(inputvs3, outputvs3);
    System.out.println("\n");

    //==========================================================================
    //======== Neural Net for Lenses
    //==========================================================================

    System.out.println("==========================================");
    System.out.println("======== Lenses Neural Net");
    System.out.println("==========================================");

    //set up
    hidLayers = 1;
    learnRate = 3;

    //Training
    DataProcessor data_lense = new DataProcessor("lenses.training", 1);
    int[] layers4 = { 4, hidLayers, 1 };
    NeuralNet net4 = new NeuralNet(layers4);
    net4.connectAll();

    double[][] inputvs4 = data_lense.m_inputvs;
    double[][] outputvs4 = data_lense.m_outputvs;

    System.out.println("==== Paramaters");
    System.out.println("Learning Rate: " + learnRate);
    System.out.println("# of Hidden Layers: " + hidLayers);

    for (int n = 0; n < trainingRounds; ++n) {
      net4.train(inputvs4, outputvs4, learnRate);
    }

    System.out.println("==== Training");
    System.out.println("Error is :" + net4.error(inputvs4, outputvs4));
    net4.errorrate(inputvs4, outputvs4);
    System.out.println("\n");


    // Testing
    data_lense = new DataProcessor("lenses.testing", 1);
    // net4.connectAll();

    inputvs4 = data_lense.m_inputvs;
    outputvs4 = data_lense.m_outputvs;

    System.out.println("==== Testing");
    System.out.println("Error is " + net4.error(inputvs4, outputvs4));
    net4.errorrate(inputvs4, outputvs4);
    System.out.println("\n");

    //==========================================================================
    //======== Neural Net for Lenses
    //==========================================================================

    System.out.println("==========================================");
    System.out.println("======== BUBIL Neural Net");
    System.out.println("==========================================");

    //set up
    hidLayers = 1;
    learnRate = 3;

    //Training
    DataProcessor data_bubil = new DataProcessor("BUBIL.training", 2);
    int[] layers5 = { 4, hidLayers, 1 };
    NeuralNet net5 = new NeuralNet(layers5);
    net4.connectAll();

    double[][] inputvs5 = data_bubil.m_inputvs;
    double[][] outputvs5 = data_bubil.m_outputvs;

    System.out.println("==== Paramaters");
    System.out.println("Learning Rate: " + learnRate);
    System.out.println("# of Hidden Layers: " + hidLayers);




    for (int n = 0; n < trainingRounds; ++n) {
      net5.train(inputvs5, outputvs5, learnRate);
    }

    System.out.println("==== Training");
    System.out.println("Error is :" + net5.error(inputvs5, outputvs5));
    net5.errorrate(inputvs5, outputvs5);
    System.out.println("\n");


    // Testing
    data_bubil = new DataProcessor("BUBIL.testing", 2);

    inputvs5 = data_bubil.m_inputvs;
    outputvs5 = data_bubil.m_outputvs;

    System.out.println("==== Testing");
    System.out.println("Error is :" + net5.error(inputvs5, outputvs5));
    net5.errorrate(inputvs5, outputvs5);

    return;
  }

}
