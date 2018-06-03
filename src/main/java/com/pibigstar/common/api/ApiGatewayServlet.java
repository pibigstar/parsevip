package com.pibigstar.common.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 接收http请求
 * 将http请求转发到 ApiGatewayHandler
 * @author pibigstar
 *
 */
public class ApiGatewayServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	ApplicationContext context;
	private ApiGatewayHandler apiHand;

	@Override
	public void init() throws ServletException {
		super.init();
		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		apiHand = context.getBean(ApiGatewayHandler.class);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		apiHand.handle(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		apiHand.handle(req,resp);
	}
}
