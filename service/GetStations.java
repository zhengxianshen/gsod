package service;

import model.Station;
import utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhengxs
 * @Date 19-6-24 下午5:23
 * @Since 1.0
 *
 * 用于获得站点的数据,service类
 */

public class GetStations {
	
	private static Connection conn= null;
	
	/**
	 * @param
	 * @return ArrayList station的list
	 * @see DB#getConn()
	 *
	 */
	public ArrayList<Station> getStations()  {
		conn= DB.getConn();
		String sql="select stn,wban,longitude,latitude from station_info";
		ResultSet rs = null;
		ArrayList<Station> list= new ArrayList<>();
		Station temp = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			String[] v;
			while(rs.next()){
				temp = new Station(rs.getString(1)+"-"+rs.getString(2));
				v=new String[]{rs.getString(3),rs.getString(4)};
				temp.setValue(v);
				list.add(temp);
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				System.out.println("conn close");
				if(conn!=null)
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(conn!=null)conn=null;
			}
		}
		return list;
	}
	
	public ArrayList<Station> getCountryStation(String country){
		Connection conn = null;
		PreparedStatement ps =null;
		ResultSet rs = null;
		String sql = "select  station_name,stn,wban,longitude,latitude from CLIST,station_info where CLIST.country = ? and CLIST.fid = station_info.country_code";
		ArrayList<Station> list = new ArrayList<>();
		try {
			conn=DB.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1,country);
			rs = ps.executeQuery();
			Station temp = null;
			String [] v =null;
			while(rs.next()){
				temp = new Station(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
				v = new String[]{rs.getString(4),rs.getString(5)};
				temp.setValue(v);
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(conn!=null)conn=null;
			}
		}
		
		return list;
	}
}
