/*
 * CS440 Programming Assignment 2
 * Author: Zhiqiang Ren
 * Edited by: Veena Dali and Leen AlShenibr
 * Date: 2/25/15
 */

public class Connection {

    public Connection(Node from, Node to, double weight) {
        m_from = from;
        m_to = to;
        m_weight = weight;
    }

    public Node getFromNode() {
        return m_from;
    }

    public Node getToNode() {
        return m_to;
    }

    public double getWeight() {
        return m_weight;
    }
    public void addWeight(double weight){
        m_weight = m_weight +weight;
    }

    private double m_weight;
    private double m_deltaw;

    private Node m_from;
    private Node m_to;

}
