package model;

/**
 * @Author zhengxs
 * @Date 19-6-25 下午2:15
 * @Since 1.0
 */
public class Weather {
	private String date;
	private double [] value;
	
	public Weather(String date){
		this.date=date;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date= date;
	}
	
	public double[] getValue() {
		return value;
	}
	
	public void setValue(double[] value) {
		this.value = value;
	}
}
