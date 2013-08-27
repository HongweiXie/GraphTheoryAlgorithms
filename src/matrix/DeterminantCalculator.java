package matrix;

/**
 * ����Ϳ����������ٵؼ�����������ʽ����Ϊdet(A) = det(L) det(U)�������Ǿ��������ʽ���ǶԽ���Ԫ�صĳ˻������Ҫ��L �ǵ�λ���Ǿ���Uii�ĳ˻�
 * ��ôͬ���ķ���Ҳ����Ӧ����LUP�ֽ⣬ֻ�����P������ʽ������Ӧ�û��ķ��Ų
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
