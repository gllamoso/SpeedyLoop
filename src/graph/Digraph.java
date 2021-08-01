package graph;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Digraph {
	
	private static final String ROUTE_REGEX = "[a-zA-Z]{2}\\d+";
	private static final String ROUTE_SEPARATOR_REGEX = "[\\s,]+";

	private static class Edge {
		private char dest;
		private int distance;

		private Edge(char dest, int distance) {
			this.dest = dest;
			this.distance = distance;
		}
	}

	private Map<Character, List<Edge>> nodes = new HashMap<>();
	
	public Digraph(String routesStr) {
		this(routesStr.split(ROUTE_SEPARATOR_REGEX));
	}
	
	public Digraph(Path filePath) throws IOException {
		this(new String(Files.readAllBytes(filePath)));
	}

	public Digraph(String... routes) {
		for (String route : routes)
			makeRoute(route);
	}

	private void makeRoute(String route) {
		if(!route.matches(ROUTE_REGEX))
			throw new IllegalArgumentException(String.format("Illegal route argument: %s. Please use the format {start char}{end char}{distance}", route));
		
		
		char start = route.charAt(0);
		char dest = route.charAt(1);
		int distance = Integer.parseInt(route.substring(2, route.length()));

		nodes.putIfAbsent(start, new ArrayList<>());
		nodes.get(start).add(new Edge(dest, distance));
	}
	
	/**
	 * Returns total distance required to complete the route. Returns -1 if route does not exist.
	 * @param towns Towns needed to be visited by the route.
	 * @return
	 */
	public int distanceFromRoute(char... towns) {
		if(towns.length <= 1) return 0;
		
		int curDistance = 0;
		
		for(int nextTownIdx = 1; nextTownIdx < towns.length; nextTownIdx++) {
			final char curTown = towns[nextTownIdx - 1];
			final char nextTown = towns[nextTownIdx];
			boolean nextTownFound = false;
			for(Edge e : nodes.get(curTown)) {
				if(e.dest == nextTown) {
					curDistance += e.distance;
					nextTownFound = true;
					break;
				}
			}
			if(!nextTownFound) return -1;
		}
		
		return curDistance;
	}
	
	/**
	 * Uses Depth First Search to count the possible routes from start to destination with stops less than or equal to a maximum amount of stops.
	 * @param start Starting town
	 * @param dest Destination town
	 * @param maxStops Maximum amount of stops
	 * @param countLessThan If true, counts possible routes less than the maximum amount of stops. Otherwise, only routes equal to maxStops is counted.
	 * @return
	 */
	public int countTripsWithMaxStops(char start, char dest, int maxStops, boolean countLessThan) {
		int[] count = new int[1];
		visitTownWithMaxStops(start, dest, 0, maxStops, count, countLessThan);
		return count[0];
	}
	
	private void visitTownWithMaxStops(char town, char dest, int stops, int maxStops, int[] count, boolean countLessThan) {
		if(stops == maxStops) return;
		
		for(Edge e: nodes.get(town)) {
			if(e.dest == dest && (countLessThan || stops + 1 == maxStops))
				count[0]++;
			visitTownWithMaxStops(e.dest, dest, stops + 1, maxStops, count, countLessThan);
		}
	}
	
	/**
	 * Uses Djikstra's algorithm to "relax" (or shorten) distance from each town by finding shorter routes.
	 * @param start Starting town
	 * @param dest Destination town
	 * @return
	 */
	public int shortestDistance(char start, char dest) {
		Map<Character, Integer> shortestDistanceMap = new HashMap<>();
		for(char town : nodes.keySet())
			shortestDistanceMap.put(town, Integer.MAX_VALUE);
		
		Queue<Character> q = new ArrayDeque<>();
		Set<Character> set = new HashSet<>(); // For checking if a town is already in queue
		q.add(start);
		set.add(start);
		while(!q.isEmpty()) {
			char curTown = q.poll();
			set.remove(curTown);
			final int curDistance = shortestDistanceMap.get(curTown) == Integer.MAX_VALUE ? 0 : shortestDistanceMap.get(curTown);
			for(Edge e : nodes.get(curTown)) {
				if(shortestDistanceMap.get(e.dest) <= curDistance + e.distance)
					continue;
				shortestDistanceMap.put(e.dest, curDistance + e.distance);
				if(!set.contains(e.dest)) {
					set.add(e.dest);
					q.add(e.dest);
				}
			}
		}
		
		return shortestDistanceMap.get(dest) == Integer.MAX_VALUE ? -1 : shortestDistanceMap.get(dest);
	}
	
	/**
	 * Uses Depth First Search to count the of possible routes from start to destination with a distance less than the maximum distance.
	 * @param start Starting town
	 * @param dest Destination town
	 * @param maxDistance Maximum distance
	 * @return 
	 */
	public int countTripsWithMaxDistance(char start, char dest, int maxDistance) {
		int[] count = new int[1];
		visitTownWithMaxDistance(start, dest, 0, maxDistance, count);
		return count[0];
	}
	
	private void visitTownWithMaxDistance(char town, char dest, int curDistance, int maxDistance, int[] count) {
		if(curDistance >= maxDistance) return;
		
		for(Edge e: nodes.get(town)) {
			if(curDistance + e.distance < maxDistance && e.dest == dest)
				count[0]++;
			visitTownWithMaxDistance(e.dest, dest, curDistance + e.distance, maxDistance, count);
		}
	}
}