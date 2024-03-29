/*
 * CS440 Programming Assignment 2
 * Author: Zhiqiang Ren
 * Edited by: Veena Dali and Leen AlShenibr
 * Date: 2/25/15
 */

import java.util.ArrayList;
import java.util.List;

public class Node {
    public Node(int layer, int pos, boolean isThreshold)
    {
        m_input = 0;
        m_output = 0;
        m_input_conn = new ArrayList<Connection>();
        m_output_conn = new ArrayList<Connection>();

        m_beta = 0;

        m_pos = pos;
        m_layer = layer;
    }

    public void addInputConnection(Connection con) {
        m_input_conn.add(con);
    }

    public void addOutputConnection(Connection con) {
        m_output_conn.add(con);
    }

    public void setOutput(double output) {
        m_output = output;
    }

    public void setBeta(double beta){
        m_beta = beta;
    }

    public double getOutput() {
        return m_output;
    }

		//Sigmoid function
    public double f(double sigma)
    {
        return 1 / (1 + Math.exp(-1 * sigma));
    }

    public double getBeta() {
        return m_beta;
    }

    public Connection getOutputConnection(int j) {
        return m_output_conn.get(j);
    }

    public List<Connection> getOutConnections(){
        return m_output_conn;
    }

    public List<Connection> getInputConnection() {
        return m_input_conn;
    }

    public int getPos() {
        return m_pos;
    }

    public int getLayer() {
        return m_layer;
    }

    public void calcOutput() {
        m_input = 0;
        m_output = 0;

        for (Connection con: m_input_conn) {
            m_input += (con.getWeight() * con.getFromNode().getOutput());
        }
        m_output = f(m_input);
    }

    private List<Connection> m_input_conn;
    private List<Connection> m_output_conn;

    private double m_input;
    private double m_output;

    private double m_beta;

    private int m_pos;  // starting from 0
    private int m_layer;  // starting form 0
}
