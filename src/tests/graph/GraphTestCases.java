package tests.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import graph.Digraph;

public abstract class GraphTestCases {
	
	private final Digraph graph;
	
	public GraphTestCases(Digraph graph) {
		this.graph = graph;
	}
	
	@Test
	public void testCase1() {
		assertEquals(9, graph.distanceFromRoute('A', 'B', 'C'));
	}
	
	@Test
	public void testCase2() {
		assertEquals(5, graph.distanceFromRoute('A', 'D'));
	}
	
	@Test
	public void testCase3() {
		assertEquals(13, graph.distanceFromRoute('A', 'D', 'C'));
	}
	
	@Test
	public void testCase4() {
		assertEquals(22, graph.distanceFromRoute('A', 'E', 'B', 'C', 'D'));
	}
	
	@Test
	public void testCase5() {
		assertEquals(-1, graph.distanceFromRoute('A', 'E', 'D'));
	}
	
	@Test
	public void testCase6() {
		assertEquals(2, graph.countTripsWithMaxStops('C', 'C', 3, true));
	}
	
	@Test
	public void testCase7() {
		assertEquals(3, graph.countTripsWithMaxStops('A', 'C', 4, false));
	}
	
	@Test
	public void testCase8() {
		assertEquals(9, graph.shortestDistance('A', 'C'));
	}
	
	@Test
	public void testCase9() {
		assertEquals(9, graph.shortestDistance('B', 'B'));
	}
	
	@Test
	public void testCase10() {
		assertEquals(7, graph.countTripsWithMaxDistance('C', 'C', 30));
	}

}
