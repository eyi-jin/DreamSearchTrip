package com.mycom.myboard.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// FrontEnd / project / trip / areaCategoryBasedListWithBE.html
@Controller
@CrossOrigin(
		origins="http://localhost:5500", 
		allowCredentials="true",
		allowedHeaders="*",
		methods= {RequestMethod.GET
				, RequestMethod.POST
				, RequestMethod.PUT
				, RequestMethod.DELETE
				, RequestMethod.OPTIONS
				,RequestMethod.HEAD})
public class AreaCategoryBasedListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private final String serviceId = "areaBasedList1";
    private final String serviceKey = "%2FicmQTs5MjuIFEvZGKIEXYvEk8TrlUIKAjQqVdKo9AYjjzNmHJmNb%2Bn1yM7QxiPHhqOI%2BDg7%2BwfOVyDkaRjvxg%3D%3D";
    private String serviceUrl = "";
    private String MobileOS = "ETC";
    private String MobileApp = "TestApp";
    private String _type = "json";
	private String numOfRows;
	private String pageNo;

	private String getServiceURL(String areaCode, String sigunguCode, String cat1, String cat2, String cat3) {
		StringBuilder sb = new StringBuilder("https://apis.data.go.kr/B551011/KorService1/");
		sb.append(serviceId)
			.append("?serviceKey=").append(serviceKey)
			.append("&numOfRows=").append(numOfRows)
			.append("&pageNo=").append(pageNo)
			.append("&MobileOS=").append(MobileOS)
			.append("&MobileOS=").append(MobileOS)
			.append("&MobileApp=").append(MobileApp)
			.append("&_type=").append(_type)
			.append("&areaCode=").append(areaCode)
			.append("&sigunguCode=").append(sigunguCode)
			.append("&cat1=").append(cat1)
			.append("&cat2=").append(cat2)
			.append("&cat3=").append(cat3);
		return sb.toString();
	}
	
	@RequestMapping("/trip/list")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// parameter
		String numOfRows = request.getParameter("numOfRows");
		if( numOfRows == null ) this.numOfRows = "10";
		
		String pageNo = request.getParameter("pageNo");
		if( pageNo == null ) this.pageNo = "1";
		
		String areaCode = request.getParameter("areaCode");
		areaCode = areaCode == null || "".equals(areaCode) ? "" : areaCode;
		
		String sigunguCode = request.getParameter("sigunguCode");
		sigunguCode = sigunguCode == null || "".equals(sigunguCode) ? "" : sigunguCode;
		
		String cat1 = request.getParameter("cat1");
		cat1 = cat1 == null || "".equals(cat1) ? "" : cat1;
		
		String cat2 = request.getParameter("cat2");
		cat2 = cat2 == null || "".equals(cat2) ? "" : cat2;
		
		String cat3 = request.getParameter("cat3");
		cat3 = cat3 == null || "".equals(cat3) ? "" : cat3;
		
		this.serviceUrl = getServiceURL(areaCode, sigunguCode, cat1, cat2, cat3);
		
		System.out.println(serviceUrl);
		// data request
		URL url = new URL(this.serviceUrl);
		
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        //conn.setRequestProperty("Content-type", "application/jdson");
        System.out.println("Response code: " + conn.getResponseCode());
        
        BufferedReader br;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder result = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
        	result.append(line);
        }
        br.close();
        conn.disconnect();
        
        System.out.println(result.toString());
        
//		response.addHeader("Access-Control-Allow-Origin", "*"); // VSCode Live Server Request
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().write(result.toString());
        
	}

}
