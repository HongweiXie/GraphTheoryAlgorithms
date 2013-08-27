package connectivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import util.EdgeUtil;
import entry.Edge;
/**
 * 计算一个图的连通分支
 * @author xhw
 *
 */
public class ConnectedBrance {

	private int CONNECTED_BRANCE_NUM=-1;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		double graph[][]={{0,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE},
				  {Double.MAX_VALUE,0,3,7,5},
				  {Double.MAX_VALUE,3,0,6,Double.MAX_VALUE},
				  {Double.MAX_VALUE,7,6,0,2},
				  {Double.MAX_VALUE,5,Double.MAX_VALUE,2,0}};
		ConnectedBrance cb=new ConnectedBrance();
		System.out.println(cb.numberOfConnectedBrance(graph));
	}
	
	/**
	 * 返回连通分支数
	 * @param graph
	 * @return
	 */
	public int numberOfConnectedBrance(double graph[][])
	{
		if(CONNECTED_BRANCE_NUM==-1)
		{
			Set<Set<Integer>> cs=getConnectedBranceInfo(graph);
			CONNECTED_BRANCE_NUM=cs.size();
		}
		return CONNECTED_BRANCE_NUM;
	}
	
	public Set<Set<Integer>> getConnectedBranceInfo(double graph[][])
	{
		Map<Integer,Set<Integer>> map=new HashMap<Integer,Set<Integer>>();
		Queue<Edge> edgeList;
		edgeList=EdgeUtil.generateEdgeList(graph);
		
		for(int i=0;i<graph.length;i++)
		{
			Set<Integer> s=new HashSet<Integer>();
			s.add(i);
			map.put(i, s);
		}
		
		while(!edgeList.isEmpty())
		{
			Edge e=edgeList.poll();
			Set<Integer> setU=map.get(e.u);
			Set<Integer> setV=map.get(e.v);
			if(setU!=setV)
			{
				for(int v:setV)
				{
					map.put(v, setU);
					
				}
				
				setU.addAll(setV);
			}//两个顶点在同一个结合中，会出现环路，舍弃
			else
			{
				continue;
			}
			
			
		}
		CONNECTED_BRANCE_NUM=map.values().size();
		Set<Set<Integer>> s=new HashSet<Set<Integer>>();
		s.addAll(map.values());
		return s;
	}

}


