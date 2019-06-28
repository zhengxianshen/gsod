package service;

import model.Station;
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
 * @Date 19-6-25 下午2:17
 * @Since 1.0
 */
public class GetWeather {
	public List<Weather> getWeather(String[] value) {
		ArrayList<Weather> list = new ArrayList<>();
		String sql = "select * from weather2 where stn=? and wban = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = DB.getConn();
		if (conn == null) return null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, value[0]);
			ps.setString(2, value[1]);
			rs = ps.executeQuery();
			Weather temp = null;
			double[] v = null;
			while (rs.next()) {
				temp = new Weather(rs.getString(4));
				v = new double[]{rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10),
						rs.getDouble(11), rs.getDouble(12), rs.getDouble(13), rs.getDouble(14), rs.getDouble(15), rs.getDouble(16)};
				temp.setValue(v);
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) conn = null;
			}
		}
		return list;
	}
	
	public Weather getStationAvg(String stn,String wban){
		
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		Weather temp=null;
		String sql = "select sum(temp.TEMP < 59),sum(temp.TEMP>=59 and temp.TEMP<=77),sum(temp.TEMP >77),max(temp.TEMP),min(temp.TEMP),avg(temp.TEMP) from temp where stn = ? and wban = ? group by stn,wban ";
		conn = DB.getConn();
		try {
			temp =null;
			ps = conn.prepareStatement(sql);
			ps.setString(1,stn);
			ps.setString(2,wban);
			rs = ps.executeQuery();
			if(rs.next()){
				temp = new Weather(stn+"-"+wban);
				temp.setValue(new double[]{rs.getDouble(1),rs.getDouble(2),
						rs.getDouble(3),rs.getDouble(4),rs.getDouble(5),
						rs.getDouble(6)
				});
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
		
		return temp;
	}
	
}

