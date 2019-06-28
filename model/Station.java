package model;

import java.util.Arrays;

/**
 * @Author zhengxs
 * @Date 19-6-24 下午5:27
 * @Since 1.0
 */
public class Station {
	
	private String name;
	private String[] value;
	public Station(){
	
	}
	
	public Station(String name){
		this.name=name;
	}
	
	public void setStation_name(String name) {
		this.name = name;
	}
	
	public String getName(){return name;}
	public String[] getValue(){
		return value;
	}
	
	public void setValue(String[] v){
		this.value=v;
	}
	
	@Override
	public String toString() {
		return "Station{" +
				"name='" + name + '\'' +
				", value=" + Arrays.toString(value) +
				'}';
	}
}
