package tests.graph.inputs;

import graph.Digraph;
import tests.graph.GraphTestCases;

public class GraphByArray extends GraphTestCases {

	public GraphByArray() {
		super(new Digraph("AB5", "BC4", "CD8", "DC8", "DE6", "AD5", "CE2", "EB3", "AE7"));
	}

}
