package traversability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 旅行商问题，动态规划解法，复杂度O(n^2*2^n)
 * @author xhw
 *
 */
public class testTravellingSalesmanProblem {

	
	public static AdjacentMatrix AM;
	public static int currentStart;
	public static Map<String,String> path=new HashMap<String,String>();
	public static double[][] result;
	public static AdjacentMatrix lastAM;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double graph[][]={{0,5,8,5,5},
						  {5,0,9,7,8},
						  {8,9,0,9,16},
						  {5,7,9,0,8},
						  {5,8,16,8,0}};
		
		String label[]={"0","1","2","3","4"};
		result=new double[label.length][label.length];
		//AdjacentMatrix am=new AdjacentMatrix(label,graph);
		AM=new AdjacentMatrix(label,graph);
		currentStart=1;
		System.out.println(DPOfTSPFromStartPoint(AM,currentStart,1));
		System.out.print(path);
		
		/*for(int i=0;i<result.length;i++)
		{
			System.out.println();
			for(int j=0;j<result.length;j++)
			{
				System.out.format("%5f ", result[i][j]);
			}
		}*/
		while(lastAM!=null)
		{
			System.out.println(lastAM);
			lastAM=lastAM.child;
		}
	}
	
	public static double DPOfTSPFromStartPoint(AdjacentMatrix adjacentMatrix,int start,int index)
	{
		double graph[][]=adjacentMatrix.graph;
		if(graph.length<1)
			return 0;
		if(graph.length==1)
		{
			int end=Integer.parseInt(adjacentMatrix.labels[0]);
			return AM.graph[end][currentStart];
		}
		
		double min=Double.MAX_VALUE;
		AdjacentMatrix subAM=subAdjacentMatrix(adjacentMatrix,start);
		int v=0;
		String label = null;
		for(int i=0;i<graph.length;i++)
		{
			if(start!=i&&graph[start][i]<Double.MAX_VALUE&&graph[start][i]>0)
			{
				double temp=DPOfTSPFromStartPoint(subAM,v,index+1)+graph[start][i];
				//System.out.println(index+","+adjacentMatrix.labels[i]);
				if(temp<min)
				{
					min=temp;
					label=adjacentMatrix.labels[i];
					
					
				}
				v++;
				//result[Integer.parseInt(adjacentMatrix.labels[start])][Integer.parseInt(adjacentMatrix.labels[i])]=temp;
			}
		}
		path.put(adjacentMatrix.labels[start]+","+index+","+label, label);
		adjacentMatrix.child=subAM;
		lastAM=adjacentMatrix;
		
		
		
		return min;
	}
	/**
	 * 去掉start行，start列的内容
	 * @param start
	 * @return
	 */
	public static AdjacentMatrix subAdjacentMatrix(AdjacentMatrix adjacentMatrix,int start)
	{
		double graph[][]=adjacentMatrix.graph;
		double sub[][]=new double[graph.length-1][graph.length-1];
		int row=0;int col=0;
		String[] label=new String[adjacentMatrix.labels.length-1];
		for(int i=0;i<graph.length;i++)
		{
			if(i==start)
				continue;
			col=0;
			for(int j=0;j<graph.length;j++)
			{
				if(j==start)
					continue;
				sub[row][col]=graph[i][j];
				col++;
			}
			row++;
		}
		col=0;
		for(int i=0;i<adjacentMatrix.labels.length;i++)
		{
			if(i==start)
				continue;
			label[col]=adjacentMatrix.labels[i];
			col++;
		}
		AdjacentMatrix am=new AdjacentMatrix(label,sub);
		return am;
	}
	
	

}

