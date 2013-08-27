package entry;

import java.util.PriorityQueue;


public class Edge implements Comparable<Edge>
{
	public int u;
	public int v;
	public double weight;
	
	
	
	public Edge(int u, int v, double weight) {
		super();
		this.u = u;
		this.v = v;
		this.weight = weight;
	}



	@Override
	public int compareTo(Edge e) {
		if(e.weight==weight)
		return 0;
		else if(weight<e.weight)
			return -1;
		else
			return 1;
			
	}
	
	public String toString()
	{
		return u+"--"+v+":"+weight;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof Edge)
		{
			Edge e=(Edge)obj;
			if(e.u==u&&e.v==v&&e.weight==weight)
				return true;
		}
		return false;
	}
	
	
}
