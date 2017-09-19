package com.zamong.web.payment;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.bp.service.BuyproductDTO;
import com.zamong.bp.service.impl.BuyproductDAO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;


public class PaymentListController extends HttpServlet {

	@Override 
	protected void service(
			HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		//3]요청분석
		String no = req.getParameter("bp_no");
		String me_no = req.getParameter("me_no");
		BuyproductDAO dao = new BuyproductDAO(req.getServletContext());

		BuyproductDTO dto = dao.selectOne(no);

		//줄바꿈 처리
		dao.close();
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/payment/PaymentList.jsp").forward(req, resp);
		
	}/////////////////////service()
	
	
}
