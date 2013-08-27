package maxflow;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import util.EdgeUtil;
import util.NodeUtil;
import entry.Edge;
import entry.Node;
/**
 * Ford Fulkerson方法求最大流，这是一种迭代的方法，开始是，初始流为0，每次迭代中，课通过寻找一条增广路径来增加流值。反复进行这一过程，直至找不到任何增广路径
 * 本算法使用了Edmonds-Karp算法（一种对Ford Fulkerson方法的实现），在寻找增广路径时使用了寻找s到t的最短路径的方法。复杂度O（VE2）
 * @author xhw
 *
 */
public class FordFulkerson {

	private static double residualNetwork[][]=null;
	private static double flowNetwork[][]=null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double graph[][]={{0,16,13,0,0,0},
						  {0,0,10,12,0,0},
						  {0,4,0,0,14,0},
						  {0,0,9,0,0,20},
						  {0,0,0,7,0,4},
						  {0,0,0,0,0,0}};
		
		System.out.println(edmondsKarpMaxFlow(graph,0,5));

	}
	/**
	 * 实现FordFulkerson方法的一种算法——edmondsKarp算法
	 * @param graph
	 * @param s
	 * @param t
	 * @return
	 */
	public static double edmondsKarpMaxFlow(double graph[][],int s,int t)
	{
		int length=graph.length;
		//List<Node> nodeList=NodeUtil.generateNodeList(graph);
		double f[][]=new double[length][length];
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				f[i][j]=0;
			}
		}
		double r[][]=residualNetwork(graph,f);
		
		Node result=augmentPath(r,s,t);
		double sum=0;
		while(result!=null)
		{
			double cfp=0;
			cfp=minimumAugment(r,result);
			//说明已经没有增广路径了
			if(cfp==0)
			{
				break;
			}
			
			while(result.getParent()!=null)
			{
				Node parent=result.getParent();
				
				f[parent.nodeId][result.nodeId]+=cfp;
				f[result.nodeId][parent.nodeId]=-f[parent.nodeId][result.nodeId];
				
				result=parent;
			}
			
			sum+=cfp;
			r=residualNetwork(graph,f);
			result=augmentPath(r,s,t);
			
		}
		
		residualNetwork=r;
		flowNetwork=calculateFlowNetwork(graph,r);
		
		/*for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				
				System.out.print((flowNetwork[i][j]>0?flowNetwork[i][j]:0.0)+" ");
			}
			System.out.println();
		}*/
		return sum;
	}
	/**
	 * 重新计算流网络
	 * @param graph
	 * @param r
	 * @return
	 */
	private static double[][] calculateFlowNetwork(double[][] graph, double[][] r) {
		int length=graph.length;
		double f[][]=new double[graph.length][graph.length];
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				f[i][j]=graph[i][j]-r[i][j];
			}
		}
		return f;
	}

	/**
	 * 确定增广路径可扩充的流值
	 * @param graph
	 * @param result
	 * @return
	 */
	public static double minimumAugment(double graph[][],Node result)
	{
		double cfp=Double.MAX_VALUE;
		while(result.getParent()!=null)
		{
			Node parent=result.getParent();
			
			double weight=graph[parent.nodeId][result.nodeId];
			if(weight<cfp&&weight>0)
			{
				cfp=weight;
			}
			else if(weight<=0)
			{
				cfp=0;
				break;
			}
			result=parent;
		}
		return cfp;
	}

	/**
	 * 计算残余网络
	 * @param c
	 * @param f
	 * @return
	 */
	private static double[][] residualNetwork(double c[][],double f[][]) {
		int length=c.length;
		double r[][]=new double[length][length];
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				r[i][j]=c[i][j]-f[i][j];
			}
		}
		
		return r;
	}


	/**
	 * 广度优先遍历，寻找增光路径，也是最短增广路径
	 * @param graph
	 * @param s
	 * @param t
	 * @return
	 */
	public static Node augmentPath(double graph[][],int s,int t)
	{
		Node result=null;
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
			if(u.nodeId==t)
			{
				result=u;
				break;
			}
		}
		return  result;
		
	}

	public static double[][] getResidualNetwork() {
			
		return residualNetwork;
			
	}


	public static double[][] getFlowNetwork() {
		return flowNetwork;
	}



}
