package trees;

import matrix.DeterminantCalculator;

/**
 * 计算任意一个图的生成树的个数，是Kirchhoff提出的理论，通常称为Matrix Tree Theorem
 * Let G be a graph with V(G)={v1,v2,...,vn},let A={aij}be the adjacentcy matrix of G,
 * and let C={cij}be the n*n matrix, where cij=deg vi if i=j; cij=-aij if i!=j; Then the
 * number of spanning trees of G is the vlaue of any cofactor(余子式) of C
 * @author xhw
 *
 */
public class NumberOfSpanningTree {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		double a[][]={{0,1,1,0},
					  {1,0,1,0},
					  {1,1,0,1},
					  {0,0,1,0}};
		int n=numberOfSpanningTree(a);
		System.out.println("numberOfSpanningTree:"+n);
		
	}

	public static int numberOfSpanningTree(double[][] a) {

		double c[][]=generateKirchhoffMatrix(a);
		double confactor[][]=new double[c.length-1][c.length-1];
		for(int i=1;i<c.length;i++)
		{
			for(int j=1;j<c.length;j++)
			{
				confactor[i-1][j-1]=c[i][j];
				//System.out.print(c[i][j]+" ");
			}
			//System.out.println();
		}
		
		DeterminantCalculator dc=new DeterminantCalculator();
		int n=(int)dc.det(confactor);
		return n;
	}

	/**
	 * C={cij}be the n*n matrix, where cij=deg vi if i=j; cij=-aij if i!=j
	 * @param a
	 * @return
	 */
	public static double[][] generateKirchhoffMatrix(double[][] a) {

		int length=a.length;
		double c[][]=new double[length][length];
		for(int i=0;i<length;i++)
		{
			int deg=0;
			for(int j=0;j<length;j++)
			{
				deg+=a[i][j];
				c[i][j]=-a[i][j];
			}
			c[i][i]=deg;
			
		}
		return c;
	}
	
	
	
	

}
