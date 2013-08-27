package matrix;

/**
 * LUP分解
 * P是置换矩阵，L是下三角矩阵，并且对角线为1，U是上三角矩阵；P的出现主要是为了选主元，在选取Ade非对角线元素中选主元避免除数为0，
 * 除此以外，除数的值也不能过小，否则导致计算中数值不稳定，因此所选的主元是个较大的值。详细参见算法导论P461
 * @author xhw
 *
 */
public class LUPDecomposition{

	private int p[];
	private int exchangeTimes=0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public double[][] decomposition(double a[][])
	{
		int length=a.length;
		//p是置换矩阵，p[i]=j说明P的第i行第j列为1
		p=new int [length];
		for(int i=0;i<length;i++)
		{
			p[i]=i;
		}
		
		for(int k=0;k<length;k++)
		{
			double max=0;
			int maxK=0;
			for(int i=k;i<length;i++)
			{
				if(Math.abs(a[i][k])>max)
				{
					max=Math.abs(a[i][k]);
					maxK=i;
				}
			}
			if(max==0)
			{
				System.out.println("singular matrix");
				return null;
			}
			if(k!=maxK)
			{
				//交换k和maxk行
				exchange(p,k,maxK);
				exchangeTimes++;
				for(int i=0;i<length;i++)
				{
					double temp=a[k][i];
					a[k][i]=a[maxK][i];
					a[maxK][i]=temp;
				}
			}
			
			//“原地”计算LU，矩阵a的上半部分为U，下半部分为L
			for(int i=k+1;i<length;i++)
			{
				a[i][k]=a[i][k]/a[k][k];
				for(int j=k+1;j<length;j++)
				{
					a[i][j]=a[i][j]-a[i][k]*a[k][j]; 
				}
			}
			
		}
		return a;
	}
	
	public void exchange(int p[],int k,int maxK)
	{
		int temp=p[k];
		p[k]=p[maxK];
		p[maxK]=temp;
	}

	public int[] getP() {
		return p;
	}

	public void setP(int[] p) {
		this.p = p;
	}

	public int getExchangeTimes() {
		return exchangeTimes;
	}

	public void setExchangeTimes(int exchangeTimes) {
		this.exchangeTimes = exchangeTimes;
	}

}
