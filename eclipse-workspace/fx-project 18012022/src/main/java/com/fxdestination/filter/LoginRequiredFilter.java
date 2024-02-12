package com.fxdestination.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/ProductServlet", "/ToolsServlet", "/product.jsp", "/tools.jsp" })
public class LoginRequiredFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

		boolean isLoggedIn = (session != null && session.getAttribute("User") != null);
		String loginURI = httpRequest.getContextPath() + "MemberServlet";
		boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
		boolean isLoginPage = httpRequest.getRequestURI().endsWith("#Login");

		String url = httpRequest.getRequestURL().toString();
		String activeServlet = "";
		if (url.contains("Product")) {
			activeServlet = "IndexServlet#Login?filter=product";
		} else if (url.contains("Tools")) {
			activeServlet = "IndexServlet#Login?filter=tools";
		} else {
			activeServlet = "IndexServlet#Login";
		}
		;

		if (isLoggedIn && isLoginPage) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("MemberServlet");
			dispatcher.forward(request, response);
		} else if (isLoggedIn || isLoginPage) {
			chain.doFilter(request, response);
		} else {
			httpResponse.sendRedirect(activeServlet);
		}

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
