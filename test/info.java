package test;

import java.time.LocalDate;
import java.util.ArrayList;

public class info implements Cloneable{
	public int age;
	public String name;
	private int b;
	
	public info(int a,String n){
		age=a;
		name=n;
		b=4;
	}
	public info(){
		this(3,"dfd");
	}
	public void setB(info a){
		this.b=6;
		a.b=5;
	}
	
	protected int get(){
		this.set();
		return this.age;
		
	}
	
	private void print(){
		System.out.println("hello");
	}
	
	
	/**
	 * @param
	 * @since
	 * @return

	 * @see test #main label // STOPSHIP: 19-6-23
	 * <a href="gsod.src.test.test">label</a>
	 */
	private int set(){
		this.age=1;
		return age;
	}
	
	
	@Override
	public boolean equals(Object obj){
		
		return this==obj&&obj==null&&getClass()!=obj.getClass()&&(!(obj instanceof info));
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public  static void  main(String[] args) throws CloneNotSupportedException {
		info i = new info(1,"zheng");
		info f = (info)i.clone();
		LocalDate date=LocalDate.of(1995,10,02);
		int m=date.getDayOfMonth();
		System.out.println();
		f.get();
		date.getMonth();
		ArrayList<info> list =new ArrayList<>();
		
		f.set();
		LocalDate n= date.plusMonths(5);
		date=date.minusDays(1);
		System.out.println(n.toString());
		System.out.println((date.toString()));
		info2 f2=new info2();
		info2 f3=f2.clone();
		f3.set();
		f2.pr();
		f3.pr();
	}
	
}
