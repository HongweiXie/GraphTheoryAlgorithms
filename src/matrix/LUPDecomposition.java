package matrix;

/**
 * LUP�ֽ�
 * P���û�����L�������Ǿ��󣬲��ҶԽ���Ϊ1��U�������Ǿ���P�ĳ�����Ҫ��Ϊ��ѡ��Ԫ����ѡȡAde�ǶԽ���Ԫ����ѡ��Ԫ�������Ϊ0��
 * �������⣬������ֵҲ���ܹ�С�������¼�������ֵ���ȶ��������ѡ����Ԫ�Ǹ��ϴ��ֵ����ϸ�μ��㷨����P461
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
		//p���û�����p[i]=j˵��P�ĵ�i�е�j��Ϊ1
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
				//����k��maxk��
				exchange(p,k,maxK);
				exchangeTimes++;
				for(int i=0;i<length;i++)
				{
					double temp=a[k][i];
					a[k][i]=a[maxK][i];
					a[maxK][i]=temp;
				}
			}
			
			//��ԭ�ء�����LU������a���ϰ벿��ΪU���°벿��ΪL
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
