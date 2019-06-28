package controler;

import com.alibaba.fastjson.JSON;
import model.Weather;
import service.GetWeather;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/GetStationAvg")
public class GetStationAvg extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = request.getParameter("station");
		String[] str= param.split("-");
		Weather temp = new GetWeather().getStationAvg(str[0],str[1]);
		String js = JSON.toJSONString(temp);
		
		response.getWriter().write(js);
	}
}
