package com.fxdestination.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fxdestination.constant.Constant;
import com.fxdestination.dao.BranchDAO;
import com.fxdestination.entity.Branch;

@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LocationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Branch> branchList = new ArrayList<Branch>();
		BranchDAO branchDAO = new BranchDAO();

		branchList = branchDAO.getAllBranch();

		request.setAttribute(Constant.ATTRIBUTE_BRANCH_LIST, branchList);

		RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.LOCATIONPATH);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
