package service;

import model.Weather;
import utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhengxs
 * @Date 19-6-26 上午10:32
 * @Since 1.0
 */
public class GetCountryData {
	public ArrayList getWeather(String country){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ArrayList<Weather> list = new ArrayList<>();
		String sql = "select yearmoda,max(temp),min(temp),avg(temp) from temp where country=? group by yearmoda;";
		
		conn = DB.getConn();
		Weather w=null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,country);
			rs =  ps.executeQuery();
			while(rs.next()){
				w=new Weather(rs.getDate(1).toString());
				w.setValue(new double[]{rs.getDouble(2),rs.getDouble(3),rs.getDouble(4)});
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				rs.close();
				ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	public ArrayList<Weather> getStation(String country){
		ArrayList<Weather> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		String sql = "select t1.*,CLIST.country from station_info,CLIST,(select stn,wban,count(1),sum(temp<=59)as cold,sum(temp>59 and temp <77)as suit,sum(temp>=77)as hot from temp group by stn,wban)t1\n" +
				"where t1.stn = station_info.stn and t1.wban = station_info.wban and station_info.country_code=CLIST.FID and COUNTRY =?";
		conn = DB.getConn();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1,country);
			rs = ps.executeQuery();
			Weather w =null;
			double[] v;
			while(rs.next()){
				w = new Weather(rs.getString(1)+"-"+rs.getString(2));
				v =new double[]{rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),rs.getDouble(6)};
				w.setValue(v);
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
