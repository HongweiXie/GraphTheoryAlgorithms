package trees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import util.EdgeUtil;

import entry.Edge;
/**
 * ��С��������Kruskal�㷨��
 * For a connected weighted graph G, a spanning tree T of G is constructed as follows:
 * For the first edge e1 of T, we select any edge of G of minimum weight and for the second
 * edge e2 of T, we select any remaining edge of G of minimum weight. For the third edge e3
 * of T,we choose any remaining edge of G of minimum weight that dose not produce a cycle with
 * the previousely selected edges. We continue in this manner until a spanning tree is produced.
 * @author xhw
 *
 */
public class KruskalMinimumSpanningTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		double graph[][]={{0,1,4,4,5},
						  {1,0,3,7,5},
						  {4,3,0,6,Double.MAX_VALUE},
						  {4,7,6,0,2},
						  {5,5,Double.MAX_VALUE,2,0}};
		double tree[][]=minimumSpanningTree(graph);
		if(tree==null)
		{
			System.out.println("no spanning tree");
			System.exit(0);
		}
		PriorityQueue<Edge> edgeList=EdgeUtil.generateEdgeList(tree);
		for(Edge e:edgeList)
		{
			System.out.println(e);
		}
		
	}

	/**
	 * ����һ����Ȩֵ���ڽӾ���������i=j��ȨֵΪ0����i��j���ɴ�ȨֵΪDouble.max��������һ����С��������
	 * @param graph
	 * @return
	 */
	public static double[][] minimumSpanningTree(double[][] graph) {

		int rlength=graph.length;
		int clength=graph[0].length;
		PriorityQueue<Edge> edgeList;
		edgeList=EdgeUtil.generateEdgeList(graph);
		double tree[][]=new double[rlength][clength];
		/**
		 * ��ʼ��tree
		 */
		for(int i=0;i<rlength;i++)
		{
			for(int j=0;j<clength;j++)
			{
				if(i==j)
					tree[i][j]=0;
				else
					tree[i][j]=Double.MAX_VALUE;
			}
		}
		
		/**
		 * map���ڱ�ʶ�ߵ�ĳ�����������ĸ����ϣ���Ϊ����տ�ʼ���ڲ�ͬ�ļ��ϣ���ѡ��һ����ʱ���ͺϲ��������ϣ����ѡ��ı���ͬһ�������ڣ��ʹ����л�·������
		 */
		Map<Integer,Set<Integer>> map=new HashMap<Integer,Set<Integer>>();
		int edgeCount=0;
		while(edgeCount<rlength-1&&!edgeList.isEmpty())
		{
			Edge e=edgeList.poll();
			
			
			Set<Integer> setU=map.get(e.u);
			Set<Integer> setV=map.get(e.v);
			//System.out.println(e);
			//�ߵ��������㶼δ����������������
			if(setU==null&&setV==null)
			{
				Set<Integer> set=new HashSet<Integer>();
				set.add(e.u);
				set.add(e.v);
				map.put(e.u, set);
				map.put(e.v, set);
			}//��һ�����������������У�һ�����ڣ������ڵ��Ǹ����㼯�Ϻϲ���ȥ
			else if(setU==null&&setV!=null)
			{
				map.put(e.u, setV);
				setV.add(e.u);
			}
			else if(setU!=null&&setV==null)
			{
				map.put(e.v, setU);
				setU.add(e.v);
			}//�ֱ��ڲ�ͬ�ļ����У��ϲ���������
			else if(setU!=setV)
			{
				for(int v:setV)
				{
					map.put(v, setU);
					
				}
				
				setU.addAll(setV);
			}//����������ͬһ������У�����ֻ�·������
			else
			{
				continue;
			}
			tree[e.u][e.v]=e.weight;
			tree[e.v][e.u]=e.weight;
			edgeCount++;
			
		}
		
		
		if(edgeCount==rlength-1)
			return tree;
		else
			return null;
	}

	

	

}


