package unsw.dungeon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class Graph {
	
	private Set<Node> nodes;
	
	public Graph () {
		nodes = new LinkedHashSet<>();
	}
	
	public void addArrayListNodes(ArrayList<Node> visited) {
		for (Node currNode: visited) {
			addNode(currNode);
		}
	}
	public void addNode(Node... n) {
	    nodes.addAll(Arrays.asList(n));
	}
	
	public void addEdge(Node source, Node dest) {
		
		if ((nodes.contains(source) != true) && (nodes.contains(dest) != true)) {
			nodes.add(source);
		    nodes.add(dest);
		}
		

	    // Using addEdgeHelper to make sure we don't have duplicate edges
	    addEdgeHelper(source, dest);

	    if (source != dest) {
	        addEdgeHelper(dest, source);
	    }
	}
	
	@Override
	public String toString() {
		return "Graph [nodes=" + nodes + "]";
	}

	private void addEdgeHelper(Node a, Node b) {
	    // Go through all the edges and see whether that edge has
	    // already been added
	    for (Edge edge : a.edges) {
	        if (edge.getSource() == a && edge.getDest() == b) {
	            // Update the value in case it's a different one now
	            edge.setDistance(1);
	            return;
	        }
	    }
	    // If it hasn't been added already (we haven't returned
	    // from the for loop), add the edge
	    a.edges.add(new Edge(a, b));
	}
	
	public boolean hasEdge(Node source, Node destination) {
	    LinkedList<Edge> edges = source.edges;
	    for (Edge edge : edges) {
	        if (edge.getDest().getId().equals(destination.getId())) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public String DijkstraShortestPath(Node start, Node end) {
	    // We keep track of which path gives us the shortest path for each node
	    // by keeping track how we arrived at a particular node, we effectively
	    // keep a "pointer" to the parent node of each node, and we follow that
	    // path to the start
		LinkedHashMap<String, String> changedAt = new LinkedHashMap<>();
	    changedAt.put(start.getId(), null);

	    // Keeps track of the shortest path we've found so far for every node
	    LinkedHashMap<String, Integer> shortestPathMap = new LinkedHashMap<>();

	    // Setting every node's shortest path weight to positive infinity to start
	    // except the starting node, whose shortest path weight is 0
	    for (Node node : nodes) {
	        if (node.getId().equals(start.getId()))
	            shortestPathMap.put(start.getId(), 0);
	        else shortestPathMap.put(node.getId(), Integer.MAX_VALUE);
	    }
	

	    // Now we go through all the nodes we can go to from the starting node
	    // (this keeps the loop a bit simpler)
	    for (Edge edge : start.getEdges()) {
	        shortestPathMap.put(edge.getDest().getId(), edge.getDistance());
	        
	        changedAt.put(edge.getDest().getId(), start.getId());
	    }
	    start.visit();

	    // This loop runs as long as there is an unvisited node that we can
	    // reach from any of the nodes we could till then
	    while (true) {
	        Node currentNode = closestReachableUnvisited(shortestPathMap);
	        // If we haven't reached the end node yet, and there isn't another
	        // reachable node the path between start and end doesn't exist
	        // (they aren't connected)
	        if (currentNode == null) {
	            System.out.println("There isn't a path between vertex P " + start.xCor + start.yCor + " and vertex Z "  + end.xCor + end.yCor);
	            return null;
	        }
	        // If the closest non-visited node is our destination, we want to print the path
	        if (currentNode.equals(end)) {
	            String child = end.getId();
	            
	            // It makes no sense to use StringBuilder, since
	            // repeatedly adding to the beginning of the string
	            // defeats the purpose of using StringBuilder
	            String path = end.toString();
	            String endreturn = "";
	            int counter = 0;
	            while (true) {
	                String parent = changedAt.get(child);
	                if (parent == null) {
	                    break;
	                }

	                // Since our changedAt map keeps track of child -> parent relations
	                // in order to print the path we need to add the parent before the child and
	                // it's descendants
	                path = parent.toString() + " " + path;
	                child = parent;
	                counter++;

	            }
	            String child1 = end.getId();
	            while (counter > 1) {
	            	String parent = changedAt.get(child1);
	                if (parent == null) {
	                    break;
	                }

	                // Since our changedAt map keeps track of child -> parent relations
	                // in order to print the path we need to add the parent before the child and
	                // it's descendants
	                path = parent.toString() + " " + path;
	                child1 = parent;
	                endreturn = child1;
	                counter--;
	            }
	            //System.out.println(child1);
	            //System.out.println(path);
	            
	            return endreturn;
	        }
	        currentNode.visit();

	        // Now we go through all the unvisited nodes our current node has an edge to
	        // and check whether its shortest path value is better when going through our
	        // current node than whatever we had before
	        for (Edge edge : currentNode.edges) {
	            if (edge.getDest().isVisited())
	                continue;
	            	if ((shortestPathMap.get(currentNode.getId()) + edge.getDistance()) < shortestPathMap.get(edge.getDest().getId())) {
		                shortestPathMap.put(edge.getDest().getId(), shortestPathMap.get(currentNode.getId()) + edge.getDistance());
		                changedAt.put(edge.getDest().getId(), currentNode.getId());
		            }
	            
	        }

	    }
	}
	
	
	private Node closestReachableUnvisited(HashMap<String, Integer> shortestPathMap) {

	    Integer shortestDistance = Integer.MAX_VALUE;
	    Node closestReachableNode = null;
	    for (Node node : nodes) {
	        if (node.isVisited())
	            continue;

	        int currentDistance = shortestPathMap.get(node.getId());
	        if (currentDistance == Integer.MAX_VALUE)
	            continue;

	        if (currentDistance < shortestDistance) {
	            shortestDistance = currentDistance;
	            closestReachableNode = node;
	        }
	    }
	    return closestReachableNode;
	}
	
	public void printEdges() {
	    for (Node node : nodes) {
	        LinkedList<Edge> edges = node.edges;

	        if (edges.isEmpty()) {
	            System.out.println("Node " + node.getId() + " has no edges.");
	            continue;
	        }
	        System.out.print("Node " + node.getId() + " has edges to: ");

	        for (Edge edge : edges) {
	            System.out.print(edge.getDest().getId() + "(" + edge.getDistance() + ") ");
	        }
	        System.out.println();
	    }
	}
	
	
}
