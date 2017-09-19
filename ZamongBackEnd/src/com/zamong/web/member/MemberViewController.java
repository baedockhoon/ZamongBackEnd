package com.zamong.web.member;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zamong.me.service.MemberDAO;
import com.zamong.me.service.MemberDTO;
import com.zamong.nt.service.NotiDataDTO;
import com.zamong.nt.service.impl.NotiDataDAO;

public class MemberViewController extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String no = req.getParameter("me_no");
		String nowPage = req.getParameter("nowPage");
		//4]모델 호출 및 결과값 받기
		MemberDAO dao = new MemberDAO(req.getServletContext());
		
		MemberDTO dto = dao.selectOne(no);
		
		dao.close();
		
		
		
		//5]필요한 값 리퀘스트 영역에 저장
		req.setAttribute("dto", dto);
		req.setAttribute("nowPage", nowPage);
		//6]뷰 선택후 포워딩                
		req.getRequestDispatcher("/bbs/membermgmt/MemberView.jsp").forward(req, resp);
		
		
		
		
	}
	
}
