package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import util.NodeUtil;

import entry.Node;

public class BreadthFirstSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static List<Node> bfs(double graph[][],int s)
	{
		int length=graph.length;
		List<Node> nodeList=NodeUtil.generateNodeList(graph);
		nodeList.get(s).distance=0;
		nodeList.get(s).state=1;
		
		Queue<Node> queue=new LinkedList<Node>();
		queue.add(nodeList.get(s));
		
		while(!queue.isEmpty())
		{
			Node u=queue.poll();
			for(Node n:u.getAdjacentNodes())
			{
				if(n.state==0)
				{
					n.state=1;
					n.distance=u.distance+1;
					n.setParent(u);
					queue.add(n);
				}
			}
			u.state=2;
		}
		return nodeList;
		
	}
	
	

}
