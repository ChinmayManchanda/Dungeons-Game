package unsw.dungeon;

import java.util.LinkedList;

public class Node {
	String id;
    int yCor;
    int xCor;
    private boolean visited;
    LinkedList<Edge> edges;

    public Node(int xCor, int yCor) {
    	id = "ID X: " + xCor + " Y: " + yCor;
        this.xCor = xCor;
        this.yCor = yCor;
        visited = false;
        edges = new LinkedList<>();
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getyCor() {
		return yCor;
	}

	public void setyCor(int yCor) {
		this.yCor = yCor;
	}

	public int getxCor() {
		return xCor;
	}

	public void setxCor(int xCor) {
		this.xCor = xCor;
	}

	public LinkedList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Node [xCor=" + xCor + ", yCor=" + yCor + "]";
	}
	
    

}
