package util;

import java.util.PriorityQueue;

import entry.Edge;

public class EdgeUtil {

	/**
	 * 生成边的排序好的队列
	 * @param graph
	 * @return
	 */
	public static PriorityQueue<Edge> generateEdgeList(double[][] graph) {
		PriorityQueue<Edge> edgeList=new PriorityQueue<Edge>();
		int rlength=graph.length;
		int clength=graph[0].length;
		for(int i=0;i<rlength;i++)
		{
			for(int j=i+1;j<clength;j++ )
			{
				if(graph[i][j]>0&graph[i][j]<Double.MAX_VALUE)
				{
					Edge e=new Edge(i,j,graph[i][j]);
					edgeList.add(e);
				}
			}
		}
		return edgeList;
	}

}
