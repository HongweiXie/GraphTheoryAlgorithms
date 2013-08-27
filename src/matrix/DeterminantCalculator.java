package matrix;

/**
 * 矩阵和可以用来快速地计算矩阵的行列式，因为det(A) = det(L) det(U)，而三角矩阵的行列式就是对角线元素的乘积。如果要求L 是单位三角矩阵，Uii的乘积
 * 那么同样的方法也可以应用于LUP分解，只需乘上P的行列式，即相应置换的符号差。
 * @author xhw
 *
 */
public class DeterminantCalculator {

	private LUPDecomposition lu;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

	public DeterminantCalculator()
	{
		this.lu=new LUPDecomposition();
	}
	
	public double det(double a[][])
	{
		a=lu.decomposition(a);
		if(a==null)
			return 0;
		double d=1;
		for(int i=0;i<a.length;i++)
		{
			d=d*a[i][i];
		}
		int n=lu.getExchangeTimes();
		if(n%2==0)
			return d;
		else
			return -d;
	}
	

}
