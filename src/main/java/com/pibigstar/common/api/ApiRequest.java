package com.pibigstar.common.api;
//package com.pibigstar.common.api;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.context.support.HttpRequestHandlerServlet;
//
//public class APIRequest extends HttpRequestHandlerServlet{
//	
//	private static final long serialVersionUID = 1L;
//	
//	@Autowired
//	private APIRequestHandler handler;
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doPost(req, resp);
//		handler.hand(req, resp);
//	}
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		super.doGet(req, resp);
//		handler.hand(req, resp);
//	}
//
//}
