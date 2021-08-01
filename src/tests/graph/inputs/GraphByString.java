package tests.graph.inputs;

import graph.Digraph;
import tests.graph.GraphTestCases;

public class GraphByString extends GraphTestCases{
	
	public GraphByString() {
		super(new Digraph("AB5\n BC4\n CD8\n DC8\n DE6, AD5,CE2\tEB3,AE7"));
	}
	
}
