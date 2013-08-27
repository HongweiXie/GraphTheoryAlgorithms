package matchings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import entry.Edge;
import entry.Node;

import maxflow.FordFulkerson;

public class Matching {

	private double graph[][];
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double graph[][]={
		{0,0,0,1,1,0},
		{0,0,0,0,1,1},
		{0,0,0,0,0,1},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0},
		{0,0,0,0,0,0}
		};
		double graph2[][]={
				{0,0,0,0,0,1,0,0,0},
				{0,0,0,0,0,1,0,1,0},
				{0,0,0,0,0,0,1,1,1},
				{0,0,0,0,0,0,0,1,0},
				{0,0,0,0,0,0,0,1,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0}
				};
		Matching m=new Matching(graph2);
		int L[]={0,1,2,3,4};
		int R[]={5,6,7,8};
		/*
		double flowNetwork[][]=m.getMaxMachingFromMaxFlow(graph2,L,R);
		
		int length=flowNetwork.length;
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				
				System.out.print((flowNetwork[i][j]>0?flowNetwork[i][j]:0.0)+" ");
			}
			System.out.println();
		}*/
		System.out.println(m.getMaxMaching(graph2, L, R));
	}
	public Matching(double graph[][])
	{
		this.graph=graph;
	}
	/**
	 * Let G be a bipartite graph with partite sets U and W such that r=|U|<=|W|.
	 * Then G contains a matching of cardinality r if and only if U is neighborly.(which means that :For any nonempty subset X,N(X)>=X)
	 * �������ַ����ĸ��Ӷ�ΪO(2^n),���Կ��Բ��������������㷨ֱ��������ƥ�䣬Ȼ��ȶ��Ƿ���perfect matching
	 * @return
	 */
	public boolean hasPerfectMatching(double graph[][],int L[],int R[])
	{
		
		return false;
	}
	
	/**
	 * ����������㷨ʵ�֣��������������
	 * @param graph
	 * @param L
	 * @param R
	 * @return
	 */
	public double[][] getMaxMachingFromMaxFlow(double graph[][],int L[],int R[])
	{
		int length=graph.length;
		double augGraph[][]=new double[length+2][length+2];
		for(int i=0;i<L.length;i++)
		{
			augGraph[0][L[i]+1]=1.0;
		}
		for(int i=0;i<R.length;i++)
		{
			augGraph[R[i]+1][length+1]=1.0;
		}
		for(int i=1;i<length+1;i++)
		{
			for(int j=1;j<length+1;j++)
			{
				augGraph[i][j]=graph[i-1][j-1];
			}
		}
		/*for(int i=0;i<length+2;i++)
		{
			for(int j=0;j<length+2;j++)
			{
				
				System.out.print(augGraph[i][j]+" ");
			}
			System.out.println();
		}*/
		double maxF=FordFulkerson.edmondsKarpMaxFlow(augGraph, 0, length+1);
		double flowNetwork[][]=FordFulkerson.getFlowNetwork();
		
		return flowNetwork;
	}
	
	/**
	 * �������㷨http://imlazy.ycool.com/post.1603708.html
	 * (1)���������ߡ�
	 * (2)����ڶ���ͼ�����ߣ��յ����Ұ�ߡ�
	   (3)·���ϵĵ�һ����һ�������ߣ�һ�����Ұ�ߣ�������֡�����ʵ����ͼ�����ʾ;�������һ�㣬��Ϊ����ͼͬһ�ߵĵ�֮��û�б���������Ҫ����Ŷ����
	   (4)����·����û���ظ��ĵ㡣
	   (5)�����յ㶼��Ŀǰ��û����Եĵ㣬���������е㶼���Ѿ���öԵġ�����ͼ1��ͼ2��ʾ����1��5�ݺͣ�2��6����ͼ1���������Ѿ���öԵĵ㣻�����3���յ�4Ŀǰ��û������������ԡ���
	   (6)·���ϵ����е��������߶�����ԭƥ���У����е�ż�����߶�������ԭƥ���С�����ͼ1��ͼ2��ʾ��ԭ�е�ƥ���ǣ�1��5�ݺͣ�2��6�ݣ���������ƥ�ı���ͼ2����������·���зֱ��ǵ�2�͵�4���ߡ�������·���ĵ�1��3��5���߶�û�г�����ͼ1������ƥ���С���
	   (7)���Ҳ������Ҫ��һ����������·���ϵ����е��������߼��뵽ԭƥ����ȥ����������·���е����е�ż�����ߴ�ԭƥ����ɾ�������������Ϊ����·����ȡ���������µ�ƥ�����ͱ�ԭƥ����������1��������ͼ2��ʾ���µ�ƥ�����������ɫ�ıߣ������к�ɫ�ı����ԭƥ����ɾ�������µ�ƥ����Ϊ3����
	   
	 * @param graph
	 * @param L
	 * @param R
	 */
	public List<Edge> getMaxMaching(double graph[][],int L[],int R[])
	{
		int length=graph.length;
		for(int i=0;i<length;i++)
		{
			for(int j=i;j<length;j++)
			{
				graph[j][i]=graph[i][j];
			}
		}
		Set<Integer> rSet=new HashSet<Integer>();
		Set<Integer> lSet=new HashSet<Integer>();
		List<Edge> list=new ArrayList<Edge>();
		for(int i=0;i<L.length;i++)
		{
			if(lSet.contains(L[i]))
				continue;
			Node result=null;
			int j=0;
			while(j<R.length)
			{
				result=FordFulkerson.augmentPath(graph, L[i], R[j]);
				if(result==null||rSet.contains(R[j]))
					j++;
				else
				{
					boolean b=reverse(result,list,rSet,lSet);
					//�����ɹ���ֱ������ѭ��������һ���󲿵������������·���������ڼ����ڸõ�Ѱ������·����֪���Ҳ���Ϊֹ
					if(b)
						break;
					else
						j++;
				}
			}
					
		}
		
		return list;
	}
	/**
	 * ���֤��������·����������6�����ʣ�����з�ת������������true������ʲôҲ����������false
	 * @param result
	 * @param list
	 * @param rSet
	 * @param lSet
	 * @return
	 */
	private boolean reverse(Node result,List<Edge> list,Set<Integer> rSet,Set<Integer> lSet) {

		int idx=0;
		List<Edge> oddEdge=new ArrayList<Edge>();
		List<Edge> evenEdge=new ArrayList<Edge>();
		while(result.getParent()!=null)
		{
			Node parent=result.getParent();
			
			if(idx%2==0)
			{
				Edge e=new Edge(parent.nodeId,result.nodeId,0);
				evenEdge.add(e);
				
			}
			else
			{
				Edge e=new Edge(result.nodeId,parent.nodeId,0);
				oddEdge.add(e);
			}
			idx++;
			result=parent;
		}
		/**
		 * ����6������
		 */
		for(int i=0;i<oddEdge.size();i++)
		{
			if(!list.contains(oddEdge.get(i)))
				return false;
			else
			{
				list.remove(oddEdge.get(i));
				//System.out.println("remove: "+oddEdge.get(i));
			}
		}
		for(int i=0;i<evenEdge.size();i++)
		{
			list.add(evenEdge.get(i));
			lSet.add(evenEdge.get(i).u);
			rSet.add(evenEdge.get(i).v);
			//System.out.println("add: "+evenEdge.get(i));
		}
		return true;
	}

}
