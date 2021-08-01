package tests.graph.inputs;

import java.io.IOException;
import java.nio.file.Paths;

import graph.Digraph;
import tests.graph.GraphTestCases;

public class GraphByFile extends GraphTestCases {
	
	public GraphByFile() throws IOException {
		super(new Digraph(Paths.get("src/input.txt")));
	}
}

