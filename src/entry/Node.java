package entry;

import java.util.ArrayList;
import java.util.List;

public class Node {

	public int nodeId;
	public double distance;
	public int state;
	private Node parent;
	private List<Node> adjacentNodes;
	
	public Node(int nodeId, double distance, int state,Node parent) {
		super();
		this.nodeId = nodeId;
		this.distance = distance;
		this.state = state;
		this.parent=parent;
		adjacentNodes=new ArrayList<Node>();
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Node> getAdjacentNodes() {
		return adjacentNodes;
	}

	public void setAdjacentNodes(List<Node> adjacentNodes) {
		this.adjacentNodes = adjacentNodes;
	}
	
	
	
}
