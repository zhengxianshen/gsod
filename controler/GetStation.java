package controler;

import com.alibaba.fastjson.JSON;
import model.Station;


import service.GetStations;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;


public class GetStation extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String country = request.getParameter("name");
		String jsonString = null;
		ArrayList<Station> list = null;
		if (country != null) {
			System.out.println(country);
			list = new GetStations().getCountryStation(country);
			jsonString = JSON.toJSONString(list);
		} else {
			ServletContext context = request.getServletContext();
			if (context.getAttribute("stations") == null) {
				list = (ArrayList<Station>) new GetStations().getStations();
				jsonString = JSON.toJSONString(list);
				context.setAttribute("stations", jsonString);
			} else jsonString = (String) context.getAttribute("stations");
		}
		response.getWriter().write(jsonString);
	}
	
}
