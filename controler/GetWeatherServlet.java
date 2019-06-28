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
import java.util.List;

public class GetWeatherServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name;
		String [] str;
		name = request.getParameter("name");
		System.out.println("name====="+name);
		str = name.split("-");
		List<Weather> list = new ArrayList<Weather>();
		if(str.length==1){
		
		}
		else {
			list = new GetWeather().getWeather(str);
		}
		String json = JSON.toJSONString(list);
		response.getWriter().write(json);
	}
}
