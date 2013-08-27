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
 * Ford Fulkerson�����������������һ�ֵ����ķ�������ʼ�ǣ���ʼ��Ϊ0��ÿ�ε����У���ͨ��Ѱ��һ������·����������ֵ������������һ���̣�ֱ���Ҳ����κ�����·��
 * ���㷨ʹ����Edmonds-Karp�㷨��һ�ֶ�Ford Fulkerson������ʵ�֣�����Ѱ������·��ʱʹ����Ѱ��s��t�����·���ķ��������Ӷ�O��VE2��
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
	 * ʵ��FordFulkerson������һ���㷨����edmondsKarp�㷨
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
			//˵���Ѿ�û������·����
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
	 * ���¼���������
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
	 * ȷ������·�����������ֵ
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
	 * �����������
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
	 * ������ȱ�����Ѱ������·����Ҳ���������·��
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
