package utils;

import java.util.Scanner;

import test.info;
import test.info2;

public class test {
	public static void main(String args[]){
		String s="Ｓis the set of integers";double b=0;
		System.out.println(s.charAt(0));
		System.out.println(s.codePointAt(1));
		Scanner sc=new Scanner(System.in);
		String str=sc.nextLine();
		info aa=new info();
		int a= sc.nextInt();
		
		if(sc.hasNext()){
			 b=sc.nextDouble();
		}
		
		System.out.println(str+a+b);
		info2 f =new info2();
	}
}
