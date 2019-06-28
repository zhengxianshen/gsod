package test;

/**
 * @Author zhengxs
 * @Date 19-6-23 下午11:30
 * @Since 1.0
 */
public class info2 extends info implements Cloneable{
	private int t;
	
	public info2(){
		t=3;
	}
	public info2 clone(){
		info2 n=new info2();
		try {
			n=(info2)super.clone();n.t=4;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	public void set(){
		t=10;
	}
	public void pr(){
		System.out.println(t);
	}
}
