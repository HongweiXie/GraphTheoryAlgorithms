package util;

import java.util.ArrayList;
import java.util.List;

import entry.Node;

public class NodeUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static List<Node> generateNodeList(double graph[][])
	{
		int length=graph.length;
		List<Node> nodeList=new ArrayList<Node>();
		for(int i=0;i<length;i++)
		{
			Node n=new Node(i,Double.MAX_VALUE,0,null);
			nodeList.add(n);
		}
		for(int i=0;i<length;i++)
		{
			List<Node> list=new ArrayList<Node>();
			for(int j=0;j<length;j++)
			{
				if(i!=j&&graph[i][j]>0&&graph[i][j]<Double.MAX_VALUE)
				{
					list.add(nodeList.get(j));
				}
			}
			nodeList.get(i).setAdjacentNodes(list);
			
		}
		return nodeList;
	}
}
