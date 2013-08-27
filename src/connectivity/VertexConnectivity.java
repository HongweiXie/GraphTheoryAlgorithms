package connectivity;

import maxflow.FordFulkerson;

public class VertexConnectivity {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double graph[][]={
				  {0,1,0,1,0,0},
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
		double vGraph[][]=TransGraph(graph,2);
		for(int i=0;i<vGraph.length;i++)
		{
			for(int j=0;j<vGraph.length;j++)
			{
				System.out.format("%15g ",vGraph[i][j]);
			}
			System.out.println();
		}
		
		
		System.out.println(vertexConnectivity(graph2));
		//System.out.println(Integer.MAX_VALUE);

	}
	
	public static int vertexConnectivity(double graph[][])
	{
		int min=Integer.MAX_VALUE;
		for(int i=0;i<graph.length;i++)
		{
			graph=TransGraph(graph,1);
			int c=transGraphEdgeConnectivity(graph);
			if(c<min)
				min=c;
		}
		return min;
	}
	
	public static double[][] TransGraph(double graph[][],int step)
	{
		for(int i=0;i<graph.length;i++)
		{
			for(int j=0;j<graph.length;j++)
			{
				int next=(graph.length+j-step)%graph.length;
				double temp=graph[i][j];
				graph[i][j]=graph[i][next];
				graph[i][next]=temp;
			}
		}
		
		double temp[];
		for(int i=0;i<graph.length;i++)
		{
			int next=(graph.length+i-step)%graph.length;
			temp=graph[i];
			graph[i]=graph[next];
			graph[next]=temp;
		}
		return graph;
	}
	
	
	public static int transGraphEdgeConnectivity(double graph[][])
	{
		double vGraph[][]=transVertexGraphToEdgeGraph(graph);
		double min=Double.MAX_VALUE;
		vGraph[0][1]=Integer.MAX_VALUE;
		vGraph[vGraph.length-2][vGraph.length-1]=Integer.MAX_VALUE;
		if(vGraph[1][vGraph.length-2]==Integer.MAX_VALUE)
		{
			vGraph[1][vGraph.length-2]=1;
		}
		min=FordFulkerson.edmondsKarpMaxFlow(vGraph, 0, vGraph.length-1);
		return (int) min;
	}
	
	//将点连通度问题转换为边连通度
	public static double[][] transVertexGraphToEdgeGraph(double graph[][])
	{
		int length=graph.length;
		double vGraph[][]=new double[length*2][length*2];
		for(int i=0;i<length*2;i++)
		{
			for(int j=0;j<length*2;j++)
			{
				vGraph[i][j]=0;
			}
		}
		for(int i=0;i<length;i++)
		{
			vGraph[i*2][i*2+1]=1;
			for(int j=0;j<length;j++)
			{
				if(graph[i][j]>0&&graph[i][j]<Double.MAX_VALUE)
				{
					vGraph[i*2+1][j*2]=Integer.MAX_VALUE;
				}
			}
		}
		return vGraph;
	}
	
	/*public static double[][] assistantGraph(double graph[][])
	{
		double aGraph[][]=new double[graph.length+2][graph.length+2];
		
		for(int i=0;i<graph.length+2;i++)
		{
			aGraph[0][i]=0;
			aGraph[i][0]=0;
			aGraph[graph.length+1][i]=0;
			aGraph[i][graph.length+1]=0;
		}
		
		aGraph[0][1]=1;
		aGraph[1][0]=1;
		aGraph[graph.length+1][graph.length]=1;
		aGraph[graph.length][graph.length+1]=1;
		
		for(int i=1;i<aGraph.length-1;i++)
		{
			for(int j=1;j<aGraph.length-1;j++)
			{
				aGraph[i][j]=graph[i-1][j-1];
			}
		}
		return aGraph;
		
		
	}*/

}
