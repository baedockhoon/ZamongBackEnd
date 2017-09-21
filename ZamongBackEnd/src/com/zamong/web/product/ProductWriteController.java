package com.zamong.web.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.ch.service.CashDTO;
import com.zamong.ch.service.impl.CashDAO;
import com.zamong.pd.service.ProductDTO;
import com.zamong.pd.service.impl.ProductDAO;

public class ProductWriteController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.getRequestDispatcher("/ZAMONG/Product/List.do").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");	
	String no = req.getParameter("pd_no");
		String pd_name = req.getParameter("pd_name");
		String pd_price = req.getParameter("pd_price");




		//price = req.getParameter("price");
	ProductDAO dao = new ProductDAO(req.getServletContext());
	ProductDTO dto = new ProductDTO();
	dto.setPd_name(pd_name);
	dto.setPd_price(pd_price);
		
		dao.insert(dto);
	
		dao.close();
		req.getRequestDispatcher("/ZAMONG/Product/List.do").forward(req, resp);
	/*req.getRequestDispatcher("/ZAMONG/MemberList.do").forward(req, resp);*/
	}
	

}
