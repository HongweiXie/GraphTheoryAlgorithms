package connectivity;

import maxflow.FordFulkerson;

public class EdgeConnectivity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double graph[][]={{0,1,0,1,0,0},
				  {1,0,1,0,1,0},
				  {0,1,0,0,0,1},
				  {1,0,0,0,1,0},
				  {0,1,0,1,0,1},
				  {0,0,1,0,1,0}};
		double graph2[][]={
				  {0,1,1,1,1,1},
				  {1,0,1,1,1,1},
				  {1,1,0,1,1,1},
				  {1,1,1,0,1,1},
				  {1,1,1,1,0,1},
				  {1,1,1,1,1,0}};
		System.out.println(edgeConnectivity(graph2));

	}
	
	
	public static int edgeConnectivity(double graph[][])
	{
		double min=Double.MAX_VALUE;
		for(int i=1;i<graph.length;i++)
		{
			double maxflow=FordFulkerson.edmondsKarpMaxFlow(graph, 0, i);
			if(maxflow<min)
				min=maxflow;
		}
		return (int)min;
	}

}
