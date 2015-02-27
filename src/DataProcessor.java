/*
* CS440 Programming Assignment 2
* Author: Zhiqiang Ren
* Edited by: Veena Dali and Leen AlShenibr
* Date: 2/25/15
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Locale;

public class DataProcessor {
  static String[] A1_cand = { "b", "a", "?" };
  static String[] A4_cand = { "u", "y", "l", "t", "?" };
  static String[] A5_cand = { "g", "p", "gg", "?" };
  static String[] A6_cand = { "c", "d", "cc", "i", "j", "k", "m", "r", "q",
  "w", "x", "e", "aa", "ff", "?" };
  static String[] A7_cand = { "v", "h", "bb", "j", "n", "z", "dd", "ff", "o", "?" };
  static String[] A9_cand = { "t", "f", "?" };
  static String[] A10_cand = { "t", "f", "?" };
  static String[] A12_cand = { "t", "f", "?" };
  static String[] A13_cand = { "g", "p", "s", "?" };

  public double[][] m_inputvs;
  public double[][] m_outputvs;

  private List<CreditData> m_datas;
  private List<LensesData> m_lenses;
  private List<BUBILData> m_bubil;

  //==========================================================================
  //======== Data Classes & Constructors
  //==========================================================================

  class CreditData {
    public CreditData(double[] inputs, double[] outputs) {
      m_inputs = inputs;
      m_outputs = outputs;
    }

    public double[] m_inputs;
    public double[] m_outputs;
  }

  class LensesData {
    public LensesData(double[] inputs, double[] outputs) {
      m_inputs = inputs;
      m_outputs = outputs;
    }

    public double[] m_inputs;
    public double[] m_outputs;
  }

  class BUBILData {
    public BUBILData(double[] inputs, double[] outputs) {
      m_inputs = inputs;
      m_outputs = outputs;
    }

    public double[] m_inputs;
    public double[] m_outputs;
  }

  double cvtDouble(String [] candidates, String name) {
    for (int i = 0; i < candidates.length; ++i) {
      if (candidates[i].equals(name)) {
        return i;
      }
    }
    return candidates.length;
  }

  //Id is to denote the data set: 0 -> credit card, 1 -> Lenses, 2 -> BUBIL
  public DataProcessor(String aFileName, int id) throws FileNotFoundException {

    if(id == 0)
    {
      m_datas = new ArrayList<CreditData>();
      Scanner s = null;

      FileReader f = new FileReader(aFileName);

      try {
        s = new Scanner(new BufferedReader(f));
        s.useLocale(Locale.US);

        while (s.hasNextLine()) {
          CreditData data = processLine(s.nextLine());
          m_datas.add(data);
        }
      } finally {
        s.close();
      }

      int i = 0;
      m_inputvs = new double[m_datas.size()][];
      m_outputvs = new double[m_datas.size()][];
      for (CreditData data: m_datas) {
        m_inputvs[i] = data.m_inputs;
        m_outputvs[i] = data.m_outputs;
        ++i;
      }

      m_inputvs = normalize(m_inputvs);
    }
    else if(id == 1)
    {
      m_lenses = new ArrayList<LensesData>();
      Scanner s = null;

      FileReader f = new FileReader(aFileName);

      try {
        s = new Scanner(new BufferedReader(f));
        s.useLocale(Locale.US);

        while (s.hasNextLine()) {
          LensesData data = processLine1(s.nextLine());
          m_lenses.add(data);
        }
      } finally {
        s.close();
      }

      int i = 0;
      m_inputvs = new double[m_lenses.size()][];
      m_outputvs = new double[m_lenses.size()][];
      for (LensesData data : m_lenses) {
        m_inputvs[i] = data.m_inputs;
        m_outputvs[i] = data.m_outputs;
        ++i;
      }
      m_inputvs = normalize(m_inputvs);
    }
    else if( id == 2)
    {
      m_bubil = new ArrayList<BUBILData>();
      Scanner s = null;

      FileReader f = new FileReader(aFileName);

      try {
        s = new Scanner(new BufferedReader(f));
        s.useLocale(Locale.US);

        while (s.hasNextLine()) {
          BUBILData data = processLine2(s.nextLine());
          m_bubil.add(data);
        }
      } finally {
        s.close();
      }

      int i = 0;
      m_inputvs = new double[m_bubil.size()][];
      m_outputvs = new double[m_bubil.size()][];
      for (BUBILData data : m_bubil) {
        m_inputvs[i] = data.m_inputs;
        m_outputvs[i] = data.m_outputs;
        ++i;
      }
      m_inputvs = normalize(m_inputvs);
    }
  }



  //==========================================================================
  //======== Process Lines for the different data
  //==========================================================================

  public LensesData processLine1(String line)
  {
    Scanner scanner = new Scanner(line);
    scanner.useDelimiter(",");

    double[] inputs = new double[4];
    double[] outputs = new double[1];

    inputs[0] = nextDouble(scanner);
    inputs[1] = nextDouble(scanner);
    inputs[2] = nextDouble(scanner);
    inputs[3] = nextDouble(scanner);

    double output = scanner.nextDouble();
    if (output == 1) {
      output = 0;
    } else if (output == 2) {
      output = 0.5;
    } else {
      output = 1;
    }
    outputs[0] = output;
    return new LensesData(inputs, outputs);
  }

  public BUBILData processLine2(String line)
  {
    Scanner scanner = new Scanner(line);
    scanner.useDelimiter(",");

    double[] inputs = new double[4];
    double[] outputs = new double[1];

    inputs[0] = nextDouble(scanner);
    inputs[1] = nextDouble(scanner);
    inputs[2] = nextDouble(scanner);
    inputs[3] = nextDouble(scanner);

    double output = scanner.nextDouble();
    output = output/5.0;
    outputs[0] = output;
    return new BUBILData(inputs, outputs);
  }


  public CreditData processLine(String line)
  {
    Scanner scanner = new Scanner(line);
    scanner.useDelimiter(",");

    double[] inputs = new double[15];
    double[] outputs = new double[1];

    inputs[0] = cvtDouble(A1_cand, scanner.next());
    inputs[1] = nextDouble(scanner);
    inputs[2] = nextDouble(scanner);
    inputs[3] = cvtDouble(A4_cand, scanner.next());
    inputs[4] = cvtDouble(A5_cand, scanner.next());
    inputs[5] = cvtDouble(A6_cand, scanner.next());
    inputs[6] = cvtDouble(A7_cand, scanner.next());
    inputs[7] = nextDouble(scanner);
    inputs[8] = cvtDouble(A9_cand, scanner.next());
    inputs[9] = cvtDouble(A10_cand, scanner.next());
    inputs[10] = nextDouble(scanner);
    inputs[11] = cvtDouble(A12_cand, scanner.next());
    inputs[12] = cvtDouble(A13_cand, scanner.next());
    inputs[13] = nextDouble(scanner);
    inputs[14] = nextDouble(scanner);

    String output = scanner.next();
    if (output.equals("+")) {
      outputs[0] = 1.0;
    } else {
      outputs[0] = 0.0;
    }
    return new CreditData(inputs, outputs);
  }


  //==========================================================================
  //======== Helper Functions
  //==========================================================================

  private double nextDouble(Scanner s) {
    if (s.hasNextDouble()) {
      return s.nextDouble();
    } else {
      s.next();
      return 0.0;
    }
  }

  //normalizes the inputs using cumalitive distribution (CDF)
  public double[][] normalize(double[][] vec)
  {
    for (int i = 0; i < vec[0].length; i++)
    {
      double sum = 0;

      for (int j = 0; j < vec.length; j++)
      {
        sum += vec[j][i];
      }

      double mean = sum / vec.length;
      double s = 0;

      for (int j = 0; j < vec.length; j++)
      {
        s += Math.pow(vec[j][i] - mean, 2);
      }

      double sig = Math.sqrt(s / vec.length);

      for (int j = 0; j < vec.length; j++)
      {
        vec[j][i] = (vec[j][i] - mean) / sig;
      }
    }

    return d;
  }

}
