package unsw.dungeon;


public class Edge implements Comparable<Edge>{
	private Node source;
	private Node dest;
	private int distance;
	
	public Edge(Node source, Node dest) {
		this.source = source;
		this.dest = dest;
		this.distance = 1;
	}

	public Node getSource() {
		return source;
	}

	public void setSource(Node source) {
		this.source = source;
	}

	public Node getDest() {
		return dest;
	}

	public void setDest(Node dest) {
		this.dest = dest;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.distance > o.distance) {
	        return 1;
	    }
	    else return -1;
	}
}
